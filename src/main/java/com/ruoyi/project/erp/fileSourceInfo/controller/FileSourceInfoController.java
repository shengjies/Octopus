package com.ruoyi.project.erp.fileSourceInfo.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.fileSourceInfo.service.IFileSourceInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件素材管理 信息操作处理
 * 
 * @author zqm
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/erp/fileSourceInfo")
public class FileSourceInfoController extends BaseController
{
    private String prefix = "erp/fileSourceInfo";
	
	@Autowired
	private IFileSourceInfoService fileSourceInfoService;

	@Value("${file.url}")
	private String fileUrl;

	/**
	 *
	 * 查看文件
	 * @param save_type 保存类型
	 * @param save_id 保存id
	 * @return
	 */
	@GetMapping()
	public String fileSourceInfo(int save_type,int save_id,ModelMap mmap)
	{
		mmap.put("saveType",save_type);
		mmap.put("saveId",save_id);
	    return prefix + "/fileSourceInfo";
	}
	
	/**
	 * 查询文件素材管理列表
	 */
	@RequiresPermissions("erp:fileSourceInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FileSourceInfo fileSourceInfo)
	{
		startPage();
        List<FileSourceInfo> list = fileSourceInfoService.selectFileSourceInfoList(fileSourceInfo);
		return getDataTable(list);
	}
	

	/**
	 * 新增保存文件素材管理
	 */
	@RequiresPermissions("erp:fileSourceInfo:add")
	@Log(title = "文件素材管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestParam("avatarfile") MultipartFile file,
							  @RequestParam("saveType") int saveType,@RequestParam("saveId")int saveId)
	{
		try {
			if(!file.isEmpty()){
				FileSourceInfo info = new FileSourceInfo();
				User user = ShiroUtils.getSysUser();
				if(user == null)return error();
				info.setCompanyId(user.getCompanyId());
				String originalFilename = file.getOriginalFilename().toUpperCase();
				String fileName =  file.getOriginalFilename();
				if(originalFilename.endsWith(".JPG") || originalFilename.endsWith("PNG") || originalFilename.endsWith("GIF")){
					info.setFileType(1);//图片
				}else{
					info.setFileType(2);//文件
				}
				int fileNamelength = file.getOriginalFilename().length();
				if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
				{
					throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
							FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
				}
				info.setSaveType(saveType);
				info.setSaveId(saveId);
				info.setFileName(fileName);
				FileUploadUtils.assertAllowed(file);
				String path = RuoYiConfig.getUploadPath();
				File desc = FileUploadUtils.getAbsoluteFile(path, path + fileName);
				file.transferTo(desc);
				info.setFilePath(fileUrl + fileName);
				info.setCreateTime(new Date());
				info.setSavePath(path+fileName);
				fileSourceInfoService.insertFileSourceInfo(info);
				return  success();
			}

			return error();
		}catch (Exception e){
			return error(e.getMessage());
		}
	}

	/**
	 * 删除文件素材管理
	 */
	@RequiresPermissions("erp:fileSourceInfo:remove")
	@Log(title = "文件素材管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(fileSourceInfoService.deleteFileSourceInfoByIds(ids));
	}
	
}
