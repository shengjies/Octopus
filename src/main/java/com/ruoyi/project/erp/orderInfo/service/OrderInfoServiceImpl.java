package com.ruoyi.project.erp.orderInfo.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.orderDetails.mapper.OrderDetailsMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.orderInfo.mapper.OrderInfoMapper;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import com.ruoyi.project.erp.orderInfo.service.IOrderInfoService;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单数据 服务层实现
 * 
 * @author zqm
 * @date 2019-05-08
 */
@Service("order")
public class OrderInfoServiceImpl implements IOrderInfoService 
{
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private OrderDetailsMapper detailsMapper;

	/**
     * 查询订单数据信息
     * 
     * @param id 订单数据ID
     * @return 订单数据信息
     */
    @Override
	public OrderInfo selectOrderInfoById(Integer id)
	{   OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(id);
		OrderDetails details = new OrderDetails();
		details.setOrderId(orderInfo.getId());
		orderInfo.setOrderDetails(detailsMapper.selectOrderDetailsList(details));
	    return orderInfo;
	}
	
	/**
     * 查询订单数据列表
     * 
     * @param orderInfo 订单数据信息
     * @return 订单数据集合
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
	{
		User user = ShiroUtils.getSysUser();
		if(user == null)return Collections.emptyList();
		orderInfo.setCompanyId(user.getCompanyId());
	    return orderInfoMapper.selectOrderInfoList(orderInfo);
	}
	
    /**
     * 新增订单数据
     * 
     * @param orderInfo 订单数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.ERP)
	@Transactional
	public int insertOrderInfo(OrderInfo orderInfo)
	{
		User user = ShiroUtils.getSysUser();
		if(user ==null)return  0;
		orderInfo.setCompanyId(user.getCompanyId());
		orderInfo.setCreate_by(user.getUserId().intValue());
		orderInfo.setCreateTime(new Date());
		//添加订单主信息
		orderInfoMapper.insertOrderInfo(orderInfo);
		int totalNum =0;//订单总数量
		float totalPrice = 0F;//订单总金额
		if(!StringUtils.isEmpty(orderInfo.getDetails())){
			List<OrderDetails> details = JSON.parseArray(orderInfo.getDetails(),OrderDetails.class);
			for (OrderDetails detail : details) {
				detail.setOrderId(orderInfo.getId());
				detail.setOrderCode(orderInfo.getOrderCode());
				detail.setCompanyId(user.getCompanyId());
				detail.setCreateTime(new Date());
				detailsMapper.insertOrderDetails(detail);
				totalNum+=detail.getNumber();
				totalPrice+=detail.getTotalPrict();
			}
		}
		orderInfo.setOrderNumber(totalNum);
		orderInfo.setTotalPrice(totalPrice);
		orderInfo.setOrderDeliverNum(0);
		return orderInfoMapper.updateOrderInfo(orderInfo);
	}
	
	/**
     * 修改订单数据
     * 
     * @param orderInfo 订单数据信息
     * @return 结果
     */
	@Override
	public int updateOrderInfo(OrderInfo orderInfo)
	{
		//将对应订单的详情进行标记 为1
		detailsMapper.editOrderDetailsSign(orderInfo.getId(),1);
		int totalNum =0;//订单总数量
		float totalPrice = 0F;//订单总金额
		if(!StringUtils.isEmpty(orderInfo.getDetails())){
			List<OrderDetails> details = JSON.parseArray(orderInfo.getDetails(),OrderDetails.class);
			for (OrderDetails detail : details) {
				//查询对应的详情是否存在
				OrderDetails de = detailsMapper.findOrderDetailsByOidAndPCode(orderInfo.getId(),detail.getProdectCode());
				if(de != null){
					de.setProductModel(detail.getProductModel());
					de.setProductName(detail.getProductName());
					de.setCustomerCode(detail.getCustomerCode());
					de.setProductPrict(detail.getProductPrict());
					de.setNumber(detail.getNumber());
					de.setTotalPrict(detail.getTotalPrict());
					de.setSign(0);
					totalNum+=detail.getNumber();
					totalPrice+=detail.getTotalPrict();
					detailsMapper.updateOrderDetails(de);
				}else{
					detail.setOrderId(orderInfo.getId());
					detail.setOrderCode(orderInfo.getOrderCode());
					detail.setCreateTime(new Date());
					detail.setSign(0);
					detailsMapper.insertOrderDetails(detail);
					totalNum+=detail.getNumber();
					totalPrice+=detail.getTotalPrict();
				}
			}
		}
		//删除对应标记为1的详情数据
		detailsMapper.deleteOrderDetailsByOrderId(orderInfo.getId());
		orderInfo.setOrderNumber(totalNum);
		orderInfo.setTotalPrice(totalPrice);
	    return orderInfoMapper.updateOrderInfo(orderInfo);
	}


	/**
	 * 取消订单
	 * @param orderInfo
	 * @return
	 */
	@Override
	public int cancelOrder(OrderInfo orderInfo) {
		return orderInfoMapper.updateOrderInfo(orderInfo);
	}

	/**
     * 删除订单数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderInfoByIds(String ids)
	{
		return orderInfoMapper.deleteOrderInfoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询对应的公司所以未交付的订单信息
	 * @return
	 */
	@Override
	public List<OrderInfo> selectAllOrder() {
		User user = ShiroUtils.getSysUser();
		if(user == null)return Collections.emptyList();
		return orderInfoMapper.selectAllOrder(user.getCompanyId());
	}

	/**
	 * 关闭订单
	 * @param orderInfo 订单
	 * @return 结果
	 */
	@Override
	public int closeOrderInfo(OrderInfo orderInfo) {
		OrderInfo order = orderInfoMapper.selectOrderInfoById(orderInfo.getId());
		if (StockConstants.ORDER_STATUS_THREE.equals(order.getOrderStatus())) { // 订单已经处于关闭状态
		    throw new BusinessException("订单已经关闭，请勿重复操作");
		}
		return orderInfoMapper.updateOrderInfo(orderInfo);
	}
}
