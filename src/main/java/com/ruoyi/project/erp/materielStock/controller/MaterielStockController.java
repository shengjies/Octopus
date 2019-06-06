package com.ruoyi.project.erp.materielStock.controller;

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
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.service.IMaterielStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料库存 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielStock")
public class MaterielStockController extends BaseController {
    private String prefix = "erp/materielStock";

    @Autowired
    private IMaterielStockService materielStockService;

    @RequiresPermissions("erp:materielStock:view")
    @GetMapping()
    public String materielStock() {
        return prefix + "/materielStock";
    }

    /**
     * 查询物料库存列表
     */
    @RequiresPermissions("erp:materielStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielStock materielStock) {
        startPage();
        List<MaterielStock> list = materielStockService.selectMaterielStockList(materielStock);
        return getDataTable(list);
    }


    /**
     * 导出物料库存列表
     */
    @RequiresPermissions("erp:materielStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielStock materielStock) {
        List<MaterielStock> list = materielStockService.selectMaterielStockList(materielStock);
        ExcelUtil<MaterielStock> util = new ExcelUtil<MaterielStock>(MaterielStock.class);
        return util.exportExcel(list, "materielStock");
    }

    /**
     * 新增物料库存
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料库存
     */
    @RequiresPermissions("erp:materielStock:add")
    @Log(title = "物料库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterielStock materielStock) {
        return toAjax(materielStockService.insertMaterielStock(materielStock));
    }

    /**
     * 修改物料库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielStock materielStock = materielStockService.selectMaterielStockById(id);
        mmap.put("materielStock", materielStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料库存
     */
    @RequiresPermissions("erp:materielStock:edit")
    @Log(title = "物料库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielStock materielStock) {
        return toAjax(materielStockService.updateMaterielStock(materielStock));
    }

    /**
     * 删除物料库存
     */
    @RequiresPermissions("erp:materielStock:remove")
    @Log(title = "物料库存", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielStockService.deleteMaterielStockByIds(ids));
    }

    /**
     * 修改物料良品数量
     */
    @GetMapping("/editGoodNumber/{id}")
    public String editGoodNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielStock materielStock = materielStockService.selectMaterielStockById(id);
        mmap.put("materielStock", materielStock);
        return prefix + "/editGoodNumber";
    }

    /**
     * 修改物料不良品数量
     */
    @GetMapping("/editBadNumber/{id}")
    public String editBadNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielStock materielStock = materielStockService.selectMaterielStockById(id);
        mmap.put("materielStock", materielStock);
        return prefix + "/editBadNumber";
    }

    /**
     * 操作物料临时仓库库存数据
     * @param id 物料库存id
     * @param mmap 模型
     * @return 结果
     */
    @GetMapping("/editTemporaryNumber/{id}")
    public String editTemporaryNumber(@PathVariable("id") Integer id, ModelMap mmap){
        MaterielStock materielStock = materielStockService.selectMaterielStockById(id);
        mmap.put("materielStock", materielStock);
        return prefix + "/editTemporaryNumber";
    }

    /**
     * 跳转预收仓库管理
     * @return 结果
     */
    @GetMapping("/preWarehouse")
    public String preWarehouse(){
        return prefix + "/prewarehouse";
    }

    /**
     * 查询公司所有的物料库存信息
     * @return
     */
    @PostMapping("/materielStockList")
    @ResponseBody
    public AjaxResult materielStockList(){
        MaterielStock materielStock = new MaterielStock();
        return AjaxResult.success("success",materielStockService.selectMaterielStockList(materielStock));
    }

}
