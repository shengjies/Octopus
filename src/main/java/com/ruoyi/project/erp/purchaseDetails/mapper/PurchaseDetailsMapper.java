package com.ruoyi.project.erp.purchaseDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购清单 数据层
 *
 * @author zqm
 * @date 2019-05-10
 */
public interface PurchaseDetailsMapper
{
	/**
     * 查询采购清单信息
     *
     * @param id 采购清单ID
     * @return 采购清单信息
     */
	public PurchaseDetails selectPurchaseDetailsById(Integer id);

	/**
     * 查询采购清单列表
     *
     * @param purchaseDetails 采购清单信息
     * @return 采购清单集合
     */
	@DataSource(DataSourceType.ERP)
	public List<PurchaseDetails> selectPurchaseDetailsList(PurchaseDetails purchaseDetails);

	/**
     * 新增采购清单
     *
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int insertPurchaseDetails(PurchaseDetails purchaseDetails);


	/**
	 * 对查询详情进行标记
	 * @param pid 采购id
	 * @param sign 标记状态  0 或 1
	 * @return
	 */
	@DataSource(DataSourceType.ERP)
	int editPurchaseSign(@Param("pid")int pid,@Param("sign") int sign);

	/**
	 * 删除所以标记为1的采购详情信息
	 * @param pid 采购id
	 * @return
	 */
	@DataSource(DataSourceType.ERP)
	int deletePurchaseDetailsBySign(@Param("pid")int pid);

	/**
	 * 根据采购id和物料编码查询对应的详情
	 * @param pid 采购id
	 * @param mcode 物料编码
	 * @return
	 */
	@DataSource(DataSourceType.ERP)
	PurchaseDetails selectDetailByPidAndMCode(@Param("pid")int pid,@Param("mcode")String mcode);

	/**
     * 修改采购清单
     *
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
    @DataSource(DataSourceType.ERP)
	public int updatePurchaseDetails(PurchaseDetails purchaseDetails);

	/**
     * 删除采购清单
     *
     * @param id 采购清单ID
     * @return 结果
     */
	public int deletePurchaseDetailsById(Integer id);

	/**
     * 批量删除采购清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseDetailsByIds(String[] ids);

	/**
	 * 查询采购单明细列表
	 * @param companyId 公司id
	 * @param supplierId 供应商id
	 * @param purchaseId 采购单id
	 * @param materielCode 物料编码
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
    List<PurchaseDetails> selectPurchaseDetailsListBySidAndMatCode(@Param("companyId") Integer companyId,
																   @Param("supplierId") Integer supplierId,
																   @Param("purchaseId") Integer purchaseId,
																   @Param("materielCode") String materielCode);

	/**
	 * 查询采购详情信息
	 * @param purchaseId 采购主键
	 * @param purchaseCode 采购单号
	 * @param supplierCode 供应商物料编码
	 * @param materielCode 物料编码
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
    PurchaseDetails selectPurchaseDetailsByCode(@Param("purchaseId") Integer purchaseId,
												@Param("purchaseCode") String purchaseCode,
												@Param("supplierCode") String supplierCode,
												@Param("materielCode") String materielCode);

	/**
	 * 查询存在预收库存的采购详情信息
	 * @param companyId 公司id
	 * @param purchaseId 采购单
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	List<PurchaseDetails> selectDetailsHavePreByPurId(@Param("companyId") Integer companyId, @Param("purchaseId") Integer purchaseId);
}