package com.ruoyi.project.erp.productStockHandleDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 产品库存内部调整清单表 tab_product_stock_handle_details
 * 
 * @author zqm
 * @date 2019-04-30
 */
public class ProductStockHandleDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 产品库存内部调整清单主键ID */
	private Integer id;
	/** 公司主键ID */
	private Integer companyId;
	/** 产品编号 */
	private Integer productId;
	/** 产品编码 */
	private String productCode;
	/** 产品型号 */
	private String productModel;
	/** 产品名称 */
	private String productName;
	/** 操作类型 */
	private Integer handleType;
	/** 操作状态 */
	private Integer handleStatus;
	/** 操作数量 */
	private Integer handleNumber;
	/** 操作人 */
	private Integer handleBy;
	/** 操作者名称 */
	private String handleName;
	/** 实际时间 */
	private Date actionTime;
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
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setProductCode(String productCode) 
	{
		this.productCode = productCode;
	}

	public String getProductCode() 
	{
		return productCode;
	}
	public void setProductModel(String productModel) 
	{
		this.productModel = productModel;
	}

	public String getProductModel() 
	{
		return productModel;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public String getProductName() 
	{
		return productName;
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
	public void setActionTime(Date actionTime) 
	{
		this.actionTime = actionTime;
	}

	public Date getActionTime() 
	{
		return actionTime;
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
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productModel", getProductModel())
            .append("productName", getProductName())
            .append("handleType", getHandleType())
            .append("handleStatus", getHandleStatus())
            .append("handleNumber", getHandleNumber())
            .append("handleBy", getHandleBy())
            .append("handleName", getHandleName())
            .append("actionTime", getActionTime())
            .append("handleTime", getHandleTime())
            .toString();
    }
}
