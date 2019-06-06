package com.ruoyi.project.erp.stockHandle.domain;

import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 库存内部处理主表 tab_stock_handle
 * 
 * @author zqm
 * @date 2019-05-27
 */
public class StockHandle extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 库存内部处理主键 */
	private Integer id;
	/** 公司主键id */
	private Integer companyId;
	/** 库存处理编号 */
	private String handleCode;
	/** 处理类型(0、成品，1、物料，2、半成品) */
	private String handleType;
	/** 创建者id */
	private Integer createId;
	/** 创建者姓名 */
	private String createName;
	/** 创建时间 */
	private Date createTime;
	/** 实际处理时间 */
	private Date actualTime;
	/** 作废状态(0、默认值未作废，1、已作废) */
	private String delFlag;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 库存调整明细
	 */
	private List<StockHandleDetails> stockHandleDetailsList;

	public List<StockHandleDetails> getStockHandleDetailsList() {
		return stockHandleDetailsList;
	}

	public void setStockHandleDetailsList(List<StockHandleDetails> stockHandleDetailsList) {
		this.stockHandleDetailsList = stockHandleDetailsList;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
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
	public void setHandleCode(String handleCode) 
	{
		this.handleCode = handleCode;
	}

	public String getHandleCode() 
	{
		return handleCode;
	}
	public void setHandleType(String handleType) 
	{
		this.handleType = handleType;
	}

	public String getHandleType() 
	{
		return handleType;
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
	public void setActualTime(Date actualTime) 
	{
		this.actualTime = actualTime;
	}

	public Date getActualTime() 
	{
		return actualTime;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

	@Override
	public String toString() {
		return "StockHandle{" +
				"id=" + id +
				", companyId=" + companyId +
				", handleCode='" + handleCode + '\'' +
				", handleType='" + handleType + '\'' +
				", createId=" + createId +
				", createName='" + createName + '\'' +
				", createTime=" + createTime +
				", actualTime=" + actualTime +
				", delFlag='" + delFlag + '\'' +
				", remark='" + remark + '\'' +
				", stockHandleDetailsList=" + stockHandleDetailsList +
				'}';
	}
}
