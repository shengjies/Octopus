package com.ruoyi.project.erp.stockHandleDetails.service;

import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import java.util.List;

/**
 * 内部调整明细 服务层
 * 
 * @author zqm
 * @date 2019-05-27
 */
public interface IStockHandleDetailsService 
{
	/**
     * 查询内部调整明细信息
     * 
     * @param id 内部调整明细ID
     * @return 内部调整明细信息
     */
	public StockHandleDetails selectStockHandleDetailsById(Integer id);
	
	/**
     * 查询内部调整明细列表
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 内部调整明细集合
     */
	public List<StockHandleDetails> selectStockHandleDetailsList(StockHandleDetails stockHandleDetails);
	
	/**
     * 新增内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	public int insertStockHandleDetails(StockHandleDetails stockHandleDetails);
	
	/**
     * 修改内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	public int updateStockHandleDetails(StockHandleDetails stockHandleDetails);
		
	/**
     * 删除内部调整明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStockHandleDetailsByIds(String ids);
	
}
