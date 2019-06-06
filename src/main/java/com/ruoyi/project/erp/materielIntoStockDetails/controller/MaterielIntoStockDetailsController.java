package com.ruoyi.project.erp.materielIntoStockDetails.controller;

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
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import com.ruoyi.project.erp.materielIntoStockDetails.service.IMaterielIntoStockDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料入库清单 信息操作处理
 *
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielIntoStockDetails")
public class MaterielIntoStockDetailsController extends BaseController {
    private String prefix = "erp/materielIntoStockDetails";

    @Autowired
    private IMaterielIntoStockDetailsService materielIntoStockDetailsService;

    @RequiresPermissions("erp:materielIntoStockDetails:view")
    @GetMapping()
    public String materielIntoStockDetails() {
        return prefix + "/materielIntoStockDetails";
    }

    /**
     * 查询物料入库清单列表
     */
    @RequiresPermissions("erp:materielIntoStockDetails:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterielIntoStockDetails materielIntoStockDetails) {
        startPage();
        List<MaterielIntoStockDetails> list = materielIntoStockDetailsService.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        return getDataTable(list);
    }

    /**
     * 通过物料入库单号
     */
    @PostMapping("/listById")
    @ResponseBody
    public TableDataInfo listById(Integer inToId, Integer materielId) {
        MaterielIntoStockDetails materielIntoStockDetails = new MaterielIntoStockDetails();
        materielIntoStockDetails.setIntoId(inToId);
        materielIntoStockDetails.setMaterielId(materielId);
        startPage();
        List<MaterielIntoStockDetails> list = materielIntoStockDetailsService.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        return getDataTable(list);
    }


    /**
     * 导出物料入库清单列表
     */
    @RequiresPermissions("erp:materielIntoStockDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielIntoStockDetails materielIntoStockDetails) {
        List<MaterielIntoStockDetails> list = materielIntoStockDetailsService.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        ExcelUtil<MaterielIntoStockDetails> util = new ExcelUtil<MaterielIntoStockDetails>(MaterielIntoStockDetails.class);
        return util.exportExcel(list, "materielIntoStockDetails");
    }

    /**
     * 新增物料入库清单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物料入库清单
     */
    @RequiresPermissions("erp:materielIntoStockDetails:add")
    @Log(title = "物料入库清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterielIntoStockDetails materielIntoStockDetails) {
        return toAjax(materielIntoStockDetailsService.insertMaterielIntoStockDetails(materielIntoStockDetails));
    }

    /**
     * 修改物料入库清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MaterielIntoStockDetails materielIntoStockDetails = materielIntoStockDetailsService.selectMaterielIntoStockDetailsById(id);
        mmap.put("materielIntoStockDetails", materielIntoStockDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料入库清单
     */
    @RequiresPermissions("erp:materielIntoStockDetails:edit")
    @Log(title = "物料入库清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterielIntoStockDetails materielIntoStockDetails) {
        return toAjax(materielIntoStockDetailsService.updateMaterielIntoStockDetails(materielIntoStockDetails));
    }

    /**
     * 删除物料入库清单
     */
    @RequiresPermissions("erp:materielIntoStockDetails:remove")
    @Log(title = "物料入库清单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(materielIntoStockDetailsService.deleteMaterielIntoStockDetailsByIds(ids));
    }

    /**
     * 跳转物料入库清单页面
     *
     * @param id   物料入库单主键id
     * @param mmap
     * @return
     */
    @GetMapping("/inToStockDetail/{id}")
    public String inToStockDetail(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("inToId", id);
        return prefix + "/materielIntoStockDetails";
    }

    /**
     * 跳转物料入库清单页面
     *
     * @param id   物料主键id
     * @param mmap 视图
     * @return 结果
     */
    @RequiresPermissions("erp:materielIntoStockDetails:list")
    @GetMapping("/inToStockDetailByMaterielId/{id}")
    public String inToStockDetailByMaterielId(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("materielId", id);
        return prefix + "/materielIntoStockDetails";
    }
}
