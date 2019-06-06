package com.ruoyi.project.page.pageInfo.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import com.ruoyi.project.page.pageInfo.service.IPageInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 页面管理 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
@Controller
@RequestMapping("/page/pageInfo")
public class PageInfoController extends BaseController
{
    private String prefix = "page/pageInfo";
	
	@Autowired
	private IPageInfoService pageInfoService;

	@Value("${img.url}")
	private String imgUrl;

	
	@RequiresPermissions("page:pageInfo:view")
	@GetMapping()
	public String pageInfo()
	{
	    return prefix + "/pageInfo";
	}
	
	/**
	 * 查询页面管理列表
	 */
	@RequiresPermissions("page:pageInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PageInfo pageInfo)
	{
		startPage();
        List<PageInfo> list = pageInfoService.selectPageInfoList(pageInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出页面管理列表
	 */
	@RequiresPermissions("page:pageInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PageInfo pageInfo)
    {
    	List<PageInfo> list = pageInfoService.selectPageInfoList(pageInfo);
        ExcelUtil<PageInfo> util = new ExcelUtil<PageInfo>(PageInfo.class);
        return util.exportExcel(list, "pageInfo");
    }
	
	/**
	 * 新增页面管理
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		return prefix + "/add";
	}
	
	/**
	 * 新增保存页面管理
	 */
	@RequiresPermissions("page:pageInfo:add")
	@Log(title = "页面管理", businessType = BusinessType.INSERT)
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult addSave(@RequestBody PageInfo pageInfo)
	{		
		return toAjax(pageInfoService.insertPageInfo(pageInfo));
	}

	/**
	 * 修改页面管理
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PageInfo pageInfo = pageInfoService.selectPageInfoById(id);
		mmap.put("pageInfo", pageInfo);
		mmap.put("page1",pageInfoService.selectAllPage(1,id));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存页面管理
	 */
	@RequiresPermissions("page:pageInfo:edit")
	@Log(title = "页面管理", businessType = BusinessType.UPDATE)
	@PostMapping("/editSave")
	@ResponseBody
	public AjaxResult editSave(@RequestBody PageInfo pageInfo)
	{
		return toAjax(pageInfoService.updatePageInfo(pageInfo));
	}
	
	/**
	 * 删除页面管理
	 */
	@RequiresPermissions("page:pageInfo:remove")
	@Log(title = "页面管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pageInfoService.deletePageInfoByIds(ids));
	}

	/**
	 * 背景图片
	 * @return
	 */
	@GetMapping("/pageBack")
	public String pageBack(){
		return  prefix +"/pageBack";
	}

	/**
	 * 删除背景图片
	 * @param file
	 * @return
	 */
	@ResponseBody
	@PostMapping("/savePageBack")
	public AjaxResult savePageBack(@RequestParam("backFile") MultipartFile file){
		try {
			if(!file.isEmpty()){
				String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
				return success(imgUrl+avatar);
			}
			return error();
		}catch (Exception e){
			return error(e.getMessage());
		}
	}

	/**
	 * 查询相关密码
	 * @return
	 */
	@GetMapping("/pwd/{id}")
	@RequiresPermissions("page:pageInfo:pwd")
	public String pwdPage(@PathVariable("id") int id,ModelMap mmap){
		mmap.put("p",pageInfoService.selectPageInfoByPageId(id));
		return prefix+"/pwd";
	}

	/**
	 * 重置页面密码
	 * @param info 页面信息
	 * @return
	 */
	@ResponseBody
	@PostMapping("/savePwd")
	@RequiresPermissions("page:pageInfo:pwd")
	public AjaxResult savePwd(PageInfo info){
		return toAjax(pageInfoService.savePwd(info));
	}
}
