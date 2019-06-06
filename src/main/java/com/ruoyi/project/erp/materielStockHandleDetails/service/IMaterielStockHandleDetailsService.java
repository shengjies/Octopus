package com.ruoyi.project.erp.materielStockHandleDetails.service;

import com.ruoyi.project.erp.materielStockHandleDetails.domain.MaterielStockHandleDetails;
import java.util.List;

/**
 * 物料库存内部调整清单 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielStockHandleDetailsService 
{
	/**
     * 查询物料库存内部调整清单信息
     * 
     * @param id 物料库存内部调整清单ID
     * @return 物料库存内部调整清单信息
     */
	public MaterielStockHandleDetails selectMaterielStockHandleDetailsById(Integer id);
	
	/**
     * 查询物料库存内部调整清单列表
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 物料库存内部调整清单集合
     */
	public List<MaterielStockHandleDetails> selectMaterielStockHandleDetailsList(MaterielStockHandleDetails materielStockHandleDetails);
	
	/**
     * 新增物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	public int insertMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails);
	
	/**
     * 修改物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	public int updateMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails);
		
	/**
     * 删除物料库存内部调整清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaterielStockHandleDetailsByIds(String ids);

	/**
	 * 处理物料报废品
	 * @param id 物料库存id
	 * @return 结果
	 */
	int handleScrap(Integer id);
}
