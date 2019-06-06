package com.ruoyi.project.erp.materielFeed.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielFeed.domain.MaterielFeed;
import java.util.List;	

/**
 * 物料发料 数据层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface MaterielFeedMapper 
{
	/**
     * 查询物料发料信息
     * 
     * @param id 物料发料ID
     * @return 物料发料信息
     */
	@DataSource(value = DataSourceType.ERP)
	public MaterielFeed selectMaterielFeedById(Integer id);
	
	/**
     * 查询物料发料列表
     * 
     * @param materielFeed 物料发料信息
     * @return 物料发料集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<MaterielFeed> selectMaterielFeedList(MaterielFeed materielFeed);
	
	/**
     * 新增物料发料
     * 
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertMaterielFeed(MaterielFeed materielFeed);
	
	/**
     * 修改物料发料
     * 
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateMaterielFeed(MaterielFeed materielFeed);
	
	/**
     * 删除物料发料
     * 
     * @param id 物料发料ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielFeedById(Integer id);
	
	/**
     * 批量删除物料发料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielFeedByIds(String[] ids);
	
}