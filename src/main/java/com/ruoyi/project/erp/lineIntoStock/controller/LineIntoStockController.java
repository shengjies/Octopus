package com.ruoyi.project.erp.lineIntoStock.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.lineIntoStock.domain.LineIntoStock;
import com.ruoyi.project.erp.lineIntoStock.service.ILineIntoStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产线入库 信息操作处理
 *
 * @author zqm
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/erp/lineIntoStock")
public class LineIntoStockController extends BaseController {
    private String prefix = "erp/lineIntoStock";

    @Autowired
    private ILineIntoStockService lineIntoStockService;

    @Autowired
    private IDevCompanyService companyService;

    @RequiresPermissions("erp:lineIntoStock:view")
    @GetMapping()
    public String lineIntoStock() {
        return prefix + "/lineIntoStock";
    }

    /**
     * 查询产线入库列表
     */
    @RequiresPermissions("erp:lineIntoStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LineIntoStock lineIntoStock) {
        startPage();
        List<LineIntoStock> list = lineIntoStockService.selectLineIntoStockList(lineIntoStock);
        return getDataTable(list);
    }


    /**
     * 导出产线入库列表
     */
    @RequiresPermissions("erp:lineIntoStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LineIntoStock lineIntoStock) {
        List<LineIntoStock> list = lineIntoStockService.selectLineIntoStockList(lineIntoStock);
        ExcelUtil<LineIntoStock> util = new ExcelUtil<LineIntoStock>(LineIntoStock.class);
        return util.exportExcel(list, "lineIntoStock");
    }

    /**
     * 新增产线入库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产线入库
     */
    @RequiresPermissions("erp:lineIntoStock:add")
    @Log(title = "产线入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody LineIntoStock lineIntoStock) {
        try {
            return toAjax(lineIntoStockService.insertLineIntoStock(lineIntoStock));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 修改产线入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        LineIntoStock lineIntoStock = lineIntoStockService.selectLineIntoStockById(id);
        mmap.put("lineIntoStock", lineIntoStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存产线入库
     */
    @RequiresPermissions("erp:lineIntoStock:edit")
    @Log(title = "产线入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LineIntoStock lineIntoStock) {
        return toAjax(lineIntoStockService.updateLineIntoStock(lineIntoStock));
    }

    /**
     * 删除产线入库
     */
    @RequiresPermissions("erp:lineIntoStock:remove")
    @Log(title = "产线入库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(lineIntoStockService.deleteLineIntoStockByIds(ids));
    }

    /**
     * 作废生产入库单
     */
    @RequiresPermissions("erp:lineIntoStock:remove")
    @Log(title = "产线入库", businessType = BusinessType.DELETE)
    @PostMapping("/nullify")
    @ResponseBody
    public AjaxResult nullify(Integer id) {
        return toAjax(lineIntoStockService.nullifyLineIntoStockByIds(id));
    }

    /**
     * 查看生产入库明细
     */
    @GetMapping("/details/{id}")
    @RequiresPermissions("erp:lineIntoStock:list")
    public String details(@PathVariable("id") Integer id, ModelMap mmap) {
        LineIntoStock lineIntoStock = lineIntoStockService.selectLineIntoStockById(id);
        mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
        mmap.put("lineIntoStock", lineIntoStock);
        return prefix + "/details";
    }

}
