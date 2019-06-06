package com.ruoyi.project.production.productionLine.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.service.IProductionLineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产线 信息操作处理
 *
 * @author ruoyi
 * @date 2019-04-11
 */
@Controller
@RequestMapping("/production/productionLine")
public class ProductionLineController extends BaseController {
    private String prefix = "production/productionLine";

    @Autowired
    private IProductionLineService productionLineService;

    @RequiresPermissions("production:productionLine:view")
    @GetMapping()
    public String productionLine() {
        return prefix + "/productionLine";
    }

    /**
     * 查询生产线列表
     */
    @RequiresPermissions("production:productionLine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductionLine productionLine) {
        startPage();
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine);
        return getDataTable(list);
    }


    /**
     * 导出生产线列表
     */
    @RequiresPermissions("production:productionLine:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductionLine productionLine) {
        List<ProductionLine> list = productionLineService.selectProductionLineList(productionLine);
        ExcelUtil<ProductionLine> util = new ExcelUtil<ProductionLine>(ProductionLine.class);
        return util.exportExcel(list, "productionLine");
    }

    /**
     * 新增生产线
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存生产线
     */
    @RequiresPermissions("production:productionLine:add")
    @Log(title = "生产线", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductionLine productionLine) {
        return toAjax(productionLineService.insertProductionLine(productionLine));
    }

    /**
     * 修改生产线
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        mmap.put("productionLine", productionLine);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产线
     */
    @RequiresPermissions("production:productionLine:edit")
    @Log(title = "生产线", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductionLine productionLine) {
        try {
            return toAjax(productionLineService.updateProductionLine(productionLine));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 删除生产线
     */
    @RequiresPermissions("production:productionLine:remove")
    @Log(title = "生产线", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(productionLineService.deleteProductionLineByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 产线配置硬件
     *
     * @return
     */
    @RequiresPermissions("production:productionLine:devconfig")
    @GetMapping("/devcnfig/{id}")
    public String devConfig(@PathVariable("id") int id, ModelMap mmap) {
        mmap.put("line", productionLineService.selectProductionLineById(id));
        mmap.put("config", productionLineService.selectLineDev(id));
        return prefix + "/devconfig";
    }

    /**
     * 保存相关产线IO口的配置
     *
     * @param line
     * @return
     */
    @ResponseBody
    @RequestMapping("/save/config")
    @RequiresPermissions("production:productionLine:devconfig")
    public AjaxResult saveDevConfig(@RequestBody ProductionLine line) {
        try {
            return toAjax(productionLineService.updateLineConfigClear(line));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 通过生产线查询责任人信息
     *
     * @param lineId
     * @return
     */
    @PostMapping("/findDeviceLiableByLineId")
    @ResponseBody
    public AjaxResult findDeviceLiableByLineId(Integer lineId) {
        Map map = productionLineService.findDeviceLiableByLineId(lineId);
        return AjaxResult.success("success", map);
    }

    /**
     * 自定义数据
     *
     * @return
     */
    @GetMapping("/custom/{id}")
    @RequiresPermissions("production:productionLine:custom")
    public String custom(@PathVariable("id") int id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        if (StringUtils.isNotNull(productionLine) && !StringUtils.isEmpty(productionLine.getParamConfig())) {
            productionLine.setParamArray(JSON.parseArray(productionLine.getParamConfig(), String.class));
        }
        mmap.put("line", productionLine);
        return prefix + "/customParam";
    }

    /**
     * 产线实况
     *
     * @param id   产线编号
     * @param mmap
     * @return
     */
    @GetMapping("/live/{id}")
    @RequiresPermissions("production:productionLine:live")
    public String lineLive(@PathVariable("id") int id, ModelMap mmap) {
        ProductionLine productionLine = productionLineService.selectProductionLineById(id);
        if (StringUtils.isNotNull(productionLine) && !StringUtils.isEmpty(productionLine.getParamConfig())) {
            productionLine.setParamArray(JSON.parseArray(productionLine.getParamConfig(), String.class));
        }
        mmap.put("line", productionLine);
        return prefix + "/lineLive";
    }
}
