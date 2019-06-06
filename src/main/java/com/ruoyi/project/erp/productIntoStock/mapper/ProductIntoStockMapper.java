package com.ruoyi.project.erp.productIntoStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productIntoStock.domain.ProductIntoStock;

import java.util.List;

/**
 * 产品入库 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductIntoStockMapper {
    /**
     * 查询产品入库信息
     *
     * @param id 产品入库ID
     * @return 产品入库信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductIntoStock selectProductIntoStockById(Integer id);

    /**
     * 查询产品入库列表
     *
     * @param productIntoStock 产品入库信息
     * @return 产品入库集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductIntoStock> selectProductIntoStockList(ProductIntoStock productIntoStock);

    /**
     * 新增产品入库
     *
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductIntoStock(ProductIntoStock productIntoStock);

    /**
     * 修改产品入库
     *
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductIntoStock(ProductIntoStock productIntoStock);

    /**
     * 删除产品入库
     *
     * @param id 产品入库ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductIntoStockById(Integer id);

    /**
     * 批量删除产品入库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductIntoStockByIds(String[] ids);

}