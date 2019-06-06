package com.ruoyi.project.device.devDeviceCounts.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务包装类
 * 查询IO口上传数据记录dev_data_log表返回数据
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.device.devDeviceCounts.domain
 * @Author: Administrator
 * @Date: 2019/4/23 18:42
 * @Description //TODO
 * @Version: 1.0
 **/
public class DataLogTask implements Serializable {
    private static final long serialVersionUID = 6663397464237667197L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 公司Id
     */
    private Integer companyId;
    /**
     * 产线id
     */
    private Integer lineId;
    /**
     * 工单id
     */
    private Integer workId;
    /**
     * 硬件id
     */
    private Integer devId;
    /**
     * IO口id
     */
    private Integer ioId;
    /**
     * IO排序
     */
    private Integer ioOrder;
    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最小值
     */
    private Integer min;
    /**
     * 最大值
     */
    private Integer max;

    /**
     * 总计数
     */
    private Integer sumHour;

    /**
     * 小时
     */
    private Integer hour;

    public Integer getSumHour() {
        return sumHour;
    }

    public void setSumHour(Integer sumHour) {
        this.sumHour = sumHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getIoId() {
        return ioId;
    }

    public void setIoId(Integer ioId) {
        this.ioId = ioId;
    }

    public Integer getIoOrder() {
        return ioOrder;
    }

    public void setIoOrder(Integer ioOrder) {
        this.ioOrder = ioOrder;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "DataLogTask{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", lineId=" + lineId +
                ", workId=" + workId +
                ", devId=" + devId +
                ", ioId=" + ioId +
                ", ioOrder=" + ioOrder +
                ", createDate=" + createDate +
                ", min=" + min +
                ", max=" + max +
                ", hour=" + hour +
                '}';
    }
}
