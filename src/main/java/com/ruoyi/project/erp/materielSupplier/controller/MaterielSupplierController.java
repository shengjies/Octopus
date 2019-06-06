package com.ruoyi.project.erp.materielSupplier.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.service.IMaterielSupplierService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料供应商关联 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielSupplier")
public class MaterielSupplierController extends BaseController {
    private String prefix = "erp/materielSupplier";

    @Autowired
    private IMaterielSupplierService materielSupplierService;

    @RequiresPermissions("erp:materielSupplier:view")
    @GetMapping()
    public String materielSupplier() {
        return prefix + "/materielSupplier";
    }

    /**
     * 查询物料供应商关联列表
     */
    @RequiresPermissions("erp:materielSupplier:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielSupplier materielSupplier) {
        startPage();
        List<MaterielSupplier> list = materielSupplierService.selectMaterielSupplierList(materielSupplier);
        return getDataTable(list);
    }


    /**
     * 导出物料供应商关联列表
     */
    @RequiresPermissions("erp:materielSupplier:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielSupplier materielSupplier) {
        List<MaterielSupplier> list = materielSupplierService.selectMaterielSupplierList(materielSupplier);
        ExcelUtil<MaterielSupplier> util = new ExcelUtil<MaterielSupplier>(MaterielSupplier.class);
        return util.exportExcel(list, "materielSupplier");
    }

    /**
     * 新增物料供应商关联
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料供应商关联
     */
    @RequiresPermissions("erp:materielSupplier:add")
    @Log(title = "物料供应商关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterielSupplier materielSupplier) {
        try {
            return toAjax(materielSupplierService.insertMaterielSupplier(materielSupplier));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 修改物料供应商关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielSupplier materielSupplier = materielSupplierService.selectMaterielSupplierById(id);
        mmap.put("materielSupplier", materielSupplier);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料供应商关联
     */
    @RequiresPermissions("erp:materielSupplier:edit")
    @Log(title = "物料供应商关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielSupplier materielSupplier) {
        return toAjax(materielSupplierService.updateMaterielSupplier(materielSupplier));
    }

    /**
     * 删除物料供应商关联
     */
    @RequiresPermissions("erp:materielSupplier:remove")
    @Log(title = "物料供应商关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielSupplierService.deleteMaterielSupplierByIds(ids));
    }


    /**
     * 通过物料id查询供应商物料关联信息
     */
    @RequestMapping("/listByMaterielId")
    @ResponseBody
    public TableDataInfo listByMaterielId(Integer materielId) {
        MaterielSupplier materielSupplier = new MaterielSupplier();
        materielSupplier.setMaterielId(materielId);
        startPage();
        List<MaterielSupplier> list = materielSupplierService.selectMaterielSupplierList(materielSupplier);
        return getDataTable(list);
    }

    /**
     * 通过物料id查询供应商物料关联列表
     *
     * @param id   物料id
     * @param mmap
     * @return
     */
    @GetMapping("/connectSupplier/{id}")
    public String connectSupplier(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("materielId", id);
        return prefix + "/materielSupplier";
    }

    /**
     * 根据物料id和供应商id查询供应商编码
     * @param mid  物料id
     * @param sid 供应商id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findSupplierCodeByMaterielId")
    public AjaxResult findSupplierCodeByMaterielId(int mid,int sid){
            return AjaxResult.success("success",materielSupplierService.findSupplierCodeByMaterielId(mid,sid));
    }

    /**
     * 查询物料采购单信息
     * @param mid  物料id
     * @param sid 供应商id
     * @return
     */
    @RequestMapping("/matOutStockByMatIdAndSupId")
    @ResponseBody
    public AjaxResult matOutStockByMatIdAndSupId(int mid,int sid){
        return AjaxResult.success("success",materielSupplierService.matOutStockByMatIdAndSupId(mid,sid));
    }
}
