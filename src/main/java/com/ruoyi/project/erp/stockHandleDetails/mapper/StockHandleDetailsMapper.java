package com.ruoyi.project.erp.stockHandleDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import java.util.List;

/**
 * 内部调整明细 数据层
 * 
 * @author zqm
 * @date 2019-05-27
 */
public interface StockHandleDetailsMapper 
{
	/**
     * 查询内部调整明细信息
     * 
     * @param id 内部调整明细ID
     * @return 内部调整明细信息
     */
	@DataSource(value = DataSourceType.ERP)
	public StockHandleDetails selectStockHandleDetailsById(Integer id);
	
	/**
     * 查询内部调整明细列表
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 内部调整明细集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<StockHandleDetails> selectStockHandleDetailsList(StockHandleDetails stockHandleDetails);
	
	/**
     * 新增内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertStockHandleDetails(StockHandleDetails stockHandleDetails);
	
	/**
     * 修改内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateStockHandleDetails(StockHandleDetails stockHandleDetails);
	
	/**
     * 删除内部调整明细
     * 
     * @param id 内部调整明细ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteStockHandleDetailsById(Integer id);
	
	/**
     * 批量删除内部调整明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteStockHandleDetailsByIds(String[] ids);
}