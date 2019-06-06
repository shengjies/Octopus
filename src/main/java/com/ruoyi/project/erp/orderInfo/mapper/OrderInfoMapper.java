package com.ruoyi.project.erp.orderInfo.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfoResult;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

/**
 * 订单数据 数据层
 *
 * @author zqm
 * @date 2019-05-08
 */
public interface OrderInfoMapper {
    /**
     * 查询订单数据信息
     *
     * @param id 订单数据ID
     * @return 订单数据信息
     */
    @DataSource(value = DataSourceType.ERP)
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
    @DataSource(value = DataSourceType.ERP)
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除订单数据
     *
     * @param id 订单数据ID
     * @return 结果
     */
    public int deleteOrderInfoById(Integer id);

    /**
     * 批量删除订单数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderInfoByIds(String[] ids);

    /**
     * 通过客户id查询客户订单信息
     *
     * @param companyId  公司id
     * @param customerId 客户id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<OrderInfo> selectOrderInfoByCusId(@Param("companyId") Integer companyId, @Param("customerId") Integer customerId);

    /**
     * 通过订单号查询订单信息
     *
     * @param companyId 公司id
     * @param orderCode 订单号
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    OrderInfo selectOrderInfoListByOrderCode(@Param("companyId") Integer companyId, @Param("orderCode") String orderCode);

    /**
     * 查询最新的已交付未取消的订单信息
     *
     * @param companyId    公司id
     * @param customerId   客户id
     * @param customerCode 客户产品编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    OrderInfoResult selectOrderInfoByCusIdAndCusCode(@Param("companyId") Integer companyId,
                                                     @Param("customerId") Integer customerId,
                                                     @Param("customerCode") String customerCode);

    /**
     * 退货更新订单明细信息
     *
     * @param deliverNum      订单已交付数量
     * @param companyId       公司id
     * @param customerId      客户id
     * @param orderCode       订单编号
     * @param customerCode    客户对应产品编码
     * @param productCode     产品编码
     * @param createTime      创建时间
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int updateOrderInfoByCusIdAndCusCode(
            @Param("deliverNum") int deliverNum,
            @Param("companyId") Integer companyId,
            @Param("customerId") Integer customerId,
            @Param("orderCode") String orderCode,
            @Param("customerCode") String customerCode,
            @Param("productCode") String productCode,
            @Param("createTime") Date createTime);

    /**
     *  退货更新订单信息
     * @param companyId 公司id
     * @param intoNumber 退货数量
     * @param customerId 客户id
     * @param ordeCode 订单编号
     * @param createTime 创建时间
     * @param orderStatus 订单状态
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int updateOrderInfogOrderDeliverNum(@Param("companyId") Integer companyId,
                                        @Param("intoNumber") int intoNumber,
                                        @Param("customerId") Integer customerId,
                                        @Param("ordeCode") String ordeCode,
                                        @Param("createTime") Date createTime,
                                        @Param("orderStatus") Integer orderStatus);

    /**
     * 查询对应公司所以未交付的订单信息
     * @param companyId 公司id
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<OrderInfo> selectAllOrder(@Param("companyId")int companyId);
}