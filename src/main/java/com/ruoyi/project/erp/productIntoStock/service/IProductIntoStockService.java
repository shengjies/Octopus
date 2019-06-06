package com.ruoyi.project.erp.productIntoStock.service;

import com.ruoyi.project.erp.productIntoStock.domain.ProductIntoStock;
import java.util.List;

/**
 * 产品入库 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductIntoStockService 
{
	/**
     * 查询产品入库信息
     * 
     * @param id 产品入库ID
     * @return 产品入库信息
     */
	public ProductIntoStock selectProductIntoStockById(Integer id);
	
	/**
     * 查询产品入库列表
     * 
     * @param productIntoStock 产品入库信息
     * @return 产品入库集合
     */
	public List<ProductIntoStock> selectProductIntoStockList(ProductIntoStock productIntoStock);
	
	/**
     * 新增产品入库
     * 
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
	public int insertProductIntoStock(ProductIntoStock productIntoStock);
	
	/**
     * 修改产品入库
     * 
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
	public int updateProductIntoStock(ProductIntoStock productIntoStock);
		
	/**
     * 删除产品入库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductIntoStockByIds(String ids);

	/**
	 * 作废产品入库信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	int nullifyProductIntoStockByIds(Integer id);
}
