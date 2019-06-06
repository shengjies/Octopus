package com.ruoyi.project.erp.partStockHandleDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 半成品库存内部调整清单表 tab_part_stock_handle_details
 * 
 * @author zqm
 * @date 2019-04-30
 */
public class PartStockHandleDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 半成品库存内部调整清单主键ID */
	private Integer id;
	/** 公司主键ID */
	private Integer companyId;
	/** 半成品主键ID */
	private Integer partId;
	/** 半成品编码 */
	private String partCode;
	/** 半成品名称 */
	private String partName;
	/** 操作类型 */
	private Integer handleType;
	/** 操作状态 */
	private Integer handleStatus;
	/** 操作数量*/
	private Integer handleNumber;
	/** 操作人 */
	private Integer handleBy;
	/** 操作者名称 */
	private String handleName;
	/** 实际时间 */
	private Date actualTime;
	/** 操作时间 */
	private Date handleTime;

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
	public void setPartId(Integer partId) 
	{
		this.partId = partId;
	}

	public Integer getPartId() 
	{
		return partId;
	}
	public void setPartCode(String partCode) 
	{
		this.partCode = partCode;
	}

	public String getPartCode() 
	{
		return partCode;
	}
	public void setPartName(String partName) 
	{
		this.partName = partName;
	}

	public String getPartName() 
	{
		return partName;
	}
	public void setHandleType(Integer handleType) 
	{
		this.handleType = handleType;
	}

	public Integer getHandleType() 
	{
		return handleType;
	}
	public void setHandleStatus(Integer handleStatus) 
	{
		this.handleStatus = handleStatus;
	}

	public Integer getHandleStatus() 
	{
		return handleStatus;
	}
	public void setHandleNumber(Integer handleNumber) 
	{
		this.handleNumber = handleNumber;
	}

	public Integer getHandleNumber() 
	{
		return handleNumber;
	}
	public void setHandleBy(Integer handleBy) 
	{
		this.handleBy = handleBy;
	}

	public Integer getHandleBy() 
	{
		return handleBy;
	}
	public void setHandleName(String handleName) 
	{
		this.handleName = handleName;
	}

	public String getHandleName() 
	{
		return handleName;
	}
	public void setActualTime(Date actualTime) 
	{
		this.actualTime = actualTime;
	}

	public Date getActualTime() 
	{
		return actualTime;
	}
	public void setHandleTime(Date handleTime) 
	{
		this.handleTime = handleTime;
	}

	public Date getHandleTime() 
	{
		return handleTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("partId", getPartId())
            .append("partCode", getPartCode())
            .append("partName", getPartName())
            .append("handleType", getHandleType())
            .append("handleStatus", getHandleStatus())
            .append("handleNumber", getHandleNumber())
            .append("handleBy", getHandleBy())
            .append("handleName", getHandleName())
            .append("actualTime", getActualTime())
            .append("handleTime", getHandleTime())
            .toString();
    }
}
