package com.ruoyi.project.erp.stockHandleDetails.controller;

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
import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import com.ruoyi.project.erp.stockHandleDetails.service.IStockHandleDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 内部调整明细 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-27
 */
@Controller
@RequestMapping("/erp/stockHandleDetails")
public class StockHandleDetailsController extends BaseController
{
    private String prefix = "erp/stockHandleDetails";
	
	@Autowired
	private IStockHandleDetailsService stockHandleDetailsService;
	
	@RequiresPermissions("erp:stockHandleDetails:list")
	@GetMapping("/stockHandleDetails")
	public String stockHandleDetails(String handleType,Integer attrId,ModelMap mmap)
	{
		mmap.put("handleType",handleType);
		mmap.put("attrId",attrId);
	    return prefix + "/stockHandleDetails";
	}
	
	/**
	 * 查询内部调整明细列表
	 */
	@RequiresPermissions("erp:stockHandleDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StockHandleDetails stockHandleDetails)
	{
		startPage();
        List<StockHandleDetails> list = stockHandleDetailsService.selectStockHandleDetailsList(stockHandleDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出内部调整明细列表
	 */
	@RequiresPermissions("erp:stockHandleDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StockHandleDetails stockHandleDetails)
    {
    	List<StockHandleDetails> list = stockHandleDetailsService.selectStockHandleDetailsList(stockHandleDetails);
        ExcelUtil<StockHandleDetails> util = new ExcelUtil<StockHandleDetails>(StockHandleDetails.class);
        return util.exportExcel(list, "stockHandleDetails");
    }
	
	/**
	 * 新增内部调整明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存内部调整明细
	 */
	@RequiresPermissions("erp:stockHandleDetails:add")
	@Log(title = "内部调整明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(StockHandleDetails stockHandleDetails)
	{		
		return toAjax(stockHandleDetailsService.insertStockHandleDetails(stockHandleDetails));
	}

	/**
	 * 修改内部调整明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		StockHandleDetails stockHandleDetails = stockHandleDetailsService.selectStockHandleDetailsById(id);
		mmap.put("stockHandleDetails", stockHandleDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存内部调整明细
	 */
	@RequiresPermissions("erp:stockHandleDetails:edit")
	@Log(title = "内部调整明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StockHandleDetails stockHandleDetails)
	{		
		return toAjax(stockHandleDetailsService.updateStockHandleDetails(stockHandleDetails));
	}
	
	/**
	 * 删除内部调整明细
	 */
	@RequiresPermissions("erp:stockHandleDetails:remove")
	@Log(title = "内部调整明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(stockHandleDetailsService.deleteStockHandleDetailsByIds(ids));
	}
	
}
