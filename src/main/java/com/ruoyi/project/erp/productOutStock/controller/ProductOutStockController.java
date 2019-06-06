package com.ruoyi.project.erp.productOutStock.controller;

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
import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;
import com.ruoyi.project.erp.productOutStock.service.IProductOutStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品出库 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productOutStock")
public class ProductOutStockController extends BaseController {
    private String prefix = "erp/productOutStock";

    @Autowired
    private IProductOutStockService productOutStockService;

    @Autowired
    private IDevCompanyService companyService;

    @RequiresPermissions("erp:productOutStock:view")
    @GetMapping()
    public String productOutStock() {
        return prefix + "/productOutStock";
    }

    /**
     * 查询产品出库列表
     */
    @RequiresPermissions("erp:productOutStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductOutStock productOutStock) {
        startPage();
        List<ProductOutStock> list = productOutStockService.selectProductOutStockList(productOutStock);
        return getDataTable(list);
    }


    /**
     * 导出产品出库列表
     */
    @RequiresPermissions("erp:productOutStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductOutStock productOutStock) {
        List<ProductOutStock> list = productOutStockService.selectProductOutStockList(productOutStock);
        ExcelUtil<ProductOutStock> util = new ExcelUtil<ProductOutStock>(ProductOutStock.class);
        return util.exportExcel(list, "productOutStock");
    }

    /**
     * 新增产品出库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品出库
     */
    @RequiresPermissions("erp:productOutStock:add")
    @Log(title = "产品出库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ProductOutStock productOutStock) {
        try {
            return toAjax(productOutStockService.insertProductOutStock(productOutStock));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 修改产品出库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductOutStock productOutStock = productOutStockService.selectProductOutStockById(id);
        mmap.put("productOutStock", productOutStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品出库
     */
    @RequiresPermissions("erp:productOutStock:edit")
    @Log(title = "产品出库", businessType = BusinessType.UPDATE)
    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ProductOutStock productOutStock) {
        try {
            return toAjax(productOutStockService.updateProductOutStock(productOutStock));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 删除产品出库
     */
    @RequiresPermissions("erp:productOutStock:remove")
    @Log(title = "产品出库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productOutStockService.deleteProductOutStockByIds(ids));
    }

    /**
     * 作废产品出库
     */
    @RequiresPermissions("erp:productOutStock:remove")
    @PostMapping("/nullify")
    @ResponseBody
    public AjaxResult nullify(Integer id) {
        return toAjax(productOutStockService.nullifyProductOutStockById(id));
    }

    /**
     * 查看产品出库明细
     */
    @RequiresPermissions("erp:productOutStock:list")
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductOutStock productOutStock = productOutStockService.selectProductOutStockById(id);
        mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
        mmap.put("productOutStock", productOutStock);
        return prefix + "/details";
    }

}
