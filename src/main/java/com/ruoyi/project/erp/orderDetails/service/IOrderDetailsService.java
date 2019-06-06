package com.ruoyi.project.erp.orderDetails.service;

import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import java.util.List;

/**
 * 订单详情 服务层
 * 
 * @author zqm
 * @date 2019-05-08
 */
public interface IOrderDetailsService 
{
	/**
     * 查询订单详情信息
     * 
     * @param id 订单详情ID
     * @return 订单详情信息
     */
	public OrderDetails selectOrderDetailsById(Integer id);
	
	/**
     * 查询订单详情列表
     * 
     * @param orderDetails 订单详情信息
     * @return 订单详情集合
     */
	public List<OrderDetails> selectOrderDetailsList(OrderDetails orderDetails);
	
	/**
     * 新增订单详情
     * 
     * @param orderDetails 订单详情信息
     * @return 结果
     */
	public int insertOrderDetails(OrderDetails orderDetails);
	
	/**
     * 修改订单详情
     * 
     * @param orderDetails 订单详情信息
     * @return 结果
     */
	public int updateOrderDetails(OrderDetails orderDetails);
		
	/**
     * 删除订单详情信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderDetailsByIds(String ids);
	
}
