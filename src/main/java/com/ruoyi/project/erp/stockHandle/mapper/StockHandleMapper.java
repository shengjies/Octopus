package com.ruoyi.project.erp.stockHandle.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.stockHandle.domain.StockHandle;
import java.util.List;	

/**
 * 库存内部处理主 数据层
 * 
 * @author zqm
 * @date 2019-05-27
 */
public interface StockHandleMapper 
{
	/**
     * 查询库存内部处理主信息
     * 
     * @param id 库存内部处理主ID
     * @return 库存内部处理主信息
     */
	@DataSource(value = DataSourceType.ERP)
	public StockHandle selectStockHandleById(Integer id);
	
	/**
     * 查询库存内部处理主列表
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 库存内部处理主集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<StockHandle> selectStockHandleList(StockHandle stockHandle);
	
	/**
     * 新增库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertStockHandle(StockHandle stockHandle);
	
	/**
     * 修改库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateStockHandle(StockHandle stockHandle);
	
	/**
     * 删除库存内部处理主
     * 
     * @param id 库存内部处理主ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteStockHandleById(Integer id);
	
	/**
     * 批量删除库存内部处理主
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteStockHandleByIds(String[] ids);
	
}