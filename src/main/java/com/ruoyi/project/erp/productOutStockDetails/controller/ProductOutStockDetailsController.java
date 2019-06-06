package com.ruoyi.project.erp.productOutStockDetails.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import com.ruoyi.project.erp.productOutStockDetails.service.IProductOutStockDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品出库清单 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productOutStockDetails")
public class ProductOutStockDetailsController extends BaseController {
    private String prefix = "erp/productOutStockDetails";

    @Autowired
    private IProductOutStockDetailsService productOutStockDetailsService;

    @RequiresPermissions("erp:productOutStockDetails:view")
    @GetMapping()
    public String productOutStockDetails() {
        return prefix + "/productOutStockDetails";
    }

    /**
     * 查询所有产品出库清单列表
     */
    @RequiresPermissions("erp:productOutStockDetails:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductOutStockDetails productOutStockDetails) {
        startPage();
        List<ProductOutStockDetails> list = productOutStockDetailsService.selectProductOutStockDetailsList(productOutStockDetails);
        return getDataTable(list);
    }

    /**
     * 通过产品id查询产品出库记录信息
     * @param productId
     * @return
     */
    @RequiresPermissions("erp:productOutStockDetails:list")
    @RequestMapping("/listByProId")
    @ResponseBody
    public TableDataInfo listByProId(Integer productId) {
        ProductOutStockDetails productOutStockDetails = new ProductOutStockDetails();
        productOutStockDetails.setProductId(productId);
        startPage();
        List<ProductOutStockDetails> list = productOutStockDetailsService.selectProductOutStockDetailsList(productOutStockDetails);
        return getDataTable(list);
    }


    /**
     * 导出产品出库清单列表
     */
    @RequiresPermissions("erp:productOutStockDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductOutStockDetails productOutStockDetails) {
        List<ProductOutStockDetails> list = productOutStockDetailsService.selectProductOutStockDetailsList(productOutStockDetails);
        ExcelUtil<ProductOutStockDetails> util = new ExcelUtil<ProductOutStockDetails>(ProductOutStockDetails.class);
        return util.exportExcel(list, "productOutStockDetails");
    }

    /**
     * 新增产品出库清单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品出库清单
     */
    @RequiresPermissions("erp:productOutStockDetails:add")
    @Log(title = "产品出库清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductOutStockDetails productOutStockDetails) {
        return toAjax(productOutStockDetailsService.insertProductOutStockDetails(productOutStockDetails));
    }

    /**
     * 修改产品出库清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductOutStockDetails productOutStockDetails = productOutStockDetailsService.selectProductOutStockDetailsById(id);
        mmap.put("productOutStockDetails", productOutStockDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品出库清单
     */
    @RequiresPermissions("erp:productOutStockDetails:edit")
    @Log(title = "产品出库清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductOutStockDetails productOutStockDetails) {
        return toAjax(productOutStockDetailsService.updateProductOutStockDetails(productOutStockDetails));
    }

    /**
     * 删除产品出库清单
     */
    @RequiresPermissions("erp:productOutStockDetails:remove")
    @Log(title = "产品出库清单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productOutStockDetailsService.deleteProductOutStockDetailsByIds(ids));
    }

    /**
     * 跳转产品出库详情
     */
    @GetMapping("/productOutStockDetail/{productId}")
    public String productOutStockDetail(@PathVariable("productId") Integer productId, ModelMap mmap) {
        mmap.put("productId", productId);
        return prefix + "/productOutStockDetails";
    }

}
