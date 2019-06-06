package com.ruoyi.project.erp.orderInfo.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import com.ruoyi.project.erp.orderInfo.service.IOrderInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 订单数据 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/erp/orderInfo")
public class OrderInfoController extends BaseController
{
    private String prefix = "erp/orderInfo";
	
	@Autowired
	private IOrderInfoService orderInfoService;
	
	@RequiresPermissions("erp:orderInfo:view")
	@GetMapping()
	public String orderInfo()
	{
	    return prefix + "/orderInfo";
	}
	
	/**
	 * 查询订单数据列表
	 */
	@RequiresPermissions("erp:orderInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderInfo orderInfo)
	{
		startPage();
        List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单数据列表
	 */
	@RequiresPermissions("erp:orderInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderInfo orderInfo)
    {
    	List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
        ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
        return util.exportExcel(list, "orderInfo");
    }
	
	/**
	 * 新增订单数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单数据
	 */
	@RequiresPermissions("erp:orderInfo:add")
	@Log(title = "订单数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestBody OrderInfo orderInfo)
	{
		return toAjax(orderInfoService.insertOrderInfo(orderInfo));
	}

	/**
	 * 修改订单数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrderInfo orderInfo = orderInfoService.selectOrderInfoById(id);
		mmap.put("orderInfo", orderInfo);
	    return prefix + "/edit";
	}


	/**
	 * 审核订单数据
	 */
	@GetMapping("/examine/{id}")
	public String examine(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrderInfo orderInfo = orderInfoService.selectOrderInfoById(id);
		mmap.put("orderInfo", orderInfo);
		return prefix + "/examine";
	}
	
	/**
	 * 修改保存订单数据
	 */
	@RequiresPermissions("erp:orderInfo:edit")
	@Log(title = "订单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestBody OrderInfo orderInfo)
	{		
		return toAjax(orderInfoService.updateOrderInfo(orderInfo));
	}


	/**
	 * 修改保存订单数据
	 */
	@RequiresPermissions("erp:orderInfo:edit")
	@Log(title = "订单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/cancel")
	@ResponseBody
	public AjaxResult cancelOrder(OrderInfo orderInfo){
		return toAjax(orderInfoService.cancelOrder(orderInfo));
	}
	
	/**
	 * 删除订单数据
	 */
	@RequiresPermissions("erp:orderInfo:remove")
	@Log(title = "订单数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(orderInfoService.deleteOrderInfoByIds(ids));
	}

	/**
	 * 订单详情
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("erp:orderInfo:details")
	public String details(@PathVariable("id") Integer id, ModelMap mmap)
	{
		OrderInfo orderInfo = orderInfoService.selectOrderInfoById(id);
		mmap.put("orderInfo", orderInfo);
		return prefix + "/details";
	}

	/**
	 * 关闭订单
	 * @param orderInfo 订单
	 * @return 结果
	 */
	@RequiresPermissions("erp:orderInfo:close")
	@PostMapping("/closeOrderInfo")
	@ResponseBody
	public AjaxResult closeOrderInfo(OrderInfo orderInfo){
		try {
			return toAjax(orderInfoService.closeOrderInfo(orderInfo));
		} catch (BusinessException e) {
			return error(e.getMessage());
		}
	}
}
