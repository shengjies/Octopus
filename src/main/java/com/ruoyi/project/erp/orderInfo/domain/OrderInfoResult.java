package com.ruoyi.project.erp.orderInfo.domain;


import java.util.Date;

/**
 * 产品出库操作订单封装类
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.erp.orderInfo.domain
 * @Author: Administrator
 * @Date: 2019/5/11 9:13
 * @Description //TODO
 * @Version: 1.0
 **/
public class OrderInfoResult {
    /**
     * 订单号
     */
    private String ordeCode;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 订单总数
     */
    private Integer orderDeliverNum;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 客户对应产品编码
     */
    private String customerCode;
    /**
     * 订单明细已交付数量
     */
    private Integer deliverNum;

    public String getOrdeCode() {
        return ordeCode;
    }

    public void setOrdeCode(String ordeCode) {
        this.ordeCode = ordeCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderDeliverNum() {
        return orderDeliverNum;
    }

    public void setOrderDeliverNum(Integer orderDeliverNum) {
        this.orderDeliverNum = orderDeliverNum;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(Integer deliverNum) {
        this.deliverNum = deliverNum;
    }

    @Override
    public String toString() {
        return "OrderInfoResult{" +
                "ordeCode='" + ordeCode + '\'' +
                ", customerId=" + customerId +
                ", orderDeliverNum=" + orderDeliverNum +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", productCode='" + productCode + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", deliverNum=" + deliverNum +
                '}';
    }
}
