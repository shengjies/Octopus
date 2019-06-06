package com.ruoyi.project.page.pageInfo.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.page.layout.domain.Layout;
import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 页面管理表 page_info
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
public class PageInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 页面编号 */
	@Excel(name = "页面编号")
	private String pageId;
	/**  */
	private Integer companyId;
	/** 页面名称 */
	@Excel(name = "页面名称")
	private String pageName;
	/** 页面URL */
	@Excel(name = "页面URL")
	private String pageUrl;
	/** 布局类型 */
	private Integer pageType;  //布局类型  1、宫格布局 2、轮播布局 3、产线平衡 4、产品文件
	/**  */
	private Integer pageLayoutType;//
	/** 布局类型名称 */
	@Excel(name = "布局类型名称")
	private String layoutTypeName;//布局类型名称
	/** 背景图片 */
	@Excel(name = "背景图片")
	private String pageBackImg;
	/** 创建者 */
	@Excel(name = "创建者")
	private String createBy;
	/** 创建时间 */
	@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
	private Date createTime;

	private String pagePwd;//页面密码 默认1234356
	/** 布局类型*/
	private Layout layout;
	/** **********          相关页面操作对应的配置信息       */
	/** 页面配置*/
	private List<PageInfoConfig> configs;
	/** 所属公司*/
	private DevCompany devCompany;
	/** 专用于判断对应轮播页面是否配置其他页面 */
	private boolean exist = false;

	/** 对应产线所以未完成和正在进行的工单 */
	private List<DevWorkOrder> workOrder;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPageId(String pageId) 
	{
		this.pageId = pageId;
	}

	public String getPageId() 
	{
		return pageId;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setPageName(String pageName) 
	{
		this.pageName = pageName;
	}

	public String getPageName() 
	{
		return pageName;
	}
	public void setPageUrl(String pageUrl) 
	{
		this.pageUrl = pageUrl;
	}

	public String getPageUrl() 
	{
		return pageUrl;
	}
	public void setPageType(Integer pageType) 
	{
		this.pageType = pageType;
	}

	public Integer getPageType() 
	{
		return pageType;
	}
	public void setPageLayoutType(Integer pageLayoutType) 
	{
		this.pageLayoutType = pageLayoutType;
	}

	public Integer getPageLayoutType() 
	{
		return pageLayoutType;
	}
	public void setPageBackImg(String pageBackImg) 
	{
		this.pageBackImg = pageBackImg;
	}

	public String getPageBackImg() 
	{
		return pageBackImg;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public List<PageInfoConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<PageInfoConfig> configs) {
		this.configs = configs;
	}

	public String getLayoutTypeName() {
		return this.layoutTypeName;
	}

	public void setLayoutTypeName(String layoutTypeName) {
		this.layoutTypeName = layoutTypeName;
	}

	public DevCompany getDevCompany() {
		return devCompany;
	}

	public void setDevCompany(DevCompany devCompany) {
		this.devCompany = devCompany;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public String getPagePwd() {
		return pagePwd;
	}

	public void setPagePwd(String pagePwd) {
		this.pagePwd = pagePwd;
	}

	public List<DevWorkOrder> getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(List<DevWorkOrder> workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	public String toString() {
		return "PageInfo{" +
				"id=" + id +
				", pageId='" + pageId + '\'' +
				", companyId=" + companyId +
				", pageName='" + pageName + '\'' +
				", pageUrl='" + pageUrl + '\'' +
				", pageType=" + pageType +
				", pageLayoutType=" + pageLayoutType +
				", layoutTypeName='" + layoutTypeName + '\'' +
				", pageBackImg='" + pageBackImg + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				", pagePwd='" + pagePwd + '\'' +
				", layout=" + layout +
				", configs=" + configs +
				", devCompany=" + devCompany +
				", exist=" + exist +
				'}';
	}
}
