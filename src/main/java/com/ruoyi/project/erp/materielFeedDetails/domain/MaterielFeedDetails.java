package com.ruoyi.project.erp.materielFeedDetails.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料发料清单表 tab_materiel_feed_details
 * 
 * @author zqm
 * @date 2019-05-13
 */
public class MaterielFeedDetails extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 物料发料清单主键ID */
	private Integer id;
	/** 物料发料主键ID */
	private Integer feedId;
	/** 类型标记(0、默认值物料，1、半成品) */
	private Integer feedType;
	/** 物料/半成品id */
	private Integer feedDetailId;
	/** 物料/半成品编码 */
	private String feedDetailCode;
	/** 物料/半成品型号 */
	private String feedDetailModel;
	/** 物料/半成品名称 */
	private String feedDetailName;
	/** 单次数量 */
	private Integer oneNum;
	/** 发货数量*/
	private Integer outNumber;
	/** 单价(含税) */
	private BigDecimal price;
	/** 总价 */
	private BigDecimal totalPrice;
	/** 创建者 */
	private Integer createId;
	/** 创建时间 */
	private Date createTime;
	/**
	 * 封装库存总数
	 */
	private Integer totalNumber;
	/**
	 * 封装库存良品数量
	 */
	private Integer goodNubmer;
	/**
	 * 封装发料人名称
	 */
	private String outName;
	/**
	 * 封装发料单号
	 */
	private String feedCode;
	/** 发料产线 */
	private String feedLine;
	/** 收料人姓名 */
	private String lineReceive;
	/**
	 * 作废状态(0、默认值未作废，1、作废)
	 */
	private String delFlag;

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getFeedLine() {
		return feedLine;
	}

	public void setFeedLine(String feedLine) {
		this.feedLine = feedLine;
	}

	public String getLineReceive() {
		return lineReceive;
	}

	public void setLineReceive(String lineReceive) {
		this.lineReceive = lineReceive;
	}

	public String getFeedCode() {
		return feedCode;
	}

	public void setFeedCode(String feedCode) {
		this.feedCode = feedCode;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getGoodNubmer() {
		return goodNubmer;
	}

	public void setGoodNubmer(Integer goodNubmer) {
		this.goodNubmer = goodNubmer;
	}

	public Integer getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFeedId(Integer feedId) 
	{
		this.feedId = feedId;
	}

	public Integer getFeedId() 
	{
		return feedId;
	}
	public void setFeedType(Integer feedType) 
	{
		this.feedType = feedType;
	}

	public Integer getFeedType() 
	{
		return feedType;
	}
	public void setFeedDetailId(Integer feedDetailId) 
	{
		this.feedDetailId = feedDetailId;
	}

	public Integer getFeedDetailId() 
	{
		return feedDetailId;
	}
	public void setFeedDetailCode(String feedDetailCode) 
	{
		this.feedDetailCode = feedDetailCode;
	}

	public String getFeedDetailCode() 
	{
		return feedDetailCode;
	}
	public void setFeedDetailModel(String feedDetailModel) 
	{
		this.feedDetailModel = feedDetailModel;
	}

	public String getFeedDetailModel() 
	{
		return feedDetailModel;
	}
	public void setFeedDetailName(String feedDetailName) 
	{
		this.feedDetailName = feedDetailName;
	}

	public String getFeedDetailName() 
	{
		return feedDetailName;
	}
	public void setOneNum(Integer oneNum) 
	{
		this.oneNum = oneNum;
	}

	public Integer getOneNum() 
	{
		return oneNum;
	}
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}
	public void setTotalPrice(BigDecimal totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalPrice() 
	{
		return totalPrice;
	}
	public void setCreateId(Integer createId) 
	{
		this.createId = createId;
	}

	public Integer getCreateId() 
	{
		return createId;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feedId", getFeedId())
            .append("feedType", getFeedType())
            .append("feedDetailId", getFeedDetailId())
            .append("feedDetailCode", getFeedDetailCode())
            .append("feedDetailModel", getFeedDetailModel())
            .append("feedDetailName", getFeedDetailName())
            .append("oneNum", getOneNum())
            .append("price", getPrice())
            .append("totalPrice", getTotalPrice())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
