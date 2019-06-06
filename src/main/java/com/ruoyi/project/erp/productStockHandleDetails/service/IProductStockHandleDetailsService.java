package com.ruoyi.project.erp.productStockHandleDetails.service;

import com.ruoyi.project.erp.productStockHandleDetails.domain.ProductStockHandleDetails;
import java.util.List;

/**
 * 产品库存内部调整清单 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductStockHandleDetailsService 
{
	/**
     * 查询产品库存内部调整清单信息
     * 
     * @param id 产品库存内部调整清单ID
     * @return 产品库存内部调整清单信息
     */
	public ProductStockHandleDetails selectProductStockHandleDetailsById(Integer id);
	
	/**
     * 查询产品库存内部调整清单列表
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 产品库存内部调整清单集合
     */
	public List<ProductStockHandleDetails> selectProductStockHandleDetailsList(ProductStockHandleDetails productStockHandleDetails);
	
	/**
     * 新增产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	public int insertProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails);
	
	/**
     * 修改产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	public int updateProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails);
		
	/**
     * 删除产品库存内部调整清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductStockHandleDetailsByIds(String ids);

	/**
	 * 清空产品报废品
	 * @param id 产品库存id
	 * @return 结果
	 */
    int handleScrap(int id);
}
