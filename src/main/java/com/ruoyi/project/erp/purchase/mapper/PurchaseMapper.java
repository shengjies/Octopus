package com.ruoyi.project.erp.purchase.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import com.ruoyi.project.erp.purchase.domain.PurchaseResult;
import org.apache.ibatis.annotations.Param;
import sun.java2d.pipe.ValidatePipe;

import java.util.List;

/**
 * 采购单 数据层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface PurchaseMapper 
{
	/**
     * 查询采购单信息
     * 
     * @param id 采购单ID
     * @return 采购单信息
     */
	@DataSource(DataSourceType.ERP)
	public Purchase selectPurchaseById(Integer id);
	
	/**
     * 查询采购单列表
     * 
     * @param purchase 采购单信息
     * @return 采购单集合
     */
	@DataSource(DataSourceType.ERP)
	public List<Purchase> selectPurchaseList(Purchase purchase);
	
	/**
     * 新增采购单
     * 
     * @param purchase 采购单信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int insertPurchase(Purchase purchase);
	
	/**
     * 修改采购单
     * 
     * @param purchase 采购单信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int updatePurchase(Purchase purchase);
	
	/**
     * 删除采购单
     * 
     * @param id 采购单ID
     * @return 结果
     */
	public int deletePurchaseById(Integer id);
	
	/**
     * 批量删除采购单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseByIds(String[] ids);

	/**
	 * 物料入库操作
	 * @param companyId 公司id
	 * @param supplierId 供应商id
	 * @param materielCode 物料编码
	 * @param supplierCode 供应商物料编码
	 * @return 采购单封装结果
	 */
	@DataSource(value = DataSourceType.ERP)
	PurchaseResult selectPurchaseBySupIdAndCode(@Param("companyId") Integer companyId,
												@Param("supplierId") Integer supplierId,
												@Param("materielCode") String materielCode,
												@Param("supplierCode") String supplierCode);

	/**
	 * 查询采购单信息
	 * @param companyId 公司id
	 * @param supplierId 供应商id
	 * @param purchaseCode 采购单号
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	Purchase selectPurchaseBySupIdAndPuraseCode(@Param("companyId") Integer companyId,
												@Param("supplierId") Integer supplierId,
												@Param("purchaseCode") String purchaseCode);

	/**
	 * 查询已审核未完成交付的采购单信息
	 * @param supplierId 供应商id
	 * @param companyId 公司id
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	List<Purchase> selectPurchaseBySupIdAndComId(@Param("supplierId") Integer supplierId,
												 @Param("companyId") Integer companyId);

	/**
	 * 查询存在预收库存的采购单信息
	 * @param companyId 公司id
	 * @param supplierId 供应商id
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	List<Purchase> selectPurchaseHavePreNumberBySupId(@Param("companyId") Integer companyId,@Param("supplierId") Integer supplierId);

	/**
	 * 通过采购单交付状态查询最新的一条记录
	 * @param companyId 公司id
	 * @param supplierId 供应商id
	 * @param purchaseStatut 交付状态
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	Purchase selectPurchaseListByStatusInThree(@Param("companyId") Integer companyId,
											   @Param("supplierId") Integer supplierId,
											   @Param("purchaseStatut") Integer purchaseStatut);
}