package com.ruoyi.project.erp.productLedger.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.ruoyi.project.erp.productLedger.domain.ProductLedger;
import com.ruoyi.project.erp.productLedger.service.IProductLedgerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 产品对账 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/erp/productLedger")
public class ProductLedgerController extends BaseController
{
    private String prefix = "erp/productLedger";
	
	@Autowired
	private IProductLedgerService productLedgerService;

	@Autowired
	private IDevCompanyService devCompanyService;
	
	@RequiresPermissions("erp:productLedger:view")
	@GetMapping()
	public String productLedger()
	{
	    return prefix + "/productLedger";
	}
	
	/**
	 * 查询产品对账列表
	 */
	@RequiresPermissions("erp:productLedger:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductLedger productLedger, HttpServletRequest request)
	{
		startPage();
        List<ProductLedger> list = productLedgerService.selectProductLedgerList(productLedger,request);
		return getDataTable(list);
	}

	
	/**
	 * 新增产品对账
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产品对账
	 */
	@RequiresPermissions("erp:productLedger:add")
	@Log(title = "产品对账", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProductLedger productLedger,HttpServletRequest request)
	{
		return toAjax(productLedgerService.insertProductLedger(productLedger,request));
	}

	/**
	 * 修改产品对账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap,HttpServletRequest request)
	{

		ProductLedger productLedger = productLedgerService.selectProductLedgerById(id);
		mmap.put("ledger", productLedger);
		DevCompany company = devCompanyService.selectDevCompanyById(JwtUtil.getTokenUser(request).getCompanyId());
		mmap.put("company", company);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产品对账
	 */
	@RequiresPermissions("erp:productLedger:edit")
	@Log(title = "产品对账", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProductLedger productLedger)
	{		
		return toAjax(productLedgerService.updateProductLedger(productLedger));
	}

	/**
	 * 作废
	 * @param productLedger
	 * @return
	 */
	@RequiresPermissions("erp:productLedger:edit")
	@Log(title = "产品对账", businessType = BusinessType.UPDATE)
	@PostMapping("/cancel")
	@ResponseBody
	public AjaxResult cancelLedger(ProductLedger productLedger,HttpServletRequest request){
		return toAjax(productLedgerService.cancelLedger(productLedger,request));
	}

	/**
	 * 删除产品对账
	 */
	@RequiresPermissions("erp:productLedger:remove")
	@Log(title = "产品对账", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productLedgerService.deleteProductLedgerByIds(ids));
	}

	/**
	 * 导出产品对账单
	 * @return 结果
	 */
	@RequestMapping("/uploadProLed")
	@ResponseBody
	public AjaxResult uploadProLed(Integer id){
		Workbook wb = productLedgerService.uploadProLed(id);
		String fileName = ExcelUtils.encodingFilename("产品对账单");
		return ExcelUtils.getAjaxResult(wb,fileName);
	}
	
}
