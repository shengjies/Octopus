package com.ruoyi.project.erp.productCustomer.domain;

import com.ruoyi.project.erp.customer.domain.Customer;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.product.list.domain.DevProductList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品客户关联表 tab_product_customer
 *
 * @author zqm
 * @date 2019-04-30
 */
public class ProductCustomer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增长
     */
    private Integer id;
    /**
     * 产品主键ID
     */
    private Integer productId;
    /**
     * 产品对象
     */
    private DevProductList productList;
    /**
     * 客户主键id
     */
    private Integer customerId;
    /**
     * 客户对象
     */
    private Customer customer;
    /**
     * 客户产品编码
     */
    private String customerCode;
    /**
     * 创建者
     */
    private Integer createId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 客户关联价格
     */
    private BigDecimal customerPrice;
    /**
     * 关联订单明细
     */
    private List<OrderDetails> orderDetails;

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(BigDecimal customerPrice) {
        this.customerPrice = customerPrice;
    }

    public DevProductList getProductList() {
        return productList;
    }

    public void setProductList(DevProductList productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("productId", getProductId())
                .append("customerCode", getCustomerCode())
                .append("customerId", getCustomerId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
