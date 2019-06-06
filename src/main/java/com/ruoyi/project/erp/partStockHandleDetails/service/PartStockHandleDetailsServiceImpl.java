package com.ruoyi.project.erp.partStockHandleDetails.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.mapper.PartsStockMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.partStockHandleDetails.mapper.PartStockHandleDetailsMapper;
import com.ruoyi.project.erp.partStockHandleDetails.domain.PartStockHandleDetails;
import com.ruoyi.project.erp.partStockHandleDetails.service.IPartStockHandleDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 半成品库存内部调整清单 服务层实现
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class PartStockHandleDetailsServiceImpl implements IPartStockHandleDetailsService 
{
	@Autowired
	private PartStockHandleDetailsMapper partStockHandleDetailsMapper;

	@Autowired
	private PartsStockMapper partsStockMapper; // 半成品库存数据层

	/**
     * 查询半成品库存内部调整清单信息
     * 
     * @param id 半成品库存内部调整清单ID
     * @return 半成品库存内部调整清单信息
     */
    @Override
	public PartStockHandleDetails selectPartStockHandleDetailsById(Integer id)
	{
	    return partStockHandleDetailsMapper.selectPartStockHandleDetailsById(id);
	}
	
	/**
     * 查询半成品库存内部调整清单列表
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 半成品库存内部调整清单集合
     */
	@Override
	public List<PartStockHandleDetails> selectPartStockHandleDetailsList(PartStockHandleDetails partStockHandleDetails)
	{
	    return partStockHandleDetailsMapper.selectPartStockHandleDetailsList(partStockHandleDetails);
	}
	
    /**
     * 新增半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int insertPartStockHandleDetails(PartStockHandleDetails partStockHandleDetails)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
		    return 0;
		}
		partStockHandleDetails.setCompanyId(user.getCompanyId());
		partStockHandleDetails.setHandleBy(user.getUserId().intValue());
		partStockHandleDetails.setHandleName(user.getUserName());
		partStockHandleDetails.setHandleTime(new Date());

		// 处理状态
		Integer handleStatus = partStockHandleDetails.getHandleStatus();
		PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(partStockHandleDetails.getPartId());

		if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品转成不良品
		    if (partsStock.getGoodNumber() < partStockHandleDetails.getHandleNumber()) {
		    	throw new BusinessException("操作数量不能大于实际库存");
		    }
		    partsStock.setGoodNumber(partsStock.getGoodNumber() - partStockHandleDetails.getHandleNumber());
		    partsStock.setBadNumber(partsStock.getBadNumber() + partStockHandleDetails.getHandleNumber());
		    partsStock.setLastUpdate(new Date());
		    partsStockMapper.updatePartsStock(partsStock);

		} else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品转报废品
			if (partsStock.getGoodNumber() < partStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			partsStock.setGoodNumber(partsStock.getGoodNumber() - partStockHandleDetails.getHandleNumber());
			partsStock.setScrapNumber(partsStock.getScrapNumber() + partStockHandleDetails.getHandleNumber());
			partsStock.setLastUpdate(new Date());
			partsStockMapper.updatePartsStock(partsStock);

		} else if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品转良品
			if (partsStock.getBadNumber() < partStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			partsStock.setBadNumber(partsStock.getBadNumber() - partStockHandleDetails.getHandleNumber());
			partsStock.setGoodNumber(partsStock.getGoodNumber() + partStockHandleDetails.getHandleNumber());
			partsStock.setLastUpdate(new Date());
			partsStockMapper.updatePartsStock(partsStock);

		} else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品转报废品
			if (partsStock.getBadNumber() < partStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			partsStock.setBadNumber(partsStock.getBadNumber() - partStockHandleDetails.getHandleNumber());
			partsStock.setScrapNumber(partsStock.getScrapNumber() + partStockHandleDetails.getHandleNumber());
			partsStock.setLastUpdate(new Date());
			partsStockMapper.updatePartsStock(partsStock);
		}
		return partStockHandleDetailsMapper.insertPartStockHandleDetails(partStockHandleDetails);
	}
	
	/**
     * 修改半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int updatePartStockHandleDetails(PartStockHandleDetails partStockHandleDetails)
	{
	    return partStockHandleDetailsMapper.updatePartStockHandleDetails(partStockHandleDetails);
	}

	/**
     * 删除半成品库存内部调整清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePartStockHandleDetailsByIds(String ids)
	{
		return partStockHandleDetailsMapper.deletePartStockHandleDetailsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 清空半成品库存
	 * @param id 半成品库存id
	 * @return 结果
	 */
	@Override
	public int handleScrap(int id) {
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
			return 0;
		}
		PartsStock partsStock = partsStockMapper.selectPartsStockById(id);
		/**
		 * 新增半成品内部调整操作记录
		 */
		PartStockHandleDetails handleDetails = new PartStockHandleDetails();
		handleDetails.setCompanyId(user.getCompanyId());
		handleDetails.setHandleBy(user.getUserId().intValue());
		handleDetails.setHandleName(user.getUserName());
		handleDetails.setHandleTime(new Date());
		handleDetails.setHandleNumber(partsStock.getScrapNumber());
		handleDetails.setHandleType(StockConstants.SCRAP_OUTSTOCK); // 清空半成品报废品
		handleDetails.setPartId(partsStock.getPartId());
		handleDetails.setPartCode(partsStock.getPartCode());
		handleDetails.setPartName(partsStock.getPartName());

		partStockHandleDetailsMapper.insertPartStockHandleDetails(handleDetails);

		/**
		 * 修改库存记录
		 */
		partsStock.setTotalNumber(partsStock.getTotalNumber() - partsStock.getScrapNumber());
		partsStock.setScrapNumber(0);
		partsStock.setLastUpdate(new Date());
		return partsStockMapper.updatePartsStock(partsStock);
	}
}
