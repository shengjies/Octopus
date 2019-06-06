package com.ruoyi.project.erp.materielStock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 物料库存表 tab_materiel_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 物料库存主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
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
     * 物料总库存
     */
    private Integer totalNumber;
    /**
     * 良品数量
     */
    private Integer goodNumber;
    /**
     * 不良品数量
     */
    private Integer badNumber;
    /**
     * 报废品数量
     */
    private Integer scrapNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次更新时间
     */
    private Date lastUpdate;
    /**
     * 临时库存数量
     */
    private Integer temporaryNumber;

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

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setScrapNumber(Integer scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public Integer getScrapNumber() {
        return scrapNumber;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setTemporaryNumber(Integer temporaryNumber) {
        this.temporaryNumber = temporaryNumber;
    }

    public Integer getTemporaryNumber() {
        return temporaryNumber;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("materielId", getMaterielId())
                .append("materielCode", getMaterielCode())
                .append("materielModel", getMaterielModel())
                .append("materielName", getMaterielName())
                .append("totalNumber", getTotalNumber())
                .append("goodNumber", getGoodNumber())
                .append("badNumber", getBadNumber())
                .append("scrapNumber", getScrapNumber())
                .append("createTime", getCreateTime())
                .append("lastUpdate", getLastUpdate())
                .append("temporaryNumber", getTemporaryNumber())
                .toString();
    }
}
