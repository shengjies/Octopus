package com.ruoyi.project.erp.purchase.domain;

import java.util.Date;

/**
 * 采购单封装实体
 *
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.erp.purchase.domain
 * @Author: Administrator
 * @Date: 2019/5/11 18:31
 * @Description //TODO
 * @Version: 1.0
 **/
public class PurchaseResult {
    /**
     * 采购单id
     */
    private Integer purchaseId;
    /**
     * 采购单号
     */
    private String purchaseCode;
    /**
     * 供应商id
     */
    private Integer supplierId;
    /**
     * 采购单总数
     */
    private Integer deliverTotalNum;
    /**
     * 采购单状态
     */
    private Integer purchaseStatut;
    /**
     * 采购单创建时间
     */
    private Date createTime;
    /**
     * 供应商物料编码
     */
    private String supplierCode;
    /**
     * 物料编码
     */
    private String materielCode;
    /**
     * 采购单明细已交付数量
     */
    private Integer deliverNum;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getDeliverTotalNum() {
        return deliverTotalNum;
    }

    public void setDeliverTotalNum(Integer deliverTotalNum) {
        this.deliverTotalNum = deliverTotalNum;
    }

    public Integer getPurchaseStatut() {
        return purchaseStatut;
    }

    public void setPurchaseStatut(Integer purchaseStatut) {
        this.purchaseStatut = purchaseStatut;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public Integer getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(Integer deliverNum) {
        this.deliverNum = deliverNum;
    }

    @Override
    public String toString() {
        return "PurchaseResult{" +
                "purchaseId=" + purchaseId +
                ", purchaseCode='" + purchaseCode + '\'' +
                ", supplierId=" + supplierId +
                ", deliverTotalNum=" + deliverTotalNum +
                ", purchaseStatut=" + purchaseStatut +
                ", createTime=" + createTime +
                ", supplierCode='" + supplierCode + '\'' +
                ", materielCode='" + materielCode + '\'' +
                ", deliverNum=" + deliverNum +
                '}';
    }
}
