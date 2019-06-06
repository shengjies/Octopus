package com.ruoyi.project.erp.materielLedger.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.project.erp.materielLedger.domain.MaterielLedger;
import com.ruoyi.project.erp.materielLedger.service.IMaterielLedgerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * 物料对账 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-15
 */
@Controller
@RequestMapping("/erp/materielLedger")
public class MaterielLedgerController extends BaseController
{
    private String prefix = "erp/materielLedger";
	
	@Autowired
	private IMaterielLedgerService materielLedgerService;

	@Autowired
	private IDevCompanyService devCompanyService;
	
	@RequiresPermissions("erp:materielLedger:view")
	@GetMapping()
	public String materielLedger()
	{
	    return prefix + "/materielLedger";
	}
	
	/**
	 * 查询物料对账列表
	 */
	@RequiresPermissions("erp:materielLedger:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaterielLedger materielLedger)
	{
		startPage();
        List<MaterielLedger> list = materielLedgerService.selectMaterielLedgerList(materielLedger);
		return getDataTable(list);
	}

	
	/**
	 * 新增物料对账
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存物料对账
	 */
	@RequiresPermissions("erp:materielLedger:add")
	@Log(title = "物料对账", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaterielLedger materielLedger)
	{		
		return toAjax(materielLedgerService.insertMaterielLedger(materielLedger));
	}

	/**
	 * 修改物料对账
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaterielLedger materielLedger = materielLedgerService.selectMaterielLedgerById(id);
		mmap.put("ledger", materielLedger);
		DevCompany company = devCompanyService.selectDevCompanyById(ShiroUtils.getCompanyId());
		mmap.put("company", company);
	    return prefix + "/edit";
	}

	/**
	 * 新增保存物料对账
	 */
	@RequiresPermissions("erp:materielLedger:edit")
	@Log(title = "物料对账", businessType = BusinessType.UPDATE)
	@PostMapping("/cancel")
	@ResponseBody
	public AjaxResult cancel(MaterielLedger materielLedger){
		return toAjax(materielLedgerService.cancel(materielLedger));
	}

	/**
	 * 导出物料对账单
	 * @return 结果
	 */
	@RequestMapping("/uploadMatLed")
	@ResponseBody
	public AjaxResult uploadMatLed(Integer id){
		Workbook wb = materielLedgerService.uploadMatLed(id);
		String fileName = ExcelUtils.encodingFilename("物料对账单");
		return ExcelUtils.getAjaxResult(wb, fileName);
	}

}
