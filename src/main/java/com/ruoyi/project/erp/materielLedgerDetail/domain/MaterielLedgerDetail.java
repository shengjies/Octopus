package com.ruoyi.project.erp.materielLedgerDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 物料对账明细表 tab_materiel_ledger_detail
 * 
 * @author zqm
 * @date 2019-05-15
 */
public class MaterielLedgerDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 主表id */
	private Integer ledgerId;
	/** 公司编号 */
	private Integer companyId;
	/** 供应商编号 */
	private Integer supplierId;
	/** 对账类型 */
	private Integer ledgerType;
	/** 送货日期 */
	private Date deliveryTime;
	/** 采购单号 */
	private String purchaseCode;
	/** 供应商编码 */
	private String supplierCode;
	/** 物料编码 */
	private String materielCode;
	/** 物料型号 */
	private String materielModel;
	/** 物料名称 */
	private String materielName;
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
	public void setSupplierId(Integer supplierId) 
	{
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() 
	{
		return supplierId;
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
	public void setPurchaseCode(String purchaseCode) 
	{
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseCode() 
	{
		return purchaseCode;
	}
	public void setSupplierCode(String supplierCode) 
	{
		this.supplierCode = supplierCode;
	}

	public String getSupplierCode() 
	{
		return supplierCode;
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
            .append("supplierId", getSupplierId())
            .append("ledgerType", getLedgerType())
            .append("deliveryTime", getDeliveryTime())
            .append("purchaseCode", getPurchaseCode())
            .append("supplierCode", getSupplierCode())
            .append("materielCode", getMaterielCode())
            .append("materielModel", getMaterielModel())
            .append("materielName", getMaterielName())
            .append("ledgerNumber", getLedgerNumber())
            .append("ledgerPrice", getLedgerPrice())
            .append("ledgerMoney", getLedgerMoney())
            .append("createTime", getCreateTime())
            .toString();
    }
}
