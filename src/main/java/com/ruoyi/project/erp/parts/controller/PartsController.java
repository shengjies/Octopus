package com.ruoyi.project.erp.parts.controller;

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
import com.ruoyi.project.erp.parts.domain.Parts;
import com.ruoyi.project.erp.parts.service.IPartsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 半成品 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/parts")
public class PartsController extends BaseController
{
    private String prefix = "erp/parts";
	
	@Autowired
	private IPartsService partsService;
	
	@RequiresPermissions("erp:parts:view")
	@GetMapping()
	public String parts()
	{
	    return prefix + "/parts";
	}
	
	/**
	 * 查询半成品列表
	 */
	@RequiresPermissions("erp:parts:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Parts parts)
	{
		startPage();
        List<Parts> list = partsService.selectPartsList(parts);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出半成品列表
	 */
	@RequiresPermissions("erp:parts:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Parts parts)
    {
    	List<Parts> list = partsService.selectPartsList(parts);
        ExcelUtil<Parts> util = new ExcelUtil<Parts>(Parts.class);
        return util.exportExcel(list, "parts");
    }
	
	/**
	 * 新增半成品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存半成品
	 */
	@RequiresPermissions("erp:parts:add")
	@Log(title = "半成品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Parts parts)
	{		
		return toAjax(partsService.insertParts(parts));
	}

	/**
	 * 修改半成品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Parts parts = partsService.selectPartsById(id);
		mmap.put("parts", parts);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存半成品
	 */
	@RequiresPermissions("erp:parts:edit")
	@Log(title = "半成品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Parts parts)
	{		
		return toAjax(partsService.updateParts(parts));
	}
	
	/**
	 * 删除半成品
	 */
	@RequiresPermissions("erp:parts:remove")
	@Log(title = "半成品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partsService.deletePartsByIds(ids));
	}
	
}
