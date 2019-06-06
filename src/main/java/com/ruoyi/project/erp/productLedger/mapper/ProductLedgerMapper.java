package com.ruoyi.project.erp.productLedger.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productLedger.domain.ProductLedger;
import java.util.List;	

/**
 * 产品对账 数据层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface ProductLedgerMapper 
{
	/**
     * 查询产品对账信息
     * 
     * @param id 产品对账ID
     * @return 产品对账信息
     */
	@DataSource(DataSourceType.ERP)
	public ProductLedger selectProductLedgerById(Integer id);
	
	/**
     * 查询产品对账列表
     * 
     * @param productLedger 产品对账信息
     * @return 产品对账集合
     */
	public List<ProductLedger> selectProductLedgerList(ProductLedger productLedger);
	
	/**
     * 新增产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */

	@DataSource(DataSourceType.ERP)
	public int insertProductLedger(ProductLedger productLedger);
	
	/**
     * 修改产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int updateProductLedger(ProductLedger productLedger);
	
	/**
     * 删除产品对账
     * 
     * @param id 产品对账ID
     * @return 结果
     */
	public int deleteProductLedgerById(Integer id);
	
	/**
     * 批量删除产品对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductLedgerByIds(String[] ids);
	
}