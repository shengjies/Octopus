package com.ruoyi.project.erp.stockHandleDetails.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.stockHandleDetails.mapper.StockHandleDetailsMapper;
import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import com.ruoyi.project.erp.stockHandleDetails.service.IStockHandleDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 内部调整明细 服务层实现
 * 
 * @author zqm
 * @date 2019-05-27
 */
@Service
public class StockHandleDetailsServiceImpl implements IStockHandleDetailsService 
{
	@Autowired
	private StockHandleDetailsMapper stockHandleDetailsMapper;

	/**
     * 查询内部调整明细信息
     * 
     * @param id 内部调整明细ID
     * @return 内部调整明细信息
     */
    @Override
	public StockHandleDetails selectStockHandleDetailsById(Integer id)
	{
	    return stockHandleDetailsMapper.selectStockHandleDetailsById(id);
	}
	
	/**
     * 查询内部调整明细列表
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 内部调整明细集合
     */
	@Override
	public List<StockHandleDetails> selectStockHandleDetailsList(StockHandleDetails stockHandleDetails)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null) {
		    return Collections.emptyList();
		}
		stockHandleDetails.setCompanyId(user.getCompanyId());
		return stockHandleDetailsMapper.selectStockHandleDetailsList(stockHandleDetails);
	}
	
    /**
     * 新增内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	@Override
	public int insertStockHandleDetails(StockHandleDetails stockHandleDetails)
	{
	    return stockHandleDetailsMapper.insertStockHandleDetails(stockHandleDetails);
	}
	
	/**
     * 修改内部调整明细
     * 
     * @param stockHandleDetails 内部调整明细信息
     * @return 结果
     */
	@Override
	public int updateStockHandleDetails(StockHandleDetails stockHandleDetails)
	{
	    return stockHandleDetailsMapper.updateStockHandleDetails(stockHandleDetails);
	}

	/**
     * 删除内部调整明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStockHandleDetailsByIds(String ids)
	{
		return stockHandleDetailsMapper.deleteStockHandleDetailsByIds(Convert.toStrArray(ids));
	}
	
}
