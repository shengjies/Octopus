package com.ruoyi.project.erp.customer.service;

import com.ruoyi.project.erp.customer.domain.Customer;

import java.util.List;

/**
 * 客户数据 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ICustomerService {
    /**
     * 查询客户数据信息
     *
     * @param id 客户数据ID
     * @return 客户数据信息
     */
    public Customer selectCustomerById(Integer id);

    /**
     * 查询客户数据列表
     *
     * @param customer 客户数据信息
     * @return 客户数据集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerByIds(String ids);

}
