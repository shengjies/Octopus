package com.ruoyi.project.erp.materielStockHandleDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielStockHandleDetails.domain.MaterielStockHandleDetails;
import java.util.List;	

/**
 * 物料库存内部调整清单 数据层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielStockHandleDetailsMapper 
{
	/**
     * 查询物料库存内部调整清单信息
     * 
     * @param id 物料库存内部调整清单ID
     * @return 物料库存内部调整清单信息
     */
	@DataSource(value = DataSourceType.ERP)
	public MaterielStockHandleDetails selectMaterielStockHandleDetailsById(Integer id);
	
	/**
     * 查询物料库存内部调整清单列表
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 物料库存内部调整清单集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<MaterielStockHandleDetails> selectMaterielStockHandleDetailsList(MaterielStockHandleDetails materielStockHandleDetails);
	
	/**
     * 新增物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails);
	
	/**
     * 修改物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails);
	
	/**
     * 删除物料库存内部调整清单
     * 
     * @param id 物料库存内部调整清单ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielStockHandleDetailsById(Integer id);
	
	/**
     * 批量删除物料库存内部调整清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielStockHandleDetailsByIds(String[] ids);
	
}