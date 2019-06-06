package com.ruoyi.project.production.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PDF 报表导出
 */
public interface IReportService {
    /**
     * 导出产线在指定时间段内已经提交的各个工单的数据
     * @param lineId 产线编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    int exportReport(int lineId,String productCode, String startTime, String endTime, HttpServletResponse response, HttpServletRequest request);
}
