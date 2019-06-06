package com.ruoyi.project.erp.materielLedger.domain;

import com.ruoyi.project.erp.materielLedgerDetail.domain.MaterielLedgerDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 物料对账表 tab_materiel_ledger
 * 
 * @author zqm
 * @date 2019-05-15
 */
public class MaterielLedger extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 公司编号 */
	private Integer companyId;
	/** 供应商编号 */
	private Integer supplierId;
	/** 供应商公司名称 */
	private String supplierCompanyName;
	/** 供应商名称(联系人) */
	private String supplierName;
	/** 联系电话 */
	private String phone;
	/** 电子邮箱 */
	private String manEmail;
	/** 付款方式 */
	private String paymentMethod;
	/** 对账状态 */
	private Integer ledgerStatus;
	/** 对账时间 */
	private Date ledgerTime;
	/** 对账人 */
	private String ledgerPeople;
	/** 作废时间 */
	private Date voidTime;
	/** 作废人 */
	private String voidPeople;
	/** 创建时间 */
	private Date createTime;
	/** 开始时间 */
	private Date bTime;
	/** 结束时间 */
	private Date eTime;

	private String remark;

	private List<MaterielLedgerDetail> details;

	private Integer totalNum;

	private Float totalMoney;

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
	public void setSupplierId(Integer supplierId) 
	{
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() 
	{
		return supplierId;
	}
	public void setSupplierCompanyName(String supplierCompanyName) 
	{
		this.supplierCompanyName = supplierCompanyName;
	}

	public String getSupplierCompanyName() 
	{
		return supplierCompanyName;
	}
	public void setSupplierName(String supplierName) 
	{
		this.supplierName = supplierName;
	}

	public String getSupplierName() 
	{
		return supplierName;
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
	public void setPaymentMethod(String paymentMethod) 
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() 
	{
		return paymentMethod;
	}
	public void setLedgerStatus(Integer ledgerStatus) 
	{
		this.ledgerStatus = ledgerStatus;
	}

	public Integer getLedgerStatus() 
	{
		return ledgerStatus;
	}
	public void setLedgerTime(Date ledgerTime) 
	{
		this.ledgerTime = ledgerTime;
	}

	public Date getLedgerTime() 
	{
		return ledgerTime;
	}
	public void setLedgerPeople(String ledgerPeople) 
	{
		this.ledgerPeople = ledgerPeople;
	}

	public String getLedgerPeople() 
	{
		return ledgerPeople;
	}
	public void setVoidTime(Date voidTime) 
	{
		this.voidTime = voidTime;
	}

	public Date getVoidTime() 
	{
		return voidTime;
	}
	public void setVoidPeople(String voidPeople) 
	{
		this.voidPeople = voidPeople;
	}

	public String getVoidPeople() 
	{
		return voidPeople;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<MaterielLedgerDetail> getDetails() {
		return details;
	}

	public void setDetails(List<MaterielLedgerDetail> details) {
		this.details = details;
	}

	public Integer getTotalNum() {
		this.totalNum =0;
		if(this.getDetails() != null && this.getDetails().size() >0){
			for (MaterielLedgerDetail detail : details) {
				this.totalNum += detail.getLedgerNumber() == null?0:detail.getLedgerNumber();
			}
		}
		return totalNum;
	}

	public Float getTotalMoney() {
		this.totalMoney = 0.00F;
		if(this.getDetails() != null && this.getDetails().size() >0){
			for (MaterielLedgerDetail detail : details) {
				this.totalMoney += detail.getLedgerMoney() == null?0:detail.getLedgerMoney();
			}
		}
		return totalMoney;
	}

	@Override
	public String toString() {
		return "MaterielLedger{" +
				"id=" + id +
				", companyId=" + companyId +
				", supplierId=" + supplierId +
				", supplierCompanyName='" + supplierCompanyName + '\'' +
				", supplierName='" + supplierName + '\'' +
				", phone='" + phone + '\'' +
				", manEmail='" + manEmail + '\'' +
				", paymentMethod='" + paymentMethod + '\'' +
				", ledgerStatus=" + ledgerStatus +
				", ledgerTime=" + ledgerTime +
				", ledgerPeople='" + ledgerPeople + '\'' +
				", voidTime=" + voidTime +
				", voidPeople='" + voidPeople + '\'' +
				", createTime=" + createTime +
				", bTime=" + bTime +
				", eTime=" + eTime +
				", remark='" + remark + '\'' +
				'}';
	}
}
