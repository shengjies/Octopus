package com.ruoyi.project.erp.customer.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.customer.domain.Customer;

import java.util.List;

/**
 * 客户数据 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface CustomerMapper {
    /**
     * 查询客户数据信息
     *
     * @param id 客户数据ID
     * @return 客户数据信息
     */
    @DataSource(value = DataSourceType.ERP)
    public Customer selectCustomerById(Integer id);

    /**
     * 查询客户数据列表
     *
     * @param customer 客户数据信息
     * @return 客户数据集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertCustomer(Customer customer);

    /**
     * 修改客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateCustomer(Customer customer);

    /**
     * 删除客户数据
     *
     * @param id 客户数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteCustomerById(Integer id);

    /**
     * 批量删除客户数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteCustomerByIds(String[] ids);

}