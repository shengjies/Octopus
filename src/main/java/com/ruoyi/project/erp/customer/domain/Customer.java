package com.ruoyi.project.erp.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 客户数据表 tab_customer
 *
 * @author zqm
 * @date 2019-04-30
 */
public class Customer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户主键
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别1、男，0、女
     */
    private Integer sex;
    /**
     * 职位
     */
    private String position;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 纳税识别号
     */
    private String taxpayerNumber;
    /**
     * 公司注册地址
     */
    private String companyAddress;
    /**
     * 开户银行
     */
    private String bankDeposit;
    /**
     * 开户账号
     */
    private String openingAccount;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 创建者
     */
    private Integer createId;
    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 账期时间
     */
    private String paymentTime;
    /**
     * 客户收货地址
     */
    private String receiveAddress;

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setBankDeposit(String bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public String getBankDeposit() {
        return bankDeposit;
    }

    public void setOpeningAccount(String openingAccount) {
        this.openingAccount = openingAccount;
    }

    public String getOpeningAccount() {
        return openingAccount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("customerName", getCustomerName())
                .append("contactInformation", getContactInformation())
                .append("email", getEmail())
                .append("sex", getSex())
                .append("position", getPosition())
                .append("companyName", getCompanyName())
                .append("taxpayerNumber", getTaxpayerNumber())
                .append("companyAddress", getCompanyAddress())
                .append("bankDeposit", getBankDeposit())
                .append("openingAccount", getOpeningAccount())
                .append("remark", getRemark())
                .append("createId", getCreateId())
                .append("createName", getCreateName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
