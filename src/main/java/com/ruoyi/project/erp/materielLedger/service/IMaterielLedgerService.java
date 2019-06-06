package com.ruoyi.project.erp.materielLedger.service;

import com.ruoyi.project.erp.materielLedger.domain.MaterielLedger;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 物料对账 服务层
 * 
 * @author zqm
 * @date 2019-05-15
 */
public interface IMaterielLedgerService 
{
	/**
     * 查询物料对账信息
     * 
     * @param id 物料对账ID
     * @return 物料对账信息
     */
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
	public int insertMaterielLedger(MaterielLedger materielLedger);
	
	/**
     * 修改物料对账
     * 
     * @param materielLedger 物料对账信息
     * @return 结果
     */
	public int updateMaterielLedger(MaterielLedger materielLedger);

	/**
	 * 物料对账状态修改
	 * @param materielLedger 对账对象
	 * @return
	 */
	int  cancel(MaterielLedger materielLedger);

	/**
	 * 下载物料对账EXCEl
	 * @param id 对账id
	 * @return 工作簿对象
	 */
    Workbook uploadMatLed(Integer id);
}
