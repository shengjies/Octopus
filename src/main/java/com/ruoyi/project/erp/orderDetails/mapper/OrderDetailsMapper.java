package com.ruoyi.project.erp.orderDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.validation.ValidatorAdapter;

import java.util.List;

/**
 * 订单详情 数据层
 *
 * @author zqm
 * @date 2019-05-08
 */
public interface OrderDetailsMapper {
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
    @DataSource(value = DataSourceType.ERP)
    public List<OrderDetails> selectOrderDetailsList(OrderDetails orderDetails);

    /**
     * 新增订单详情
     *
     * @param orderDetails 订单详情信息
     * @return 结果
     */
    @DataSource(DataSourceType.ERP)
    public int insertOrderDetails(OrderDetails orderDetails);

    /**
     * 修改订单详情
     *
     * @param orderDetails 订单详情信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateOrderDetails(OrderDetails orderDetails);

    /**
     * 删除订单详情
     *
     * @param id 订单详情ID
     * @return 结果
     */
    public int deleteOrderDetailsById(Integer id);

    /**
     * 批量删除订单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderDetailsByIds(String[] ids);

    /**
     * 根据订单编号删除对应的详情信息
     *
     * @param orderId 订单编号
     * @return
     */
    @DataSource(DataSourceType.ERP)
    int deleteOrderDetailsByOrderId(int orderId);

    /**
     * 查询订单明细
     * @param companyId 公司id
     * @param orderCode 订单编号
     * @param customerId  客户id
     * @param productCode 产品编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    OrderDetails selectOrderDetailByCodeAndCusId(@Param("companyId") Integer companyId,
                                                 @Param("orderCode") String orderCode,
                                                 @Param("customerId") Integer customerId,
                                                 @Param("productCode") String productCode);

	/**
	 * 标记对应的订单详情数据
	 * @param oid 订单id
	 * @param sign 标记 0 或 1
	 * @return
	 */
    @DataSource(DataSourceType.ERP)
	int editOrderDetailsSign(@Param("oid")int oid,@Param("sign") int sign);

	/**
	 * 根据订单id和产品编码查询对应的详情是否存在
	 * @param oid 订单id
	 * @param pcode 产品编码
	 * @return
	 */
    @DataSource(DataSourceType.ERP)
	OrderDetails findOrderDetailsByOidAndPCode(@Param("oid")int oid ,@Param("pcode")String pcode);

    /**
     * 根据订单编码和产品编码查询对应公司下的订单备注信息
     * @param orderCode
     * @param productCode
     * @return
     */
    @DataSource(DataSourceType.ERP)
	OrderDetails findByOrderCodeAndProductCode(@Param("companyId")int companyId,
                                               @Param("orderCode")String orderCode,
                                               @Param("productCode")String productCode);

    /**
     * 查询订单明细列表
     * @param companyId 公司id
     * @param customerId 客户id
     * @param productId 产品id
     * @param orderStatus 订单状态
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<OrderDetails> selectOrderDetailsListByProIdAndCusId(@Param("companyId") Integer companyId,
                                                             @Param("customerId") Integer customerId,
                                                             @Param("productId") Integer productId,
                                                             @Param("orderStatus")Integer orderStatus);

    /**
     * 查询订单明细一条记录
     * @param companyId 公司id
     * @param customerId 客户id
     * @param productId 产品id
     * @param orderStatus 订单状态
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    OrderDetails selectOrderDetailsListByProIdAndCusIdOne(@Param("companyId") Integer companyId,
                                                          @Param("customerId") Integer customerId,
                                                          @Param("productId") Integer productId,
                                                          @Param("orderStatus")Integer orderStatus);

    /**
     * 查询订单明细一条记录
     * @param companyId 公司id
     * @param customerId 客户id
     * @param productId 产品id
     * @param orderStatus 订单状态
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    OrderDetails selectOrderDetailsListByNullProInStock(@Param("companyId") Integer companyId,
                                                        @Param("customerId") Integer customerId,
                                                        @Param("productId") Integer productId,
                                                        @Param("orderStatus")Integer orderStatus);
}