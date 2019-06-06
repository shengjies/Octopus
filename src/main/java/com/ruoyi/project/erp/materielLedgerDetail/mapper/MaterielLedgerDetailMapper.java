package com.ruoyi.project.erp.materielLedgerDetail.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielLedgerDetail.domain.MaterielLedgerDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料对账明细 数据层
 * 
 * @author zqm
 * @date 2019-05-15
 */
public interface MaterielLedgerDetailMapper 
{

	/**
     * 新增物料对账明细
     * 
     * @param materielLedgerDetail 物料对账明细信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int insertMaterielLedgerDetail(MaterielLedgerDetail materielLedgerDetail);

	/**
	 * 查询物料对账当详情数据
	 * @param ledger_id 主表id
	 * @return
	 */
	@DataSource(DataSourceType.ERP)
	List<MaterielLedgerDetail> selectDetailsByLedgerId(@Param("ledger_id")int ledger_id);
	
}