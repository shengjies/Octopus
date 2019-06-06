package com.ruoyi.project.erp.productOutStock.service;

import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 产品出库 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductOutStockService {
    /**
     * 查询产品出库信息
     *
     * @param id 产品出库ID
     * @return 产品出库信息
     */
    public ProductOutStock selectProductOutStockById(Integer id,HttpServletRequest request);

    /**
     * 查询产品出库列表
     *
     * @param productOutStock 产品出库信息
     * @return 产品出库集合
     */
    public List<ProductOutStock> selectProductOutStockList(ProductOutStock productOutStock, HttpServletRequest request);

    /**
     * 新增产品出库
     *
     * @param productOutStock 产品出库信息
     * @return 结果
     */
    public int insertProductOutStock(ProductOutStock productOutStock,HttpServletRequest request);

    /**
     * 修改产品出库
     *
     * @param productOutStock 产品出库信息
     * @return 结果
     */
    public int updateProductOutStock(ProductOutStock productOutStock);

    /**
     * 删除产品出库信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductOutStockByIds(String ids);

    /**
     * 作废产品出库信息
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    int nullifyProductOutStockById(Integer id,HttpServletRequest request);
}
