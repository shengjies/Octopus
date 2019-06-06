package com.ruoyi.project.erp.materiel.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料表 tab_materiel
 *
 * @author zqm
 * @date 2019-04-30
 */
public class Materiel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物料主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 物料编码
     */
    @Excel(name = "物料编码",type = Excel.Type.ALL)
    private String materielCode;
    /**
     * 物料名称
     */
    @Excel(name = "物料名称",type = Excel.Type.ALL)
    private String materielName;
    /**
     * 对应供应商对应物料的供应商编码
     */
    private String supplierCode;
    /**
     * 物料型号
     */
    @Excel(name = "物料型号",type = Excel.Type.ALL)
    private String materielModel;
    /**
     * 单价(含税)
     */
    @Excel(name = "单价(含税)",type = Excel.Type.EXPORT)
    private BigDecimal price;
    /**
     * 导入价格
     */
    @Excel(name = "单价(含税)",type = Excel.Type.IMPORT)
    private Float priceImport;
    /**
     * 物料图片(最多五张)
     */
    private String materielImg;
    /**
     * 图片张数
     */
    private Integer imgSize;
    /**
     * 备注信息
     */
    @Excel(name = "备注信息",type = Excel.Type.ALL)
    private String remark;
    /**
     * 创建者
     */
    private Integer createId;
    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 封装物料关联供应商
     */
    private MaterielSupplier materielSupplier;
    /**
     * 封装物料文件信息
     */
    private FileSourceInfo fileSourceInfo;
    /**
     * 不良品数量
     */
    private Integer badNumber;

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public FileSourceInfo getFileSourceInfo() {
        return fileSourceInfo;
    }

    public void setFileSourceInfo(FileSourceInfo fileSourceInfo) {
        this.fileSourceInfo = fileSourceInfo;
    }

    public MaterielSupplier getMaterielSupplier() {
        return materielSupplier;
    }

    public void setMaterielSupplier(MaterielSupplier materielSupplier) {
        this.materielSupplier = materielSupplier;
    }

    public Float getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(Float priceImport) {
        this.priceImport = priceImport;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielModel(String materielModel) {
        this.materielModel = materielModel;
    }

    public String getMaterielModel() {
        return materielModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setMaterielImg(String materielImg) {
        this.materielImg = materielImg;
    }

    public String getMaterielImg() {
        return materielImg;
    }

    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }

    public Integer getImgSize() {
        return imgSize;
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
                .append("materielCode", getMaterielCode())
                .append("materielName", getMaterielName())
                .append("materielModel", getMaterielModel())
                .append("price", getPrice())
                .append("materielImg", getMaterielImg())
                .append("imgSize", getImgSize())
                .append("remark", getRemark())
                .append("createId", getCreateId())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
