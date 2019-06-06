package com.ruoyi.project.erp.productOutStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 产品出库 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductOutStockMapper {
    /**
     * 查询产品出库信息
     *
     * @param id 产品出库ID
     * @return 产品出库信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductOutStock selectProductOutStockById(Integer id);

    /**
     * 查询产品出库列表
     *
     * @param productOutStock 产品出库信息
     * @return 产品出库集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductOutStock> selectProductOutStockList(ProductOutStock productOutStock);

    /**
     * 新增产品出库
     *
     * @param productOutStock 产品出库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductOutStock(ProductOutStock productOutStock);

    /**
     * 修改产品出库
     *
     * @param productOutStock 产品出库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductOutStock(ProductOutStock productOutStock);

    /**
     * 删除产品出库
     *
     * @param id 产品出库ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductOutStockById(Integer id);

    /**
     * 批量删除产品出库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductOutStockByIds(String[] ids);

    /**
     * 通过产品出库单和产品编码查询产品出库单信息
     *
     * @param outCode    产品出库单
     * @param customerId 客户id
     * @return 产品出库单信息
     */
    @DataSource(value = DataSourceType.ERP)
    ProductOutStock selectProductOutStockByOutCodeAndCusId(@Param("outCode") String outCode, @Param("customerId") Integer customerId);

    /**
     * 判断产品出库单号是否重复
     *
     * @param outCode   出库单
     * @param companyId 公司id
     * @return
     */
    @DataSource(value = DataSourceType.ERP)
    ProductOutStock checkProductOutCodeUnique(@Param("outCode") String outCode, @Param("companyId") Integer companyId);


}