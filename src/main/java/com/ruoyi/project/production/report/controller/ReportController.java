package com.ruoyi.project.production.report.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.production.report.service.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/production/report")
public class ReportController extends BaseController {
    private String prefix = "production/report";

    @Autowired
    private IReportService reportService;


    /**
     * 查询报表数
     * @return
     */
    @GetMapping
    @RequiresPermissions("production:report:view")
    public String report(){
        return  prefix+"/report";
    }

    /**
     * 导出报表数据
     * @param lineId 产线编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    @ResponseBody
    @RequestMapping("/pdf")
    @RequiresPermissions("production:report:pdf")
    public void exportReport(int lineId, String productCode,String startTime, String endTime, HttpServletResponse response, HttpServletRequest request){
        reportService.exportReport(lineId,productCode,startTime,endTime,response,request);
    }
}
