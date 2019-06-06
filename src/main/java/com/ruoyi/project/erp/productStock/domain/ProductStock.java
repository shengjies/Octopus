package com.ruoyi.project.erp.productStock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 产品库存表 tab_product_stock
 *
 * @author zqm
 * @date 2019-04-30
 */
public class ProductStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品库存主键ID
     */
    private Integer id;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 产品主键ID
     */
    private Integer productId;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 产品型号
     */
    private String productModel;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 总库存
     */
    private Integer totalNumber;
    /**
     * 良品库存
     */
    private Integer goodNumber;
    /**
     * 不良品库存
     */
    private Integer badNumber;
    /**
     * 报废品库存
     */
    private Integer scrapNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次修改时间
     */
    private Date lastUpdate;

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

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setScrapNumber(Integer scrapNumber) {
        this.scrapNumber = scrapNumber;
    }

    public Integer getScrapNumber() {
        return scrapNumber;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("companyId", getCompanyId())
                .append("productId", getProductId())
                .append("productCode", getProductCode())
                .append("productModel", getProductModel())
                .append("productName", getProductName())
                .append("totalNumber", getTotalNumber())
                .append("goodNumber", getGoodNumber())
                .append("badNumber", getBadNumber())
                .append("scrapNumber", getScrapNumber())
                .append("createTime", getCreateTime())
                .append("lastUpdate", getLastUpdate())
                .toString();
    }
}
