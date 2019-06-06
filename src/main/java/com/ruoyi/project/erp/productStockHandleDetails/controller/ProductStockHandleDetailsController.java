package com.ruoyi.project.erp.productStockHandleDetails.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
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
import com.ruoyi.project.erp.productStockHandleDetails.domain.ProductStockHandleDetails;
import com.ruoyi.project.erp.productStockHandleDetails.service.IProductStockHandleDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品库存内部调整清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/productStockHandleDetails")
public class ProductStockHandleDetailsController extends BaseController
{
    private String prefix = "erp/productStockHandleDetails";
	
	@Autowired
	private IProductStockHandleDetailsService productStockHandleDetailsService;
	
	@RequiresPermissions("erp:productStockHandleDetails:view")
	@GetMapping()
	public String productStockHandleDetails()
	{
	    return prefix + "/productStockHandleDetails";
	}
	
	/**
	 * 查询产品库存内部调整清单列表
	 */
	@RequiresPermissions("erp:productStockHandleDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductStockHandleDetails productStockHandleDetails)
	{
		startPage();
        List<ProductStockHandleDetails> list = productStockHandleDetailsService.selectProductStockHandleDetailsList(productStockHandleDetails);
		return getDataTable(list);
	}

	/**
	 * 通过产品id查询内部调整详情列表
	 * @param productId
	 * @return
	 */
	@RequiresPermissions("erp:productStockHandleDetails:list")
	@PostMapping("/listByProId")
	@ResponseBody
	public TableDataInfo listByProId(Integer productId)
	{
		ProductStockHandleDetails productStockHandleDetails = new ProductStockHandleDetails();
		productStockHandleDetails.setProductId(productId);
		startPage();
		List<ProductStockHandleDetails> list = productStockHandleDetailsService.selectProductStockHandleDetailsList(productStockHandleDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出产品库存内部调整清单列表
	 */
	@RequiresPermissions("erp:productStockHandleDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductStockHandleDetails productStockHandleDetails)
    {
    	List<ProductStockHandleDetails> list = productStockHandleDetailsService.selectProductStockHandleDetailsList(productStockHandleDetails);
        ExcelUtil<ProductStockHandleDetails> util = new ExcelUtil<ProductStockHandleDetails>(ProductStockHandleDetails.class);
        return util.exportExcel(list, "productStockHandleDetails");
    }
	
	/**
	 * 新增产品库存内部调整清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产品库存内部调整清单
	 */
	@RequiresPermissions("erp:productStockHandleDetails:add")
	@Log(title = "产品库存内部调整清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProductStockHandleDetails productStockHandleDetails)
	{
		try {
			return toAjax(productStockHandleDetailsService.insertProductStockHandleDetails(productStockHandleDetails));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}

	}

	/**
	 * 修改产品库存内部调整清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ProductStockHandleDetails productStockHandleDetails = productStockHandleDetailsService.selectProductStockHandleDetailsById(id);
		mmap.put("productStockHandleDetails", productStockHandleDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产品库存内部调整清单
	 */
	@RequiresPermissions("erp:productStockHandleDetails:edit")
	@Log(title = "产品库存内部调整清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProductStockHandleDetails productStockHandleDetails)
	{		
		return toAjax(productStockHandleDetailsService.updateProductStockHandleDetails(productStockHandleDetails));
	}
	
	/**
	 * 删除产品库存内部调整清单
	 */
	@RequiresPermissions("erp:productStockHandleDetails:remove")
	@Log(title = "产品库存内部调整清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productStockHandleDetailsService.deleteProductStockHandleDetailsByIds(ids));
	}

	/**
	 * 跳转产品内部调整详情
	 */
	@GetMapping("/productHandleDetail/{productId}")
	public String productHandleDetail(@PathVariable("productId") Integer productId, ModelMap mmap) {
		mmap.put("productId", productId);
		return prefix + "/productStockHandleDetails";
	}

	/**
	 * 清理产品报废品
	 * @param id 产品库存id
	 * @return 结果
	 */
	@RequiresPermissions("erp:productStockHandleDetails:handleScrapFlag")
	@RequestMapping("/handleScrap")
	@ResponseBody
	public AjaxResult handleScrap(int id){
		return toAjax(productStockHandleDetailsService.handleScrap(id));
	}
}
