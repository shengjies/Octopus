package com.ruoyi.project.erp.productOutStockDetails.service;

import java.util.List;

import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;
import com.ruoyi.project.erp.productOutStock.mapper.ProductOutStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productOutStockDetails.mapper.ProductOutStockDetailsMapper;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import com.ruoyi.project.erp.productOutStockDetails.service.IProductOutStockDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 产品出库清单 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class ProductOutStockDetailsServiceImpl implements IProductOutStockDetailsService {
    @Autowired
    private ProductOutStockDetailsMapper productOutStockDetailsMapper;

    @Autowired
    private ProductOutStockMapper productOutStockMapper; // 产品出库主表数据层

    /**
     * 查询产品出库清单信息
     *
     * @param id 产品出库清单ID
     * @return 产品出库清单信息
     */
    @Override
    public ProductOutStockDetails selectProductOutStockDetailsById(Integer id) {
        return productOutStockDetailsMapper.selectProductOutStockDetailsById(id);
    }

    /**
     * 查询产品出库清单列表
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 产品出库清单集合
     */
    @Override
    public List<ProductOutStockDetails> selectProductOutStockDetailsList(ProductOutStockDetails productOutStockDetails) {
        ProductOutStock productOutStock = null;
        List<ProductOutStockDetails> productOutStockDetailsList = productOutStockDetailsMapper.selectProductOutStockDetailsList(productOutStockDetails);
        for (ProductOutStockDetails outStockDetails : productOutStockDetailsList) {
            productOutStock = productOutStockMapper.selectProductOutStockById(outStockDetails.getOutId());
            outStockDetails.setOutName(productOutStock.getCreateName());
            outStockDetails.setCustomerName(productOutStock.getCustomerName());
        }
        return productOutStockDetailsList;
    }

    /**
     * 新增产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    @Override
    public int insertProductOutStockDetails(ProductOutStockDetails productOutStockDetails) {
        return productOutStockDetailsMapper.insertProductOutStockDetails(productOutStockDetails);
    }

    /**
     * 修改产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    @Override
    public int updateProductOutStockDetails(ProductOutStockDetails productOutStockDetails) {
        return productOutStockDetailsMapper.updateProductOutStockDetails(productOutStockDetails);
    }

    /**
     * 删除产品出库清单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductOutStockDetailsByIds(String ids) {
        return productOutStockDetailsMapper.deleteProductOutStockDetailsByIds(Convert.toStrArray(ids));
    }

}
