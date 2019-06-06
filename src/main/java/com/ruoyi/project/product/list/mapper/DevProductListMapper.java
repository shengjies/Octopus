package com.ruoyi.project.product.list.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.product.list.domain.DevProductList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品管理 数据层
 *
 * @author ruoyi
 * @date 2019-04-10
 */
public interface DevProductListMapper {
    /**
     * 查询产品管理信息
     *
     * @param id 产品管理ID
     * @return 产品管理信息
     */
    @DataSource(value = DataSourceType.SLAVE)
    public DevProductList selectDevProductListById(Integer id);

    /**
     * 根据产品编码查询对应的产品是否存在
     *
     * @param code
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    DevProductList selectDevProductByCode(@Param("company_id") int company_id, @Param("code") String code);

    /**
     * 查询产品管理列表
     *
     * @param devProductList 产品管理信息
     * @return 产品管理集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    public List<DevProductList> selectDevProductListList(DevProductList devProductList);

    /**
     * 新增产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    public int insertDevProductList(DevProductList devProductList);

    /**
     * 修改产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    public int updateDevProductList(DevProductList devProductList);

    /**
     * 删除产品管理
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deleteDevProductListById(Integer id);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteDevProductListByIds(String[] ids);

    /**
     * 查询所属公司所有的产品信息
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevProductList> selectProductAllByCompanyId(@Param("companyId") Integer companyId);

    /**
     * 通过产品id查询产品信息
     * @param productId 产品id
     * @return 产品信息
     */
    DevProductList findProductInfo(@Param("productId") Integer productId);

    /**
     * 通过产品编码查询产品信息
     * @param productCode 产品编码
     * @return 产品信息
     */
    @DataSource(value = DataSourceType.SLAVE)
    DevProductList selectProductByCode(@Param("productCode") String productCode);

    /**
     * 查询产品编码是否为一
     * @param productCode 产品编码
     * @param companyId 公司id
     * @return 查询结果数量
     */
    @DataSource(value = DataSourceType.SLAVE)
    DevProductList checkProductCodeUnique(@Param("productCode") String productCode, @Param("companyId") Integer companyId);

    /**
     * 查询客户关联的产品
     * @param pid 产品编号集合
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevProductList> findCustomerProduct(@Param("array") List<Integer> pid);

    /**
     * 根据订单id查询对应的产品信息
     * @param orderId 订单id
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<DevProductList> findProductByOrderId(@Param("orderId")int orderId);

    /**
     * 根据产品编码查询对应公司的产品信息
     * @param companyId 公司id
     * @param productCode 产品编码
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    DevProductList findByCompanyIdAndProductCode(@Param("companyId")int companyId,
                                                 @Param("productCode")String productCode);

    /**
     * 查询产品名称信息
     * @param companyId 公司id
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevProductList> selectProNameAllByComId(@Param("companyId") Integer companyId);

}