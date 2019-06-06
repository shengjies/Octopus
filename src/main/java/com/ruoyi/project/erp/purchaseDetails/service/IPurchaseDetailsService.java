package com.ruoyi.project.erp.purchaseDetails.service;

import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import java.util.List;

/**
 * 采购清单 服务层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface IPurchaseDetailsService 
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
	public List<PurchaseDetails> selectPurchaseDetailsList(PurchaseDetails purchaseDetails);
	
	/**
     * 新增采购清单
     * 
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
	public int insertPurchaseDetails(PurchaseDetails purchaseDetails);
	
	/**
     * 修改采购清单
     * 
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
	public int updatePurchaseDetails(PurchaseDetails purchaseDetails);
		
	/**
     * 删除采购清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseDetailsByIds(String ids);

	/**
	 * 通过采购单id查询存在预收库存的采购单明细信息
	 * @param purchaseId 采购单id
	 * @return 结果
	 */
    List<PurchaseDetails> selectDetailsHavePreByPurId(Integer purchaseId);
}
