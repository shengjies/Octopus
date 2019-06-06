package com.ruoyi.project.erp.materielIntoStock.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import com.ruoyi.project.erp.materielStockIqc.service.IMaterielStockIqcService;
import com.ruoyi.project.erp.materielStockIqc.service.MaterielStockIqcServiceImpl;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.materielIntoStock.domain.MaterielIntoStock;
import com.ruoyi.project.erp.materielIntoStock.service.IMaterielIntoStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 物料入库 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielIntoStock")
public class MaterielIntoStockController extends BaseController {
    private String prefix = "erp/materielIntoStock";

    @Autowired
    private IMaterielIntoStockService materielIntoStockService;

    @Autowired
    private IMaterielStockIqcService materielStockIqcService; // 物料iqc服务层

    @Autowired
    private IDevCompanyService companyService;

    @RequiresPermissions("erp:materielIntoStock:view")
    @GetMapping()
    public String materielIntoStock(ModelMap mmap, HttpServletRequest request) {
        // 查询iqc状态信息
        MaterielStockIqc materielStockIqc = materielStockIqcService.selectMaterielStockIqcByComId(JwtUtil.getTokenUser(request).getCompanyId());
        mmap.put("materielStockIqc",materielStockIqc);
        return prefix + "/materielIntoStock";
    }

    /**
     * 查询物料入库列表
     */
    @RequiresPermissions("erp:materielIntoStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielIntoStock materielIntoStock,HttpServletRequest request) {
        startPage();
        List<MaterielIntoStock> list = materielIntoStockService.selectMaterielIntoStockList(materielIntoStock,request);
        return getDataTable(list);
    }


    /**
     * 导出物料入库列表
     */
    @RequiresPermissions("erp:materielIntoStock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielIntoStock materielIntoStock,HttpServletRequest request) {
        List<MaterielIntoStock> list = materielIntoStockService.selectMaterielIntoStockList(materielIntoStock,request);
        ExcelUtil<MaterielIntoStock> util = new ExcelUtil<MaterielIntoStock>(MaterielIntoStock.class);
        return util.exportExcel(list, "materielIntoStock");
    }

    /**
     * 新增物料入库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料入库
     */
    @RequiresPermissions("erp:materielIntoStock:add")
    @Log(title = "物料入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody MaterielIntoStock materielIntoStock,HttpServletRequest request) {
        try {
            return toAjax(materielIntoStockService.insertMaterielIntoStock(materielIntoStock,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改物料入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielIntoStock materielIntoStock = materielIntoStockService.selectMaterielIntoStockById(id);
        mmap.put("materielIntoStock", materielIntoStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料入库
     */
    @RequiresPermissions("erp:materielIntoStock:edit")
    @Log(title = "物料入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielIntoStock materielIntoStock) {
        return toAjax(materielIntoStockService.updateMaterielIntoStock(materielIntoStock));
    }

    /**
     * 删除物料入库
     */
    @RequiresPermissions("erp:materielIntoStock:remove")
    @Log(title = "物料入库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielIntoStockService.deleteMaterielIntoStockByIds(ids));
    }

    /**
     * 查看物料详情
     */
    @GetMapping("/details/{id}")
    @RequiresPermissions("erp:materielIntoStock:list")
    public String details(@PathVariable("id") Integer id, ModelMap mmap,HttpServletRequest request) {
        MaterielIntoStock materielIntoStock = materielIntoStockService.selectMaterielIntoStockById(id);
        mmap.put("company",companyService.selectDevCompanyById(JwtUtil.getTokenUser(request).getCompanyId()));
        mmap.put("materielIntoStock", materielIntoStock);
        return prefix + "/details";
    }

    /**
     * 作废物料入库单
     */
    @RequiresPermissions("erp:materielIntoStock:remove")
    @Log(title = "物料入库", businessType = BusinessType.DELETE)
    @PostMapping("/nullify")
    @ResponseBody
    public AjaxResult nullify(Integer id,HttpServletRequest request) {
        return toAjax(materielIntoStockService.nullifyMaterielIntoStockByIds(id,request));
    }
}
