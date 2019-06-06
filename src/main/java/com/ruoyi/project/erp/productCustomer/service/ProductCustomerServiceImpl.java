package com.ruoyi.project.erp.productCustomer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.customer.mapper.CustomerMapper;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.orderDetails.mapper.OrderDetailsMapper;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import com.ruoyi.project.erp.orderInfo.mapper.OrderInfoMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productCustomer.mapper.ProductCustomerMapper;
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import com.ruoyi.project.erp.productCustomer.service.IProductCustomerService;
import com.ruoyi.common.support.Convert;

/**
 * 产品客户关联 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class ProductCustomerServiceImpl implements IProductCustomerService {
    @Autowired
    private ProductCustomerMapper productCustomerMapper;

    @Autowired
    private CustomerMapper customerMapper; // 客户数据层

    @Autowired
    private DevProductListMapper productListMapper; // 产品数据层

    @Autowired
    private OrderInfoMapper orderInfoMapper; // 订单数据层

    @Autowired
    private OrderDetailsMapper orderDetailsMapper; // 订单详情数据层

    @Autowired
    private DevProductListMapper productMapper; // 产品数据层

    /**
     * 查询产品客户关联信息
     *
     * @param id 产品客户关联ID
     * @return 产品客户关联信息
     */
    @Override
    public ProductCustomer selectProductCustomerById(Integer id) {
        return productCustomerMapper.selectProductCustomerById(id);
    }

    /**
     * 查询产品客户关联列表
     *
     * @param productCustomer 产品客户关联信息
     * @return 产品客户关联集合
     */
    @Override
    public List<ProductCustomer> selectProductCustomerList(ProductCustomer productCustomer) {
        List<ProductCustomer> productCustomers = productCustomerMapper.selectProductCustomerList(productCustomer);
        for (ProductCustomer productCustomer1 : productCustomers) {
            productCustomer1.setCustomer(customerMapper.selectCustomerById(productCustomer1.getCustomerId()));
            productCustomer1.setProductList(productListMapper.selectDevProductListById(productCustomer1.getProductId()));
        }
        return productCustomers;
    }

    /**
     * 新增产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    @Override
    public int insertProductCustomer(ProductCustomer productCustomer) {

        // 判断客户是否存在相同的产品编码
        int count = productCustomerMapper.checkCustomerCodeUnique(productCustomer.getCustomerId(), productCustomer.getCustomerCode());
        if (count > 0) { // 数据库存在记录
            throw new BusinessException("该客户已经存在该产品编码");
        }
        // 判断该产品是否已经录入过
        int count1 = productCustomerMapper.checkProductUnique(productCustomer.getProductId(), productCustomer.getCustomerId());
        if (count1 > 0) { // 数据库存在记录
            throw new BusinessException("该产品已经关联过该客户");
        }
        productCustomer.setCreateId(ShiroUtils.getUserId().intValue()); // 创建者id
        productCustomer.setCreateTime(new Date()); // 创建时间
        return productCustomerMapper.insertProductCustomer(productCustomer);
    }

    /**
     * 修改产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    @Override
    public int updateProductCustomer(ProductCustomer productCustomer) {
        return productCustomerMapper.updateProductCustomer(productCustomer);
    }

    /**
     * 删除产品客户关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductCustomerByIds(String ids) {
        return productCustomerMapper.deleteProductCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据客户编号和产品编号查询对应的客户编号
     *
     * @param cid 客户编号
     * @param pid 产品编号
     * @return
     */
    @Override
    public ProductCustomer findCustomerCode(int cid, int pid) {
        // 产品信息
        DevProductList product = productMapper.selectDevProductListById(pid);
        // 客户产品关联
        ProductCustomer customerCode = productCustomerMapper.findCustomerCode(cid, pid);
        // 查询各公司已审核未交付完成的订单明细信息
        List<OrderDetails> orderDetailsList = orderDetailsMapper.selectOrderDetailsListByProIdAndCusId(ShiroUtils.getCompanyId(), cid, pid, StockConstants.ORDER_STATUS_TWO);
        customerCode.setOrderDetails(orderDetailsList);
        return customerCode;
    }
}
