package com.ruoyi.project.erp.purchase.service;

import com.ruoyi.project.erp.contract.domain.Contract;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 采购单 服务层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface IPurchaseService 
{
	/**
     * 查询采购单信息
     * 
     * @param id 采购单ID
     * @return 采购单信息
     */
	public Purchase selectPurchaseById(Integer id);

	
	/**
     * 查询采购单列表
     * 
     * @param purchase 采购单信息
     * @return 采购单集合
     */
	public List<Purchase> selectPurchaseList(Purchase purchase);
	
	/**
     * 新增采购单
     * 
     * @param purchase 采购单信息
     * @return 结果
     */
	public int insertPurchase(Purchase purchase);
	
	/**
     * 修改采购单
     * 
     * @param purchase 采购单信息
     * @return 结果
     */
	public int updatePurchase(Purchase purchase);
		
	/**
     * 删除采购单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseByIds(String ids);

	/**
	 * 操作采购单状态
	 * @param purchase 采购单对象
	 * @return
	 */
	int editStatus(Purchase purchase);

	/**
	 * 查询存在预收库存的采购单信息
	 * @param supplierId 供应商id
	 * @return 结果
	 */
    List<Purchase> selectPurchaseHavePreNumberBySupId(Integer supplierId);

	/**
	 * 关闭采购单
	 * @param purchase 采购单
	 * @return 结果
	 */
	int closePurchase(Purchase purchase);

	/**
	 * 导出采购单明细
	 * @param id 采购单主键
	 * @return 结果
	 */
    Workbook uploadPurchase(Integer id);
}
