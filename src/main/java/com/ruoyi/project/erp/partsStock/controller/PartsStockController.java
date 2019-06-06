package com.ruoyi.project.erp.partsStock.controller;

import java.util.List;

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
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.service.IPartsStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 半成品库存 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/partsStock")
public class PartsStockController extends BaseController {
    private String prefix = "erp/partsStock";

    @Autowired
    private IPartsStockService partsStockService;

    @RequiresPermissions("erp:partsStock:view")
    @GetMapping()
    public String partsStock() {
        return prefix + "/partsStock";
    }

    /**
     * 查询半成品库存列表
     */
    @RequiresPermissions("erp:partsStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PartsStock partsStock) {
        startPage();
        List<PartsStock> list = partsStockService.selectPartsStockList(partsStock);
        return getDataTable(list);
    }


    /**
     * 导出半成品库存列表
     */
    @RequiresPermissions("erp:partsStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PartsStock partsStock) {
        List<PartsStock> list = partsStockService.selectPartsStockList(partsStock);
        ExcelUtil<PartsStock> util = new ExcelUtil<PartsStock>(PartsStock.class);
        return util.exportExcel(list, "partsStock");
    }

    /**
     * 新增半成品库存
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存半成品库存
     */
    @RequiresPermissions("erp:partsStock:add")
    @Log(title = "半成品库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PartsStock partsStock) {
        return toAjax(partsStockService.insertPartsStock(partsStock));
    }

    /**
     * 修改半成品库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        PartsStock partsStock = partsStockService.selectPartsStockById(id);
        mmap.put("partsStock", partsStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存半成品库存
     */
    @RequiresPermissions("erp:partsStock:edit")
    @Log(title = "半成品库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PartsStock partsStock) {
        return toAjax(partsStockService.updatePartsStock(partsStock));
    }

    /**
     * 删除半成品库存
     */
    @RequiresPermissions("erp:partsStock:remove")
    @Log(title = "半成品库存", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(partsStockService.deletePartsStockByIds(ids));
    }

    /**
     * 修改半成品良品库存
     */
    @GetMapping("/editGoodNumber/{id}")
    public String editGoodNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        PartsStock partsStock = partsStockService.selectPartsStockById(id);
        mmap.put("partsStock", partsStock);
        return prefix + "/editGoodNumber";
    }

    /**
     * 修改半成品不良品库存
     */
    @GetMapping("/editBadNumber/{id}")
    public String editBadNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        PartsStock partsStock = partsStockService.selectPartsStockById(id);
        mmap.put("partsStock", partsStock);
        return prefix + "/editBadNumber";
    }

    /**
     * 查询公司所有的半成品库存信息
     * @return
     */
    @PostMapping("/partsStockList")
    @ResponseBody
    public AjaxResult partsStockList(){
        PartsStock partsStock = new PartsStock();
        return AjaxResult.success("success",partsStockService.selectPartsStockList(partsStock));
    }
}
