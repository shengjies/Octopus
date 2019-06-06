package com.ruoyi.project.erp.productOutStockDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 产品出库清单 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductOutStockDetailsMapper {
    /**
     * 查询产品出库清单信息
     *
     * @param id 产品出库清单ID
     * @return 产品出库清单信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductOutStockDetails selectProductOutStockDetailsById(Integer id);

    /**
     * 查询产品出库清单列表
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 产品出库清单集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductOutStockDetails> selectProductOutStockDetailsList(ProductOutStockDetails productOutStockDetails);

    /**
     * 新增产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductOutStockDetails(ProductOutStockDetails productOutStockDetails);

    /**
     * 修改产品出库清单
     *
     * @param productOutStockDetails 产品出库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductOutStockDetails(ProductOutStockDetails productOutStockDetails);

    /**
     * 删除产品出库清单
     *
     * @param id 产品出库清单ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductOutStockDetailsById(Integer id);

    /**
     * 批量删除产品出库清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductOutStockDetailsByIds(String[] ids);

    /**
     * 通过产品出库单号查询出库详情
     * @param outCode
     * @return
     */
    @DataSource(value = DataSourceType.ERP)
    List<ProductOutStockDetails> selectProductOutStockDetailsByOutCode(String outCode);

    /**
     * 通过产品出库单号修改产品明细信息
     *
     * @param productOutStockDetail 产品出库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int updateProductOutStockDetailsByCode(ProductOutStockDetails productOutStockDetail);

    /**
     * 通过产品出库单id删除产品出库清单
     * @param outId 出库单id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int deleteProductOutStockDetailsByOutId(@Param("outId") Integer outId);

    /**
     * 根据公司id和客户id查询对应时间段内容的出货记录
     * @param cid 客户id
     * @param company_id 公司id
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<ProductOutStockDetails> selectOutStockByCIdAndCompanyId(@Param("cid")int cid, @Param("company_id")int company_id,
                                                          @Param("bTime") Date bTime, @Param("eTime")Date eTime);
}