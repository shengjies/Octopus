package com.ruoyi.project.page.layout.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.page.layout.domain.Layout;
import com.ruoyi.project.page.layout.service.ILayoutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *页面布局类型
 */
@Controller
@RequestMapping("/layout/type")
public class LayoutController extends BaseController {

    private String prefix = "/page/layout";

    @GetMapping()
    @RequiresPermissions("layout:type:view")
    public String layout(){
        return  prefix +"/layout";
    }

    @Autowired
    private ILayoutService layoutService;

    /**
     * 分页查询页面布局类型
     * @param layout
     * @return
     */
    @RequiresPermissions("layout:type:list")
    @ResponseBody
    @RequestMapping("/list")
    public TableDataInfo list(Layout layout)
    {
        startPage();
        List<Layout> layouts = layoutService.selectLayoutList(layout);
        return getDataTable(layouts);
    }

    /**
     * 添加页面布局类型
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("layout:type:add")
    public String add(){
        return prefix+"/add";
    }

    /**
     * 新增页面布局类型
     * @param layout
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("layout:type:add")
    @Log(title = "页面类型", businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult addSave(Layout layout){
        return toAjax(layoutService.addLayoutInfo(layout));
    }

    /**
     * 改变状态
     * @param layout
     * @return
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    @RequiresPermissions("layout:type:status")
    @Log(title = "页面类型", businessType = BusinessType.UPDATE)
    public AjaxResult changeStatus(Layout layout){
        return  toAjax(layoutService.changeStatus(layout));
    }

    /**
     * 修改类型
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("layout:type:edit")
    public String edit(@PathVariable("id") int id, ModelMap mmap){
        mmap.put("layout",layoutService.selectLayoutById(id));
        return prefix+"/edit";
    }

    /**
     * 修改保存类型
     * @param layout
     * @return
     */
    @Log(title = "页面类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @RequiresPermissions("layout:type:edit")
    public AjaxResult editSave(Layout layout){
        return toAjax(layoutService.updateUser(layout));
    }

    /**
     * 删除页面类型
     * @param id
     * @return
     */
    @Log(title = "页面类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("layout:type:remove")
    public AjaxResult remove( int id){
        return  toAjax(layoutService.delLayoutById(id));
    }
}
