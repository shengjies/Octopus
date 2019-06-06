package com.ruoyi.project.erp.productIntoStockDetails.controller;

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
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import com.ruoyi.project.erp.productIntoStockDetails.service.IProductIntoStockDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品入库清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/erp/productIntoStockDetails")
public class ProductIntoStockDetailsController extends BaseController
{
    private String prefix = "erp/productIntoStockDetails";
	
	@Autowired
	private IProductIntoStockDetailsService productIntoStockDetailsService;
	
	@RequiresPermissions("erp:productIntoStockDetails:view")
	@GetMapping()
	public String productIntoStockDetails()
	{
	    return prefix + "/productIntoStockDetails";
	}
	
	/**
	 * 查询产品入库清单列表
	 */
	@RequiresPermissions("erp:productIntoStockDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductIntoStockDetails productIntoStockDetails)
	{
		startPage();
        List<ProductIntoStockDetails> list = productIntoStockDetailsService.selectProductIntoStockDetailsList(productIntoStockDetails);
		return getDataTable(list);
	}

	/**
	 * 通过产品id查询产品入库信息
	 * @param productId
	 * @return
	 */
	@RequiresPermissions("erp:productIntoStockDetails:list")
	@PostMapping("/listByProId")
	@ResponseBody
	public TableDataInfo listByProId(Integer productId)
	{
		ProductIntoStockDetails productIntoStockDetails = new ProductIntoStockDetails();
		productIntoStockDetails.setProductId(productId);
		startPage();
		List<ProductIntoStockDetails> list = productIntoStockDetailsService.selectProductIntoStockDetailsList(productIntoStockDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出产品入库清单列表
	 */
	@RequiresPermissions("erp:productIntoStockDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductIntoStockDetails productIntoStockDetails)
    {
    	List<ProductIntoStockDetails> list = productIntoStockDetailsService.selectProductIntoStockDetailsList(productIntoStockDetails);
        ExcelUtil<ProductIntoStockDetails> util = new ExcelUtil<ProductIntoStockDetails>(ProductIntoStockDetails.class);
        return util.exportExcel(list, "productIntoStockDetails");
    }
	
	/**
	 * 新增产品入库清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产品入库清单
	 */
	@RequiresPermissions("erp:productIntoStockDetails:add")
	@Log(title = "产品入库清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProductIntoStockDetails productIntoStockDetails)
	{		
		return toAjax(productIntoStockDetailsService.insertProductIntoStockDetails(productIntoStockDetails));
	}

	/**
	 * 修改产品入库清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ProductIntoStockDetails productIntoStockDetails = productIntoStockDetailsService.selectProductIntoStockDetailsById(id);
		mmap.put("productIntoStockDetails", productIntoStockDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产品入库清单
	 */
	@RequiresPermissions("erp:productIntoStockDetails:edit")
	@Log(title = "产品入库清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProductIntoStockDetails productIntoStockDetails)
	{		
		return toAjax(productIntoStockDetailsService.updateProductIntoStockDetails(productIntoStockDetails));
	}
	
	/**
	 * 删除产品入库清单
	 */
	@RequiresPermissions("erp:productIntoStockDetails:remove")
	@Log(title = "产品入库清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productIntoStockDetailsService.deleteProductIntoStockDetailsByIds(ids));
	}

	/**
	 * 跳转产品入库详情
	 */
	@GetMapping("/productInToStockDetail/{productId}")
	public String productInToStockDetail(@PathVariable("productId") Integer productId, ModelMap mmap) {
		mmap.put("productId", productId);
		return prefix + "/productIntoStockDetails";
	}
	
}
