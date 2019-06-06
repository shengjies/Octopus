package com.ruoyi.project.erp.productOutStock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 产品出库表 tab_product_out_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class ProductOutStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品出库主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 发货单号
     */
    private String outCode;
    /**
     * 订单号
     */
    private String orderCode;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
     * 产品出库单作废状态 0 未作废，1、作废
     */
    private String delFlag;

    /**
     * 产品出库清单
     */
    private String details;
    /**
     * 产品出库清单
     */
    private List<ProductOutStockDetails> productOutStockDetailsList;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<ProductOutStockDetails> getProductOutStockDetailsList() {
        return productOutStockDetailsList;
    }

    public void setProductOutStockDetailsList(List<ProductOutStockDetails> productOutStockDetailsList) {
        this.productOutStockDetailsList = productOutStockDetailsList;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("outCode", getOutCode())
                .append("orderCode", getOrderCode())
                .append("customerId", getCustomerId())
                .append("customerName", getCustomerName())
                .append("actualTime", getActualTime())
                .append("remark", getRemark())
                .append("createId", getCreateId())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
