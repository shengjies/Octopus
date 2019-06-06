package com.ruoyi.project.erp.materielFeed.domain;

import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 物料发料表 tab_materiel_feed
 * 
 * @author zqm
 * @date 2019-05-13
 */
public class MaterielFeed extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 物料发料主键ID */
	private Integer id;
	/** 公司主键ID */
	private Integer companyId;
	/** 发料单号 */
	private String feedCode;
	/** 发料产线 */
	private String feedLine;
	/** 发料bom单编号 */
	private Integer feedBomId;
	/** 发料bom单编码 */
	private String feedBomCode;
	/** 发料bom单版本号 */
	private String feedBomVersion;
	/** 发料类型(0、默认值物料发料，1、半成品发料) */
	private Integer feedType;
	/**  发料数量*/
	private Integer feedNumber;
	/** 实际时间 */
	private Date actualTime;
	/** 备注信息 */
	private String remark;
	/** 创建者 */
	private Integer createId;
	/** 创建名称 */
	private String createName;
	/** 创建时间 */
	private Date createTime;
	/** 产线id */
	private Integer lineId;
	/** 收料人姓名 */
	private String lineReceive;
	/**
	 * 作废状态(0、默认值未作废，1、作废)
	 */
	private String delFlag;
	/**
	 * 封装物料发料详情
	 */
	private List<MaterielFeedDetails> materielFeedDetailsList;

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<MaterielFeedDetails> getMaterielFeedDetailsList() {
		return materielFeedDetailsList;
	}

	public void setMaterielFeedDetailsList(List<MaterielFeedDetails> materielFeedDetailsList) {
		this.materielFeedDetailsList = materielFeedDetailsList;
	}

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
	public void setFeedCode(String feedCode) 
	{
		this.feedCode = feedCode;
	}

	public String getFeedCode() 
	{
		return feedCode;
	}
	public void setFeedLine(String feedLine) 
	{
		this.feedLine = feedLine;
	}

	public String getFeedLine() 
	{
		return feedLine;
	}
	public void setFeedBomId(Integer feedBomId) 
	{
		this.feedBomId = feedBomId;
	}

	public Integer getFeedBomId() 
	{
		return feedBomId;
	}
	public void setFeedBomCode(String feedBomCode) 
	{
		this.feedBomCode = feedBomCode;
	}

	public String getFeedBomCode() 
	{
		return feedBomCode;
	}
	public void setFeedBomVersion(String feedBomVersion) 
	{
		this.feedBomVersion = feedBomVersion;
	}

	public String getFeedBomVersion() 
	{
		return feedBomVersion;
	}
	public void setFeedType(Integer feedType) 
	{
		this.feedType = feedType;
	}

	public Integer getFeedType() 
	{
		return feedType;
	}
	public void setFeedNumber(Integer feedNumber) 
	{
		this.feedNumber = feedNumber;
	}

	public Integer getFeedNumber() 
	{
		return feedNumber;
	}
	public void setActualTime(Date actualTime) 
	{
		this.actualTime = actualTime;
	}

	public Date getActualTime() 
	{
		return actualTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setCreateId(Integer createId) 
	{
		this.createId = createId;
	}

	public Integer getCreateId() 
	{
		return createId;
	}
	public void setCreateName(String createName) 
	{
		this.createName = createName;
	}

	public String getCreateName() 
	{
		return createName;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setLineId(Integer lineId) 
	{
		this.lineId = lineId;
	}

	public Integer getLineId() 
	{
		return lineId;
	}
	public void setLineReceive(String lineReceive) 
	{
		this.lineReceive = lineReceive;
	}

	public String getLineReceive() 
	{
		return lineReceive;
	}

	@Override
	public String toString() {
		return "MaterielFeed{" +
				"id=" + id +
				", companyId=" + companyId +
				", feedCode='" + feedCode + '\'' +
				", feedLine='" + feedLine + '\'' +
				", feedBomId=" + feedBomId +
				", feedBomCode='" + feedBomCode + '\'' +
				", feedBomVersion='" + feedBomVersion + '\'' +
				", feedType=" + feedType +
				", feedNumber=" + feedNumber +
				", actualTime=" + actualTime +
				", remark='" + remark + '\'' +
				", createId=" + createId +
				", createName='" + createName + '\'' +
				", createTime=" + createTime +
				", lineId=" + lineId +
				", lineReceive='" + lineReceive + '\'' +
				", materielFeedDetailsList=" + materielFeedDetailsList +
				'}';
	}
}
