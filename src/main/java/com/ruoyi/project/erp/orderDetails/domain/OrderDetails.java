package com.ruoyi.project.erp.orderDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 订单详情表 tab_order_details
 * 
 * @author zqm
 * @date 2019-05-08
 */
public class OrderDetails extends BaseEntity
{
	/**  */
	private Integer id;
	/**  */
	private Integer orderId;
	/**  */
	private String orderCode;
	/**  */
	private String prodectCode;
	/**  */
	private String productModel;
	/**  */
	private String customerCode;
	/**  */
	private String productName;
	/**  */
	private Float productPrict;
	/**  */
	private Integer number;
	/**  */
	private Float totalPrict;
	/**  */
	private Integer deliverNum;
	/**  */
	private Date createTime;
	/**
	 * 客户主键ID
	 */
	private Integer customerId;
	/**
	 * 公司主键ID
	 */
	private Integer companyId;

	private String remark;

	private Integer productId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	private int sign;//标记详情 默认为 0


	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setOrderCode(String orderCode) 
	{
		this.orderCode = orderCode;
	}

	public String getOrderCode() 
	{
		return orderCode;
	}
	public void setProdectCode(String prodectCode) 
	{
		this.prodectCode = prodectCode;
	}

	public String getProdectCode() 
	{
		return prodectCode;
	}
	public void setProductModel(String productModel) 
	{
		this.productModel = productModel;
	}

	public String getProductModel() 
	{
		return productModel;
	}
	public void setCustomerCode(String customerCode) 
	{
		this.customerCode = customerCode;
	}

	public String getCustomerCode() 
	{
		return customerCode;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public String getProductName() 
	{
		return productName;
	}
	public void setProductPrict(Float productPrict) 
	{
		this.productPrict = productPrict;
	}

	public Float getProductPrict() 
	{
		return productPrict;
	}
	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
	}
	public void setTotalPrict(Float totalPrict) 
	{
		this.totalPrict = totalPrict;
	}

	public Float getTotalPrict() 
	{
		return totalPrict;
	}
	public void setDeliverNum(Integer deliverNum) 
	{
		this.deliverNum = deliverNum;
	}

	public Integer getDeliverNum() 
	{
		return deliverNum;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("prodectCode", getProdectCode())
            .append("productModel", getProductModel())
            .append("customerCode", getCustomerCode())
            .append("productName", getProductName())
            .append("productPrict", getProductPrict())
            .append("number", getNumber())
            .append("totalPrict", getTotalPrict())
            .append("deliverNum", getDeliverNum())
            .append("createTime", getCreateTime())
            .toString();
    }
}
