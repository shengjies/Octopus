package com.ruoyi.project.production.devWorkOrder.domain;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.system.user.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 工单表 dev_work_order
 *
 * @author zqm
 * @date 2019-04-12
 */
public class DevWorkOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 工单主键ID
     */
    private Integer id;
    /**
     * 工单名称
     */
    private String workorderName;
    /**
     * 工单号(唯一)
     */
    @Excel(name = "工单号", type = Excel.Type.EXPORT)
    private String workorderNumber;
    /**
     * 公司主键ID
     */
    private Integer companyId;
    /**
     * 生产线主键ID
     */
    private Integer lineId;
    /**
     * 生产线对象
     */
    private ProductionLine productionLine;
    /**
     * 产品名称
     */
    @Excel(name = "产品名称", type = Excel.Type.EXPORT)
    private String productName;
    /**
     * 产品编码
     */
    @Excel(name = "产品编码", type = Excel.Type.EXPORT)
    private String productCode;
    /**
     * 生产数量
     */
    @Excel(name = "生产数量", type = Excel.Type.EXPORT)
    private Integer productNumber;
    /**
     * 计划生产开始时间
     */
    @Excel(name = "计划开始时间", type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd HH:ss")
    private Date productionStart;
    /**
     * 计划生产完成时间
     */
    @Excel(name = "计划完成时间", type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd HH:ss")
    private Date productionEnd;
    /**
     * 实际生产开始时间
     */
    @Excel(name = "实际开始时间", type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd HH:ss")
    private Date startTime;
    /**
     * 实际生产完成时间
     */
    @Excel(name = "实际完成时间", type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd HH:ss")
    private Date endTime;
    /**
     * 标准小时产量
     */
    private Integer productStandardHour;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 责任人id主键
     */
    private Integer deviceLiable;
    /**
     * 责任人对象
     */
    private User user;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 用工人数
     */
    private Integer peopleNumber;
    /**
     * 正常工时
     */
    private Float workingHour;
    /**
     * 加班工时
     */
    private Float overtimeHour;
    /**
     * 加班倍率
     */
    private Float overtimeRace;
    /**
     * 手动调整工时
     */
    private Float manualTime;
    /**
     * 工单生产状态
     */
    private Integer workorderStatus;
    /**
     * 工单操作状态
     */
    private Integer operationStatus;
    /**
     * 是否需要初始化数据
     */
    private Integer initDate;
    /**
     * 标记是否确认数据
     */
    private Integer workSign;
    /**
     * 报废品
     */
    @Excel(name = "报废品数量", type = Excel.Type.EXPORT)
    private Integer scrapNum;
    /**
     * 实际入库数量
     */
    @Excel(name = "实际入库数量", type = Excel.Type.EXPORT)
    private Integer actualWarehouseNum;
    /**
     * 不良品数量
     */
    @Excel(name = "不良品数量", type = Excel.Type.EXPORT)
    private Integer badNumber;
    /**
     * 产品型号
     */
    private String productModel;
    /**
     * 封装产品id
     */
    private Integer productId;

    private String remark;//备注信息

    private String orderCode;

    private Integer ecnStatus;

    private String ecnText;
    /**
     * 用于标记完成后统计第一次上报数据，默认是1，当统计完成后修改为 0
     */
    private Integer sign;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    private Integer cumulativeNumber;//累计产量

    private Float directPassRate;//直通率

    private String paramConfig;//自定义参数

    private Float reachRate;//达成率

    private JSONArray pConfig;//自定义参数

    private int productEcn =0;//产品enc状态

    private String orderRemark;//订单备注

    private EcnLog ecnLog;//产品ecn修改信息

    /** 报表导出使用*/
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;
    /**
     * 加班工时
     */
    private String work1;//1.5
    private String work2;//2.0
    private String work3;//3.0


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public ProductionLine getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(ProductionLine productionLine) {
        this.productionLine = productionLine;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setWorkorderName(String workorderName) {
        this.workorderName = workorderName;
    }

    public String getWorkorderName() {
        return workorderName;
    }

    public void setWorkorderNumber(String workorderNumber) {
        this.workorderNumber = workorderNumber;
    }

    public String getWorkorderNumber() {
        return workorderNumber;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductionStart(Date productionStart) {
        this.productionStart = productionStart;
    }

    public Date getProductionStart() {
        return productionStart;
    }

    public void setProductionEnd(Date productionEnd) {
        this.productionEnd = productionEnd;
    }

    public Date getProductionEnd() {
        return productionEnd;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setProductStandardHour(Integer productStandardHour) {
        this.productStandardHour = productStandardHour;
    }

    public Integer getProductStandardHour() {
        return productStandardHour;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setDeviceLiable(Integer deviceLiable) {
        this.deviceLiable = deviceLiable;
    }

    public Integer getDeviceLiable() {
        return deviceLiable;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setWorkingHour(Float workingHour) {
        this.workingHour = workingHour;
    }

    public Float getWorkingHour() {
        return workingHour;
    }

    public void setOvertimeHour(Float overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public Float getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeRace(Float overtimeRace) {
        this.overtimeRace = overtimeRace;
    }

    public Float getOvertimeRace() {
        return overtimeRace;
    }

    public void setManualTime(Float manualTime) {
        this.manualTime = manualTime;
    }

    public Float getManualTime() {
        return manualTime;
    }

    public void setWorkorderStatus(Integer workorderStatus) {
        this.workorderStatus = workorderStatus;
    }

    public Integer getWorkorderStatus() {
        return workorderStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setInitDate(Integer initDate) {
        this.initDate = initDate;
    }

    public Integer getInitDate() {
        return initDate;
    }

    public void setWorkSign(Integer workSign) {
        this.workSign = workSign;
    }

    public Integer getWorkSign() {
        return workSign;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setActualWarehouseNum(Integer actualWarehouseNum) {
        this.actualWarehouseNum = actualWarehouseNum;
    }

    public Integer getActualWarehouseNum() {
        return actualWarehouseNum;
    }

    public Integer getCumulativeNumber() {
        return cumulativeNumber;
    }

    public void setCumulativeNumber(Integer cumulativeNumber) {
        this.cumulativeNumber = cumulativeNumber;
    }

    public Float getDirectPassRate() {
        return directPassRate;
    }

    public void setDirectPassRate(Float directPassRate) {
        this.directPassRate = directPassRate;
    }

    public String getParamConfig() {
        return paramConfig;
    }

    public void setParamConfig(String paramConfig) {
        this.paramConfig = paramConfig;
    }

    public Float getReachRate() {
        return reachRate;
    }

    public void setReachRate(Float reachRate) {
        this.reachRate = reachRate;
    }

    public JSONArray getpConfig() {
        return pConfig;
    }

    public void setpConfig(JSONArray pConfig) {
        this.pConfig = pConfig;
    }

    public String getParam1() {
        return StringUtils.isEmpty(param1)?"":param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return StringUtils.isEmpty(param2)?"":param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return StringUtils.isEmpty(param3)?"":param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return StringUtils.isEmpty(param4)?"":param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return StringUtils.isEmpty(param5)?"":param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getWork1() {
         return StringUtils.isEmpty(work1)?"":work1;
    }

    public void setWork1(String work1) {
        this.work1 = work1;
    }

    public String getWork2() {
         return StringUtils.isEmpty(work2)?"":work2;
    }

    public void setWork2(String work2) {
        this.work2 = work2;
    }

    public String getWork3() {
        return StringUtils.isEmpty(work3)?"":work3;
    }

    public void setWork3(String work3) {
        this.work3 = work3;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public int getProductEcn() {
        return productEcn;
    }

    public void setProductEcn(int productEcn) {
        this.productEcn = productEcn;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }


    public EcnLog getEcnLog() {
        return ecnLog;
    }

    public void setEcnLog(EcnLog ecnLog) {
        this.ecnLog = ecnLog;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "DevWorkOrder{" +
                "id=" + id +
                ", workorderName='" + workorderName + '\'' +
                ", workorderNumber='" + workorderNumber + '\'' +
                ", companyId=" + companyId +
                ", lineId=" + lineId +
                ", productionLine=" + productionLine +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productNumber=" + productNumber +
                ", productionStart=" + productionStart +
                ", productionEnd=" + productionEnd +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", productStandardHour=" + productStandardHour +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", deviceLiable=" + deviceLiable +
                ", user=" + user +
                ", updateBy='" + updateBy + '\'' +
                ", peopleNumber=" + peopleNumber +
                ", workingHour=" + workingHour +
                ", overtimeHour=" + overtimeHour +
                ", overtimeRace=" + overtimeRace +
                ", manualTime=" + manualTime +
                ", workorderStatus=" + workorderStatus +
                ", operationStatus=" + operationStatus +
                ", initDate=" + initDate +
                ", workSign=" + workSign +
                ", scrapNum=" + scrapNum +
                ", actualWarehouseNum=" + actualWarehouseNum +
                ", badNumber=" + badNumber +
                ", productModel='" + productModel + '\'' +
                ", productId=" + productId +
                ", remark='" + remark + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", ecnStatus=" + ecnStatus +
                ", ecnText='" + ecnText + '\'' +
                ", sign=" + sign +
                ", cumulativeNumber=" + cumulativeNumber +
                ", directPassRate=" + directPassRate +
                ", paramConfig='" + paramConfig + '\'' +
                ", reachRate=" + reachRate +
                ", pConfig=" + pConfig +
                ", productEcn=" + productEcn +
                ", orderRemark='" + orderRemark + '\'' +
                ", ecnLog=" + ecnLog +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                ", param4='" + param4 + '\'' +
                ", param5='" + param5 + '\'' +
                ", work1='" + work1 + '\'' +
                ", work2='" + work2 + '\'' +
                ", work3='" + work3 + '\'' +
                '}';
    }
}
