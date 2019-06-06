package com.ruoyi.project.production.devWorkOrder.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.TimeUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.service.IProductionLineService;
import com.ruoyi.project.system.user.domain.User;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.org.apache.regexp.internal.RE;
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
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 工单 信息操作处理
 *
 * @author zqm
 * @date 2019-04-12
 */
@Controller
@RequestMapping("/device/devWorkOrder")
public class DevWorkOrderController extends BaseController {
    private String prefix = "production/devWorkOrder";

    @Autowired
    private IDevWorkOrderService devWorkOrderService;

    @Autowired
    private IProductionLineService productionLineService;

    @RequiresPermissions("device:devWorkOrder:view")
    @GetMapping()
    public String devWorkOrder() {
        return prefix + "/devWorkOrder";
    }

    /**
     * 查询工单列表
     */
    @RequiresPermissions("device:devWorkOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        startPage();
        List<DevWorkOrder> list = devWorkOrderService.selectDevWorkOrderList(devWorkOrder,request);
        return getDataTable(list);
    }


    /**
     * 导出工单列表
     */
    @RequiresPermissions("device:devWorkOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevWorkOrder devWorkOrder, HttpServletRequest request) {
        List<DevWorkOrder> list = devWorkOrderService.selectDevWorkOrderList(devWorkOrder,request);
        ExcelUtil<DevWorkOrder> util = new ExcelUtil<DevWorkOrder>(DevWorkOrder.class);
        return util.exportExcel(list, "devWorkOrder");
    }

    /**
     * 新增工单
     */
    @GetMapping("/add")
    public String add(ModelMap mmap,HttpServletRequest request) {
        mmap.put("workorderNumber",CodeUtils.getWorkOrderCode(JwtUtil.getTokenUser(request).getCompanyId()));
        return prefix + "/add";
    }

    /**
     * 获取工单编号
     * @return
     */
    @ResponseBody
    @PostMapping("/getWorkOrderCode")
    public String getWorkOrderCode(HttpServletRequest request){
        return CodeUtils.getWorkOrderCode(JwtUtil.getTokenUser(request).getCompanyId());
    }

    /**
     * 新增保存工单
     */
    @RequiresPermissions("device:devWorkOrder:add")
    @Log(title = "工单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        return toAjax(devWorkOrderService.insertDevWorkOrder(devWorkOrder,request));
    }

    /**
     * 修改工单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        DevWorkOrder devWorkOrder = devWorkOrderService.selectDevWorkOrderById(id);
        mmap.put("devWorkOrder", devWorkOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存工单
     */
    @RequiresPermissions("device:devWorkOrder:edit")
    @Log(title = "工单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        if (u == null) {
            return error();
        }
        try {
            return toAjax(devWorkOrderService.updateDevWorkOrder(devWorkOrder,u));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 删除工单
     */
    @RequiresPermissions("device:devWorkOrder:remove")
    @Log(title = "工单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(devWorkOrderService.deleteDevWorkOrderByIds(ids));
        } catch (BusinessException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 校验工单号是否存在
     *
     * @return
     */
    @PostMapping("/checkWorkOrderNumber")
    @ResponseBody
    public String checkWorkOrderNumber(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        return devWorkOrderService.checkWorkOrderNumber(devWorkOrder,request);
    }

    /**
     * 工单状态控制 <br>
     * 工单开始暂停状态修改，第一次点击开始初始化数据
     *
     * @return
     */
    @PostMapping("/editWorkerOrderById")
    @ResponseBody
    public AjaxResult editWorkerOrderById(Integer id,HttpServletRequest request) {
        User u =JwtUtil.getTokenUser(request);
        if (u == null) {
            return error();
        }
        try {
            return toAjax(devWorkOrderService.editWorkerOrderById(id,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 页面点击提交工单，工单不可修改
     *
     * @param id
     * @return
     */
    @PostMapping("/submitWorkOrder")
    @ResponseBody
    public AjaxResult submitWorkOrder(Integer id,HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        if (u == null) {
            return error();
        }
        try {
            return toAjax(devWorkOrderService.submitWorkOrder(id,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 页面点击结束工单
     *
     * @param id
     * @return
     */
    @PostMapping("/finishWorkerOrder")
    @ResponseBody
    public AjaxResult finishWorkerOrder(Integer id,HttpServletRequest request) {

        User u = JwtUtil.getTokenUser(request);
        if (u == null) {
            return error();
        }
        try {
            return toAjax(devWorkOrderService.finishWorkerOrder(id,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    /**
     * 通过产线Id查询该产线正在生产的工单
     *
     * @param lineId
     * @return
     */
    @PostMapping("/selectWorkOrderBeInByLineId")
    @ResponseBody
    public AjaxResult selectWorkOrderBeInByLineId(Integer lineId,HttpServletRequest request) {
        DevWorkOrder workOrder = devWorkOrderService.selectWorkOrderBeInByLineId(lineId,request);
        return AjaxResult.success("success", workOrder);
    }
    /**
     * 根据工单信息查询对应的工单信息
     * @return
     */
    @ResponseBody
    @PostMapping("/findWorkInfoById")
    public Map<String,Object> findWorkInfoById(int work_id){
        Map<String,Object> map = new HashMap<>();
        map.put("code",devWorkOrderService.findWorkInfoById(work_id));
        return  map;
    }

    /**
     * 跳转工单详情
     */
    @GetMapping("/showWorkOrderDetail/{id}")
    public String showWorkOrderDetail(@PathVariable("id") Integer id, ModelMap mmap) {
        DevWorkOrder devWorkOrder = devWorkOrderService.selectDevWorkOrderById(id);
        if(devWorkOrder.getWorkorderStatus() == 1){
            //达成率 = 累计产量/标准工时*(实际用时) *100
            devWorkOrder.setReachRate(0.00F);
            if(devWorkOrder.getCumulativeNumber() != 0){
                float standardTotal = devWorkOrder.getProductStandardHour() * TimeUtil.getDateDel(devWorkOrder.getStartTime());
                devWorkOrder.setReachRate(standardTotal == 0 ? 0.0F : new BigDecimal(((float) devWorkOrder.getCumulativeNumber() / standardTotal)*100).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue());
            }
        }
        if(devWorkOrder.getWorkorderStatus() == 2){
            devWorkOrder.setReachRate(0.00F);
            if(devWorkOrder.getCumulativeNumber() != 0){
                float standardTotal = devWorkOrder.getProductStandardHour() * TimeUtil.getDateDel(devWorkOrder.getStartTime(),devWorkOrder.getEndTime());
                devWorkOrder.setReachRate(standardTotal == 0 ? 0.0F : new BigDecimal(((float) devWorkOrder.getCumulativeNumber() / standardTotal)*100).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue());
            }
        }
        ProductionLine productionLine = productionLineService.selectProductionLineById(devWorkOrder.getLineId());
        if (StringUtils.isNotNull(productionLine) && !StringUtils.isEmpty(productionLine.getParamConfig())) {
            productionLine.setParamArray(JSON.parseArray(productionLine.getParamConfig(), String.class));
        }
        mmap.put("line", productionLine);
        mmap.put("devWorkOrder", devWorkOrder);
        return prefix + "/workOrderDetail";
    }

    /**
     * 通过产线id查询已经提交的工单列表
     * @param lineId 产线
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectWorkOrderFinishByLineId")
    public TableDataInfo selectWorkOrderFinishByLineId(int lineId,HttpServletRequest request){
        return getDataTable(devWorkOrderService.selectWorkOrderFinishByLineId(lineId,request));
    }

    /**
     * 根据产线id和工单id查询对应工单信息
     * @param lineId 产线id
     * @param workOrderId 工单id
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("/selectWorkOrderFinishByLineIdAndWorkOrderId")
    public AjaxResult selectWorkOrderFinishByLineIdAndWorkOrderId(int lineId,int workOrderId,HttpServletRequest request){
        return AjaxResult.success("sucess",devWorkOrderService.selectWorkOrderFinishByLineIdAndWorkOrderId(lineId,workOrderId,request));
    }

    /**
     * 工单变更
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping("/chang")
    public AjaxResult changeOrder(DevWorkOrder order,HttpServletRequest request){
        return toAjax(devWorkOrderService.changeOrder(order,request));
    }

    @RequestMapping("/workecn")
    public String workEcn(int workId,ModelMap mmap){
        DevWorkOrder order = devWorkOrderService.selectWorkOrderEcn(workId);
        mmap.put("work",order);
        return prefix+"/workecn";
    }
}
