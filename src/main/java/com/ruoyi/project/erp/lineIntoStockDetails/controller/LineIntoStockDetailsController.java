package com.ruoyi.project.erp.lineIntoStockDetails.controller;

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
import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;
import com.ruoyi.project.erp.lineIntoStockDetails.service.ILineIntoStockDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产线入库明细 信息操作处理
 *
 * @author zqm
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/erp/lineIntoStockDetails")
public class LineIntoStockDetailsController extends BaseController {
    private String prefix = "erp/lineIntoStockDetails";

    @Autowired
    private ILineIntoStockDetailsService lineIntoStockDetailsService;

    @RequiresPermissions("erp:lineIntoStockDetails:view")
    @GetMapping()
    public String lineIntoStockDetails() {
        return prefix + "/lineIntoStockDetails";
    }

    /**
     * 查询产线入库明细列表
     */
    @RequiresPermissions("erp:lineIntoStockDetails:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LineIntoStockDetails lineIntoStockDetails) {
        startPage();
        List<LineIntoStockDetails> list = lineIntoStockDetailsService.selectLineIntoStockDetailsList(lineIntoStockDetails);
        return getDataTable(list);
    }

    /**
     * 通过入库类型查询入库明细列表
     * @param intoType
     * @param detId
     * @return
     */
    @RequiresPermissions("erp:lineIntoStockDetails:list")
    @PostMapping("/listByDetId")
    @ResponseBody
    public TableDataInfo listByDetId(Integer intoType,Integer detId) {
        LineIntoStockDetails lineIntoStockDetails = new LineIntoStockDetails();
        lineIntoStockDetails.setIntoType(intoType);
        lineIntoStockDetails.setDetIntoId(detId);
        startPage();
        List<LineIntoStockDetails> list = lineIntoStockDetailsService.selectLineIntoStockDetailsList(lineIntoStockDetails);
        return getDataTable(list);
    }


    /**
     * 导出产线入库明细列表
     */
    @RequiresPermissions("erp:lineIntoStockDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LineIntoStockDetails lineIntoStockDetails) {
        List<LineIntoStockDetails> list = lineIntoStockDetailsService.selectLineIntoStockDetailsList(lineIntoStockDetails);
        ExcelUtil<LineIntoStockDetails> util = new ExcelUtil<LineIntoStockDetails>(LineIntoStockDetails.class);
        return util.exportExcel(list, "lineIntoStockDetails");
    }

    /**
     * 新增产线入库明细
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产线入库明细
     */
    @RequiresPermissions("erp:lineIntoStockDetails:add")
    @Log(title = "产线入库明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LineIntoStockDetails lineIntoStockDetails) {
        return toAjax(lineIntoStockDetailsService.insertLineIntoStockDetails(lineIntoStockDetails));
    }

    /**
     * 修改产线入库明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        LineIntoStockDetails lineIntoStockDetails = lineIntoStockDetailsService.selectLineIntoStockDetailsById(id);
        mmap.put("lineIntoStockDetails", lineIntoStockDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存产线入库明细
     */
    @RequiresPermissions("erp:lineIntoStockDetails:edit")
    @Log(title = "产线入库明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LineIntoStockDetails lineIntoStockDetails) {
        return toAjax(lineIntoStockDetailsService.updateLineIntoStockDetails(lineIntoStockDetails));
    }

    /**
     * 删除产线入库明细
     */
    @RequiresPermissions("erp:lineIntoStockDetails:remove")
    @Log(title = "产线入库明细", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(lineIntoStockDetailsService.deleteLineIntoStockDetailsByIds(ids));
    }
    /**
     * 跳转查看半成品入库明细
     */
    @GetMapping("/partsInToStockDetail/{partId}")
    public String partsInToStockDetail(@PathVariable("partId") Integer partId, ModelMap mmap) {
        mmap.put("partId", partId);
        return prefix + "/lineIntoStockDetails";
    }

}
