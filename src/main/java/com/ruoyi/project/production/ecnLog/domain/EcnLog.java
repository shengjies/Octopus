package com.ruoyi.project.production.ecnLog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * ECN 变更记录表 dev_ecn_log
 * 
 * @author zqm
 * @date 2019-05-16
 */
public class EcnLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 保存ecn信息类别  1、产品ecn 2、工单ecn  */
	private Integer ecnType;
	/** 保存对应类型的id */
	private Integer saveId;
	/** 对应编码 */
	private String saveCode;
	/** ecn修改信息 */
	private String ecnText;
	/** 创建者id */
	private Integer createId;
	/** 创建者 */
	private String createPeople;
	/** 创建时间 */
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setEcnType(Integer ecnType) 
	{
		this.ecnType = ecnType;
	}

	public Integer getEcnType() 
	{
		return ecnType;
	}
	public void setSaveId(Integer saveId) 
	{
		this.saveId = saveId;
	}

	public Integer getSaveId() 
	{
		return saveId;
	}
	public void setSaveCode(String saveCode) 
	{
		this.saveCode = saveCode;
	}

	public String getSaveCode() 
	{
		return saveCode;
	}
	public void setEcnText(String ecnText) 
	{
		this.ecnText = ecnText;
	}

	public String getEcnText() 
	{
		return ecnText;
	}
	public void setCreateId(Integer createId) 
	{
		this.createId = createId;
	}

	public Integer getCreateId() 
	{
		return createId;
	}
	public void setCreatePeople(String createPeople) 
	{
		this.createPeople = createPeople;
	}

	public String getCreatePeople() 
	{
		return createPeople;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("ecnType", getEcnType())
            .append("saveId", getSaveId())
            .append("saveCode", getSaveCode())
            .append("ecnText", getEcnText())
            .append("createId", getCreateId())
            .append("createPeople", getCreatePeople())
            .append("createTime", getCreateTime())
            .toString();
    }
}
