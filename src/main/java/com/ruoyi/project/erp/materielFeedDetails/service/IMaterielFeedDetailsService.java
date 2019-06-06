package com.ruoyi.project.erp.materielFeedDetails.service;

import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import java.util.List;

/**
 * 物料发料清单 服务层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface IMaterielFeedDetailsService 
{
	/**
     * 查询物料发料清单信息
     * 
     * @param id 物料发料清单ID
     * @return 物料发料清单信息
     */
	public MaterielFeedDetails selectMaterielFeedDetailsById(Integer id);
	
	/**
     * 查询物料发料清单列表
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 物料发料清单集合
     */
	public List<MaterielFeedDetails> selectMaterielFeedDetailsList(MaterielFeedDetails materielFeedDetails);
	
	/**
     * 新增物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	public int insertMaterielFeedDetails(MaterielFeedDetails materielFeedDetails);
	
	/**
     * 修改物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	public int updateMaterielFeedDetails(MaterielFeedDetails materielFeedDetails);
		
	/**
     * 删除物料发料清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaterielFeedDetailsByIds(String ids);
	
}
