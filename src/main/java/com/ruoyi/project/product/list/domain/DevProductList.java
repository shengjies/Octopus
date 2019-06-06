package com.ruoyi.project.product.list.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品管理表 dev_product_list
 * 
 * @author ruoyi
 * @date 2019-04-10
 */

public class DevProductList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /**
     * 产品所属公司
     */
    private Integer companyId;
    /**
     * 公司名称
     */
    @Excel(name = "所属公司",type = Excel.Type.EXPORT)
    private String comName;
    /**
     * 产品编码
     */
    @Excel(name = "产品编码")
    private String productCode;
    /**
     * 产品型号
     */
    @Excel(name = "产品型号")
    private String productModel;
    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;
    /**
     * 导入价格
     */
    @Excel(name = "产品价格", type = Excel.Type.IMPORT)
    private float priceImport;
    /**
     * 产品价格
     */
    @Excel(name = "产品价格", type = Excel.Type.EXPORT)
    private BigDecimal price;
    /**
     * 标准工时
     */
    @Excel(name = "标准工时[pcs/h]")
    private Integer standardHourYield;
    /**
     * 备注信息
     */
    @Excel(name = "备注信息")
    private String remark;
    /**  */
    private Date createTime;
    private int def_id;
    /**
     * 对应客户的产品编码信息
     */
    private String customerCode;
    /**
     * 创建者
     */
    @Excel(name = "创建者",type = Excel.Type.EXPORT)
    private String create_by;
    /**
     * 产品图片
     */
    private String productImg;
    /**
     * 图片数量
     */
    private Integer imgSize;

    /** ECN状态 0、默认为开启 1、已开启 */
    private Integer ecnStatus;
    /** ecn修改信息 */
    private String ecnText;
    /**
     * 封装产品关联客户信息
     */
    private ProductCustomer productCustomer;
    /**
     * 封装产品文件信息
     */
    private FileSourceInfo fileSourceInfo;
    /**
     * 库存良品数量
     */
    private Integer goodNumber;

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public FileSourceInfo getFileSourceInfo() {
        return fileSourceInfo;
    }

    public void setFileSourceInfo(FileSourceInfo fileSourceInfo) {
        this.fileSourceInfo = fileSourceInfo;
    }

    public ProductCustomer getProductCustomer() {
        return productCustomer;
    }

    public void setProductCustomer(ProductCustomer productCustomer) {
        this.productCustomer = productCustomer;
    }

    public float getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(float priceImport) {
        this.priceImport = priceImport;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getImgSize() {
        return imgSize;
    }

    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setStandardHourYield(Integer standardHourYield) {
        this.standardHourYield = standardHourYield;
    }

    public Integer getStandardHourYield() {
        return standardHourYield;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public int getDef_id() {
        return def_id;
    }

    public void setDef_id(int def_id) {
        this.def_id = def_id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getEcnStatus() {
        return ecnStatus;
    }

    public void setEcnStatus(Integer ecnStatus) {
        this.ecnStatus = ecnStatus;
    }

    public String getEcnText() {
        return ecnText;
    }

    public void setEcnText(String ecnText) {
        this.ecnText = ecnText;
    }

    @Override
    public String toString() {
        return "DevProductList{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", comName='" + comName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productModel='" + productModel + '\'' +
                ", price=" + price +
                ", priceImport=" + priceImport +
                ", productName='" + productName + '\'' +
                ", standardHourYield=" + standardHourYield +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", def_id=" + def_id +
                ", customerCode='" + customerCode + '\'' +
                ", create_by='" + create_by + '\'' +
                ", productImg='" + productImg + '\'' +
                ", imgSize=" + imgSize +
                ", ecnStatus=" + ecnStatus +
                ", ecnText='" + ecnText + '\'' +
                '}';
    }
}
