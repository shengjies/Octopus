package com.ruoyi.project.erp.productStockHandleDetails.service;

import java.util.Date;
import java.util.List;
import java.util.UnknownFormatConversionException;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productStockHandleDetails.mapper.ProductStockHandleDetailsMapper;
import com.ruoyi.project.erp.productStockHandleDetails.domain.ProductStockHandleDetails;
import com.ruoyi.project.erp.productStockHandleDetails.service.IProductStockHandleDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 产品库存内部调整清单 服务层实现
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class ProductStockHandleDetailsServiceImpl implements IProductStockHandleDetailsService 
{
	@Autowired
	private ProductStockHandleDetailsMapper productStockHandleDetailsMapper;

	@Autowired
	private ProductStockMapper productStockMapper; // 产品库存数据层

	/**
     * 查询产品库存内部调整清单信息
     * 
     * @param id 产品库存内部调整清单ID
     * @return 产品库存内部调整清单信息
     */
    @Override
	public ProductStockHandleDetails selectProductStockHandleDetailsById(Integer id)
	{
	    return productStockHandleDetailsMapper.selectProductStockHandleDetailsById(id);
	}
	
	/**
     * 查询产品库存内部调整清单列表
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 产品库存内部调整清单集合
     */
	@Override
	public List<ProductStockHandleDetails> selectProductStockHandleDetailsList(ProductStockHandleDetails productStockHandleDetails)
	{
	    return productStockHandleDetailsMapper.selectProductStockHandleDetailsList(productStockHandleDetails);
	}
	
    /**
     * 新增产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int insertProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null ) return 0;
		// 内部调整状态
		Integer handleStatus = productStockHandleDetails.getHandleStatus();
		ProductStock productStock = productStockMapper.selectProductStockByProId(productStockHandleDetails.getProductId());

		if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品转不良品
			// 判断操作数量是够大于库存数量
			if (productStock.getGoodNumber() < productStockHandleDetails.getHandleNumber()) {
			    throw new BusinessException("操作数量不能大于实际库存");
			}
			productStock.setGoodNumber(productStock.getGoodNumber() - productStockHandleDetails.getHandleNumber());
			productStock.setBadNumber(productStock.getBadNumber() + productStockHandleDetails.getHandleNumber());
			productStock.setLastUpdate(new Date());
			productStockMapper.updateProductStock(productStock);

		} else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品转报废品
			if (productStock.getGoodNumber() < productStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			productStock.setGoodNumber(productStock.getGoodNumber() - productStockHandleDetails.getHandleNumber());
			productStock.setScrapNumber(productStock.getScrapNumber() + productStockHandleDetails.getHandleNumber());
			productStock.setLastUpdate(new Date());
			productStockMapper.updateProductStock(productStock);

		} else if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品转成良品
			if (productStock.getBadNumber() < productStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			productStock.setBadNumber(productStock.getBadNumber() - productStockHandleDetails.getHandleNumber());
			productStock.setGoodNumber(productStock.getGoodNumber() + productStockHandleDetails.getHandleNumber());
			productStock.setLastUpdate(new Date());
			productStockMapper.updateProductStock(productStock);

		} else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品转报废品
			if (productStock.getBadNumber() < productStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			productStock.setBadNumber(productStock.getBadNumber() - productStockHandleDetails.getHandleNumber());
			productStock.setScrapNumber(productStock.getScrapNumber() + productStockHandleDetails.getHandleNumber());
			productStock.setLastUpdate(new Date());
			productStockMapper.updateProductStock(productStock);
		}

		productStockHandleDetails.setCompanyId(user.getCompanyId());
		productStockHandleDetails.setHandleBy(user.getUserId().intValue());
		productStockHandleDetails.setHandleName(user.getUserName());
		productStockHandleDetails.setHandleTime(new Date());
		return productStockHandleDetailsMapper.insertProductStockHandleDetails(productStockHandleDetails);
	}
	
	/**
     * 修改产品库存内部调整清单
     * 
     * @param productStockHandleDetails 产品库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int updateProductStockHandleDetails(ProductStockHandleDetails productStockHandleDetails)
	{
	    return productStockHandleDetailsMapper.updateProductStockHandleDetails(productStockHandleDetails);
	}

	/**
     * 删除产品库存内部调整清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductStockHandleDetailsByIds(String ids)
	{
		return productStockHandleDetailsMapper.deleteProductStockHandleDetailsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 清空产品报废品
	 * @param id 产品库存id
	 * @return 结果
	 */
	@Override
	public int handleScrap(int id) {
		User user = ShiroUtils.getSysUser();
		if (user == null) {
		    return 0;
		}
		// 查询产品库存信息
		ProductStock productStock = productStockMapper.selectProductStockById(id);
		/**
		 * 新增产品内部调整操作记录
		 */
		ProductStockHandleDetails handleDetails = new ProductStockHandleDetails();
		handleDetails.setCompanyId(user.getCompanyId());
		handleDetails.setProductId(productStock.getProductId());
		handleDetails.setHandleTime(new Date());
		handleDetails.setHandleBy(user.getUserId().intValue());
		handleDetails.setHandleName(user.getUserName());
		handleDetails.setHandleNumber(productStock.getScrapNumber());
		handleDetails.setHandleType(StockConstants.SCRAP_OUTSTOCK); // 产品报废品清库
		handleDetails.setProductCode(productStock.getProductCode());
		handleDetails.setProductModel(productStock.getProductModel());
		handleDetails.setProductName(productStock.getProductName());

		productStockHandleDetailsMapper.insertProductStockHandleDetails(handleDetails);

		/**
		 * 操作产品库存
		 */
		productStock.setTotalNumber(productStock.getTotalNumber() - productStock.getScrapNumber());
		productStock.setScrapNumber(0);
		productStock.setLastUpdate(new Date());
		return productStockMapper.updateProductStock(productStock);
	}
}
