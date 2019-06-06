package com.ruoyi.project.erp.materielStockIqc.service;

import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import java.util.List;

/**
 * 物料库存IQC 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielStockIqcService 
{
	/**
     * 查询物料库存IQC信息
     * 
     * @param id 物料库存IQCID
     * @return 物料库存IQC信息
     */
	public MaterielStockIqc selectMaterielStockIqcById(Integer id);
	
	/**
     * 查询物料库存IQC列表
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 物料库存IQC集合
     */
	public List<MaterielStockIqc> selectMaterielStockIqcList(MaterielStockIqc materielStockIqc);
	
	/**
     * 新增物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	public int insertMaterielStockIqc(MaterielStockIqc materielStockIqc);
	
	/**
     * 修改物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	public int updateMaterielStockIqc(MaterielStockIqc materielStockIqc);
		
	/**
     * 删除物料库存IQC信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaterielStockIqcByIds(String ids);

	/**
	 * 更改物料检验iqc的状态
	 * @param stockIqc iqc状态
	 * @return 结果
	 */
    int updateMaterielIQCStatus(Integer stockIqc);

	/**
	 * 查询物料检验IQC信息
	 * @param companyId 公司id
	 * @return 结果
	 */
	MaterielStockIqc selectMaterielStockIqcByComId(Integer companyId);
}
