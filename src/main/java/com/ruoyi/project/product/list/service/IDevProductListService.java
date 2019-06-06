package com.ruoyi.project.product.list.service;

import com.ruoyi.project.product.list.domain.DevProductList;

import java.util.List;

/**
 * 产品管理 服务层
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
public interface IDevProductListService 
{
	/**
     * 查询产品管理信息
     * 
     * @param id 产品管理ID
     * @return 产品管理信息
     */
	public DevProductList selectDevProductListById(Integer id);
	
	/**
     * 查询产品管理列表
     * 
     * @param devProductList 产品管理信息
     * @return 产品管理集合
     */
	public List<DevProductList> selectDevProductListList(DevProductList devProductList);
	
	/**
     * 新增产品管理
     * 
     * @param devProductList 产品管理信息
     * @return 结果
     */
	public int insertDevProductList(DevProductList devProductList);
	
	/**
     * 修改产品管理
     * 
     * @param devProductList 产品管理信息
     * @return 结果
     */
	public int updateDevProductList(DevProductList devProductList);
		
	/**
     * 删除产品管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDevProductListByIds(String ids);

	/**
	 * 导入产品数据
	 * @param list
	 * @param isUpdateSupport
	 * @return
	 */
	String importProduct(List<DevProductList> list,boolean isUpdateSupport);

	/**
	 * 查询所属公司所有的产品信息
	 * @return
	 */
	public List<DevProductList> selectProductAllByCompanyId();

	/**
	 * 通过产品id查询产品信息
	 * @param productId
	 * @return
	 */
	public DevProductList findProductInfo(Integer productId);

	/**
	 * 检验产品编码是否唯一
	 * @param product
	 * @return
	 */
	String checkProductCodeUnique(DevProductList product);

	/**
	 * 通过客户id查询相关联的产品信息
	 * @param customerId 客户id
	 * @return 结果
	 */
    List<DevProductList> findProductByCustomerId(Integer customerId);

	/**
	 * 通过产品id和客户id查询产品信息
	 * @param productId 产品id
	 * @param customerId 客户id
	 * @return 结果
	 */
	DevProductList findProductByProIdAndCusId(Integer productId, Integer customerId);

	/**
	 * 根据客户编号查询对应产品
	 * @param customerId 客户编号
	 * @return
	 */
	List<DevProductList> selectProductByCustomerId(int customerId);

	/**
	 * ecn 信息操作
	 * @param productList
	 * @return
	 */
	int ecnChange(DevProductList productList);

	/**
	 * 根据订单id查询对应的产品信息
	 * @param orderId 订单id
	 * @return
	 */
	List<DevProductList> selectProductAllByOrderId(int orderId);

	/**
	 * 查询各公司的产品名称信息
	 * @return 结果
	 */
	List<DevProductList> selectProNameAllByComId();

	/**
	 * 根据公司id和产品编号查询对应的产品信息
	 * @param companyId 公司id
	 * @param code 产品编号
	 * @return
	 */
	DevProductList selectProductByCompanyIdAndCode(int companyId,String code);
}
