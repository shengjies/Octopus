package com.ruoyi.project.erp.productIntoStockDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品入库清单表 tab_product_into_stock_details
 *
 * @author zqm
 * @date 2019-05-08
 */
public class ProductIntoStockDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品入库清单主键ID
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
     * 入库数量
     */
    private Integer intoNumber;
    /**
     * 产品入库主键ID
     */
    private Integer intoId;
    /**
     * 入库单号
     */
    private String intoCode;
    /**
     * 退货单号
     */
    private String backCode;
    /**
     * 入库时间
     */
    private Date createTime;
    /**
     * 结款状态(0、未结款，1、已结款)
     */
    private String paymentStatus;
    /**
     * 客户退货明细总价格
     */
    private BigDecimal totalPrice;
    /**
     * 封装入库人姓名
     */
    private String intoName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 退货客户名称
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

    public String getIntoName() {
        return intoName;
    }

    public void setIntoName(String intoName) {
        this.intoName = intoName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public void setIntoNumber(Integer intoNumber) {
        this.intoNumber = intoNumber;
    }

    public Integer getIntoNumber() {
        return intoNumber;
    }

    public void setIntoId(Integer intoId) {
        this.intoId = intoId;
    }

    public Integer getIntoId() {
        return intoId;
    }

    public void setIntoCode(String intoCode) {
        this.intoCode = intoCode;
    }

    public String getIntoCode() {
        return intoCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "ProductIntoStockDetails{" +
                "id=" + id +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productName='" + productName + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", intoNumber=" + intoNumber +
                ", intoId=" + intoId +
                ", intoCode='" + intoCode + '\'' +
                ", backCode='" + backCode + '\'' +
                ", createTime=" + createTime +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
