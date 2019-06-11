package com.ruoyi.project.system.user.controller;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.jwt.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.post.service.IPostService;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user, HttpServletRequest request) {
        startPage();
        List<User> list = userService.selectUserList(user,request);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user,HttpServletRequest request) {
        List<User> list = userService.selectUserList(user,request);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport,HttpServletRequest request) throws Exception {
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        List<User> userList = util.importExcel(file.getInputStream());
        String message = null;
        try {
            message = userService.importUser(userList, updateSupport,request);
        } catch (BusinessException e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap,HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        if (User.isSys(sysUser)) {
            mmap.put("roles", roleService.selectRoleAllByCompanyId(sysUser.getCompanyId().longValue()));
        } else {
            mmap.put("roles",roleService.selectRoleAll(request));
        }

        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(User user,HttpServletRequest request) {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.insertUser(user,request));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap,HttpServletRequest request) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId,request));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(User user,HttpServletRequest request) {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        try {
            return toAjax(userService.updateUser(user,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(User user) {
        try {
            return toAjax(userService.resetUserPwd(user));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }

    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids,HttpServletRequest request) {
        try {
            return toAjax(userService.deleteUserByIds(ids,request));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    //@PostMapping("/checkPhoneUnique")
    //@ResponseBody
    //public String checkPhoneUnique(User user)
    //{
    //    return userService.checkPhoneUnique(user);
    //}

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(User user,HttpServletRequest request) {
        try {
            return toAjax(userService.changeStatus(user,request));
        } catch (BusinessException e) {
            return error(e.getMessage());
        }
    }

    ///*********      用户API 操作  **********/
    //@Log(title = "用户管理", businessType = BusinessType.UPDATE)
    //@PostMapping("/api/edit")
    //@Transactional(rollbackFor = Exception.class)
    //@ResponseBody
    //public AjaxResult ApiEdit(@RequestBody User user,HttpServletRequest request){
    //    try {
    //        userService.ApiEdit(user);
    //        return AjaxResult.success();
    //    }catch (Exception e){
    //        e.printStackTrace();
    //    }
    //    return AjaxResult.error();
    //}
}