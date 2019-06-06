package com.ruoyi.project.erp.orderInfo.domain;

import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 订单数据表 tab_order_info
 * 
 * @author zqm
 * @date 2019-05-08
 */
public class OrderInfo extends BaseEntity
{
	/**  */
	private Integer id;
	/**  */
	private Integer companyId;
	/**  */
	private String orderCode;
	/**  */
	private Integer orderNumber;
	/**  */
	private Integer orderDeliverNum;

	private Float totalPrice;//订单总金额
	/**  */
	private Integer customerId;
	/**  */
	private String customerConsignee;
	/**  */
	private String customerName;
	/**  */
	private String receivingAddress;
	/**  */
	private String contactInformation;
	/**  */
	private Date deliverTime;
	/**  */
	private String examineContent;
	/**  */
	private Integer orderStatus;
	/**  */
	private String remark;
	/**  */
	private Date createTime;
	/**  */
	private Integer create_by;
	/** 订单详情 */
	private String details;//订单详情
	/**
	 * 封装客户产品编码
	 */
	private String customerCode;
	/**
	 * 封装产品编码
	 */
	private String productCode;
	/**
	 * 订单详情
	 */
	private List<OrderDetails> orderDetails;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

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
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setOrderNumber(Integer orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	public Integer getOrderNumber() 
	{
		return orderNumber;
	}
	public void setOrderDeliverNum(Integer orderDeliverNum) 
	{
		this.orderDeliverNum = orderDeliverNum;
	}

	public Integer getOrderDeliverNum() 
	{
		return orderDeliverNum;
	}
	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId() 
	{
		return customerId;
	}
	public void setCustomerConsignee(String customerConsignee) 
	{
		this.customerConsignee = customerConsignee;
	}

	public String getCustomerConsignee() 
	{
		return customerConsignee;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName() 
	{
		return customerName;
	}
	public void setReceivingAddress(String receivingAddress) 
	{
		this.receivingAddress = receivingAddress;
	}

	public String getReceivingAddress() 
	{
		return receivingAddress;
	}
	public void setContactInformation(String contactInformation) 
	{
		this.contactInformation = contactInformation;
	}

	public String getContactInformation() 
	{
		return contactInformation;
	}
	public void setDeliverTime(Date deliverTime) 
	{
		this.deliverTime = deliverTime;
	}

	public Date getDeliverTime() 
	{
		return deliverTime;
	}
	public void setExamineContent(String examineContent) 
	{
		this.examineContent = examineContent;
	}

	public String getExamineContent() 
	{
		return examineContent;
	}
	public void setOrderStatus(Integer orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus() 
	{
		return orderStatus;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "OrderInfo{" +
				"id=" + id +
				", companyId=" + companyId +
				", orderCode='" + orderCode + '\'' +
				", orderNumber=" + orderNumber +
				", orderDeliverNum=" + orderDeliverNum +
				", totalPrice=" + totalPrice +
				", customerId=" + customerId +
				", customerConsignee='" + customerConsignee + '\'' +
				", customerName='" + customerName + '\'' +
				", receivingAddress='" + receivingAddress + '\'' +
				", contactInformation='" + contactInformation + '\'' +
				", deliverTime=" + deliverTime +
				", examineContent='" + examineContent + '\'' +
				", orderStatus=" + orderStatus +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", create_by=" + create_by +
				", details='" + details + '\'' +
				", customerCode='" + customerCode + '\'' +
				", productCode='" + productCode + '\'' +
				", orderDetails=" + orderDetails +
				'}';
	}
}
