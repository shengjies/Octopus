package com.ruoyi.project.erp.materielIntoStockDetails.service;

import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import java.util.List;

/**
 * 物料入库清单 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielIntoStockDetailsService 
{
	/**
     * 查询物料入库清单信息
     * 
     * @param id 物料入库清单ID
     * @return 物料入库清单信息
     */
	public MaterielIntoStockDetails selectMaterielIntoStockDetailsById(Integer id);
	
	/**
     * 查询物料入库清单列表
     * 
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 物料入库清单集合
     */
	public List<MaterielIntoStockDetails> selectMaterielIntoStockDetailsList(MaterielIntoStockDetails materielIntoStockDetails);
	
	/**
     * 新增物料入库清单
     * 
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
	public int insertMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails);
	
	/**
     * 修改物料入库清单
     * 
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
	public int updateMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails);
		
	/**
     * 删除物料入库清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaterielIntoStockDetailsByIds(String ids);
	
}
