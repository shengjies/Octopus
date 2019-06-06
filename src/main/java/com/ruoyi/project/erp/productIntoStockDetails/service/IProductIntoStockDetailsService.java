package com.ruoyi.project.erp.productIntoStockDetails.service;

import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import java.util.List;

/**
 * 产品入库清单 服务层
 * 
 * @author zqm
 * @date 2019-05-08
 */
public interface IProductIntoStockDetailsService 
{
	/**
     * 查询产品入库清单信息
     * 
     * @param id 产品入库清单ID
     * @return 产品入库清单信息
     */
	public ProductIntoStockDetails selectProductIntoStockDetailsById(Integer id);
	
	/**
     * 查询产品入库清单列表
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 产品入库清单集合
     */
	public List<ProductIntoStockDetails> selectProductIntoStockDetailsList(ProductIntoStockDetails productIntoStockDetails);
	
	/**
     * 新增产品入库清单
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
	public int insertProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails);
	
	/**
     * 修改产品入库清单
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
	public int updateProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails);
		
	/**
     * 删除产品入库清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductIntoStockDetailsByIds(String ids);
	
}
