package com.ruoyi.project.erp.purchaseDetails.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.purchaseDetails.mapper.PurchaseDetailsMapper;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.service.IPurchaseDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 采购清单 服务层实现
 * 
 * @author zqm
 * @date 2019-05-10
 */
@Service
public class PurchaseDetailsServiceImpl implements IPurchaseDetailsService 
{
	@Autowired
	private PurchaseDetailsMapper purchaseDetailsMapper;

	/**
     * 查询采购清单信息
     * 
     * @param id 采购清单ID
     * @return 采购清单信息
     */
    @Override
	public PurchaseDetails selectPurchaseDetailsById(Integer id)
	{
	    return purchaseDetailsMapper.selectPurchaseDetailsById(id);
	}
	
	/**
     * 查询采购清单列表
     * 
     * @param purchaseDetails 采购清单信息
     * @return 采购清单集合
     */
	@Override
	public List<PurchaseDetails> selectPurchaseDetailsList(PurchaseDetails purchaseDetails)
	{
	    return purchaseDetailsMapper.selectPurchaseDetailsList(purchaseDetails);
	}
	
    /**
     * 新增采购清单
     * 
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
	@Override
	public int insertPurchaseDetails(PurchaseDetails purchaseDetails)
	{
	    return purchaseDetailsMapper.insertPurchaseDetails(purchaseDetails);
	}
	
	/**
     * 修改采购清单
     * 
     * @param purchaseDetails 采购清单信息
     * @return 结果
     */
	@Override
	public int updatePurchaseDetails(PurchaseDetails purchaseDetails)
	{
	    return purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);
	}

	/**
     * 删除采购清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePurchaseDetailsByIds(String ids)
	{
		return purchaseDetailsMapper.deletePurchaseDetailsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 通过采购单id查询存在预收库存的采购单明细信息
	 * @param purchaseId 采购单id
	 * @return 结果
	 */
	@Override
	public List<PurchaseDetails> selectDetailsHavePreByPurId(Integer purchaseId) {
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
		    return Collections.emptyList();
		}
		return purchaseDetailsMapper.selectDetailsHavePreByPurId(user.getCompanyId(),purchaseId);
	}
}
