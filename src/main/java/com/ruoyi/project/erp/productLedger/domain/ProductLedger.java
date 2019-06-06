package com.ruoyi.project.erp.productLedger.domain;

import com.ruoyi.project.erp.productLedgerDetail.domain.ProductLedgerDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 产品对账表 tab_product_ledger
 * 
 * @author zqm
 * @date 2019-05-13
 */
public class ProductLedger extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 公司编号 */
	private Integer companyId;
	/** 客户编号 */
	private Integer customerId;
	/** 客户公司名称 */
	private String customerCompanyName;
	/** 客户名称(联系人) */
	private String customerName;
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

	private String remark;//备注信息

	private Date beginTime;//对账开始时间

	private Date endTime;//对账结束时间

	private List<ProductLedgerDetail> details;

	private Integer totalNum;//总数量

	private Float totalMoney;//总金额

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
	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId() 
	{
		return customerId;
	}
	public void setCustomerCompanyName(String customerCompanyName) 
	{
		this.customerCompanyName = customerCompanyName;
	}

	public String getCustomerCompanyName() 
	{
		return customerCompanyName;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName() 
	{
		return customerName;
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

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<ProductLedgerDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ProductLedgerDetail> details) {
		this.details = details;
	}

	public Integer getTotalNum() {
		this.totalNum = 0;
		if(this.getDetails() != null && this.getDetails().size() >0){
			for (ProductLedgerDetail detail : details) {
				this.totalNum += detail.getLedgerNumber() ==null?0:detail.getLedgerNumber();
			}
		}
		return totalNum;
	}

	public Float getTotalMoney() {
		this.totalMoney = 0F;
		if(this.getDetails() != null && this.getDetails().size() >0){
			for (ProductLedgerDetail detail : details) {
				this.totalMoney += detail.getLedgerMoney() ==null?0F:detail.getLedgerMoney();
			}
		}
		return totalMoney;
	}

	@Override
	public String toString() {
		return "ProductLedger{" +
				"id=" + id +
				", companyId=" + companyId +
				", customerId=" + customerId +
				", customerCompanyName='" + customerCompanyName + '\'' +
				", customerName='" + customerName + '\'' +
				", phone='" + phone + '\'' +
				", manEmail='" + manEmail + '\'' +
				", paymentMethod='" + paymentMethod + '\'' +
				", ledgerStatus=" + ledgerStatus +
				", ledgerTime=" + ledgerTime +
				", ledgerPeople='" + ledgerPeople + '\'' +
				", voidTime=" + voidTime +
				", voidPeople='" + voidPeople + '\'' +
				", createTime=" + createTime +
				", remark='" + remark + '\'' +
				", beginTime=" + beginTime +
				", endTime=" + endTime +
				'}';
	}
}
