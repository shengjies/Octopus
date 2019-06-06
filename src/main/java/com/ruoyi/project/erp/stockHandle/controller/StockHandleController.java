package com.ruoyi.project.erp.stockHandle.controller;

import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import com.ruoyi.project.erp.materielStock.service.IMaterielStockService;
import com.ruoyi.project.erp.partsStock.service.IPartsStockService;
import com.ruoyi.project.erp.productStock.service.IProductStockService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.stockHandle.domain.StockHandle;
import com.ruoyi.project.erp.stockHandle.service.IStockHandleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 库存内部处理主 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-27
 */
@Controller
@RequestMapping("/erp/stockHandle")
public class StockHandleController extends BaseController
{
    private String prefix = "erp/stockHandle";
	
	@Autowired
	private IStockHandleService stockHandleService;

	@Autowired
	private IDevCompanyService companyService;

	@Autowired
	private IProductStockService productStockService;

	@Autowired
	private IMaterielStockService materielStockService;

	@Autowired
	private IPartsStockService partsStockService;
	
	@RequiresPermissions("erp:stockHandle:list")
	@GetMapping("/handleStock")
	public String stockHandle(String handleType, ModelMap mmap)
	{
		mmap.put("handleType",handleType);
	    return prefix + "/stockHandle";
	}
	
	/**
	 * 查询库存内部处理主列表
	 */
	@RequiresPermissions("erp:stockHandle:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StockHandle stockHandle)
	{
		startPage();
        List<StockHandle> list = stockHandleService.selectStockHandleList(stockHandle);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出库存内部处理主列表
	 */
	@RequiresPermissions("erp:stockHandle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StockHandle stockHandle)
    {
    	List<StockHandle> list = stockHandleService.selectStockHandleList(stockHandle);
        ExcelUtil<StockHandle> util = new ExcelUtil<StockHandle>(StockHandle.class);
        return util.exportExcel(list, "stockHandle");
    }
	
	/**
	 * 新增库存内部处理
	 */
	@GetMapping("/add")
	public String add(String handleType,Integer attrId,ModelMap mmap)
	{
		mmap.put("handleType",handleType);
		if ( attrId != null ) {
		    if (StockConstants.DETAILS_TYPE_PRODUCT.equals(Integer.parseInt(handleType))) { // 产品类型
		        mmap.put("productStock",productStockService.selectProductStockByProId(attrId));
		    } else if (StockConstants.DETAILS_TYPE_MATERIEL.equals(Integer.parseInt(handleType))) { // 物料类型
		        mmap.put("materielStock",materielStockService.selectMaterielStockByMatId(attrId));
		    } else {
		    	mmap.put("partsStock",partsStockService.selectPartsStockByParId(attrId));
			}
		}
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存库存内部处理
	 */
	@RequiresPermissions("erp:stockHandle:add")
	@Log(title = "库存内部处理主", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody StockHandle stockHandle)
	{		
		return toAjax(stockHandleService.insertStockHandle(stockHandle));
	}

	/**
	 * 修改库存内部处理主
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		StockHandle stockHandle = stockHandleService.selectStockHandleById(id);
		mmap.put("stockHandle", stockHandle);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存库存内部处理主
	 */
	@RequiresPermissions("erp:stockHandle:edit")
	@Log(title = "库存内部处理主", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StockHandle stockHandle)
	{		
		return toAjax(stockHandleService.updateStockHandle(stockHandle));
	}
	
	/**
	 * 删除库存内部处理主
	 */
	@RequiresPermissions("erp:stockHandle:remove")
	@Log(title = "库存内部处理主", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(stockHandleService.deleteStockHandleByIds(ids));
	}

	/**
	 * 查看库存内部管理明细
	 */
	@RequiresPermissions("erp:stockHandle:list")
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Integer id, ModelMap mmap)
	{
		StockHandle stockHandle = stockHandleService.selectStockHandleById(id);
		mmap.put("stockHandle", stockHandle);
		mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
		return prefix + "/details";
	}

	/**
	 * 作废库存处理单
	 */
	@RequiresPermissions("erp:stockHandle:remove")
	@PostMapping( "/nullify")
	@ResponseBody
	public AjaxResult nullify(Integer id)
	{
		return toAjax(stockHandleService.nullifyStockHandleById(id));
	}
	
}
