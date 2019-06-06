package com.ruoyi.project.erp.productStock.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.service.IProductStockService;
import com.ruoyi.common.support.Convert;

/**
 * 产品库存 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;

    /**
     * 查询产品库存信息
     *
     * @param id 产品库存ID
     * @return 产品库存信息
     */
    @Override
    public ProductStock selectProductStockById(Integer id) {
        return productStockMapper.selectProductStockById(id);
    }

    /**
     * 查询产品库存列表
     *
     * @param productStock 产品库存信息
     * @return 产品库存集合
     */
    @Override
    public List<ProductStock> selectProductStockList(ProductStock productStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        productStock.setCompanyId(user.getCompanyId());
        return productStockMapper.selectProductStockList(productStock);
    }

    /**
     * 新增产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    @Override
    public int insertProductStock(ProductStock productStock) {
        return productStockMapper.insertProductStock(productStock);
    }

    /**
     * 修改产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    @Override
    public int updateProductStock(ProductStock productStock) {
        return productStockMapper.updateProductStock(productStock);
    }

    /**
     * 删除产品库存对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductStockByIds(String ids) {
        return productStockMapper.deleteProductStockByIds(Convert.toStrArray(ids));
    }

    @Override
    public ProductStock selectProductStockByProId(Integer attrId) {
        return productStockMapper.selectProductStockByProId(attrId);
    }
}
