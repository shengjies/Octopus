package com.ruoyi.project.production.workExceptionList.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
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
import com.ruoyi.project.production.workExceptionList.domain.WorkExceptionList;
import com.ruoyi.project.production.workExceptionList.service.IWorkExceptionListService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 各个工单异常情况记录 信息操作处理
 *
 * @author zqm
 * @date 2019-04-16
 */
@Controller
@RequestMapping("/production/workExceptionList")
public class WorkExceptionListController extends BaseController {
    private String prefix = "production/workExceptionList";

    @Autowired
    private IWorkExceptionListService workExceptionListService;

    @RequiresPermissions("production:workExceptionList:view")
    @GetMapping()
    public String workExceptionList() {
        return prefix + "/workExceptionList";
    }

    /**
     * 查询各个工单异常情况记录列表
     */
    @RequiresPermissions("production:workExceptionList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WorkExceptionList workExceptionList, HttpServletRequest request) {
        startPage();
        List<WorkExceptionList> list = workExceptionListService.selectWorkExceptionListList(workExceptionList,request);
        return getDataTable(list);
    }


    /**
     * 导出各个工单异常情况记录列表
     */
    @RequiresPermissions("production:workExceptionList:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkExceptionList workExceptionList,HttpServletRequest request) {
        List<WorkExceptionList> list = workExceptionListService.selectWorkExceptionListList(workExceptionList,request);
        ExcelUtil<WorkExceptionList> util = new ExcelUtil<WorkExceptionList>(WorkExceptionList.class);
        return util.exportExcel(list, "workExceptionList");
    }

    /**
     * 新增各个工单异常情况记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 查询实况添加异常
     * @param lineId 产品编号
     * @param workId 工单编号
     * @param mmap
     * @return
     */
    @GetMapping("/add2")
    public String add2(int lineId,int workId,ModelMap mmap){
        mmap.put("lineId",lineId);
        mmap.put("workId",workId);
        return prefix +"/add2";
    }

    /**
     * 新增保存各个工单异常情况记录
     */
    @RequiresPermissions("production:workExceptionList:add")
    @Log(title = "各个工单异常情况记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WorkExceptionList workExceptionList,HttpServletRequest request) {
        return toAjax(workExceptionListService.insertWorkExceptionList(workExceptionList,request));
    }

    /**
     * 修改各个工单异常情况记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WorkExceptionList workExceptionList = workExceptionListService.selectWorkExceptionListById(id);
        mmap.put("workExceptionList", workExceptionList);
        return prefix + "/edit";
    }

    /**
     * 修改保存各个工单异常情况记录
     */
    @RequiresPermissions("production:workExceptionList:edit")
    @Log(title = "各个工单异常情况记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WorkExceptionList workExceptionList) {
        return toAjax(workExceptionListService.updateWorkExceptionList(workExceptionList));
    }

    /**
     * 删除各个工单异常情况记录
     */
    @RequiresPermissions("production:workExceptionList:remove")
    @Log(title = "各个工单异常情况记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(workExceptionListService.deleteWorkExceptionListByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 跳转异常工单描述页面
     */
    @GetMapping("/handleExcp/{id}")
    public String handleExcp(@PathVariable("id") Integer id, ModelMap mmap) {
        WorkExceptionList workExceptionList = workExceptionListService.selectWorkExceptionListById(id);
        mmap.put("workExceptionList", workExceptionList);
        return prefix + "/handleExcp";
    }

    /**
     * 处理工单异常操作
     *
     * @return
     */
    @PostMapping("/handleWorkExcp")
    @ResponseBody
    public AjaxResult handleWorkExcp(WorkExceptionList workExceptionList,HttpServletRequest request) {
        return toAjax(workExceptionListService.handleWorkExcp(workExceptionList,request));
    }

    /**
     * 解决完工单异常操作
     *
     * @return
     */
    @PostMapping("/finishWorkExcp")
    @ResponseBody
    public AjaxResult finishWorkExcp(Integer id) {
        return toAjax(workExceptionListService.finishWorkExcp(id));
    }

}
