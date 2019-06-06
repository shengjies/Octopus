package com.ruoyi.project.erp.contractContent.controller;

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
import com.ruoyi.project.erp.contractContent.domain.ContractContent;
import com.ruoyi.project.erp.contractContent.service.IContractContentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 合同内容 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-10
 */
@Controller
@RequestMapping("/erp/contractContent")
public class ContractContentController extends BaseController
{
    private String prefix = "erp/contractContent";
	
	@Autowired
	private IContractContentService contractContentService;
	
	@RequiresPermissions("erp:contractContent:view")
	@GetMapping()
	public String contractContent()
	{
	    return prefix + "/contractContent";
	}
	
	/**
	 * 查询合同内容列表
	 */
	@RequiresPermissions("erp:contractContent:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ContractContent contractContent)
	{
		startPage();
        List<ContractContent> list = contractContentService.selectContractContentList(contractContent);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出合同内容列表
	 */
	@RequiresPermissions("erp:contractContent:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ContractContent contractContent)
    {
    	List<ContractContent> list = contractContentService.selectContractContentList(contractContent);
        ExcelUtil<ContractContent> util = new ExcelUtil<ContractContent>(ContractContent.class);
        return util.exportExcel(list, "contractContent");
    }
	
	/**
	 * 新增合同内容
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存合同内容
	 */
	@RequiresPermissions("erp:contractContent:add")
	@Log(title = "合同内容", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ContractContent contractContent)
	{		
		return toAjax(contractContentService.insertContractContent(contractContent));
	}

	/**
	 * 修改合同内容
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ContractContent contractContent = contractContentService.selectContractContentById(id);
		mmap.put("contractContent", contractContent);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存合同内容
	 */
	@RequiresPermissions("erp:contractContent:edit")
	@Log(title = "合同内容", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ContractContent contractContent)
	{		
		return toAjax(contractContentService.updateContractContent(contractContent));
	}
	
	/**
	 * 删除合同内容
	 */
	@RequiresPermissions("erp:contractContent:remove")
	@Log(title = "合同内容", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(contractContentService.deleteContractContentByIds(ids));
	}
	
}
