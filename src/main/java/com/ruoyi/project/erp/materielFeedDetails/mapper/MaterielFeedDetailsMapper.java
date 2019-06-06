package com.ruoyi.project.erp.materielFeedDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import java.util.List;	

/**
 * 物料发料清单 数据层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface MaterielFeedDetailsMapper 
{
	/**
     * 查询物料发料清单信息
     * 
     * @param id 物料发料清单ID
     * @return 物料发料清单信息
     */
	@DataSource(value = DataSourceType.ERP)
	public MaterielFeedDetails selectMaterielFeedDetailsById(Integer id);
	
	/**
     * 查询物料发料清单列表
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 物料发料清单集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<MaterielFeedDetails> selectMaterielFeedDetailsList(MaterielFeedDetails materielFeedDetails);
	
	/**
     * 新增物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertMaterielFeedDetails(MaterielFeedDetails materielFeedDetails);
	
	/**
     * 修改物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateMaterielFeedDetails(MaterielFeedDetails materielFeedDetails);
	
	/**
     * 删除物料发料清单
     * 
     * @param id 物料发料清单ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielFeedDetailsById(Integer id);
	
	/**
     * 批量删除物料发料清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielFeedDetailsByIds(String[] ids);
	
}