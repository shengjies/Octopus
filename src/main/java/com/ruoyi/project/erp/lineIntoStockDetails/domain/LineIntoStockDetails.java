package com.ruoyi.project.erp.lineIntoStockDetails.domain;

import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 产线入库明细表 tab_line_into_stock_details
 *
 * @author zqm
 * @date 2019-05-07
 */
public class LineIntoStockDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产线入库明细主键ID
     */
    private Integer id;
    /**
     * 产线入库主键ID
     */
    private Integer lineIntoId;
    /**
     * 工单主键
     */
    private Integer workOrderId;
    /**
     * 产品/半成品主键id
     */
    private Integer detIntoId;
    /**
     * 产品/半成品编码
     */
    private String detIntoCode;
    /**
     * 产品/半成品型号
     */
    private String detIntoModel;
    /**
     * 产品/半成品名称
     */
    private String detIntoName;
    /**
     * 入库数量
     */
    private Integer detIntoNum;
    /**
     * 入库编码
     */
    private String intoCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 封装工单信息
     */
    private DevWorkOrder workOrder;
    /**
     * 入库类型(0、产品入库，1、半成品入库)
     */
    private Integer intoType;
    /**
     * 封装入库人姓名
     */
    private String intoName;
    /**
     * 封装产线名称
     */
    private String lineName;
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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getIntoName() {
        return intoName;
    }

    public void setIntoName(String intoName) {
        this.intoName = intoName;
    }

    public Integer getIntoType() {
        return intoType;
    }

    public void setIntoType(Integer intoType) {
        this.intoType = intoType;
    }

    public DevWorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(DevWorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public Integer getDetIntoId() {
        return detIntoId;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public void setDetIntoId(Integer detIntoId) {
        this.detIntoId = detIntoId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setLineIntoId(Integer lineIntoId) {
        this.lineIntoId = lineIntoId;
    }

    public Integer getLineIntoId() {
        return lineIntoId;
    }

    public void setDetIntoCode(String detIntoCode) {
        this.detIntoCode = detIntoCode;
    }

    public String getDetIntoCode() {
        return detIntoCode;
    }

    public void setDetIntoModel(String detIntoModel) {
        this.detIntoModel = detIntoModel;
    }

    public String getDetIntoModel() {
        return detIntoModel;
    }

    public void setDetIntoName(String detIntoName) {
        this.detIntoName = detIntoName;
    }

    public String getDetIntoName() {
        return detIntoName;
    }

    public void setDetIntoNum(Integer detIntoNum) {
        this.detIntoNum = detIntoNum;
    }

    public Integer getDetIntoNum() {
        return detIntoNum;
    }

    public void setIntoCode(String intoCode) {
        this.intoCode = intoCode;
    }

    public String getIntoCode() {
        return intoCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "LineIntoStockDetails{" +
                "id=" + id +
                ", lineIntoId=" + lineIntoId +
                ", workOrderId=" + workOrderId +
                ", detIntoId=" + detIntoId +
                ", detIntoCode='" + detIntoCode + '\'' +
                ", detIntoModel='" + detIntoModel + '\'' +
                ", detIntoName='" + detIntoName + '\'' +
                ", detIntoNum=" + detIntoNum +
                ", intoCode='" + intoCode + '\'' +
                ", createTime=" + createTime +
                ", workOrder=" + workOrder +
                ", intoType=" + intoType +
                '}';
    }
}
