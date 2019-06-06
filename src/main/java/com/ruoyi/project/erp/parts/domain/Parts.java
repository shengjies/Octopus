package com.ruoyi.project.erp.parts.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 半成品表 tab_parts
 *
 * @author zqm
 * @date 2019-04-30
 */
public class Parts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 半成品主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 半成品编码
     */
    private String partsCode;
    /**
     * 半成品名称
     */
    private String partsName;
    /**
     * 半成品单价
     */
    private BigDecimal price;
    /**
     * 单位
     */
    private String unit;
    /**
     * 备注信息
     */
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

    public void setPartsCode(String partsCode) {
        this.partsCode = partsCode;
    }

    public String getPartsCode() {
        return partsCode;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
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
                .append("partsCode", getPartsCode())
                .append("partsName", getPartsName())
                .append("price", getPrice())
                .append("unit", getUnit())
                .append("remark", getRemark())
                .append("createId", getCreateId())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
