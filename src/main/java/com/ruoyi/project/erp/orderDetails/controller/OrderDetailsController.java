package com.ruoyi.project.erp.orderDetails.controller;

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
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.orderDetails.service.IOrderDetailsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 订单详情 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/erp/orderDetails")
public class OrderDetailsController extends BaseController
{
    private String prefix = "erp/orderDetails";
	
	@Autowired
	private IOrderDetailsService orderDetailsService;
	
	@RequiresPermissions("erp:orderDetails:view")
	@GetMapping()
	public String orderDetails()
	{
	    return prefix + "/orderDetails";
	}
	
	/**
	 * 查询订单详情列表
	 */
	@RequiresPermissions("erp:orderDetails:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderDetails orderDetails)
	{
		startPage();
        List<OrderDetails> list = orderDetailsService.selectOrderDetailsList(orderDetails);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单详情列表
	 */
	@RequiresPermissions("erp:orderDetails:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderDetails orderDetails)
    {
    	List<OrderDetails> list = orderDetailsService.selectOrderDetailsList(orderDetails);
        ExcelUtil<OrderDetails> util = new ExcelUtil<OrderDetails>(OrderDetails.class);
        return util.exportExcel(list, "orderDetails");
    }
	
	/**
	 * 新增订单详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单详情
	 */
	@RequiresPermissions("erp:orderDetails:add")
	@Log(title = "订单详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrderDetails orderDetails)
	{		
		return toAjax(orderDetailsService.insertOrderDetails(orderDetails));
	}

	/**
	 * 修改订单详情
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrderDetails orderDetails = orderDetailsService.selectOrderDetailsById(id);
		mmap.put("orderDetails", orderDetails);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单详情
	 */
	@RequiresPermissions("erp:orderDetails:edit")
	@Log(title = "订单详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrderDetails orderDetails)
	{		
		return toAjax(orderDetailsService.updateOrderDetails(orderDetails));
	}
	
	/**
	 * 删除订单详情
	 */
	@RequiresPermissions("erp:orderDetails:remove")
	@Log(title = "订单详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(orderDetailsService.deleteOrderDetailsByIds(ids));
	}
	
}
