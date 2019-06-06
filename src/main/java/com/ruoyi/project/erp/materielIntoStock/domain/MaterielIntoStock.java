package com.ruoyi.project.erp.materielIntoStock.domain;

import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 物料入库表 tab_materiel_into_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielIntoStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物料入库主键ID
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
     * 采购单号
     */
    private String purchaseCode;
    /**
     * 供应商主键ID
     */
    private Integer supplierId;
    /**
     * 供应商名称
     */
    private String supplierName;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    private List<MaterielIntoStockDetails> materielIntoStockDetails;

    public List<MaterielIntoStockDetails> getMaterielIntoStockDetails() {
        return materielIntoStockDetails;
    }

    public void setMaterielIntoStockDetails(List<MaterielIntoStockDetails> materielIntoStockDetails) {
        this.materielIntoStockDetails = materielIntoStockDetails;
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

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
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
        return "MaterielIntoStock{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", intoCode='" + intoCode + '\'' +
                ", purchaseCode='" + purchaseCode + '\'' +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", actualTime=" + actualTime +
                ", remark='" + remark + '\'' +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", materielIntoStockDetails=" + materielIntoStockDetails +
                '}';
    }
}
