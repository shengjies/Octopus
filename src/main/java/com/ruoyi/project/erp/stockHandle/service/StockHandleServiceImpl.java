package com.ruoyi.project.erp.stockHandle.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.mapper.PartsStockMapper;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import com.ruoyi.project.erp.stockHandleDetails.mapper.StockHandleDetailsMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.stockHandle.mapper.StockHandleMapper;
import com.ruoyi.project.erp.stockHandle.domain.StockHandle;
import com.ruoyi.project.erp.stockHandle.service.IStockHandleService;
import com.ruoyi.common.support.Convert;

/**
 * 库存内部处理主 服务层实现
 * 
 * @author zqm
 * @date 2019-05-27
 */
@Service
public class StockHandleServiceImpl implements IStockHandleService 
{
	@Autowired
	private StockHandleMapper stockHandleMapper;

	@Autowired
	private ProductStockMapper productStockMapper;

	@Autowired
	private MaterielStockMapper materielStockMapper;

	@Autowired
	private PartsStockMapper partsStockMapper;

	@Autowired
    private StockHandleDetailsMapper stockHandleDetailsMapper;

	/**
     * 查询库存内部处理主信息
     * 
     * @param id 库存内部处理主ID
     * @return 库存内部处理主信息
     */
    @Override
	public StockHandle selectStockHandleById(Integer id)
	{
        StockHandle stockHandle = stockHandleMapper.selectStockHandleById(id);
        StockHandleDetails stockHandleDetails = new StockHandleDetails();
	    stockHandleDetails.setHandleId(id);
        List<StockHandleDetails> stockHandleDetailsList = stockHandleDetailsMapper.selectStockHandleDetailsList(stockHandleDetails);
        stockHandle.setStockHandleDetailsList(stockHandleDetailsList);
        return stockHandle;
	}
	
	/**
     * 查询库存内部处理主列表
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 库存内部处理主集合
     */
	@Override
	public List<StockHandle> selectStockHandleList(StockHandle stockHandle)
	{
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        stockHandle.setCompanyId(user.getCompanyId());
        return stockHandleMapper.selectStockHandleList(stockHandle);
	}
	
    /**
     * 新增库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	@Override
	public int insertStockHandle(StockHandle stockHandle)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
		    return 0;
		}
		String handleStockCode = CodeUtils.getHandleStockCode(); // 自动生成库存处理单号
        Integer handleType = Integer.parseInt(stockHandle.getHandleType());
        stockHandle.setHandleCode(handleStockCode);
        stockHandle.setCompanyId(user.getCompanyId());
        stockHandle.setCreateId(user.getUserId().intValue());
        stockHandle.setCreateName(user.getUserName());
        stockHandle.setCreateTime(new Date());
        stockHandleMapper.insertStockHandle(stockHandle);
        List<StockHandleDetails> stockHandleDetailsList = stockHandle.getStockHandleDetailsList();
        if (!StringUtils.isEmpty(stockHandleDetailsList)) {
            for (StockHandleDetails stockHandleDetails : stockHandleDetailsList) {
                /**
                 * 保存库存处理明细
                 */
                stockHandleDetails.setHandleId(stockHandle.getId());
                stockHandleDetails.setHandleCode(handleStockCode);
                stockHandleDetails.setCompanyId(user.getCompanyId());
                stockHandleDetails.setCreateId(user.getUserId().intValue());
                stockHandleDetails.setCreateName(user.getUserName());
                stockHandleDetails.setCreateTime(new Date());
                stockHandleDetailsMapper.insertStockHandleDetails(stockHandleDetails);
                /**
                 * 根据不同的处理类型执行不同的操作
                 */
                if (StockConstants.DETAILS_TYPE_PRODUCT.equals(handleType)) { // 成品库存管理
                    ProductStock productStock = productStockMapper.selectProductStockByProId(stockHandleDetails.getAttrId());
                    /**
                     * 通过不同的处理类型更新相对应的库存信息
                     */
                    Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                    if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                        productStock.setGoodNumber(productStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                        productStock.setBadNumber(productStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                        productStock.setBadNumber(productStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                        productStock.setScrapNumber(productStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                        productStock.setGoodNumber(productStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        productStock.setBadNumber(productStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                        productStock.setGoodNumber(productStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        productStock.setScrapNumber(productStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                        productStock.setScrapNumber(productStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                    }
                    productStock.setLastUpdate(new Date());
                    productStockMapper.updateProductStock(productStock);
                } else if (StockConstants.DETAILS_TYPE_MATERIEL.equals(handleType)) { // 物料库存管理
                    MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(stockHandleDetails.getAttrId());
                    /**
                     * 通过不同的处理类型更新相对应的库存信息
                     */
                    Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                    if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                        materielStock.setGoodNumber(materielStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                        materielStock.setBadNumber(materielStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                        materielStock.setBadNumber(materielStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                        materielStock.setScrapNumber(materielStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                        materielStock.setGoodNumber(materielStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        materielStock.setBadNumber(materielStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                        materielStock.setGoodNumber(materielStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        materielStock.setScrapNumber(materielStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                        materielStock.setScrapNumber(materielStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                    }
                    materielStock.setLastUpdate(new Date());
                    materielStockMapper.updateMaterielStock(materielStock);
                } else if (StockConstants.DETAILS_TYPE_PART.equals(handleType)) { // 半成品库存管理
                    PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(stockHandleDetails.getAttrId());
                    /**
                     * 通过不同的处理类型更新相对应的库存信息
                     */
                    Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                    if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                        partsStock.setGoodNumber(partsStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                        partsStock.setBadNumber(partsStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                        partsStock.setBadNumber(partsStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                        partsStock.setScrapNumber(partsStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                        partsStock.setGoodNumber(partsStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        partsStock.setBadNumber(partsStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                        partsStock.setGoodNumber(partsStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                        partsStock.setScrapNumber(partsStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                    } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                        partsStock.setScrapNumber(partsStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                    }
                    partsStock.setLastUpdate(new Date());
                    partsStockMapper.updatePartsStock(partsStock);
                }
            }

        }

		return 1;
	}

	/**
     * 修改库存内部处理主
     * 
     * @param stockHandle 库存内部处理主信息
     * @return 结果
     */
	@Override
	public int updateStockHandle(StockHandle stockHandle)
	{
	    return stockHandleMapper.updateStockHandle(stockHandle);
	}

	/**
     * 删除库存内部处理主对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStockHandleByIds(String ids)
	{
		return stockHandleMapper.deleteStockHandleByIds(Convert.toStrArray(ids));
	}

    /**
     * 作废库存处理单 数据回滚
     * @param id 库存处理id
     * @return 结果
     */
    @Override
    public int nullifyStockHandleById(Integer id) {
        StockHandleDetails handleDetails = new StockHandleDetails();
        handleDetails.setHandleId(id);
        List<StockHandleDetails> stockHandleDetailList = stockHandleDetailsMapper.selectStockHandleDetailsList(handleDetails);
        for (StockHandleDetails stockHandleDetails : stockHandleDetailList) {
            Integer handleType = Integer.parseInt(stockHandleDetails.getHandleType());
            /**
             * 根据不同的处理类型进行不同的数据回滚
             */
            if (StockConstants.DETAILS_TYPE_PRODUCT.equals(handleType)) { // 成品库存管理
                ProductStock productStock = productStockMapper.selectProductStockByProId(stockHandleDetails.getAttrId());
                /**
                 * 通过不同的处理类型更新相对应的库存信息
                 */
                Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                    productStock.setGoodNumber(productStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                    productStock.setBadNumber(productStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                    productStock.setBadNumber(productStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    productStock.setScrapNumber(productStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                    productStock.setGoodNumber(productStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    productStock.setBadNumber(productStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                    productStock.setGoodNumber(productStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    productStock.setScrapNumber(productStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                    productStock.setScrapNumber(productStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                }
                productStock.setLastUpdate(new Date());
                productStockMapper.updateProductStock(productStock);
            } else if (StockConstants.DETAILS_TYPE_MATERIEL.equals(handleType)) { // 物料库存管理
                MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(stockHandleDetails.getAttrId());
                /**
                 * 通过不同的处理类型更新相对应的库存信息
                 */
                Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                    materielStock.setGoodNumber(materielStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                    materielStock.setBadNumber(materielStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                    materielStock.setBadNumber(materielStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    materielStock.setScrapNumber(materielStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                    materielStock.setGoodNumber(materielStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    materielStock.setBadNumber(materielStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                    materielStock.setGoodNumber(materielStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    materielStock.setScrapNumber(materielStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                    materielStock.setScrapNumber(materielStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                }
                materielStock.setLastUpdate(new Date());
                materielStockMapper.updateMaterielStock(materielStock);
            } else if (StockConstants.DETAILS_TYPE_PART.equals(handleType)) { // 半成品库存管理
                PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(stockHandleDetails.getAttrId());
                /**
                 * 通过不同的处理类型更新相对应的库存信息
                 */
                Integer handleStatus = Integer.parseInt(stockHandleDetails.getHandleStatus());// 获取处理类型
                if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品到良品
                    partsStock.setGoodNumber(partsStock.getGoodNumber() - stockHandleDetails.getHandleNumber());
                    partsStock.setBadNumber(partsStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                } else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品到报废品
                    partsStock.setBadNumber(partsStock.getBadNumber() + stockHandleDetails.getHandleNumber());
                    partsStock.setScrapNumber(partsStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品到不良品
                    partsStock.setGoodNumber(partsStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    partsStock.setBadNumber(partsStock.getBadNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品到报废品
                    partsStock.setGoodNumber(partsStock.getGoodNumber() + stockHandleDetails.getHandleNumber());
                    partsStock.setScrapNumber(partsStock.getScrapNumber() - stockHandleDetails.getHandleNumber());
                } else if (StockConstants.CLEAN_SCRAP.equals(handleStatus)) {
                    partsStock.setScrapNumber(partsStock.getScrapNumber() + stockHandleDetails.getHandleNumber());
                }
                partsStock.setLastUpdate(new Date());
                partsStockMapper.updatePartsStock(partsStock);
            }

            // 更新库存内部管理明细为作废状态
            stockHandleDetailsMapper.deleteStockHandleDetailsById(stockHandleDetails.getId());
        }
        return stockHandleMapper.deleteStockHandleById(id);
    }
}
