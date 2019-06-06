package com.ruoyi.project.erp.materielFeed.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.service.IDevCompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.materielFeed.domain.MaterielFeed;
import com.ruoyi.project.erp.materielFeed.service.IMaterielFeedService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料发料 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/erp/materielFeed")
public class MaterielFeedController extends BaseController
{
    private String prefix = "erp/materielFeed";
	
	@Autowired
	private IMaterielFeedService materielFeedService;

	@Autowired
	private IDevCompanyService companyService;
	
	@RequiresPermissions("erp:materielFeed:view")
	@GetMapping()
	public String materielFeed()
	{
	    return prefix + "/materielFeed";
	}
	
	/**
	 * 查询物料发料列表
	 */
	@RequiresPermissions("erp:materielFeed:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaterielFeed materielFeed)
	{
		startPage();
        List<MaterielFeed> list = materielFeedService.selectMaterielFeedList(materielFeed);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出物料发料列表
	 */
	@RequiresPermissions("erp:materielFeed:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielFeed materielFeed)
    {
    	List<MaterielFeed> list = materielFeedService.selectMaterielFeedList(materielFeed);
        ExcelUtil<MaterielFeed> util = new ExcelUtil<MaterielFeed>(MaterielFeed.class);
        return util.exportExcel(list, "materielFeed");
    }
	
	/**
	 * 新增物料发料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存物料发料
	 */
	@RequiresPermissions("erp:materielFeed:add")
	@Log(title = "物料发料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody MaterielFeed materielFeed)
	{
		try {
			return toAjax(materielFeedService.insertMaterielFeed(materielFeed));
		} catch (BusinessException e) {
			return  error(e.getMessage());
		}
	}

	/**
	 * 修改物料发料
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaterielFeed materielFeed = materielFeedService.selectMaterielFeedById(id);
		mmap.put("materielFeed", materielFeed);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存物料发料
	 */
	@RequiresPermissions("erp:materielFeed:edit")
	@Log(title = "物料发料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaterielFeed materielFeed)
	{		
		return toAjax(materielFeedService.updateMaterielFeed(materielFeed));
	}
	
	/**
	 * 删除物料发料
	 */
	@RequiresPermissions("erp:materielFeed:remove")
	@Log(title = "物料发料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(materielFeedService.deleteMaterielFeedByIds(ids));
	}

	/**
	 * 生产发料添加
	 * @param feedType 发料类型
	 * @return 结果
	 */
	@PostMapping("/addDetail")
	@ResponseBody
	public AjaxResult addDetail(String feedType){
		Map map = materielFeedService.selectAllMaterielOrParts(feedType);
		return AjaxResult.success("success",map);
	}

	/**
	 * 作废生产发料单
	 */
	@RequiresPermissions("erp:materielFeed:remove")
	@Log(title = "物料发料", businessType = BusinessType.DELETE)
	@PostMapping( "/nullify")
	@ResponseBody
	public AjaxResult nullify(Integer id)
	{
		return toAjax(materielFeedService.nullifyMaterielFeedById(id));
	}

	/**
	 * 查看生产发料明细
	 */
	@RequiresPermissions("erp:materielFeed:list")
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Integer id, ModelMap mmap)
	{
		mmap.put("company",companyService.selectDevCompanyById(ShiroUtils.getCompanyId()));
		MaterielFeed materielFeed = materielFeedService.selectMaterielFeedById(id);
		mmap.put("materielFeed", materielFeed);
		return prefix + "/details";
	}
}
