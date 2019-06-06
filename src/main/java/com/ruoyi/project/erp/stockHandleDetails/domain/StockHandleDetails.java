package com.ruoyi.project.erp.stockHandleDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 内部调整明细表 tab_stock_handle_details
 * 
 * @author zqm
 * @date 2019-05-27
 */
public class StockHandleDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 公司主键id */
	private Integer companyId;
	/** 内部调整主键id */
	private Integer handleId;
	/** 处理单号 */
	private String handleCode;
	/** 处理类型(0、成品，1、物料，2、半成品) */
	private String handleType;
	/** 创建者 */
	private Integer createId;
	/** 创建者姓名 */
	private String createName;
	/** 创建时间 */
	private Date createTime;
	/** 实际处理时间 */
	private Date actualTime;
	/** 产品/物料/半成品id */
	private Integer attrId;
	/** 产品/物料/半成品编码 */
	private String attrCode;
	/** 产品/物料/半成品型号*/
	private String attrModel;
	/** 产品/物料/半成品名称 */
	private String attrName;
	/** 处理状态(0、不良=>良品 1、不良=>报废 2、良品=>不良 3、良品=>报废 4、报废品清库) */
	private String handleStatus;
	/** 处理数量 */
	private Integer handleNumber;
	/** 作废状态(0、默认值未作废，1、已作废) */
	private String delFlag;

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
	public void setHandleId(Integer handleId) 
	{
		this.handleId = handleId;
	}

	public Integer getHandleId() 
	{
		return handleId;
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
	public void setAttrId(Integer attrId) 
	{
		this.attrId = attrId;
	}

	public Integer getAttrId() 
	{
		return attrId;
	}
	public void setAttrCode(String attrCode) 
	{
		this.attrCode = attrCode;
	}

	public String getAttrCode() 
	{
		return attrCode;
	}
	public void setAttrModel(String attrModel) 
	{
		this.attrModel = attrModel;
	}

	public String getAttrModel() 
	{
		return attrModel;
	}
	public void setAttrName(String attrName) 
	{
		this.attrName = attrName;
	}

	public String getAttrName() 
	{
		return attrName;
	}
	public void setHandleStatus(String handleStatus) 
	{
		this.handleStatus = handleStatus;
	}

	public String getHandleStatus() 
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
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("handleId", getHandleId())
            .append("handleCode", getHandleCode())
            .append("handleType", getHandleType())
            .append("createId", getCreateId())
            .append("createName", getCreateName())
            .append("createTime", getCreateTime())
            .append("actualTime", getActualTime())
            .append("attrId", getAttrId())
            .append("attrCode", getAttrCode())
            .append("attrModel", getAttrModel())
            .append("attrName", getAttrName())
            .append("handleStatus", getHandleStatus())
            .append("handleNumber", getHandleNumber())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
