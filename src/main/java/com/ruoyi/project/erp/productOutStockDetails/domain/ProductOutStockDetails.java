package com.ruoyi.project.erp.productOutStockDetails.domain;

import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品出库清单表 tab_product_out_stock_details
 *
 * @author zqm
 * @date 2019-04-30
 */
public class ProductOutStockDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品出库清单主键ID
     */
    private Integer id;
    /**
     * 产品主键ID
     */
    private Integer productId;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 产品型号
     */
    private String productModel;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 客户编码(自填)
     */
    private String customerCode;
    /**
     * 出库数量
     */
    private Integer outNumber;
    /**
     * 产品出库主键ID
     */
    private Integer outId;
    /**
     * 发货单号
     */
    private String outCode;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 订单总数量
     */
    private Integer number;
    /**
     * 订单已经交付数量
     */
    private Integer deliverNum;
    /**
     * 出库时间
     */
    private Date createTime;
    /**
     * 结款状态(0、未结款，1、已结款)
     */
    private String paymentStatus;
    /**
     * 订单明细
     */
    private OrderDetails orderDetails;
    /**
     * 产品出库明细总价格
     */
    private BigDecimal totalPrice;
    /**
     * 封装操作人姓名
     */
    private String outName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 作废状态(0、默认值未作废，1、作废)
     */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(Integer deliverNum) {
        this.deliverNum = deliverNum;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "ProductOutStockDetails{" +
                "id=" + id +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productName='" + productName + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", outNumber=" + outNumber +
                ", outId=" + outId +
                ", outCode='" + outCode + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", number=" + number +
                ", deliverNum=" + deliverNum +
                ", createTime=" + createTime +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orderDetails=" + orderDetails +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
