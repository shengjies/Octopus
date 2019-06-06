package com.ruoyi.project.page.pageInfoConfig.domain;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.workExceptionType.domain.WorkExceptionType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 页面配置表 page_info_config
 * 
 * @author zqm
 * @date 2019-04-13
 */
public class PageInfoConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer pId;
	/**  */
	private Integer devId;
	/**  */
	private String devName;
	/**  */
	private Integer ioId;
	/**  */
	private String ioName;
	/** 累计产量 */
	private Integer idTotal;
	/**  */
	private String devIoConfig;
	/**  */
	private Date createTime;
	/**  */
	private Integer devId1;
	/**  */
	private String devName1;
	/**  */
	private Integer ioId1;
	/**  */
	private String ioName1;
	/** 累计产量 */
	private Integer idTotal1;
	/**  */
	private Integer lineId;


	/** ===========页面配置信息========== */
	private String lineName;//产线名称
	/** 相关产线正在进行的工单 **/
	private DevWorkOrder devWorkOrder;

	private JSONObject ioConfig;//对应勾选配置

	private List<String> workOrderList;//当天工单计划

	private List<WorkExceptionType> workExceptionTypes;//异常情况

	private ProductionLine line;//对应产线

	private boolean vigilance = false;//是否进行警戒显示

	private List<Map<String,Object>> ph;//平衡看板数据

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPId(Integer pId) 
	{
		this.pId = pId;
	}

	public Integer getPId() 
	{
		return pId;
	}
	public void setDevId(Integer devId) 
	{
		this.devId = devId;
	}

	public Integer getDevId() 
	{
		return devId;
	}
	public void setDevName(String devName) 
	{
		this.devName = devName;
	}

	public String getDevName() 
	{
		return devName;
	}
	public void setIoId(Integer ioId) 
	{
		this.ioId = ioId;
	}

	public Integer getIoId() 
	{
		return ioId;
	}
	public void setIoName(String ioName) 
	{
		this.ioName = ioName;
	}

	public String getIoName() 
	{
		return ioName;
	}
	public void setDevIoConfig(String devIoConfig) 
	{
		this.devIoConfig = devIoConfig;
	}

	public String getDevIoConfig() 
	{
		return devIoConfig;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setDevId1(Integer devId1) 
	{
		this.devId1 = devId1;
	}

	public Integer getDevId1() 
	{
		return devId1;
	}
	public void setDevName1(String devName1) 
	{
		this.devName1 = devName1;
	}

	public String getDevName1() 
	{
		return devName1;
	}
	public void setIoId1(Integer ioId1) 
	{
		this.ioId1 = ioId1;
	}

	public Integer getIoId1() 
	{
		return ioId1;
	}
	public void setIoName1(String ioName1) 
	{
		this.ioName1 = ioName1;
	}

	public String getIoName1() 
	{
		return ioName1;
	}
	public void setLineId(Integer lineId) 
	{
		this.lineId = lineId;
	}

	public Integer getLineId() 
	{
		return lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Integer getIdTotal() {
		return idTotal;
	}

	public void setIdTotal(Integer idTotal) {
		this.idTotal = idTotal;
	}

	public Integer getIdTotal1() {
		return idTotal1;
	}

	public void setIdTotal1(Integer idTotal1) {
		this.idTotal1 = idTotal1;
	}

	public DevWorkOrder getDevWorkOrder() {
		return devWorkOrder;
	}

	public void setDevWorkOrder(DevWorkOrder devWorkOrder) {
		this.devWorkOrder = devWorkOrder;
	}

	public JSONObject getIoConfig() {
		return ioConfig;
	}

	public void setIoConfig(JSONObject ioConfig) {
		this.ioConfig = ioConfig;
	}

	public List<String> getWorkOrderList() {
		return workOrderList;
	}

	public void setWorkOrderList(List<String> workOrderList) {
		this.workOrderList = workOrderList;
	}

	public boolean isVigilance() {
		return vigilance;
	}

	public void setVigilance(boolean vigilance) {
		this.vigilance = vigilance;
	}

	public List<Map<String, Object>> getPh() {
		return ph;
	}

	public void setPh(List<Map<String, Object>> ph) {
		this.ph = ph;
	}

	public List<WorkExceptionType> getWorkExceptionTypes() {
		return workExceptionTypes;
	}

	public void setWorkExceptionTypes(List<WorkExceptionType> workExceptionTypes) {
		this.workExceptionTypes = workExceptionTypes;
	}

	public ProductionLine getLine() {
		return line;
	}

	public void setLine(ProductionLine line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "PageInfoConfig{" +
				"id=" + id +
				", pId=" + pId +
				", devId=" + devId +
				", devName='" + devName + '\'' +
				", ioId=" + ioId +
				", ioName='" + ioName + '\'' +
				", idTotal=" + idTotal +
				", devIoConfig='" + devIoConfig + '\'' +
				", createTime=" + createTime +
				", devId1=" + devId1 +
				", devName1='" + devName1 + '\'' +
				", ioId1=" + ioId1 +
				", ioName1='" + ioName1 + '\'' +
				", idTotal1=" + idTotal1 +
				", lineId=" + lineId +
				", lineName='" + lineName + '\'' +
				", devWorkOrder=" + devWorkOrder +
				", ioConfig=" + ioConfig +
				", workOrderList=" + workOrderList +
				", workExceptionTypes=" + workExceptionTypes +
				", line=" + line +
				", vigilance=" + vigilance +
				", ph=" + ph +
				'}';
	}
}
