package com.ruoyi.project.erp.customer.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.customer.mapper.CustomerMapper;
import com.ruoyi.project.erp.customer.domain.Customer;
import com.ruoyi.project.erp.customer.service.ICustomerService;
import com.ruoyi.common.support.Convert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 客户数据 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service("customer")
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询客户数据信息
     *
     * @param id 客户数据ID
     * @return 客户数据信息
     */
    @Override
    public Customer selectCustomerById(Integer id) {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户数据列表
     *
     * @param customer 客户数据信息
     * @return 客户数据集合
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer, HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        if (sysUser == null) return Collections.emptyList();
        if (!User.isSys(sysUser)) {
            customer.setCompanyId(sysUser.getCompanyId()); // 查询自己公司的客户
        }
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer,HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        customer.setCompanyId(sysUser.getCompanyId()); // 所属公司
        customer.setCreateId(sysUser.getUserId().intValue()); // 创建者ID
        customer.setCreateName(sysUser.getUserName()); // 创建者名称
        customer.setCreateTime(new Date()); // 创建时间
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户数据
     *
     * @param customer 客户数据信息
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 删除客户数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(String ids) {
        return customerMapper.deleteCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询对应公司的所有客户
     * @return
     */
    public List<Customer> selectCustomerAllByCompanyId(Cookie[] cookies){
        Customer customer = new Customer();
        customer.setCompanyId(JwtUtil.getTokenCookie(cookies).getCompanyId());
        return customerMapper.selectCustomerList(customer);
    }
}
