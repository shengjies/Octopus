package com.ruoyi.project.erp.productOutStockDetails.service;

import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;

import java.util.List;

/**
 * 产品出库清单 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductOutStockDetailsService {
    /**
     * 查询产品出库清单信息
     *
     * @param id 产品出库清单ID
     * @return 产品出库清单信息
     */
    public ProductOutStockDetails selectProductOutStockDetailsById(Integer id);

    /**
     * 查询产品出库清单列表
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 产品出库清单集合
     */
    public List<ProductOutStockDetails> selectProductOutStockDetailsList(ProductOutStockDetails productOutStockDetails);

    /**
     * 新增产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    public int insertProductOutStockDetails(ProductOutStockDetails productOutStockDetails);

    /**
     * 修改产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    public int updateProductOutStockDetails(ProductOutStockDetails productOutStockDetails);

    /**
     * 删除产品出库清单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductOutStockDetailsByIds(String ids);

}
