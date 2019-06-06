package com.ruoyi.project.erp.contractContent.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 合同内容表 tab_contract_content
 * 
 * @author zqm
 * @date 2019-05-10
 */
public class ContractContent extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 合同id */
	private Integer cId;
	/** 合同内容 */
	private String content;
	/** 显示顺序 */
	private Integer cOrder;
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
	public void setCId(Integer cId) 
	{
		this.cId = cId;
	}

	public Integer getCId() 
	{
		return cId;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setCOrder(Integer cOrder) 
	{
		this.cOrder = cOrder;
	}

	public Integer getCOrder() 
	{
		return cOrder;
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
            .append("cId", getCId())
            .append("content", getContent())
            .append("cOrder", getCOrder())
            .append("createTime", getCreateTime())
            .toString();
    }
}
