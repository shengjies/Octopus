package com.ruoyi.project.erp.materielOutStock.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.materielOutStock.domain.MaterielOutStock;
import com.ruoyi.project.erp.materielOutStock.service.IMaterielOutStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料出库 信息操作处理
 *
 * @author zqm
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/erp/materielOutStock")
public class MaterielOutStockController extends BaseController {
    private String prefix = "erp/materielOutStock";

    @Autowired
    private IMaterielOutStockService materielOutStockService;

    @Autowired
    private IDevCompanyService companyService;

    @RequiresPermissions("erp:materielOutStock:view")
    @GetMapping()
    public String materielOutStock() {
        return prefix + "/materielOutStock";
    }

    /**
     * 查询物料出库列表
     */
    @RequiresPermissions("erp:materielOutStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielOutStock materielOutStock) {
        startPage();
        List<MaterielOutStock> list = materielOutStockService.selectMaterielOutStockList(materielOutStock);
        return getDataTable(list);
    }


    /**
     * 导出物料出库列表
     */
    @RequiresPermissions("erp:materielOutStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielOutStock materielOutStock) {
        List<MaterielOutStock> list = materielOutStockService.selectMaterielOutStockList(materielOutStock);
        ExcelUtil<MaterielOutStock> util = new ExcelUtil<MaterielOutStock>(MaterielOutStock.class);
        return util.exportExcel(list, "materielOutStock");
    }

    /**
     * 新增物料出库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料出库
     */
    @RequiresPermissions("erp:materielOutStock:add")
    @Log(title = "物料出库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody MaterielOutStock materielOutStock) {
        try {
            return toAjax(materielOutStockService.insertMaterielOutStock(materielOutStock));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 修改物料出库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielOutStock materielOutStock = materielOutStockService.selectMaterielOutStockById(id);
        mmap.put("materielOutStock", materielOutStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料出库
     */
    @RequiresPermissions("erp:materielOutStock:edit")
    @Log(title = "物料出库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielOutStock materielOutStock) {
        return toAjax(materielOutStockService.updateMaterielOutStock(materielOutStock));
    }

    /**
     * 删除物料出库
     */
    @RequiresPermissions("erp:materielOutStock:remove")
    @Log(title = "物料出库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielOutStockService.deleteMaterielOutStockByIds(ids));
    }

    /**
     * 查看物料退货明细
     */
    @RequiresPermissions("erp:materielOutStock:list")
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielOutStock materielOutStock = materielOutStockService.selectMaterielOutStockById(id);
        mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
        mmap.put("materielOutStock", materielOutStock);
        return prefix + "/details";
    }

    /**
     * 作废物料退货单
     */
    @RequiresPermissions("erp:materielOutStock:remove")
    @Log(title = "物料出库", businessType = BusinessType.DELETE)
    @PostMapping("/nullify")
    @ResponseBody
    public AjaxResult nullify(Integer id) {
        return toAjax(materielOutStockService.nullifyMaterielOutStockByIds(id));
    }

}
