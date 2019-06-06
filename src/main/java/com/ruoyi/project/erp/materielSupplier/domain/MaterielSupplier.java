package com.ruoyi.project.erp.materielSupplier.domain;

import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.supplier.domain.Supplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 物料供应商关联表 tab_materiel_supplier
 *
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielSupplier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 自增长主键
     */
    private Integer id;
    /**
     * 物料主键ID
     */
    private Integer materielId;
    /**
     * 物料对象
     */
    private Materiel materiel;
    /**
     * 供应商主键ID
     */
    private Integer supplierId;
    /**
     * 供应商对象
     */
    private Supplier supplier;
    /**
     * 供应商物料编码(自填)
     */
    private String supplierCode;
    /**
     * 创建者
     */
    private Integer createId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 供应商关联价格
     */
    private BigDecimal supplierPrice;
    /**
     * 封装采购单明细信息
     */
    private List<PurchaseDetails> purchaseDetailsList;

    public List<PurchaseDetails> getPurchaseDetailsList() {
        return purchaseDetailsList;
    }

    public void setPurchaseDetailsList(List<PurchaseDetails> purchaseDetailsList) {
        this.purchaseDetailsList = purchaseDetailsList;
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getCreateId() {
        return createId;
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
                .append("materielId", getMaterielId())
                .append("supplierId", getSupplierId())
                .append("supplierCode", getSupplierCode())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
