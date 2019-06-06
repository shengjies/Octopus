package com.ruoyi.project.erp.contract.domain;

import com.ruoyi.project.erp.contractContent.domain.ContractContent;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 合同表 tab_contract
 * 
 * @author zqm
 * @date 2019-05-10
 */
public class Contract extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 所属公司 */
	private Integer companyId;
	/** 联系人 */
	private String liaisonMan;
	/** 联系电话 */
	private String phone;
	/** 电子邮箱 */
	private String manEmail;
	/** 授权人 */
	private String authorizedPerson;
	/** 创建时间 */
	private Date createTime;
	/** 备注信息 */
	private String remark;
	/** 合同内容 */
	private List<ContractContent> contractContents;

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
	public void setAuthorizedPerson(String authorizedPerson) 
	{
		this.authorizedPerson = authorizedPerson;
	}

	public String getAuthorizedPerson() 
	{
		return authorizedPerson;
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

	public List<ContractContent> getContractContents() {
		return contractContents;
	}

	public void setContractContents(List<ContractContent> contractContents) {
		this.contractContents = contractContents;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("liaisonMan", getLiaisonMan())
            .append("phone", getPhone())
            .append("manEmail", getManEmail())
            .append("authorizedPerson", getAuthorizedPerson())
            .append("createTime", getCreateTime())
            .toString();
    }
}
