package com.ruoyi.project.erp.productStock.controller;

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
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.service.IProductStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品库存 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productStock")
public class ProductStockController extends BaseController {
    private String prefix = "erp/productStock";

    @Autowired
    private IProductStockService productStockService;

    @RequiresPermissions("erp:productStock:view")
    @GetMapping()
    public String productStock() {
        return prefix + "/productStock";
    }

    /**
     * 查询产品库存列表
     */
    @RequiresPermissions("erp:productStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductStock productStock) {
        startPage();
        List<ProductStock> list = productStockService.selectProductStockList(productStock);
        return getDataTable(list);
    }


    /**
     * 导出产品库存列表
     */
    @RequiresPermissions("erp:productStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductStock productStock) {
        List<ProductStock> list = productStockService.selectProductStockList(productStock);
        ExcelUtil<ProductStock> util = new ExcelUtil<ProductStock>(ProductStock.class);
        return util.exportExcel(list, "productStock");
    }

    /**
     * 新增产品库存
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品库存
     */
    @RequiresPermissions("erp:productStock:add")
    @Log(title = "产品库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductStock productStock) {
        return toAjax(productStockService.insertProductStock(productStock));
    }

    /**
     * 修改产品库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductStock productStock = productStockService.selectProductStockById(id);
        mmap.put("productStock", productStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品库存
     */
    @RequiresPermissions("erp:productStock:edit")
    @Log(title = "产品库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductStock productStock) {
        return toAjax(productStockService.updateProductStock(productStock));
    }

    /**
     * 删除产品库存
     */
    @RequiresPermissions("erp:productStock:remove")
    @Log(title = "产品库存", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productStockService.deleteProductStockByIds(ids));
    }

    /**
     * 查看产品出库详情
     */
    @GetMapping("/productOutStockDetail/{id}")
    public String productOutStockDetail(@PathVariable("id") Integer id, ModelMap mmap) {
        return "erp/productOutStockDetails/productOutStockDetails";
    }

    /**
     * 修改产品良品数量
     */
    @GetMapping("/editGoodNumber/{id}")
    public String editGoodNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductStock productStock = productStockService.selectProductStockById(id);
        mmap.put("productStock", productStock);
        return prefix + "/editGoodNumber";
    }

    /**
     * 修改产品不良品数量
     */
    @GetMapping("/editBadNumber/{id}")
    public String editBadNumber(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductStock productStock = productStockService.selectProductStockById(id);
        mmap.put("productStock", productStock);
        return prefix + "/editBadNumber";
    }

    /**
     * 查询产品库存里列表
     * @return
     */
    @PostMapping("/productStockList")
    @ResponseBody
    public AjaxResult productStockList(){
        ProductStock productStock = new ProductStock();
        return AjaxResult.success("success",productStockService.selectProductStockList(productStock));
    }
}
