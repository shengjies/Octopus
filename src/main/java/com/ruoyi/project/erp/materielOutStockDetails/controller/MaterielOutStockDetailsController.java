package com.ruoyi.project.erp.materielOutStockDetails.controller;

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
import com.ruoyi.project.erp.materielOutStockDetails.domain.MaterielOutStockDetails;
import com.ruoyi.project.erp.materielOutStockDetails.service.IMaterielOutStockDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料出库清单 信息操作处理
 *
 * @author zqm
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/erp/materielOutStockDetails")
public class MaterielOutStockDetailsController extends BaseController {
    private String prefix = "erp/materielOutStockDetails";

    @Autowired
    private IMaterielOutStockDetailsService materielOutStockDetailsService;

    @RequiresPermissions("erp:materielOutStockDetails:view")
    @GetMapping()
    public String materielOutStockDetails() {
        return prefix + "/materielOutStockDetails";
    }

    /**
     * 查询物料出库清单列表
     */
    @RequiresPermissions("erp:materielOutStockDetails:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielOutStockDetails materielOutStockDetails) {
        startPage();
        List<MaterielOutStockDetails> list = materielOutStockDetailsService.selectMaterielOutStockDetailsList(materielOutStockDetails);
        return getDataTable(list);
    }

    /**
     * 通过物料id查询物料退货清单列表
     * @param materielId 物料id
     * @return 结果
     */
    @RequiresPermissions("erp:materielOutStockDetails:list")
    @PostMapping("/listByMatId")
    @ResponseBody
    public TableDataInfo listByMatId(Integer materielId) {
        MaterielOutStockDetails materielOutStockDetails = new MaterielOutStockDetails();
        materielOutStockDetails.setMaterielId(materielId);
        startPage();
        List<MaterielOutStockDetails> list = materielOutStockDetailsService.selectMaterielOutStockDetailsList(materielOutStockDetails);
        return getDataTable(list);
    }

    /**
     * 导出物料出库清单列表
     */
    @RequiresPermissions("erp:materielOutStockDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielOutStockDetails materielOutStockDetails) {
        List<MaterielOutStockDetails> list = materielOutStockDetailsService.selectMaterielOutStockDetailsList(materielOutStockDetails);
        ExcelUtil<MaterielOutStockDetails> util = new ExcelUtil<MaterielOutStockDetails>(MaterielOutStockDetails.class);
        return util.exportExcel(list, "materielOutStockDetails");
    }

    /**
     * 新增物料出库清单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料出库清单
     */
    @RequiresPermissions("erp:materielOutStockDetails:add")
    @Log(title = "物料出库清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterielOutStockDetails materielOutStockDetails) {
        return toAjax(materielOutStockDetailsService.insertMaterielOutStockDetails(materielOutStockDetails));
    }

    /**
     * 修改物料出库清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielOutStockDetails materielOutStockDetails = materielOutStockDetailsService.selectMaterielOutStockDetailsById(id);
        mmap.put("materielOutStockDetails", materielOutStockDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料出库清单
     */
    @RequiresPermissions("erp:materielOutStockDetails:edit")
    @Log(title = "物料出库清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielOutStockDetails materielOutStockDetails) {
        return toAjax(materielOutStockDetailsService.updateMaterielOutStockDetails(materielOutStockDetails));
    }

    /**
     * 删除物料出库清单
     */
    @RequiresPermissions("erp:materielOutStockDetails:remove")
    @Log(title = "物料出库清单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielOutStockDetailsService.deleteMaterielOutStockDetailsByIds(ids));
    }

    /**
     * 跳转到物料出库清单页面
     *
     * @param materielId 物料主键id
     * @param mmap       传递对象
     * @return 结果
     */
    @GetMapping("/outStockDetailByMaterielId/{materielId}")
    public String outStockDetailByMaterielId(@PathVariable("materielId") Integer materielId, ModelMap mmap) {
        mmap.put("materielId", materielId);
        return prefix + "/materielOutStockDetails";
    }

}
