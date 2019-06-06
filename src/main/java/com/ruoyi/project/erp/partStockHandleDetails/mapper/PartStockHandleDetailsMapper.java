package com.ruoyi.project.erp.partStockHandleDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.partStockHandleDetails.domain.PartStockHandleDetails;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

import java.util.List;

/**
 * 半成品库存内部调整清单 数据层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface PartStockHandleDetailsMapper 
{
	/**
     * 查询半成品库存内部调整清单信息
     * 
     * @param id 半成品库存内部调整清单ID
     * @return 半成品库存内部调整清单信息
     */
	@DataSource(value = DataSourceType.ERP)
	public PartStockHandleDetails selectPartStockHandleDetailsById(Integer id);
	
	/**
     * 查询半成品库存内部调整清单列表
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 半成品库存内部调整清单集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<PartStockHandleDetails> selectPartStockHandleDetailsList(PartStockHandleDetails partStockHandleDetails);
	
	/**
     * 新增半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertPartStockHandleDetails(PartStockHandleDetails partStockHandleDetails);
	
	/**
     * 修改半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updatePartStockHandleDetails(PartStockHandleDetails partStockHandleDetails);
	
	/**
     * 删除半成品库存内部调整清单
     * 
     * @param id 半成品库存内部调整清单ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deletePartStockHandleDetailsById(Integer id);
	
	/**
     * 批量删除半成品库存内部调整清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deletePartStockHandleDetailsByIds(String[] ids);
	
}