package com.ruoyi.project.erp.materielFeedDetails.controller;

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
import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import com.ruoyi.project.erp.materielFeedDetails.service.IMaterielFeedDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料发料清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/erp/materielFeedDetails")
public class MaterielFeedDetailsController extends BaseController
{
    private String prefix = "erp/materielFeedDetails";
	
	@Autowired
	private IMaterielFeedDetailsService materielFeedDetailsService;
	
	@RequiresPermissions("erp:materielFeedDetails:view")
	@GetMapping()
	public String materielFeedDetails()
	{
	    return prefix + "/materielFeedDetails";
	}
	
	/**
	 * 查询物料发料清单列表
	 */
	@RequiresPermissions("erp:materielFeedDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaterielFeedDetails materielFeedDetails)
	{
		startPage();
        List<MaterielFeedDetails> list = materielFeedDetailsService.selectMaterielFeedDetailsList(materielFeedDetails);
		return getDataTable(list);
	}

	/**
	 * 通过发料类型查询对应物料查询发料清单
	 * @param feedType 发料类型
	 * @param feedDetailId 物料半成品id
	 * @return 结果
	 */
	@RequiresPermissions("erp:materielFeedDetails:list")
	@PostMapping("/listByMatId")
	@ResponseBody
	public TableDataInfo listByMatId(Integer feedType,Integer feedDetailId)
	{
		MaterielFeedDetails materielFeedDetails = new MaterielFeedDetails();
		materielFeedDetails.setFeedType(feedType);
		materielFeedDetails.setFeedDetailId(feedDetailId);
		startPage();
		List<MaterielFeedDetails> list = materielFeedDetailsService.selectMaterielFeedDetailsList(materielFeedDetails);
		return getDataTable(list);
	}


	/**
	 * 导出物料发料清单列表
	 */
	@RequiresPermissions("erp:materielFeedDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielFeedDetails materielFeedDetails)
    {
    	List<MaterielFeedDetails> list = materielFeedDetailsService.selectMaterielFeedDetailsList(materielFeedDetails);
        ExcelUtil<MaterielFeedDetails> util = new ExcelUtil<MaterielFeedDetails>(MaterielFeedDetails.class);
        return util.exportExcel(list, "materielFeedDetails");
    }
	
	/**
	 * 新增物料发料清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存物料发料清单
	 */
	@RequiresPermissions("erp:materielFeedDetails:add")
	@Log(title = "物料发料清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaterielFeedDetails materielFeedDetails)
	{		
		return toAjax(materielFeedDetailsService.insertMaterielFeedDetails(materielFeedDetails));
	}

	/**
	 * 修改物料发料清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaterielFeedDetails materielFeedDetails = materielFeedDetailsService.selectMaterielFeedDetailsById(id);
		mmap.put("materielFeedDetails", materielFeedDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存物料发料清单
	 */
	@RequiresPermissions("erp:materielFeedDetails:edit")
	@Log(title = "物料发料清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaterielFeedDetails materielFeedDetails)
	{		
		return toAjax(materielFeedDetailsService.updateMaterielFeedDetails(materielFeedDetails));
	}
	
	/**
	 * 删除物料发料清单
	 */
	@RequiresPermissions("erp:materielFeedDetails:remove")
	@Log(title = "物料发料清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(materielFeedDetailsService.deleteMaterielFeedDetailsByIds(ids));
	}

	/**
	 * 跳转查看半成品出库明细
	 */
	@GetMapping("/partsOutStockDetail/{partId}")
	public String partsOutStockDetail(@PathVariable("partId") Integer partId, ModelMap mmap) {
		mmap.put("partId", partId);
		return prefix + "/materielFeedDetails";
	}
}
