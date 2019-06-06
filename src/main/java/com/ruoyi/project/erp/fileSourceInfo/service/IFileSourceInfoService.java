package com.ruoyi.project.erp.fileSourceInfo.service;

import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import java.util.List;

/**
 * 文件素材管理 服务层
 * 
 * @author zqm
 * @date 2019-05-09
 */
public interface IFileSourceInfoService 
{
	
	/**
     * 查询文件素材管理列表
     * 
     * @param fileSourceInfo 文件素材管理信息
     * @return 文件素材管理集合
     */
	public List<FileSourceInfo> selectFileSourceInfoList(FileSourceInfo fileSourceInfo);
	
	/**
     * 新增文件素材管理
     * 
     * @param fileSourceInfo 文件素材管理信息
     * @return 结果
     */
	public int insertFileSourceInfo(FileSourceInfo fileSourceInfo);

		
	/**
     * 删除文件素材管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFileSourceInfoByIds(String ids);
	
}
