package com.ruoyi.project.erp.productLedgerDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 产品对账明细表 tab_product_ledger_detail
 * 
 * @author zqm
 * @date 2019-05-13
 */
public class ProductLedgerDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 主表id */
	private Integer ledgerId;
	/** 公司编号 */
	private Integer companyId;
	/** 客户编号 */
	private Integer customerId;
	/** 对账类型 */
	private Integer ledgerType;
	/** 送货日期 */
	private Date deliveryTime;
	/** 订单号 */
	private String orderCode;
	/** 客户编码 */
	private String customerCode;
	/** 产品编码 */
	private String productCode;
	/** 产品型号 */
	private String productModel;
	/** 产品名称 */
	private String productName;
	/** 对账数量 */
	private Integer ledgerNumber;
	/** 对账单价 */
	private Float ledgerPrice;
	/** 对账金额 */
	private Float ledgerMoney;
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
	public void setLedgerId(Integer ledgerId) 
	{
		this.ledgerId = ledgerId;
	}

	public Integer getLedgerId() 
	{
		return ledgerId;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId() 
	{
		return customerId;
	}
	public void setLedgerType(Integer ledgerType) 
	{
		this.ledgerType = ledgerType;
	}

	public Integer getLedgerType() 
	{
		return ledgerType;
	}
	public void setDeliveryTime(Date deliveryTime) 
	{
		this.deliveryTime = deliveryTime;
	}

	public Date getDeliveryTime() 
	{
		return deliveryTime;
	}
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setCustomerCode(String customerCode) 
	{
		this.customerCode = customerCode;
	}

	public String getCustomerCode() 
	{
		return customerCode;
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
	public void setLedgerNumber(Integer ledgerNumber) 
	{
		this.ledgerNumber = ledgerNumber;
	}

	public Integer getLedgerNumber() 
	{
		return ledgerNumber;
	}
	public void setLedgerPrice(Float ledgerPrice) 
	{
		this.ledgerPrice = ledgerPrice;
	}

	public Float getLedgerPrice() 
	{
		return ledgerPrice;
	}
	public void setLedgerMoney(Float ledgerMoney) 
	{
		this.ledgerMoney = ledgerMoney;
	}

	public Float getLedgerMoney() 
	{
		return ledgerMoney;
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
            .append("ledgerId", getLedgerId())
            .append("companyId", getCompanyId())
            .append("customerId", getCustomerId())
            .append("ledgerType", getLedgerType())
            .append("deliveryTime", getDeliveryTime())
            .append("orderCode", getOrderCode())
            .append("customerCode", getCustomerCode())
            .append("productCode", getProductCode())
            .append("productModel", getProductModel())
            .append("productName", getProductName())
            .append("ledgerNumber", getLedgerNumber())
            .append("ledgerPrice", getLedgerPrice())
            .append("ledgerMoney", getLedgerMoney())
            .append("createTime", getCreateTime())
            .toString();
    }
}
