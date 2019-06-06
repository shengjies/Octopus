package com.ruoyi.project.erp.productIntoStockDetails.mapper;

import com.ruoyi.common.validator.ValidatorUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 产品入库清单 数据层
 *
 * @author zqm
 * @date 2019-05-08
 */
public interface ProductIntoStockDetailsMapper {
    /**
     * 查询产品入库清单信息
     *
     * @param id 产品入库清单ID
     * @return 产品入库清单信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductIntoStockDetails selectProductIntoStockDetailsById(Integer id);

    /**
     * 查询产品入库清单列表
     *
     * @param productIntoStockDetails 产品入库清单信息
     * @return 产品入库清单集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductIntoStockDetails> selectProductIntoStockDetailsList(ProductIntoStockDetails productIntoStockDetails);

    /**
     * 新增产品入库清单
     *
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails);

    /**
     * 修改产品入库清单
     *
     * @param productIntoStockDetails 产品入库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductIntoStockDetails(ProductIntoStockDetails productIntoStockDetails);

    /**
     * 删除产品入库清单
     *
     * @param id 产品入库清单ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductIntoStockDetailsById(Integer id);

    /**
     * 批量删除产品入库清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductIntoStockDetailsByIds(String[] ids);

    /**
     * 通过产品退货单主键查询退货单详情列表
     * @param intoId 退货单主键
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<ProductIntoStockDetails> selectProductIntoStockDetailsByIntoId(Integer intoId);

    /**
     * 根据公司id和客户id查询对应时间段内客户退货记录信息
     * @param company_id 公司id
     * @param cid 客户id
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<ProductIntoStockDetails> selectProductIntoStockDetailsByCompanyIdAndCid(@Param("company_id")int company_id, @Param("cid")int cid,
                                                                                 @Param("bTime")Date bTime,@Param("eTime")Date eTime);
}