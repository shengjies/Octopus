package com.ruoyi.project.erp.stockHandle.service;

import com.ruoyi.project.erp.stockHandle.domain.StockHandle;
import java.util.List;

/**
 * 库存内部处理主 服务层
 * 
 * @author zqm
 * @date 2019-05-27
 */
public interface IStockHandleService 
{
	/**
     * 查询库存内部处理主信息
     * 
     * @param id 库存内部处理主ID
     * @return 库存内部处理主信息
     */
	public StockHandle selectStockHandleById(Integer id);
	
	/**
     * 查询库存内部处理主列表
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 库存内部处理主集合
     */
	public List<StockHandle> selectStockHandleList(StockHandle stockHandle);
	
	/**
     * 新增库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	public int insertStockHandle(StockHandle stockHandle);
	
	/**
     * 修改库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	public int updateStockHandle(StockHandle stockHandle);
		
	/**
     * 删除库存内部处理主信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStockHandleByIds(String ids);

	/**
	 * 作废库存处理单
	 * @param id 库存处理id
	 * @return 结果
	 */
	int nullifyStockHandleById(Integer id);
}
