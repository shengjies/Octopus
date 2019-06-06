package com.ruoyi.project.erp.productCustomer.service;

import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;

import java.util.List;

/**
 * 产品客户关联 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IProductCustomerService {
    /**
     * 查询产品客户关联信息
     *
     * @param id 产品客户关联ID
     * @return 产品客户关联信息
     */
    public ProductCustomer selectProductCustomerById(Integer id);

    /**
     * 查询产品客户关联列表
     *
     * @param productCustomer 产品客户关联信息
     * @return 产品客户关联集合
     */
    public List<ProductCustomer> selectProductCustomerList(ProductCustomer productCustomer);

    /**
     * 新增产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    public int insertProductCustomer(ProductCustomer productCustomer);

    /**
     * 修改产品客户关联
     *
     * @param productCustomer 产品客户关联信息
     * @return 结果
     */
    public int updateProductCustomer(ProductCustomer productCustomer);

    /**
     * 删除产品客户关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductCustomerByIds(String ids);

    /**
     * 根据客户编号和产品编号查询对应的客户编号
     * @param cid 客户编号
     * @param pid 产品编号
     * @return
     */
    ProductCustomer findCustomerCode(int cid,int pid);

}
