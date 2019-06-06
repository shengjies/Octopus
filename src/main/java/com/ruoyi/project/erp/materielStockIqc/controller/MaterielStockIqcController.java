package com.ruoyi.project.erp.materielStockIqc.controller;

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
import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import com.ruoyi.project.erp.materielStockIqc.service.IMaterielStockIqcService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料库存IQC 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielStockIqc")
public class MaterielStockIqcController extends BaseController
{
    private String prefix = "erp/materielStockIqc";
	
	@Autowired
	private IMaterielStockIqcService materielStockIqcService;
	
	@RequiresPermissions("erp:materielStockIqc:view")
	@GetMapping()
	public String materielStockIqc()
	{
	    return prefix + "/materielStockIqc";
	}
	
	/**
	 * 查询物料库存IQC列表
	 */
	@RequiresPermissions("erp:materielStockIqc:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaterielStockIqc materielStockIqc)
	{
		startPage();
        List<MaterielStockIqc> list = materielStockIqcService.selectMaterielStockIqcList(materielStockIqc);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出物料库存IQC列表
	 */
	@RequiresPermissions("erp:materielStockIqc:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielStockIqc materielStockIqc)
    {
    	List<MaterielStockIqc> list = materielStockIqcService.selectMaterielStockIqcList(materielStockIqc);
        ExcelUtil<MaterielStockIqc> util = new ExcelUtil<MaterielStockIqc>(MaterielStockIqc.class);
        return util.exportExcel(list, "materielStockIqc");
    }
	
	/**
	 * 新增物料库存IQC
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存物料库存IQC
	 */
	@RequiresPermissions("erp:materielStockIqc:add")
	@Log(title = "物料库存IQC", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaterielStockIqc materielStockIqc)
	{		
		return toAjax(materielStockIqcService.insertMaterielStockIqc(materielStockIqc));
	}

	/**
	 * 修改物料库存IQC
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaterielStockIqc materielStockIqc = materielStockIqcService.selectMaterielStockIqcById(id);
		mmap.put("materielStockIqc", materielStockIqc);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存物料库存IQC
	 */
	@RequiresPermissions("erp:materielStockIqc:edit")
	@Log(title = "物料库存IQC", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaterielStockIqc materielStockIqc)
	{		
		return toAjax(materielStockIqcService.updateMaterielStockIqc(materielStockIqc));
	}
	
	/**
	 * 删除物料库存IQC
	 */
	@RequiresPermissions("erp:materielStockIqc:remove")
	@Log(title = "物料库存IQC", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(materielStockIqcService.deleteMaterielStockIqcByIds(ids));
	}

	/**
	 * 物料iqc状态改变
	 * @param stockIqc
	 * @return
	 */
	@RequiresPermissions("erp:materielIntoStock:updateStatus")
	@PostMapping("/updateStatus")
	@ResponseBody
	public AjaxResult updateStatus(Integer stockIqc){
		return toAjax(materielStockIqcService.updateMaterielIQCStatus(stockIqc));
	}
	
}
