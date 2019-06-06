package com.ruoyi.project.production.productionLine.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 生产线表 production_line
 * 
 * @author ruoyi
 * @date 2019-04-11
 */
public class ProductionLine extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 生产线名称 */
	@Excel(name = "生产线名称")
	private String lineName;
	/**  */
	private Integer deviceLiable;
	/** 责任人 */
	@Excel(name = "责任人1")
	private String deviceLiableName;
	/** 第二责任人 */
	private Integer deviceLiableTow;
	@Excel(name = "责任人2")
	private String deviceLiableTowName;
	@Excel(name = "手动")
	private Integer manual;//是否是手动 1、是 0、不是
	/** 备注信息 */
	@Excel(name = "备注信息")
	private String remark;
	/**  */
	private Integer defId;
	/**  */
	private Integer companyId;
	/** 所属公司 */
	@Excel(name = "所属公司")
	private String comName;
	/** 创建时间 */
	@Excel(name = "创建时间",width = 30,dateFormat = "yyyy-MM-dd HH:ss:mm")
	private Date createTime;
	/**  */
	private Integer create_by;
	/** 创建者 */
	@Excel(name = "创建者")
	private String createName;
	/** 产线IO口配置 */
	private Integer[] devIo;
	/** 自定义数据 */
	private String paramConfig;

	private List<String> paramArray;//自定义数据数组

	public Integer getManual() {
		return manual;
	}

	public void setManual(Integer manual) {
		this.manual = manual;
	}

	private int isSign;//是否标记警戒线 0 没有 大于0则是相关id编号

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setLineName(String lineName) 
	{
		this.lineName = lineName;
	}

	public String getLineName() 
	{
		return lineName;
	}
	public void setDeviceLiable(Integer deviceLiable) 
	{
		this.deviceLiable = deviceLiable;
	}

	public Integer getDeviceLiable() 
	{
		return deviceLiable;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setDefId(Integer defId) 
	{
		this.defId = defId;
	}

	public Integer getDefId() 
	{
		return defId;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
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

	public String getDeviceLiableName() {
		return deviceLiableName;
	}

	public void setDeviceLiableName(String deviceLiableName) {
		this.deviceLiableName = deviceLiableName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer[] getDevIo() {
		return devIo;
	}

	public void setDevIo(Integer[] devIo) {
		this.devIo = devIo;
	}

	public int getIsSign() {
		return isSign;
	}

	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}


	public Integer getDeviceLiableTow() {
		return deviceLiableTow;
	}

	public void setDeviceLiableTow(Integer deviceLiableTow) {
		this.deviceLiableTow = deviceLiableTow;
	}

	public String getDeviceLiableTowName() {
		return deviceLiableTowName;
	}

	public void setDeviceLiableTowName(String deviceLiableTowName) {
		this.deviceLiableTowName = deviceLiableTowName;
	}

	public String getParamConfig() {
		return paramConfig;
	}

	public void setParamConfig(String paramConfig) {
		this.paramConfig = paramConfig;
	}

	public List<String> getParamArray() {
		return paramArray;
	}

	public void setParamArray(List<String> paramArray) {
		this.paramArray = paramArray;
	}

	@Override
	public String toString() {
		return "ProductionLine{" +
				"id=" + id +
				", lineName='" + lineName + '\'' +
				", deviceLiable=" + deviceLiable +
				", deviceLiableName='" + deviceLiableName + '\'' +
				", deviceLiableTow=" + deviceLiableTow +
				", deviceLiableTowName='" + deviceLiableTowName + '\'' +
				", manual=" + manual +
				", remark='" + remark + '\'' +
				", defId=" + defId +
				", companyId=" + companyId +
				", comName='" + comName + '\'' +
				", createTime=" + createTime +
				", create_by=" + create_by +
				", createName='" + createName + '\'' +
				", devIo=" + Arrays.toString(devIo) +
				", paramConfig='" + paramConfig + '\'' +
				", paramArray=" + paramArray +
				", isSign=" + isSign +
				'}';
	}
}
