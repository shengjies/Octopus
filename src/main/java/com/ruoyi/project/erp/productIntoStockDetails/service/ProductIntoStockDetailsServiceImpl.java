package com.ruoyi.project.erp.productIntoStockDetails.service;

import java.util.List;

import com.ruoyi.project.erp.productIntoStock.domain.ProductIntoStock;
import com.ruoyi.project.erp.productIntoStock.mapper.ProductIntoStockMapper;
import com.ruoyi.project.erp.productOutStock.mapper.ProductOutStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productIntoStockDetails.mapper.ProductIntoStockDetailsMapper;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import com.ruoyi.project.erp.productIntoStockDetails.service.IProductIntoStockDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 产品入库清单 服务层实现
 * 
 * @author zqm
 * @date 2019-05-08
 */
@Service
public class ProductIntoStockDetailsServiceImpl implements IProductIntoStockDetailsService 
{
	@Autowired
	private ProductIntoStockDetailsMapper productIntoStockDetailsMapper;

	@Autowired
	private ProductIntoStockMapper productIntoStockMapper; // 产品入库主表数据层

	/**
     * 查询产品入库清单信息
     * 
     * @param id 产品入库清单ID
     * @return 产品入库清单信息
     */
    @Override
	public ProductIntoStockDetails selectProductIntoStockDetailsById(Integer id)
	{
	    return productIntoStockDetailsMapper.selectProductIntoStockDetailsById(id);
	}
	
	/**
     * 查询产品入库清单列表
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 产品入库清单集合
     */
	@Override
	public List<ProductIntoStockDetails> selectProductIntoStockDetailsList(ProductIntoStockDetails productIntoStockDetails)
	{
		ProductIntoStock productIntoStock = null;
		List<ProductIntoStockDetails> productIntoStockDetailsList = productIntoStockDetailsMapper.selectProductIntoStockDetailsList(productIntoStockDetails);
		for (ProductIntoStockDetails intoStockDetails : productIntoStockDetailsList) {
			productIntoStock = productIntoStockMapper.selectProductIntoStockById(intoStockDetails.getIntoId());
			intoStockDetails.setIntoName(productIntoStock.getCreateName());
			intoStockDetails.setCustomerName(productIntoStock.getCustomerName());
		}
		return productIntoStockDetailsList;
	}
	
    /**
     * 新增产品入库清单
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
	@Override
	public int insertProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails)
	{
	    return productIntoStockDetailsMapper.insertProductIntoStockDetails(productIntoStockDetails);
	}
	
	/**
     * 修改产品入库清单
     * 
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
	@Override
	public int updateProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails)
	{
	    return productIntoStockDetailsMapper.updateProductIntoStockDetails(productIntoStockDetails);
	}

	/**
     * 删除产品入库清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductIntoStockDetailsByIds(String ids)
	{
		return productIntoStockDetailsMapper.deleteProductIntoStockDetailsByIds(Convert.toStrArray(ids));
	}
	
}
