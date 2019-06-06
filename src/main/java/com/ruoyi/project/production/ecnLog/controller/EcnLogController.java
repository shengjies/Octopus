package com.ruoyi.project.production.ecnLog.controller;

import java.util.List;

import com.ruoyi.project.product.list.domain.DevProductList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.service.IEcnLogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * ECN 变更记录 信息操作处理
 *
 * @author zqm
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/production/ecnLog")
public class EcnLogController extends BaseController {
    private String prefix = "production/ecnLog";

    @Autowired
    private IEcnLogService ecnLogService;

    @RequiresPermissions("production:ecnLog:view")
    @GetMapping()
    public String ecnLog(int ecn_type, int save_id, int ecn_status, ModelMap mmap) {
        mmap.put("ecn_type", ecn_type);
        mmap.put("save_id", save_id);
        mmap.put("ecn_status", ecn_status);
        return prefix + "/ecnLog";
    }

    /**
     * 查询ECN 变更记录列表
     */
    @RequiresPermissions("production:ecnLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EcnLog ecnLog) {
        startPage();
        List<EcnLog> list = ecnLogService.selectEcnLogList(ecnLog);
        return getDataTable(list);
    }

    /**
     * 修改ECN 变更记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        EcnLog ecnLog = ecnLogService.selectEcnLogById(id);
        mmap.put("ecnLog", ecnLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存ECN 变更记录
     */
    @RequiresPermissions("production:ecnLog:edit")
    @Log(title = "ECN 变更记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EcnLog ecnLog) {
        return toAjax(ecnLogService.updateEcnLog(ecnLog));
    }

    /**
     * 删除ECN 变更记录
     */
    @RequiresPermissions("production:ecnLog:remove")
    @Log(title = "ECN 变更记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id) {
        return toAjax(ecnLogService.deleteEcnLogById(id));
    }

}
