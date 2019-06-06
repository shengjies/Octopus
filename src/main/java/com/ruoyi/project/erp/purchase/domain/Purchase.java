package com.ruoyi.project.erp.purchase.domain;

import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 采购单表 tab_purchase
 * 
 * @author zqm
 * @date 2019-05-10
 */
public class Purchase extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 所属公司 */
	private Integer companyId;
	/** 采购单号 */
	private String purchaseCode;
	/** 供应商id */
	private Integer supplierId;
	/** 供应商名称 */
	private String supplierName;
	/** 供应商地址 */
	private String supplierAddress;
	/** 总数量 */
	private Integer totalNumber;
	/** 交付总数量 */
	private Integer deliverTotalNum;
	/** 联系人 */
	private String liaisonMan;
	/** 联系电话 */
	private String phone;
	/** 电子邮箱 */
	private String manEmail;
	/** 交付日期 */
	private Date deliverTime;
	/** 交付地址 */
	private String deliverAddress;
	/** 运输费用 */
	private String cost;
	/** 付款方式 */
	private String paymentMethod;
	/** 审核内容 */
	private String contractContent;
	/** 采购状态 */
	private Integer purchaseStatut;
	/** 下单时间 */
	private Date createTime;
	/** 创建者 */
	private Integer create_by;
	/** 备注信息 */
	private String remark;

	private Float totalPrice;

	private List<PurchaseDetails> details;

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
	public void setPurchaseCode(String purchaseCode) 
	{
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseCode() 
	{
		return purchaseCode;
	}
	public void setSupplierId(Integer supplierId) 
	{
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() 
	{
		return supplierId;
	}
	public void setSupplierName(String supplierName) 
	{
		this.supplierName = supplierName;
	}

	public String getSupplierName() 
	{
		return supplierName;
	}
	public void setSupplierAddress(String supplierAddress) 
	{
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierAddress() 
	{
		return supplierAddress;
	}
	public void setTotalNumber(Integer totalNumber) 
	{
		this.totalNumber = totalNumber;
	}

	public Integer getTotalNumber() 
	{
		return totalNumber;
	}
	public void setDeliverTotalNum(Integer deliverTotalNum) 
	{
		this.deliverTotalNum = deliverTotalNum;
	}

	public Integer getDeliverTotalNum() 
	{
		return deliverTotalNum;
	}
	public void setLiaisonMan(String liaisonMan) 
	{
		this.liaisonMan = liaisonMan;
	}

	public String getLiaisonMan() 
	{
		return liaisonMan;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setManEmail(String manEmail) 
	{
		this.manEmail = manEmail;
	}

	public String getManEmail() 
	{
		return manEmail;
	}
	public void setDeliverTime(Date deliverTime) 
	{
		this.deliverTime = deliverTime;
	}

	public Date getDeliverTime() 
	{
		return deliverTime;
	}
	public void setDeliverAddress(String deliverAddress) 
	{
		this.deliverAddress = deliverAddress;
	}

	public String getDeliverAddress() 
	{
		return deliverAddress;
	}
	public void setCost(String cost) 
	{
		this.cost = cost;
	}

	public String getCost() 
	{
		return cost;
	}
	public void setPaymentMethod(String paymentMethod) 
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() 
	{
		return paymentMethod;
	}
	public void setContractContent(String contractContent) 
	{
		this.contractContent = contractContent;
	}

	public String getContractContent() 
	{
		return contractContent;
	}
	public void setPurchaseStatut(Integer purchaseStatut) 
	{
		this.purchaseStatut = purchaseStatut;
	}

	public Integer getPurchaseStatut() 
	{
		return purchaseStatut;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PurchaseDetails> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseDetails> details) {
		this.details = details;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Purchase{" +
				"id=" + id +
				", companyId=" + companyId +
				", purchaseCode='" + purchaseCode + '\'' +
				", supplierId=" + supplierId +
				", supplierName='" + supplierName + '\'' +
				", supplierAddress='" + supplierAddress + '\'' +
				", totalNumber=" + totalNumber +
				", deliverTotalNum=" + deliverTotalNum +
				", liaisonMan='" + liaisonMan + '\'' +
				", phone='" + phone + '\'' +
				", manEmail='" + manEmail + '\'' +
				", deliverTime=" + deliverTime +
				", deliverAddress='" + deliverAddress + '\'' +
				", cost='" + cost + '\'' +
				", paymentMethod='" + paymentMethod + '\'' +
				", contractContent='" + contractContent + '\'' +
				", purchaseStatut=" + purchaseStatut +
				", createTime=" + createTime +
				", create_by=" + create_by +
				", remark='" + remark + '\'' +
				", details=" + details +
				'}';
	}
}
