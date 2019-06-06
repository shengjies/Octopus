package com.ruoyi.project.erp.partStockHandleDetails.controller;

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
import com.ruoyi.project.erp.partStockHandleDetails.domain.PartStockHandleDetails;
import com.ruoyi.project.erp.partStockHandleDetails.service.IPartStockHandleDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 半成品库存内部调整清单 信息操作处理
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Controller
@RequestMapping("/erp/partStockHandleDetails")
public class PartStockHandleDetailsController extends BaseController
{
    private String prefix = "erp/partStockHandleDetails";
	
	@Autowired
	private IPartStockHandleDetailsService partStockHandleDetailsService;
	
	@RequiresPermissions("erp:partStockHandleDetails:view")
	@GetMapping()
	public String partStockHandleDetails()
	{
	    return prefix + "/partStockHandleDetails";
	}
	
	/**
	 * 查询半成品库存内部调整清单列表
	 */
	@RequiresPermissions("erp:partStockHandleDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PartStockHandleDetails partStockHandleDetails)
	{
		startPage();
        List<PartStockHandleDetails> list = partStockHandleDetailsService.selectPartStockHandleDetailsList(partStockHandleDetails);
		return getDataTable(list);
	}

	/**
	 * 通过半成品id查询半成品内部调整信息
	 * @param partId 半成品id
	 * @return 结果
	 */
	@RequiresPermissions("erp:partStockHandleDetails:list")
	@PostMapping("/listByParId")
	@ResponseBody
	public TableDataInfo listByParId(Integer partId)
	{
		PartStockHandleDetails partStockHandleDetails = new PartStockHandleDetails();
		partStockHandleDetails.setPartId(partId);
		startPage();
		List<PartStockHandleDetails> list = partStockHandleDetailsService.selectPartStockHandleDetailsList(partStockHandleDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出半成品库存内部调整清单列表
	 */
	@RequiresPermissions("erp:partStockHandleDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PartStockHandleDetails partStockHandleDetails)
    {
    	List<PartStockHandleDetails> list = partStockHandleDetailsService.selectPartStockHandleDetailsList(partStockHandleDetails);
        ExcelUtil<PartStockHandleDetails> util = new ExcelUtil<PartStockHandleDetails>(PartStockHandleDetails.class);
        return util.exportExcel(list, "partStockHandleDetails");
    }
	
	/**
	 * 新增半成品库存内部调整清单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存半成品库存内部调整清单
	 */
	@RequiresPermissions("erp:partStockHandleDetails:add")
	@Log(title = "半成品库存内部调整清单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PartStockHandleDetails partStockHandleDetails)
	{
		try {
			return toAjax(partStockHandleDetailsService.insertPartStockHandleDetails(partStockHandleDetails));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}

	}

	/**
	 * 修改半成品库存内部调整清单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PartStockHandleDetails partStockHandleDetails = partStockHandleDetailsService.selectPartStockHandleDetailsById(id);
		mmap.put("partStockHandleDetails", partStockHandleDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存半成品库存内部调整清单
	 */
	@RequiresPermissions("erp:partStockHandleDetails:edit")
	@Log(title = "半成品库存内部调整清单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PartStockHandleDetails partStockHandleDetails)
	{		
		return toAjax(partStockHandleDetailsService.updatePartStockHandleDetails(partStockHandleDetails));
	}
	
	/**
	 * 删除半成品库存内部调整清单
	 */
	@RequiresPermissions("erp:partStockHandleDetails:remove")
	@Log(title = "半成品库存内部调整清单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(partStockHandleDetailsService.deletePartStockHandleDetailsByIds(ids));
	}

	/**
	 * 跳转产品内部调整详情
	 */
	@GetMapping("/partsHandleDetail/{partId}")
	public String partsHandleDetail(@PathVariable("partId") Integer partId, ModelMap mmap) {
		mmap.put("partId", partId);
		return prefix + "/partStockHandleDetails";
	}

	/**
	 * 半成品清理报废品
	 * @param id 半成品库存id
	 * @return 结果
	 */
	@RequiresPermissions("erp:partStockHandleDetails:handleScrap")
	@RequestMapping("/handleScrap")
	@ResponseBody
	public AjaxResult handleScrap(int id){
		return toAjax(partStockHandleDetailsService.handleScrap(id));
	}
	
}
