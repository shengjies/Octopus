package com.ruoyi.project.erp.materiel.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.service.IMaterielSupplierService;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.system.user.domain.User;
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
import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.materiel.service.IMaterielService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materiel")
public class MaterielController extends BaseController {
    private String prefix = "erp/materiel";

    @Autowired
    private IMaterielService materielService;

    @Autowired
    private IMaterielSupplierService materielSupplierService; // 物料供应商管理服务

    @RequiresPermissions("erp:materiel:view")
    @GetMapping()
    public String materiel() {
        return prefix + "/materiel";
    }

    /**
     * 查询物料列表
     */
    @RequiresPermissions("erp:materiel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Materiel materiel) {
        startPage();
        List<Materiel> list = materielService.selectMaterielList(materiel);
        return getDataTable(list);
    }

    /**
     * 导出模板
     *
     * @return
     */
    @RequiresPermissions("erp:materiel:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Materiel> materielListExcelUtil = new ExcelUtil<>(Materiel.class);
        return materielListExcelUtil.importTemplateExcel("物料数据");
    }

    /**
     * 导入物料列表
     *
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "物料导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("erp:materiel:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Materiel> util = new ExcelUtil<Materiel>(Materiel.class);
        List<Materiel> materielList = util.importExcel(file.getInputStream());
        String message = null;
        try {
            message = materielService.importMateriel(materielList, updateSupport);
        } catch (BusinessException e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(message);
    }

    /**
     * 导出物料列表
     */
    @RequiresPermissions("erp:materiel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Materiel materiel) {
        List<Materiel> list = materielService.selectMaterielList(materiel);
        ExcelUtil<Materiel> util = new ExcelUtil<Materiel>(Materiel.class);
        return util.exportExcel(list, "materiel");
    }

    /**
     * 新增物料
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料
     */
    @RequiresPermissions("erp:materiel:add")
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Materiel materiel) {
        return toAjax(materielService.insertMateriel(materiel));
    }

    /**
     * 修改物料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Materiel materiel = materielService.selectMaterielById(id);
        mmap.put("materiel", materiel);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料
     */
    @RequiresPermissions("erp:materiel:edit")
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Materiel materiel) {
        return toAjax(materielService.updateMateriel(materiel));
    }

    /**
     * 删除物料
     */
    @RequiresPermissions("erp:materiel:remove")
    @Log(title = "物料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(materielService.deleteMaterielByIds(ids));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 通过供应商id查询和供应商关联的物料列表
     *
     * @param supplierId
     * @return
     */
    @RequestMapping("/materielListBySupplierId")
    @ResponseBody
    public AjaxResult materielListBySupplierId(Integer supplierId) {
        List<Materiel> materielList = materielService.materielListBySupplierId(supplierId);
        return AjaxResult.success("success", materielList);
    }

    /**
     * 通过物料id查询物料相关信息
     *
     * @param supplierId
     * @param materielId 物料id
     * @return 结果
     */
    @RequestMapping("/materielListByMaterielId")
    @ResponseBody
    public AjaxResult materielListByMaterielId(Integer supplierId, Integer materielId) {
        Materiel materiel= materielService.materielListByMaterielId(supplierId,materielId);
        return AjaxResult.success("success", materiel);
    }

    /**
     * 根据供应商id查询对应物料信息
     * @param sid 供应商id
     * @return 物料信息
     */
    @ResponseBody
    @RequestMapping("/selectMaterielBySupplierId")
    public AjaxResult selectMaterielBySupplierId(int sid){
        return AjaxResult.success("success",materielService.selectMaterielBySupplierId(sid));
    }

    @PostMapping("/checkMaterielCodeUnique")
    @ResponseBody
    public String checkMaterielCodeUnique(Materiel materiel){
        return materielService.checkMaterielCodeUnique(materiel);
    }
}
