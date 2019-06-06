package com.ruoyi.project.erp.productStockHandleDetails.mapper;

import com.ruoyi.common.validator.ValidatorUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productStockHandleDetails.domain.ProductStockHandleDetails;
import java.util.List;	

/**
 * 产品库存内部调整清单 数据层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductStockHandleDetailsMapper 
{
	/**
     * 查询产品库存内部调整清单信息
     * 
     * @param id 产品库存内部调整清单ID
     * @return 产品库存内部调整清单信息
     */
	@DataSource(value = DataSourceType.ERP)
	public ProductStockHandleDetails selectProductStockHandleDetailsById(Integer id);
	
	/**
     * 查询产品库存内部调整清单列表
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 产品库存内部调整清单集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<ProductStockHandleDetails> selectProductStockHandleDetailsList(ProductStockHandleDetails productStockHandleDetails);
	
	/**
     * 新增产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails);
	
	/**
     * 修改产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails);
	
	/**
     * 删除产品库存内部调整清单
     * 
     * @param id 产品库存内部调整清单ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteProductStockHandleDetailsById(Integer id);
	
	/**
     * 批量删除产品库存内部调整清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deleteProductStockHandleDetailsByIds(String[] ids);
	
}