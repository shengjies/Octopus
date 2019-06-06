package com.ruoyi.project.page.pageInfo.controller;

import com.ruoyi.common.constant.PageTypeConstants;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.fileSourceInfo.service.IFileSourceInfoService;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import com.ruoyi.project.page.pageInfo.service.IPageInfoService;
import com.ruoyi.project.page.pageInfoConfig.service.IPageInfoConfigService;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.service.IDevProductListService;
import com.ruoyi.project.production.devWorkData.service.IDevWorkDataService;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.service.IDevWorkOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转
 */
@Controller
@RequestMapping("/t")
public class PageTemController extends BaseController {
    private String prefix = "tem";

    @Autowired
    private IPageInfoService pageInfoService;

    @Autowired
    private IPageInfoConfigService pageInfoConfigService;

    @Autowired
    private IFileSourceInfoService fileSourceInfoService;

    @Autowired
    private IDevWorkOrderService workOrderService;

    @Autowired
    private IDevProductListService productListService;


    /**
     * 动态页面跳转
     * @param code 页面编号
     * @return
     */
    @RequestMapping("/{code}")
    public String tem(@PathVariable("code")String code,String pwd, ModelMap mmap){
        if(StringUtils.isEmpty(code))return "error/404";
        mmap.put("code",code);
        if(StringUtils.isEmpty(pwd))return prefix+ "/pwd";
        PageInfo info = pageInfoService.selectPageByCode(code);
        if(info == null) {
            mmap.put("msg","页面不存在");
            return prefix + "/pwd";
        }
        if(!info.getPagePwd().equals(pwd)){
            mmap.put("msg","密码错误");
            return prefix + "/pwd";
        }
        if(info.getDevCompany() == null) return "error/404";
        mmap.put("info",info);
        mmap.put("company",info.getDevCompany());
        String to = "error/404";
        switch (info.getPageType()){
            case PageTypeConstants.PAGE_TYPE_GG:
                to = prefix +"/lattice";
                break;
            case PageTypeConstants.PAGE_TYPE_LB:
                to = prefix +"/swing";
                break;
            case PageTypeConstants.PAGE_TYPE_SCPH:
                to = prefix +"/balance";
                break;
            case PageTypeConstants.PAGE_TYPE_TZLB:
                to = prefix +"/drawing";
                break;
        }
        return to;
    }

    /**
     * 宫格布局中js调用刷新数据接口
     * @return
     */
    @ResponseBody
    @PostMapping("/gg")
    public Map<String,Object> temJs(String code){
        Map<String,Object> map = new HashMap<>();
        map.put("code",pageInfoConfigService.selectPageConfigByPageCode(code));
        return map;
    }

    /**
     * 生产平衡报表JS调用接口
     * @param code
     * @return
     */
    @ResponseBody
    @PostMapping("/balance")
    public Map<String,Object> temBalanceJs(String code){
        Map<String,Object> map = new HashMap<>();
        map.put("code",pageInfoConfigService.selectBalanceConfigDataByPageCode(code));
        return map;
    }

    /**
     * 查询文件素材管理列表
     */
    @PostMapping("/file/list")
    @ResponseBody
    public TableDataInfo list(FileSourceInfo fileSourceInfo)
    {
        if(fileSourceInfo.getSaveId() != null) {
            //根据工单id查询对应的产品id
            DevWorkOrder order = workOrderService.selectDevWorkOrderById(fileSourceInfo.getSaveId());
            if (order != null) {
                DevProductList productList = productListService.selectProductByCompanyIdAndCode(fileSourceInfo.getCompanyId(), order.getProductCode());
                if (productList != null) {
                    fileSourceInfo.setSaveId(productList.getId());
                    startPage();
                    List<FileSourceInfo> list = fileSourceInfoService.selectFileSourceInfoList(fileSourceInfo);
                    return getDataTable(list);
                }
            }
        }
        return getDataTable(Collections.emptyList());
    }
}
