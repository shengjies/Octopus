package com.ruoyi.project.erp.productCustomer.mapper;

import com.ruoyi.common.validator.ValidatorUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品客户关联 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ProductCustomerMapper {
    /**
     * 查询产品客户关联信息
     *
     * @param id 产品客户关联ID
     * @return 产品客户关联信息
     */
    @DataSource(value = DataSourceType.ERP)
    public ProductCustomer selectProductCustomerById(Integer id);

    /**
     * 查询产品客户关联列表
     *
     * @param productCustomer 产品客户关联信息
     * @return 产品客户关联集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<ProductCustomer> selectProductCustomerList(ProductCustomer productCustomer);

    /**
     * 新增产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertProductCustomer(ProductCustomer productCustomer);

    /**
     * 修改产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateProductCustomer(ProductCustomer productCustomer);

    /**
     * 删除产品客户关联
     *
     * @param id 产品客户关联ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductCustomerById(Integer id);

    /**
     * 批量删除产品客户关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteProductCustomerByIds(String[] ids);

    /**
     * 校验对应客户的产品编码唯一性
     *
     * @param customerId   客户id
     * @param customerCode 客户产品编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int checkCustomerCodeUnique(@Param("customerId") Integer customerId, @Param("customerCode") String customerCode);

    /**
     * 校验产品是否已经关联过该客户
     *
     * @param productId  产品id
     * @param customerId 客户id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int checkProductUnique(@Param("productId") Integer productId, @Param("customerId") Integer customerId);

    /**
     * 查询产品编号
     * @param customerId 客户id
     * @return
     */
    @DataSource(DataSourceType.ERP)
    @Select("SELECT product_id FROM tab_product_customer where customer_id = #{customerId}")
    List<Integer> selectProductIdByCustomerId(@Param("customerId")int customerId);

    /**
     * 查询产品客户关联信息
     * @param productId 产品id
     * @param customerId 客户id
     * @return 结果
     */
    @DataSource(DataSourceType.ERP)
    List<ProductCustomer> selectProductCustomerByProIdOrCusId(@Param("productId") Integer productId, @Param("customerId") Integer customerId);

    /**
     * 根据客户编号和产品编号查询对应的客户编号
     * @param cid 客户编号
     * @param pid 产品编号
     * @return
     */
    @DataSource(DataSourceType.ERP)
    ProductCustomer findCustomerCode(@Param("cid")int cid,@Param("pid")int pid);
}