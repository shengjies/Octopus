package com.ruoyi.project.erp.materielStockIqc.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料库存IQC 数据层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielStockIqcMapper 
{
	/**
     * 查询物料库存IQC信息
     * 
     * @param id 物料库存IQCID
     * @return 物料库存IQC信息
     */
	@DataSource(value = DataSourceType.ERP)
	public MaterielStockIqc selectMaterielStockIqcById(Integer id);
	
	/**
     * 查询物料库存IQC列表
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 物料库存IQC集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<MaterielStockIqc> selectMaterielStockIqcList(MaterielStockIqc materielStockIqc);
	
	/**
     * 新增物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertMaterielStockIqc(MaterielStockIqc materielStockIqc);
	
	/**
     * 修改物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateMaterielStockIqc(MaterielStockIqc materielStockIqc);
	
	/**
     * 删除物料库存IQC
     * 
     * @param id 物料库存IQCID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielStockIqcById(Integer id);
	
	/**
     * 批量删除物料库存IQC
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteMaterielStockIqcByIds(String[] ids);

	/**
	 * 查询物料库存IQC
	 * @param companyId 公司id
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	MaterielStockIqc selectMaterielStockIqcByComId(@Param("companyId") Integer companyId);
}