package com.ruoyi.project.system.ser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 服务器管理表 tab_ser
 * 
 * @author ruoyi
 * @date 2019-06-03
 */
public class Ser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 服务器名称 */
	private String sname;
	/** 服务器IP */
	private String sip;
	/** 服务器路径 */
	private String spath;
	/** 用户数量 */
	private Integer suserNum;
	/** 备注信息 */
	private String remark;
	/** 创建时间 */
	private Date createTime;
	/** 最大用户数量 */
	private Integer maxNum;
	/** API验证密码 */
	private String spwd;
	/** 服务器密码 */
	private String pwd;

	/** 行业id */
	private Integer inId;
	/** 行业名称 */
	private String inName;

	private Integer s_status ;//状态 0、开启 1、关闭

	private Integer s_type;//0、共用 1、专用

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setSname(String sname) 
	{
		this.sname = sname;
	}

	public String getSname() 
	{
		return sname;
	}
	public void setSip(String sip) 
	{
		this.sip = sip;
	}

	public String getSip() 
	{
		return sip;
	}
	public void setSpath(String spath) 
	{
		this.spath = spath;
	}

	public String getSpath() 
	{
		return spath;
	}
	public void setSuserNum(Integer suserNum) 
	{
		this.suserNum = suserNum;
	}

	public Integer getSuserNum() 
	{
		return suserNum;
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
	public void setMaxNum(Integer maxNum) 
	{
		this.maxNum = maxNum;
	}

	public Integer getMaxNum() 
	{
		return maxNum;
	}
	public void setSpwd(String spwd) 
	{
		this.spwd = spwd;
	}

	public String getSpwd() 
	{
		return spwd;
	}
	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}

	public String getPwd() 
	{
		return pwd;
	}

	public Integer getS_status() {
		return s_status;
	}

	public void setS_status(Integer s_status) {
		this.s_status = s_status;
	}

	public Integer getS_type() {
		return s_type;
	}

	public void setS_type(Integer s_type) {
		this.s_type = s_type;
	}

	public Integer getInId() {
		return inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	@Override
	public String toString() {
		return "Ser{" +
				"id=" + id +
				", sname='" + sname + '\'' +
				", sip='" + sip + '\'' +
				", spath='" + spath + '\'' +
				", suserNum=" + suserNum +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", maxNum=" + maxNum +
				", spwd='" + spwd + '\'' +
				", pwd='" + pwd + '\'' +
				", s_status=" + s_status +
				", s_type=" + s_type +
				'}';
	}
}
