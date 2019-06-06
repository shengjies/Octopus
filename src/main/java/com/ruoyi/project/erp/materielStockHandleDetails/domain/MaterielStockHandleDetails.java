package com.ruoyi.project.erp.materielStockHandleDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 物料库存内部调整清单表 tab_materiel_stock_handle_details
 * 
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielStockHandleDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 物料库存内部调整清单主键ID */
	private Integer id;
	/** 公司主键ID */
	private Integer companyId;
	/** 物料主键ID */
	private Integer materielId;
	/** 物料编码 */
	private String materielCode;
	/** 物料型号 */
	private String materielModel;
	/** 物料名称 */
	private String materielName;
	/** 操作类型 */
	private Integer handleType;
	/** 操作状态 */
	private Integer handleStatus;
	/** 临时仓库操作状态 */
	private Integer temporaryStatus;
	/**  */
	private String purchaseCode;
	/** 操作数量 */
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
	public void setMaterielId(Integer materielId) 
	{
		this.materielId = materielId;
	}

	public Integer getMaterielId() 
	{
		return materielId;
	}
	public void setMaterielCode(String materielCode) 
	{
		this.materielCode = materielCode;
	}

	public String getMaterielCode() 
	{
		return materielCode;
	}
	public void setMaterielModel(String materielModel) 
	{
		this.materielModel = materielModel;
	}

	public String getMaterielModel() 
	{
		return materielModel;
	}
	public void setMaterielName(String materielName) 
	{
		this.materielName = materielName;
	}

	public String getMaterielName() 
	{
		return materielName;
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
	public void setTemporaryStatus(Integer temporaryStatus) 
	{
		this.temporaryStatus = temporaryStatus;
	}

	public Integer getTemporaryStatus() 
	{
		return temporaryStatus;
	}
	public void setPurchaseCode(String purchaseCode) 
	{
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseCode() 
	{
		return purchaseCode;
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
            .append("materielId", getMaterielId())
            .append("materielCode", getMaterielCode())
            .append("materielModel", getMaterielModel())
            .append("materielName", getMaterielName())
            .append("handleType", getHandleType())
            .append("handleStatus", getHandleStatus())
            .append("temporaryStatus", getTemporaryStatus())
            .append("purchaseCode", getPurchaseCode())
            .append("handleNumber", getHandleNumber())
            .append("handleBy", getHandleBy())
            .append("handleName", getHandleName())
            .append("actualTime", getActualTime())
            .append("handleTime", getHandleTime())
            .toString();
    }
}
