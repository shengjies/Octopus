package com.ruoyi.project.device.devList.api;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.device.devList.service.IDevListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName deviceManage
 * @PackageName com.ruoyi.project.device.devList.api
 * @Author: Administrator
 * @Date: 2019/6/11 12:17
 * @Description //TODO
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/dev/api")
public class DevListApi {

    @Autowired
    private IDevListService devListService;

    /**
     * 用户添加硬件时校验硬件编码
     */
    @RequestMapping("/validateCode")
    public AjaxResult validateCode(@RequestBody String code, HttpServletRequest request){
        try {
            return AjaxResult.success("success",devListService.deviceValidate(code));
        } catch (BusinessException e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
