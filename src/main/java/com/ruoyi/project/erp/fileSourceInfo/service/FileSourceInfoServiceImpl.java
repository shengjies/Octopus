package com.ruoyi.project.erp.fileSourceInfo.service;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.system.user.domain.User;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.fileSourceInfo.mapper.FileSourceInfoMapper;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.fileSourceInfo.service.IFileSourceInfoService;
import com.ruoyi.common.support.Convert;

/**
 * 文件素材管理 服务层实现
 * 
 * @author zqm
 * @date 2019-05-09
 */
@Service
public class FileSourceInfoServiceImpl implements IFileSourceInfoService 
{
	@Autowired
	private FileSourceInfoMapper fileSourceInfoMapper;

	/**
     * 查询文件素材管理列表
     * 
     * @param fileSourceInfo 文件素材管理信息
     * @return 文件素材管理集合
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public List<FileSourceInfo> selectFileSourceInfoList(FileSourceInfo fileSourceInfo)
	{
		if(fileSourceInfo.getCompanyId() == null) {
			User user = ShiroUtils.getSysUser();
			if (user == null) return Collections.emptyList();
			fileSourceInfo.setCompanyId(user.getCompanyId());
		}
	    return fileSourceInfoMapper.selectFileSourceInfoList(fileSourceInfo);
	}
	
    /**
     * 新增文件素材管理
     * 
     * @param fileSourceInfo 文件素材管理信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public int insertFileSourceInfo(FileSourceInfo fileSourceInfo)
	{
	    return fileSourceInfoMapper.insertFileSourceInfo(fileSourceInfo);
	}

	/**
     * 删除文件素材管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public int deleteFileSourceInfoByIds(String ids)
	{
		FileSourceInfo info = fileSourceInfoMapper.selectFileSourceInfoById(Integer.parseInt(ids));
		if(info != null && !StringUtils.isEmpty(info.getSavePath())){
			//删除对应的文件
			File file = new File(info.getSavePath());
			file.delete();
		}
		return fileSourceInfoMapper.deleteFileSourceInfoByIds(Convert.toStrArray(ids));
	}
	
}
