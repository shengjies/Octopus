package com.ruoyi.project.production.workExceptionType.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 工单工单异常类型表 dev_work_exception_type
 *
 * @author zqm
 * @date 2019-04-16
 */
public class WorkExceptionType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 工单异常类型主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 异常类型名称
     */
    private String typeName;
    /**
     * 是否删除
     */
    private Integer defId;
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setDefId(Integer defId) {
        this.defId = defId;
    }

    public Integer getDefId() {
        return defId;
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
                .append("typeName", getTypeName())
                .append("defId", getDefId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
