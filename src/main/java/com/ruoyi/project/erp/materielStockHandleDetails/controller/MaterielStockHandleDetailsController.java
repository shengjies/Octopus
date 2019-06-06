package com.ruoyi.project.erp.materielStockHandleDetails.controller;

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
import com.ruoyi.project.erp.materielStockHandleDetails.domain.MaterielStockHandleDetails;
import com.ruoyi.project.erp.materielStockHandleDetails.service.IMaterielStockHandleDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料库存内部调整清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/materielStockHandleDetails")
public class MaterielStockHandleDetailsController extends BaseController
{
    private String prefix = "erp/materielStockHandleDetails";
	
	@Autowired
	private IMaterielStockHandleDetailsService materielStockHandleDetailsService;
	
	@RequiresPermissions("erp:materielStockHandleDetails:view")
	@GetMapping()
	public String materielStockHandleDetails()
	{
	    return prefix + "/materielStockHandleDetails";
	}
	
	/**
	 * 查询物料库存内部调整清单列表
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MaterielStockHandleDetails materielStockHandleDetails)
	{
		startPage();
        List<MaterielStockHandleDetails> list = materielStockHandleDetailsService.selectMaterielStockHandleDetailsList(materielStockHandleDetails);
		return getDataTable(list);
	}

	/**
	 * 通过物料id查询物料内部调整清单列表信息
	 * @param materielId 物料id
	 * @return 结果
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:list")
	@PostMapping("/listByMatId")
	@ResponseBody
	public TableDataInfo listByMatId(Integer materielId)
	{
		MaterielStockHandleDetails materielStockHandleDetails = new MaterielStockHandleDetails();
		materielStockHandleDetails.setMaterielId(materielId);
		startPage();
		List<MaterielStockHandleDetails> list = materielStockHandleDetailsService.selectMaterielStockHandleDetailsList(materielStockHandleDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出物料库存内部调整清单列表
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterielStockHandleDetails materielStockHandleDetails)
    {
    	List<MaterielStockHandleDetails> list = materielStockHandleDetailsService.selectMaterielStockHandleDetailsList(materielStockHandleDetails);
        ExcelUtil<MaterielStockHandleDetails> util = new ExcelUtil<MaterielStockHandleDetails>(MaterielStockHandleDetails.class);
        return util.exportExcel(list, "materielStockHandleDetails");
    }
	
	/**
	 * 新增物料库存内部调整清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存物料库存内部调整清单
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:add")
	@Log(title = "物料库存内部调整清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MaterielStockHandleDetails materielStockHandleDetails)
	{
		try {
			return toAjax(materielStockHandleDetailsService.insertMaterielStockHandleDetails(materielStockHandleDetails));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}

	}

	/**
	 * 修改物料库存内部调整清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MaterielStockHandleDetails materielStockHandleDetails = materielStockHandleDetailsService.selectMaterielStockHandleDetailsById(id);
		mmap.put("materielStockHandleDetails", materielStockHandleDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存物料库存内部调整清单
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:edit")
	@Log(title = "物料库存内部调整清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MaterielStockHandleDetails materielStockHandleDetails)
	{		
		return toAjax(materielStockHandleDetailsService.updateMaterielStockHandleDetails(materielStockHandleDetails));
	}
	
	/**
	 * 删除物料库存内部调整清单
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:remove")
	@Log(title = "物料库存内部调整清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(materielStockHandleDetailsService.deleteMaterielStockHandleDetailsByIds(ids));
	}

	/**
	 * 通过物料id调整物料内部调整清单页面
	 */
	@GetMapping("/materielHandleDetail/{materielId}")
	public String materielHandleDetail(@PathVariable("materielId") Integer materielId, ModelMap mmap)
	{
		mmap.put("materielId", materielId);
		return prefix + "/materielStockHandleDetails";
	}

	/**
	 * 清空物料报废品
	 * @param id 物料库存id
	 * @return 结果
	 */
	@RequiresPermissions("erp:materielStockHandleDetails:handleScrapFlag")
	@PostMapping("/handleScrap")
	@ResponseBody
	public AjaxResult handleScrap(int id){
		return toAjax(materielStockHandleDetailsService.handleScrap(id));
	}
}
