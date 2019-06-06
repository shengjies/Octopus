package com.ruoyi.project.erp.productIntoStock.controller;

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
import com.ruoyi.project.erp.productIntoStock.domain.ProductIntoStock;
import com.ruoyi.project.erp.productIntoStock.service.IProductIntoStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品入库 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productIntoStock")
public class ProductIntoStockController extends BaseController {
    private String prefix = "erp/productIntoStock";

    @Autowired
    private IProductIntoStockService productIntoStockService;

    @Autowired
    private IDevCompanyService companyService;

    @RequiresPermissions("erp:productIntoStock:view")
    @GetMapping()
    public String productIntoStock() {
        return prefix + "/productIntoStock";
    }

    /**
     * 查询产品入库列表
     */
    @RequiresPermissions("erp:productIntoStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductIntoStock productIntoStock) {
        startPage();
        List<ProductIntoStock> list = productIntoStockService.selectProductIntoStockList(productIntoStock);
        return getDataTable(list);
    }


    /**
     * 导出产品入库列表
     */
    @RequiresPermissions("erp:productIntoStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductIntoStock productIntoStock) {
        List<ProductIntoStock> list = productIntoStockService.selectProductIntoStockList(productIntoStock);
        ExcelUtil<ProductIntoStock> util = new ExcelUtil<ProductIntoStock>(ProductIntoStock.class);
        return util.exportExcel(list, "productIntoStock");
    }

    /**
     * 新增产品入库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品入库
     */
    @RequiresPermissions("erp:productIntoStock:add")
    @Log(title = "产品入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ProductIntoStock productIntoStock) {
        try {
            return toAjax(productIntoStockService.insertProductIntoStock(productIntoStock));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改产品入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductIntoStock productIntoStock = productIntoStockService.selectProductIntoStockById(id);
        mmap.put("productIntoStock", productIntoStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品入库
     */
    @RequiresPermissions("erp:productIntoStock:edit")
    @Log(title = "产品入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductIntoStock productIntoStock) {
        return toAjax(productIntoStockService.updateProductIntoStock(productIntoStock));
    }

    /**
     * 删除产品入库
     */
    @RequiresPermissions("erp:productIntoStock:remove")
    @Log(title = "产品入库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productIntoStockService.deleteProductIntoStockByIds(ids));
    }

    /**
     * 作废客户退货单
     */
    @RequiresPermissions("erp:productIntoStock:remove")
    @Log(title = "产品入库", businessType = BusinessType.DELETE)
    @PostMapping("/nullify")
    @ResponseBody
    public AjaxResult nullify(Integer id) {
        return toAjax(productIntoStockService.nullifyProductIntoStockByIds(id));
    }

    /**
     * 查看产品退货明细信息
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductIntoStock productIntoStock = productIntoStockService.selectProductIntoStockById(id);
        mmap.put("productIntoStock", productIntoStock);
        mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
        return prefix + "/details";
    }
}
