package com.ruoyi.project.erp.materielStockHandleDetails.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielStockHandleDetails.mapper.MaterielStockHandleDetailsMapper;
import com.ruoyi.project.erp.materielStockHandleDetails.domain.MaterielStockHandleDetails;
import com.ruoyi.project.erp.materielStockHandleDetails.service.IMaterielStockHandleDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 物料库存内部调整清单 服务层实现
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielStockHandleDetailsServiceImpl implements IMaterielStockHandleDetailsService 
{
	@Autowired
	private MaterielStockHandleDetailsMapper materielStockHandleDetailsMapper;

	@Autowired
	private MaterielStockMapper materielStockMapper; // 物料库存数据层

	/**
     * 查询物料库存内部调整清单信息
     * 
     * @param id 物料库存内部调整清单ID
     * @return 物料库存内部调整清单信息
     */
    @Override
	public MaterielStockHandleDetails selectMaterielStockHandleDetailsById(Integer id)
	{
	    return materielStockHandleDetailsMapper.selectMaterielStockHandleDetailsById(id);
	}
	
	/**
     * 查询物料库存内部调整清单列表
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 物料库存内部调整清单集合
     */
	@Override
	public List<MaterielStockHandleDetails> selectMaterielStockHandleDetailsList(MaterielStockHandleDetails materielStockHandleDetails)
	{
	    return materielStockHandleDetailsMapper.selectMaterielStockHandleDetailsList(materielStockHandleDetails);
	}
	
    /**
     * 新增物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int insertMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null ) return 0;
		materielStockHandleDetails.setCompanyId(user.getCompanyId());
		materielStockHandleDetails.setHandleBy(user.getUserId().intValue());
		materielStockHandleDetails.setHandleName(user.getUserName());
		materielStockHandleDetails.setHandleTime(new Date());
		MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(materielStockHandleDetails.getMaterielId());
		/**
		 * 临时仓库操作
		 */
		Integer temporaryStatus = materielStockHandleDetails.getTemporaryStatus(); // 临时仓库操作状态
		if (StockConstants.TEMPORARY_TO_GOOD.equals(temporaryStatus)) { // 开了IQC检验，改变临时仓库库存
			if (materielStock.getTemporaryNumber() < materielStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
		    materielStock.setGoodNumber(materielStock.getGoodNumber() + materielStockHandleDetails.getHandleNumber());
		    materielStock.setTemporaryNumber(materielStock.getTemporaryNumber() - materielStockHandleDetails.getHandleNumber());
		    materielStock.setLastUpdate(new Date());
		    materielStockMapper.updateMaterielStock(materielStock);
		}
		/**
		 * 库存操作
		 */
		Integer handleStatus = materielStockHandleDetails.getHandleStatus(); // 操作类型
		if (StockConstants.GOOD_TO_BAD.equals(handleStatus)) { // 良品转成不良品
		    if (materielStock.getGoodNumber() < materielStockHandleDetails.getHandleNumber()) {
		        throw new BusinessException("操作数量不能大于实际库存");
		    }
		    materielStock.setGoodNumber(materielStock.getGoodNumber() - materielStockHandleDetails.getHandleNumber());
		    materielStock.setBadNumber(materielStock.getBadNumber() + materielStockHandleDetails.getHandleNumber());
		    materielStock.setLastUpdate(new Date());
		    materielStockMapper.updateMaterielStock(materielStock);

		} else if (StockConstants.GOOD_TO_SCRAP.equals(handleStatus)) { // 良品转报废品
			if (materielStock.getGoodNumber() < materielStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			materielStock.setGoodNumber(materielStock.getGoodNumber() - materielStockHandleDetails.getHandleNumber());
			materielStock.setScrapNumber(materielStock.getScrapNumber() + materielStockHandleDetails.getHandleNumber());
			materielStock.setLastUpdate(new Date());
			materielStockMapper.updateMaterielStock(materielStock);

		} else if (StockConstants.BAD_TO_GOOD.equals(handleStatus)) { // 不良品转良品
			if (materielStock.getBadNumber() < materielStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			materielStock.setBadNumber(materielStock.getBadNumber() - materielStockHandleDetails.getHandleNumber());
			materielStock.setGoodNumber(materielStock.getGoodNumber() + materielStockHandleDetails.getHandleNumber());
			materielStock.setLastUpdate(new Date());
			materielStockMapper.updateMaterielStock(materielStock);

		} else if (StockConstants.BAD_TO_SCRAP.equals(handleStatus)) { // 不良品转报废品
			if (materielStock.getBadNumber() < materielStockHandleDetails.getHandleNumber()) {
				throw new BusinessException("操作数量不能大于实际库存");
			}
			materielStock.setBadNumber(materielStock.getBadNumber() - materielStockHandleDetails.getHandleNumber());
			materielStock.setScrapNumber(materielStock.getScrapNumber() + materielStockHandleDetails.getHandleNumber());
			materielStock.setLastUpdate(new Date());
			materielStockMapper.updateMaterielStock(materielStock);
		}

		return materielStockHandleDetailsMapper.insertMaterielStockHandleDetails(materielStockHandleDetails);
	}
	
	/**
     * 修改物料库存内部调整清单
     * 
     * @param materielStockHandleDetails 物料库存内部调整清单信息
     * @return 结果
     */
	@Override
	public int updateMaterielStockHandleDetails(MaterielStockHandleDetails materielStockHandleDetails)
	{
	    return materielStockHandleDetailsMapper.updateMaterielStockHandleDetails(materielStockHandleDetails);
	}

	/**
     * 删除物料库存内部调整清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMaterielStockHandleDetailsByIds(String ids)
	{
		return materielStockHandleDetailsMapper.deleteMaterielStockHandleDetailsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 处理物料报废品
	 * @param id 物料库存id
	 * @return 结果
	 */
	@Override
	public int handleScrap(Integer id) {
		User user = ShiroUtils.getSysUser();
		if (user == null) {
			return 0;
		}
		/**
		 * 新增内部操作记录
		 */
		MaterielStock materielStock = materielStockMapper.selectMaterielStockById(id); // 物料库存
		MaterielStockHandleDetails materielStockHandleDetails = new MaterielStockHandleDetails(); // 物料内部处理记录
		materielStockHandleDetails.setMaterielId(materielStock.getMaterielId());
		materielStockHandleDetails.setMaterielCode(materielStock.getMaterielCode());
		materielStockHandleDetails.setMaterielModel(materielStock.getMaterielModel());
		materielStockHandleDetails.setMaterielName(materielStock.getMaterielName());
		materielStockHandleDetails.setCompanyId(user.getCompanyId());
		materielStockHandleDetails.setHandleTime(new Date());
		materielStockHandleDetails.setHandleBy(user.getUserId().intValue());
		materielStockHandleDetails.setHandleName(user.getUserName());
		materielStockHandleDetails.setHandleNumber(materielStock.getScrapNumber());
		materielStockHandleDetails.setHandleType(StockConstants.SCRAP_OUTSTOCK); // 报废品出库
		materielStockHandleDetailsMapper.insertMaterielStockHandleDetails(materielStockHandleDetails);
		/**
		 * 库存变更
		 */
		materielStock.setTotalNumber(materielStock.getTotalNumber() - materielStock.getScrapNumber());
		materielStock.setScrapNumber(0);
		materielStock.setLastUpdate(new Date());
		return materielStockMapper.updateMaterielStock(materielStock);
	}
}
