package com.ruoyi.project.erp.orderInfo.service;

import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import java.util.List;

/**
 * 订单数据 服务层
 * 
 * @author zqm
 * @date 2019-05-08
 */
public interface IOrderInfoService 
{
	/**
     * 查询订单数据信息
     * 
     * @param id 订单数据ID
     * @return 订单数据信息
     */
	public OrderInfo selectOrderInfoById(Integer id);
	
	/**
     * 查询订单数据列表
     * 
     * @param orderInfo 订单数据信息
     * @return 订单数据集合
     */
	public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);
	
	/**
     * 新增订单数据
     * 
     * @param orderInfo 订单数据信息
     * @return 结果
     */
	public int insertOrderInfo(OrderInfo orderInfo);
	
	/**
     * 修改订单数据
     * 
     * @param orderInfo 订单数据信息
     * @return 结果
     */
	public int updateOrderInfo(OrderInfo orderInfo);

	/**
	 * 取消订单
	 * @param orderInfo
	 * @return
	 */
	int cancelOrder(OrderInfo orderInfo);

		
	/**
     * 删除订单数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderInfoByIds(String ids);

	/**
	 * 查询对应的公司所以未交付的订单信息
	 * @return
	 */
	List<OrderInfo> selectAllOrder();

	/**
	 * 关闭订单
	 * @param orderInfo 订单
	 * @return 结果
	 */
    int closeOrderInfo(OrderInfo orderInfo);
}
