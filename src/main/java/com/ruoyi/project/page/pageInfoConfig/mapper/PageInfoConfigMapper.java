package com.ruoyi.project.page.pageInfoConfig.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 页面配置 数据层
 * 
 * @author zqm
 * @date 2019-04-13
 */
public interface PageInfoConfigMapper 
{
	/**
     * 查询页面配置信息
     * 
     * @param id 页面配置ID
     * @return 页面配置信息
     */
	public PageInfoConfig selectPageInfoConfigById(Integer id);
	
	/**
     * 查询页面配置列表
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 页面配置集合
     */
	public List<PageInfoConfig> selectPageInfoConfigList(PageInfoConfig pageInfoConfig);
	
	/**
     * 新增页面配置
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int insertPageInfoConfig(PageInfoConfig pageInfoConfig);
	
	/**
     * 修改页面配置
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 结果
     */
	public int updatePageInfoConfig(PageInfoConfig pageInfoConfig);
	
	/**
     * 删除页面配置
     * 
     * @param id 页面配置ID
     * @return 结果
     */
	public int deletePageInfoConfigById(Integer id);
	
	/**
     * 批量删除页面配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePageInfoConfigByIds(String[] ids);

	/**
	 * 根据页面编号查询对应的页面配置信息
	 * @param page_id
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	List<PageInfoConfig> selectPageConfigByPageId(@Param("page_id")int page_id);

	/**
	 * 根据页面编号批量删除相关配置
	 * @param pids 页面编号
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	int deletePageInfoConfigByPIds(String[] pids);

	/**
	 * 查询对应的轮播页面是否配置其他页面
	 * @param p_id 轮播页面编号
	 * @param other_id 其他页面编号
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	PageInfoConfig selectPageConfigOtherPage(@Param("p_id")int p_id,@Param("other_id")int other_id);
}