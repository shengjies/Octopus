package com.ruoyi.project.page.pageInfoConfig.controller;

import java.util.List;

import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import com.ruoyi.project.page.pageInfoConfig.service.IPageInfoConfigService;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 页面配置 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-13
 */
@Controller
@RequestMapping("/production/pageInfoConfig")
public class PageInfoConfigController extends BaseController
{
    private String prefix = "production/pageInfoConfig";
	
	@Autowired
	private IPageInfoConfigService pageInfoConfigService;
	
	@RequiresPermissions("production:pageInfoConfig:view")
	@GetMapping()
	public String pageInfoConfig()
	{
	    return prefix + "/pageInfoConfig";
	}
	
	/**
	 * 查询页面配置列表
	 */
	@RequiresPermissions("production:pageInfoConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PageInfoConfig pageInfoConfig)
	{
		startPage();
        List<PageInfoConfig> list = pageInfoConfigService.selectPageInfoConfigList(pageInfoConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出页面配置列表
	 */
	@RequiresPermissions("production:pageInfoConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PageInfoConfig pageInfoConfig)
    {
    	List<PageInfoConfig> list = pageInfoConfigService.selectPageInfoConfigList(pageInfoConfig);
        ExcelUtil<PageInfoConfig> util = new ExcelUtil<PageInfoConfig>(PageInfoConfig.class);
        return util.exportExcel(list, "pageInfoConfig");
    }
	
	/**
	 * 新增页面配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存页面配置
	 */
	@RequiresPermissions("production:pageInfoConfig:add")
	@Log(title = "页面配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PageInfoConfig pageInfoConfig)
	{		
		return toAjax(pageInfoConfigService.insertPageInfoConfig(pageInfoConfig));
	}

	/**
	 * 修改页面配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PageInfoConfig pageInfoConfig = pageInfoConfigService.selectPageInfoConfigById(id);
		mmap.put("pageInfoConfig", pageInfoConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存页面配置
	 */
	@RequiresPermissions("production:pageInfoConfig:edit")
	@Log(title = "页面配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PageInfoConfig pageInfoConfig)
	{		
		return toAjax(pageInfoConfigService.updatePageInfoConfig(pageInfoConfig));
	}
	
	/**
	 * 删除页面配置
	 */
	@RequiresPermissions("production:pageInfoConfig:remove")
	@Log(title = "页面配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pageInfoConfigService.deletePageInfoConfigByIds(ids));
	}
	
}
