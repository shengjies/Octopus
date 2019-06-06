package com.ruoyi.project.erp.materielFeed.service;

import com.ruoyi.project.erp.materielFeed.domain.MaterielFeed;
import java.util.List;
import java.util.Map;

/**
 * 物料发料 服务层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface IMaterielFeedService 
{
	/**
     * 查询物料发料信息
     * 
     * @param id 物料发料ID
     * @return 物料发料信息
     */
	public MaterielFeed selectMaterielFeedById(Integer id);
	
	/**
     * 查询物料发料列表
     * 
     * @param materielFeed 物料发料信息
     * @return 物料发料集合
     */
	public List<MaterielFeed> selectMaterielFeedList(MaterielFeed materielFeed);
	
	/**
     * 新增物料发料
     * 
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	public int insertMaterielFeed(MaterielFeed materielFeed);
	
	/**
     * 修改物料发料
     * 
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	public int updateMaterielFeed(MaterielFeed materielFeed);
		
	/**
     * 删除物料发料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaterielFeedByIds(String ids);

	/**
	 * 生产发料添加详情
	 * @param feedType 发料类型
	 * @return 结果
	 */
    Map selectAllMaterielOrParts(String feedType);

	/**
	 * 作废生产发料单
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	int nullifyMaterielFeedById(Integer id);
}
