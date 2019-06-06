package com.ruoyi.project.product.list.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品管理 信息操作处理
 *
 * @author ruoyi
 * @date 2019-04-10
 */
@Controller
@RequestMapping("/product")
public class DevProductListController extends BaseController {
    private String prefix = "product/list";

    @Autowired
    private IDevProductListService devProductListService;

    @RequiresPermissions("device:devProductList:view")
    @GetMapping()
    public String devProductList() {
        return prefix + "/devProductList";
    }

    /**
     * 查询产品管理列表
     */
    @RequiresPermissions("device:devProductList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DevProductList devProductList) {
        startPage();
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        return getDataTable(list);
    }


    /**
     * 导出产品管理列表
     */
    @RequiresPermissions("device:devProductList:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevProductList devProductList) {
        List<DevProductList> list = devProductListService.selectDevProductListList(devProductList);
        ExcelUtil<DevProductList> util = new ExcelUtil<DevProductList>(DevProductList.class);
        return util.exportExcel(list, "devProductList");
    }

    /**
     * 新增产品管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品管理
     */
    @RequiresPermissions("device:devProductList:add")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DevProductList devProductList) {
        return toAjax(devProductListService.insertDevProductList(devProductList));
    }

    /**
     * 修改产品管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        DevProductList devProductList = devProductListService.selectDevProductListById(id);
        mmap.put("product", devProductList);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品管理
     */
    @RequiresPermissions("device:devProductList:edit")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DevProductList devProductList) {
        return toAjax(devProductListService.updateDevProductList(devProductList));
    }

    /**
     * 删除产品管理
     */
    @RequiresPermissions("device:devProductList:remove")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(devProductListService.deleteDevProductListByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 导出模板
     *
     * @return
     */
    @RequiresPermissions("device:devProductList:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DevProductList> productListExcelUtil = new ExcelUtil<>(DevProductList.class);
        return productListExcelUtil.importTemplateExcel("产品数据");
    }

    @Log(title = "产品导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("device:devProductList:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DevProductList> util = new ExcelUtil<>(DevProductList.class);
        List<DevProductList> devProductList = util.importExcel(file.getInputStream());
        try {
            return AjaxResult.success(devProductListService.importProduct(devProductList, updateSupport));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 通过产品id查询产品信息
     *
     * @param productId
     * @return
     */
    @PostMapping("/findProductInfo")
    @ResponseBody
    public AjaxResult findProductInfo(Integer productId) {
        DevProductList productCode = devProductListService.findProductInfo(productId);
        return AjaxResult.success("成功", productCode);
    }

    /**
     * 校验产品编码唯一性
     *
     * @return
     */
    @PostMapping("/checkProductCodeUnique")
    @ResponseBody
    public String checkProductCodeUnique(DevProductList product) {
        return devProductListService.checkProductCodeUnique(product);
    }

    /**
     * 通过客户id查询相关产品信息
     * @param customerId 客户id
     * @return 结果
     */
    @PostMapping("/findProductByCustomerId")
    @ResponseBody
    public AjaxResult findProductByCustomerId(Integer customerId){
        List<DevProductList> productList = devProductListService.findProductByCustomerId(customerId);
        return AjaxResult.success("success", productList);
    }

    /**
     * 通过产品id和客户id查询产品信息
     * @param productId 产品id
     * @param customerId 客户id
     * @return 结果
     */
    @PostMapping("/findProductByProIdAndCusId")
    @ResponseBody
    public AjaxResult findProductByProIdAndCusId(Integer productId,Integer customerId){
        return AjaxResult.success("success",devProductListService.findProductByProIdAndCusId(productId,customerId));
    }

    /**
     * 查询对应客户关联的产品信息
     * @param customerId 客户编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectProductByCustomerId")
    public AjaxResult selectProductByCustomerId(int customerId){
        List<DevProductList> productLists = devProductListService.selectProductByCustomerId(customerId);
        return AjaxResult.success("success", productLists);
    }

    /**
     * ecn 变更
     * @param productList
     * @return
     */
    @ResponseBody
    @RequestMapping("/ecn")
    public AjaxResult ecnChange(DevProductList productList){
        return toAjax(devProductListService.ecnChange(productList));
    }

    /**
     * 查询对应的产品数据
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectProductAllByOrderId")
    public AjaxResult selectProductAllByOrderId(int orderId){
        return AjaxResult.success("success",devProductListService.selectProductAllByOrderId(orderId));
    }
}
