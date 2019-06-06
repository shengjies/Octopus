package com.ruoyi.project.erp.productCustomer.controller;

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
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import com.ruoyi.project.erp.productCustomer.service.IProductCustomerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品客户关联 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productCustomer")
public class ProductCustomerController extends BaseController {
    private String prefix = "erp/productCustomer";

    @Autowired
    private IProductCustomerService productCustomerService;

    @RequiresPermissions("erp:productCustomer:view")
    @GetMapping()
    public String productCustomer() {
        return prefix + "/productCustomer";
    }

    /**
     * 查询产品客户关联列表
     */
    @RequiresPermissions("erp:productCustomer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Integer productId) {
        ProductCustomer productCustomer = new ProductCustomer();
        productCustomer.setProductId(productId);
        startPage();
        List<ProductCustomer> list = productCustomerService.selectProductCustomerList(productCustomer);
        return getDataTable(list);
    }


    /**
     * 导出产品客户关联列表
     */
    @RequiresPermissions("erp:productCustomer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductCustomer productCustomer) {
        List<ProductCustomer> list = productCustomerService.selectProductCustomerList(productCustomer);
        ExcelUtil<ProductCustomer> util = new ExcelUtil<ProductCustomer>(ProductCustomer.class);
        return util.exportExcel(list, "productCustomer");
    }

    /**
     * 新增产品客户关联
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品客户关联
     */
    @RequiresPermissions("erp:productCustomer:add")
    @Log(title = "产品客户关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductCustomer productCustomer) {
        try {
            return toAjax(productCustomerService.insertProductCustomer(productCustomer));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改产品客户关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductCustomer productCustomer = productCustomerService.selectProductCustomerById(id);
        mmap.put("productCustomer", productCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品客户关联
     */
    @RequiresPermissions("erp:productCustomer:edit")
    @Log(title = "产品客户关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductCustomer productCustomer) {
        return toAjax(productCustomerService.updateProductCustomer(productCustomer));
    }

    /**
     * 删除产品客户关联
     */
    @RequiresPermissions("erp:productCustomer:remove")
    @Log(title = "产品客户关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(productCustomerService.deleteProductCustomerByIds(ids));
    }

    /**
     * 通过物料id查询供应商物料关联列表
     *
     * @param id   产品id
     * @param mmap
     * @return
     */
    @GetMapping("/connectCustomer/{id}")
    public String connectCustomer(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("productId", id);
        return prefix + "/productCustomer";
    }

    /**
     * 根据客户编号和产品编号查询对应的客户编号
     * @param cid 客户编号
     * @param pid 产品编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/findCustomerCode")
    public AjaxResult findCustomerCode(int cid,int pid){
        return AjaxResult.success("sucess",productCustomerService.findCustomerCode(cid,pid));
    }

}
