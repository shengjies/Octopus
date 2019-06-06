package com.ruoyi.project.production.workExceptionList.domain;

import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.workExceptionType.domain.WorkExceptionType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 各个工单异常情况记录表 dev_work_exception_list
 *
 * @author zqm
 * @date 2019-04-16
 */
public class WorkExceptionList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 工单异常主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 工单主键ID
     */
    private Integer workId;
    /**
     * 工单对象
     */
    private DevWorkOrder devWorkOrder;
    /**
     * 异常类型ID
     */
    private Integer exceType;
    /**
     * 异常类型对象
     */
    private WorkExceptionType workExceptionType;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 异常状态
     */
    private Integer exceStatut;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 处理者
     */
    private String handleUser;
    /**
     * 处理时间
     */
    private Date handleTime;
    /**
     * 处理的备注信息
     */
    private String handleContent;

    /**报表所需字段*/
    private String exce;//异常处理
    private String typeName;//异常类型
    private String workorderNumber;//工单好

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public DevWorkOrder getDevWorkOrder() {
        return devWorkOrder;
    }

    public void setDevWorkOrder(DevWorkOrder devWorkOrder) {
        this.devWorkOrder = devWorkOrder;
    }

    public Integer getExceType() {
        return exceType;
    }

    public void setExceType(Integer exceType) {
        this.exceType = exceType;
    }

    public WorkExceptionType getWorkExceptionType() {
        return workExceptionType;
    }

    public void setWorkExceptionType(WorkExceptionType workExceptionType) {
        this.workExceptionType = workExceptionType;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getExceStatut() {
        return exceStatut;
    }

    public void setExceStatut(Integer exceStatut) {
        this.exceStatut = exceStatut;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }


    public String getExce() {
        return exce;
    }

    public void setExce(String exce) {
        this.exce = exce;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getWorkorderNumber() {
        return workorderNumber;
    }

    public void setWorkorderNumber(String workorderNumber) {
        this.workorderNumber = workorderNumber;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("workId", getWorkId())
                .append("exceType", getExceType())
                .append("remark", getRemark())
                .append("exceStatut", getExceStatut())
                .append("createTime", getCreateTime())
                .append("handleUser", getHandleUser())
                .append("handleTime", getHandleTime())
                .toString();
    }

}
