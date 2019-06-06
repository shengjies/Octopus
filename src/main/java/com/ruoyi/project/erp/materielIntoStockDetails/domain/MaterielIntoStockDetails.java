package com.ruoyi.project.erp.materielIntoStockDetails.domain;

import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料入库清单表 tab_materiel_into_stock_details
 *
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielIntoStockDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物料入库清单主键ID
     */
    private Integer id;
    /**
     * 物料主键ID
     */
    private Integer materielId;
    /**
     * 物料编码
     */
    private String materielCode;
    /**
     * 物料型号
     */
    private String materielModel;
    /**
     * 物料名称
     */
    private String materielName;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 入库数量
     */
    private Integer intoNumber;
    /**
     * 物料入库主键ID
     */
    private Integer intoId;
    /**
     * 入库单号
     */
    private String intoCode;
    /**
     * 采购单主键id
     */
    private Integer purchaseId;
    /**
     * 采购单号
     */
    private String purchaseCode;
    /**
     * 入库时间
     */
    private Date createTime;
    /**
     * 入库人姓名
     */
    private String inToName;
    /**
     * 结款状态
     */
    private String paymentStatus;
    /**
     * 封装采购单明细
     */
    private PurchaseDetails purchaseDetails;
    /**
     * 物料入库明细总价格
     */
    private BigDecimal totalPrice;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 供应商名称
     */
    private String supplierName;

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

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PurchaseDetails getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(PurchaseDetails purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    public String getInToName() {
        return inToName;
    }

    public void setInToName(String inToName) {
        this.inToName = inToName;
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

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielModel(String materielModel) {
        this.materielModel = materielModel;
    }

    public String getMaterielModel() {
        return materielModel;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
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

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "MaterielIntoStockDetails{" +
                "id=" + id +
                ", materielId=" + materielId +
                ", materielCode='" + materielCode + '\'' +
                ", materielModel='" + materielModel + '\'' +
                ", materielName='" + materielName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", intoNumber=" + intoNumber +
                ", intoId=" + intoId +
                ", intoCode='" + intoCode + '\'' +
                ", purchaseCode='" + purchaseCode + '\'' +
                ", createTime=" + createTime +
                ", inToName='" + inToName + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", purchaseDetails=" + purchaseDetails +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
