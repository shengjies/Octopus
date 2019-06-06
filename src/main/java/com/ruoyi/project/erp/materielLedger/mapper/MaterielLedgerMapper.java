package com.ruoyi.project.erp.materielLedger.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import com.ruoyi.project.erp.materielLedger.domain.MaterielLedger;
import java.util.List;	

/**
 * 物料对账 数据层
 * 
 * @author zqm
 * @date 2019-05-15
 */
public interface MaterielLedgerMapper 
{
	/**
     * 查询物料对账信息
     * 
     * @param id 物料对账ID
     * @return 物料对账信息
     */
	@DataSource(DataSourceType.ERP)
	public MaterielLedger selectMaterielLedgerById(Integer id);
	
	/**
     * 查询物料对账列表
     * 
     * @param materielLedger 物料对账信息
     * @return 物料对账集合
     */
	public List<MaterielLedger> selectMaterielLedgerList(MaterielLedger materielLedger);
	
	/**
     * 新增物料对账
     * 
     * @param materielLedger 物料对账信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int insertMaterielLedger(MaterielLedger materielLedger);
	
	/**
     * 修改物料对账
     * 
     * @param materielLedger 物料对账信息
     * @return 结果
     */
	public int updateMaterielLedger(MaterielLedger materielLedger);


}