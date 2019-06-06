package com.ruoyi.project.erp.lineIntoStock.domain;

import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 产线入库表 tab_line_into_stock
 *
 * @author zqm
 * @date 2019-05-07
 */
public class LineIntoStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产线入库主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 入库类型(0、产品入库，1、半成品入库)
     */
    private Integer intoType;
    /**
     * 产线名称
     */
    private String lineName;
    /**
     * 入库编码
     */
    private String intoCode;
    /**
     * 实际时间
     */
    private Date actualTime;
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

    /**
     * 作废状态(0、默认值未作废，1、作废)
     */
    private String delFlag;

    /**
     * 入库单详情
     */
    private String details;
    /**
     * 入库单详情
     */
    private List<LineIntoStockDetails> lineIntoStockDetails;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<LineIntoStockDetails> getLineIntoStockDetails() {
        return lineIntoStockDetails;
    }

    public void setLineIntoStockDetails(List<LineIntoStockDetails> lineIntoStockDetails) {
        this.lineIntoStockDetails = lineIntoStockDetails;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public void setIntoType(Integer intoType) {
        this.intoType = intoType;
    }

    public Integer getIntoType() {
        return intoType;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setIntoCode(String intoCode) {
        this.intoCode = intoCode;
    }

    public String getIntoCode() {
        return intoCode;
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
                .append("intoType", getIntoType())
                .append("lineName", getLineName())
                .append("intoCode", getIntoCode())
                .append("actualTime", getActualTime())
                .append("remark", getRemark())
                .append("createId", getCreateId())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
