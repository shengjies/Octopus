package com.ruoyi.project.erp.materielOutStockDetails.domain;

import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料出库清单表 tab_materiel_out_stock_details
 *
 * @author zqm
 * @date 2019-05-07
 */
public class MaterielOutStockDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 材料出库清单主键ID
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
     * 出库数量
     */
    private Integer outNumber;
    /**
     * 物料出库主键ID
     */
    private Integer outId;
    /**
     * 出库单号
     */
    private String outCode;
    /**
     * 出库时间
     */
    private Date createTime;
    /**
     * 采购单号
     */
    private String purchaseCode;
    /**
     * 结款状态(0、未结款，1、已结款)
     */
    private String paymentStatus;
    /**
     * 封装采购单明细信息
     */
    private PurchaseDetails purchaseDetails;
    /**
     * 物料退货明细总价格
     */
    private BigDecimal totalPrice;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 封装供应商名称
     */
    private String supplierName;
    /**
     * 出库姓名
     */
    private String outName;
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

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    @Override
    public String toString() {
        return "MaterielOutStockDetails{" +
                "id=" + id +
                ", materielId=" + materielId +
                ", materielCode='" + materielCode + '\'' +
                ", materielModel='" + materielModel + '\'' +
                ", materielName='" + materielName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", outNumber=" + outNumber +
                ", outId=" + outId +
                ", outCode='" + outCode + '\'' +
                ", createTime=" + createTime +
                ", purchaseCode='" + purchaseCode + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", purchaseDetails=" + purchaseDetails +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
