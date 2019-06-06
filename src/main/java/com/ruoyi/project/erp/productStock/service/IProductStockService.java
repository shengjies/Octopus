package com.ruoyi.project.erp.productStock.service;

import com.ruoyi.project.erp.productStock.domain.ProductStock;

import java.util.List;

/**
 * 产品库存 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductStockService {
    /**
     * 查询产品库存信息
     *
     * @param id 产品库存ID
     * @return 产品库存信息
     */
    public ProductStock selectProductStockById(Integer id);

    /**
     * 查询产品库存列表
     *
     * @param productStock 产品库存信息
     * @return 产品库存集合
     */
    public List<ProductStock> selectProductStockList(ProductStock productStock);

    /**
     * 新增产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    public int insertProductStock(ProductStock productStock);

    /**
     * 修改产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    public int updateProductStock(ProductStock productStock);

    /**
     * 删除产品库存信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductStockByIds(String ids);

    /**
     * 通过产品id查询产品库存信息
     * @param attrId 产品id
     * @return 结果
     */
    ProductStock selectProductStockByProId(Integer attrId);
}
