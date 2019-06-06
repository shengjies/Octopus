package com.ruoyi.project.erp.partsStock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 半成品库存表 tab_parts_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class PartsStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 半成品库存主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 半成品主键ID
     */
    private Integer partId;
    /**
     * 半成品编码
     */
    private String partCode;
    /**
     * 半成品名称
     */
    private String partName;
    /**
     * 库存总数
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
     * 最后一次修改时间
     */
    private Date lastUpdate;

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

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartName() {
        return partName;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("partId", getPartId())
                .append("partCode", getPartCode())
                .append("partName", getPartName())
                .append("totalNumber", getTotalNumber())
                .append("goodNumber", getGoodNumber())
                .append("badNumber", getBadNumber())
                .append("scrapNumber", getScrapNumber())
                .append("createTime", getCreateTime())
                .append("lastUpdate", getLastUpdate())
                .toString();
    }
}
