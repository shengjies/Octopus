package com.ruoyi.project.erp.fileSourceInfo.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件素材管理 数据层
 * 
 * @author zqm
 * @date 2019-05-09
 */
public interface FileSourceInfoMapper 
{
	/**
     * 查询文件素材管理信息
     * 
     * @param id 文件素材管理ID
     * @return 文件素材管理信息
     */
	public FileSourceInfo selectFileSourceInfoById(Integer id);
	
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
     * 批量删除文件素材管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFileSourceInfoByIds(String[] ids);

	/**
	 * 查询文件信息
	 * @param saveId 保存的类型id
	 * @param companyId 公司id
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
    List<FileSourceInfo> selectFileSourceInfoBySaveIdAndComId(@Param("saveId") Integer saveId, @Param("companyId") Integer companyId);
}