package com.ruoyi.project.page.pageInfo.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 页面管理 数据层
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
public interface PageInfoMapper 
{
	/**
     * 查询页面管理信息
     * 
     * @param id 页面管理ID
     * @return 页面管理信息
     */
	@DataSource(DataSourceType.SLAVE)
	public PageInfo selectPageInfoById(Integer id);

	/**
	 * 查询对应的页面的编号是否存在
	 * @param code 页面编号
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	PageInfo selectPageInfoByCode(@Param("code")String code);
	
	/**
     * 查询页面管理列表
     * 
     * @param pageInfo 页面管理信息
     * @return 页面管理集合
     */
	@DataSource(DataSourceType.SLAVE)
	public List<PageInfo> selectPageInfoList(PageInfo pageInfo);
	
	/**
     * 新增页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int insertPageInfo(PageInfo pageInfo);
	
	/**
     * 修改页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int updatePageInfo(PageInfo pageInfo);
	
	/**
     * 删除页面管理
     * 
     * @param id 页面管理ID
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int deletePageInfoById(Integer id);
	
	/**
     * 批量删除页面管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int deletePageInfoByIds(String[] ids);

	/**
	 * 查询对应公司所有非轮播页面
	 * @param com_id
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	List<PageInfo> selectAllPage(@Param("com_id")int com_id);
	
}