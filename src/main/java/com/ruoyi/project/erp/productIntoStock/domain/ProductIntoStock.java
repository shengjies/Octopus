package com.ruoyi.project.erp.productIntoStock.domain;

import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品入库表 tab_product_into_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class ProductIntoStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品入库主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 入库单号
     */
    private String intoCode;
    /**
     * 退货单号
     */
    private String backCode;
    /**
     * 客户主键ID
     */
    private Integer customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 实际时间
     */
    private Date actualTime;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 操作者
     */
    private Integer createId;
    /**
     * 操作者名称
     */
    private String createName;
    /**
     * 操作时间
     */
    private Date createTime;
    /**
     * 结款状态(0、未结款，1、已结款)
     */
    private String paymentStatus;
    /**
     * 作废状态(0、默认值未作废，1、作废)
     */
    private String delFlag;

    /** 客户退货详情 */
    private String details;//

    /**
     * 客户退货详情
     */
    private List<ProductIntoStockDetails> productIntoStockDetails;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<ProductIntoStockDetails> getProductIntoStockDetails() {
        return productIntoStockDetails;
    }

    public void setProductIntoStockDetails(List<ProductIntoStockDetails> productIntoStockDetails) {
        this.productIntoStockDetails = productIntoStockDetails;
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

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
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

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "ProductIntoStock{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", intoCode='" + intoCode + '\'' +
                ", backCode='" + backCode + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", actualTime=" + actualTime +
                ", remark='" + remark + '\'' +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", details='" + details + '\'' +
                ", productIntoStockDetails=" + productIntoStockDetails +
                '}';
    }
}
