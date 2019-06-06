package com.ruoyi.project.erp.productStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品库存 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductStockMapper {
    /**
     * 查询产品库存信息
     *
     * @param id 产品库存ID
     * @return 产品库存信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductStock selectProductStockById(Integer id);

    /**
     * 查询产品库存列表
     *
     * @param productStock 产品库存信息
     * @return 产品库存集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductStock> selectProductStockList(ProductStock productStock);

    /**
     * 新增产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductStock(ProductStock productStock);

    /**
     * 修改产品库存
     *
     * @param productStock 产品库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductStock(ProductStock productStock);

    /**
     * 删除产品库存
     *
     * @param id 产品库存ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductStockById(Integer id);

    /**
     * 批量删除产品库存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductStockByIds(String[] ids);

    /**
     * 通过编码查询库存记录
     *
     * @param companyId 公司id
     * @param productCode 产品编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    ProductStock selectProductStockByProCode(@Param("companyId") Integer companyId, @Param("productCode") String productCode);

    /**
     * 通过产品id 查询产品库存信息
     * @param productId 产品id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    ProductStock selectProductStockByProId(@Param("productId") Integer productId);
}