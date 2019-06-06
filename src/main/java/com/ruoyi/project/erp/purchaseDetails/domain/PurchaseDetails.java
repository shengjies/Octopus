package com.ruoyi.project.erp.purchaseDetails.domain;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购清单表 tab_purchase_details
 * 
 * @author zqm
 * @date 2019-05-10
 */
public class PurchaseDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 采购id */
	private Integer purchaseId;
	/** 采购单号 */
	private String purchaseCode;
	/** 物料编码 */
	private String materielCode;
	/** 物料名称 */
	private String materielName;
	/** 物料型号 */
	private String materielModel;
	/** 单价(含税) */
	private Float price;
	/** 供应商编号 */
	private String supplierCode;
	/** 数量 */
	private Integer number;
	/** 交付数量 */
	private Integer deliverNum;
	/**
	 * 仓库预收数量
	 */
	private Integer prereceiveNumber;
	/** 合计 */
	private Float totalPrict;
	/**  */
	private String remark;
	/** moq */
	private Integer moq;

	private Integer supplierId;

	private int sign;//用于标记 默认未 0

	private Integer companyId;

	public Integer getPrereceiveNumber() {
		return prereceiveNumber;
	}

	public void setPrereceiveNumber(Integer prereceiveNumber) {
		this.prereceiveNumber = prereceiveNumber;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPurchaseId(Integer purchaseId) 
	{
		this.purchaseId = purchaseId;
	}

	public Integer getPurchaseId() 
	{
		return purchaseId;
	}
	public void setMaterielCode(String materielCode) 
	{
		this.materielCode = materielCode;
	}

	public String getMaterielCode() 
	{
		return materielCode;
	}
	public void setMaterielName(String materielName) 
	{
		this.materielName = materielName;
	}

	public String getMaterielName() 
	{
		return materielName;
	}
	public void setMaterielModel(String materielModel) 
	{
		this.materielModel = materielModel;
	}

	public String getMaterielModel() 
	{
		return materielModel;
	}
	public void setPrice(Float price) 
	{
		this.price = price;
	}

	public Float getPrice() 
	{
		return price;
	}
	public void setSupplierCode(String supplierCode) 
	{
		this.supplierCode = supplierCode;
	}

	public String getSupplierCode() 
	{
		return supplierCode;
	}
	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
	}
	public void setDeliverNum(Integer deliverNum) 
	{
		this.deliverNum = deliverNum;
	}

	public Integer getDeliverNum() 
	{
		return deliverNum;
	}
	public void setTotalPrict(Float totalPrict) 
	{
		this.totalPrict = totalPrict;
	}

	public Float getTotalPrict() 
	{
		return totalPrict;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public Integer getMoq() {
		return moq;
	}

	public void setMoq(Integer moq) {
		this.moq = moq;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseId", getPurchaseId())
            .append("materielCode", getMaterielCode())
            .append("materielName", getMaterielName())
            .append("materielModel", getMaterielModel())
            .append("price", getPrice())
            .append("supplierCode", getSupplierCode())
            .append("number", getNumber())
            .append("deliverNum", getDeliverNum())
            .append("totalPrict", getTotalPrict())
            .append("remark", getRemark())
            .toString();
    }
}
