package com.ruoyi.project.erp.purchaseDetails.controller;

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
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.service.IPurchaseDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 采购清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-10
 */
@Controller
@RequestMapping("/erp/purchaseDetails")
public class PurchaseDetailsController extends BaseController
{
    private String prefix = "erp/purchaseDetails";
	
	@Autowired
	private IPurchaseDetailsService purchaseDetailsService;
	
	@RequiresPermissions("erp:purchaseDetails:view")
	@GetMapping()
	public String purchaseDetails()
	{
	    return prefix + "/purchaseDetails";
	}
	
	/**
	 * 查询采购清单列表
	 */
	@RequiresPermissions("erp:purchaseDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PurchaseDetails purchaseDetails)
	{
		startPage();
        List<PurchaseDetails> list = purchaseDetailsService.selectPurchaseDetailsList(purchaseDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出采购清单列表
	 */
	@RequiresPermissions("erp:purchaseDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseDetails purchaseDetails)
    {
    	List<PurchaseDetails> list = purchaseDetailsService.selectPurchaseDetailsList(purchaseDetails);
        ExcelUtil<PurchaseDetails> util = new ExcelUtil<PurchaseDetails>(PurchaseDetails.class);
        return util.exportExcel(list, "purchaseDetails");
    }
	
	/**
	 * 新增采购清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存采购清单
	 */
	@RequiresPermissions("erp:purchaseDetails:add")
	@Log(title = "采购清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PurchaseDetails purchaseDetails)
	{		
		return toAjax(purchaseDetailsService.insertPurchaseDetails(purchaseDetails));
	}

	/**
	 * 修改采购清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PurchaseDetails purchaseDetails = purchaseDetailsService.selectPurchaseDetailsById(id);
		mmap.put("purchaseDetails", purchaseDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存采购清单
	 */
	@RequiresPermissions("erp:purchaseDetails:edit")
	@Log(title = "采购清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PurchaseDetails purchaseDetails)
	{		
		return toAjax(purchaseDetailsService.updatePurchaseDetails(purchaseDetails));
	}
	
	/**
	 * 删除采购清单
	 */
	@RequiresPermissions("erp:purchaseDetails:remove")
	@Log(title = "采购清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(purchaseDetailsService.deletePurchaseDetailsByIds(ids));
	}

	/**
	 * 通过采购单id查询存在预收库存的采购单明细信息
	 * @param purchaseId 采购单id
	 * @return 结果
	 */
	@PostMapping("/detailsByPurId")
	@ResponseBody
	public AjaxResult detailsByPurId(Integer purchaseId){
		List<PurchaseDetails> purchaseDetailList = purchaseDetailsService.selectDetailsHavePreByPurId(purchaseId);
		return AjaxResult.success("success",purchaseDetailList);
	}
	
}
