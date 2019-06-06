package com.ruoyi.project.erp.materielFeed.service;

import java.util.*;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import com.ruoyi.project.erp.materielFeedDetails.mapper.MaterielFeedDetailsMapper;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.mapper.PartsStockMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielFeed.mapper.MaterielFeedMapper;
import com.ruoyi.project.erp.materielFeed.domain.MaterielFeed;
import com.ruoyi.project.erp.materielFeed.service.IMaterielFeedService;
import com.ruoyi.common.support.Convert;

/**
 * 物料发料 服务层实现
 *
 * @author zqm
 * @date 2019-05-13
 */
@Service
public class MaterielFeedServiceImpl implements IMaterielFeedService
{
	@Autowired
	private MaterielFeedMapper materielFeedMapper;

	@Autowired
	private MaterielStockMapper materielStockMapper; // 物料库存数据层

	@Autowired
	private PartsStockMapper partsStockMapper; // 半成品库存数据层

    @Autowired
    private MaterielFeedDetailsMapper materielFeedDetailsMapper; // 生产发料明细数据层

	/**
     * 查询物料发料信息
     *
     * @param id 物料发料ID
     * @return 物料发料信息
     */
    @Override
	public MaterielFeed selectMaterielFeedById(Integer id)
	{
		MaterielFeed materielFeed = materielFeedMapper.selectMaterielFeedById(id);
		MaterielFeedDetails materielFeedDetails = new MaterielFeedDetails();
		materielFeedDetails.setFeedId(id);
		/**
		 * 生产发料详情信息
		 */
		List<MaterielFeedDetails> materielFeedDetailsList = materielFeedDetailsMapper.selectMaterielFeedDetailsList(materielFeedDetails);
		for (MaterielFeedDetails feedDetails : materielFeedDetailsList) {
			Integer feedType = feedDetails.getFeedType(); // 发料类型
			if (StockConstants.DETAILS_TYPE_MATERIEL.equals(feedType)) {
				MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(feedDetails.getFeedDetailId());
				feedDetails.setTotalNumber(materielStock.getTotalNumber());
				feedDetails.setGoodNubmer(materielStock.getGoodNumber());
			} else {
				PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(feedDetails.getFeedDetailId());
				feedDetails.setTotalNumber(partsStock.getTotalNumber());
				feedDetails.setGoodNubmer(partsStock.getGoodNumber());
			}
		}
		materielFeed.setMaterielFeedDetailsList(materielFeedDetailsList);
		return materielFeed;
	}

	/**
     * 查询物料发料列表
     *
     * @param materielFeed 物料发料信息
     * @return 物料发料集合
     */
	@Override
	public List<MaterielFeed> selectMaterielFeedList(MaterielFeed materielFeed)
	{
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
			return Collections.emptyList();
		}
		materielFeed.setCompanyId(user.getCompanyId());
	    return materielFeedMapper.selectMaterielFeedList(materielFeed);
	}

    /**
     * 新增物料发料
     *
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	@Override
	public int insertMaterielFeed(MaterielFeed materielFeed)
	{
        User user = ShiroUtils.getSysUser();
        if (user == null ) return 0;
        String lineOutStockCode = CodeUtils.getLineOutStockCode(); // 自动生成生产发料单
        materielFeed.setFeedCode(lineOutStockCode);
        materielFeed.setCompanyId(user.getCompanyId());
        materielFeed.setCreateId(user.getUserId().intValue());
        materielFeed.setCreateTime(new Date());
        materielFeed.setCreateName(user.getUserName());
        int i = materielFeedMapper.insertMaterielFeed(materielFeed);
        if (!StringUtils.isEmpty(materielFeed.getMaterielFeedDetailsList())) {
            // 生产发料明细
            List<MaterielFeedDetails> materielFeedDetailsList = materielFeed.getMaterielFeedDetailsList();
            for (MaterielFeedDetails materielFeedDetails : materielFeedDetailsList) {
                materielFeedDetails.setFeedId(materielFeed.getId());
                materielFeedDetails.setCreateId(user.getUserId().intValue());
                materielFeedDetails.setCreateTime(new Date());
                materielFeedDetailsMapper.insertMaterielFeedDetails(materielFeedDetails);
                /**
                 * 库存操作
                 */
                Integer feedType = materielFeedDetails.getFeedType(); // 发料类型
				Integer outNumber = materielFeedDetails.getOutNumber(); // 发料数量
                if (StockConstants.DETAILS_TYPE_MATERIEL.equals(feedType)) { // 物料发料
                    // 更新物料库存信息
                    MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(materielFeedDetails.getFeedDetailId());
					Integer goodNumber = materielStock.getGoodNumber(); // 仓库中物料良品数量
					if (outNumber > goodNumber) {
					    throw new BusinessException("物料" + materielFeedDetails.getFeedDetailCode() + "发料数量不能大于实际库存");
					}
					materielStock.setTotalNumber(materielStock.getTotalNumber() - outNumber);
                    materielStock.setGoodNumber(goodNumber - outNumber);
                    materielStock.setLastUpdate(new Date());
                    materielStockMapper.updateMaterielStock(materielStock);

                } else { // 半成品发料
                    // 更新半成品库存信息
                    PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(materielFeedDetails.getFeedDetailId());
					Integer goodNumber = partsStock.getGoodNumber();
					if (outNumber > goodNumber) {
					    throw new BusinessException("半成品" + materielFeedDetails.getFeedDetailCode() + "库存数量不足");
					}
					partsStock.setTotalNumber(partsStock.getTotalNumber() - outNumber);
                    partsStock.setGoodNumber(goodNumber - outNumber);
                    partsStock.setLastUpdate(new Date());
                    partsStockMapper.updatePartsStock(partsStock);
                }
            }
        }
        return i;
	}

	/**
     * 修改物料发料
     *
     * @param materielFeed 物料发料信息
     * @return 结果
     */
	@Override
	public int updateMaterielFeed(MaterielFeed materielFeed)
	{
	    return materielFeedMapper.updateMaterielFeed(materielFeed);
	}

	/**
     * 删除物料发料对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMaterielFeedByIds(String ids)
	{
		return materielFeedMapper.deleteMaterielFeedByIds(Convert.toStrArray(ids));
	}

	/**
	 * 生产发料添加详情
	 * @param feedType 发料类型
	 * @return 结果
	 */
	@Override
	public Map selectAllMaterielOrParts(String feedType) {
		Map<String,Object> mmap = new HashMap<String,Object>(16);
		if (StockConstants.DETAILS_TYPE_MATERIEL.equals(Integer.parseInt(feedType))) { // 物料类型
		    // 查询该公司所有的物料
			MaterielStock materielStock = new MaterielStock();
			materielStock.setCompanyId(ShiroUtils.getCompanyId());
			List<MaterielStock> materielStocks = materielStockMapper.selectMaterielStockList(materielStock);
			mmap.put("materielStock",materielStocks);
		}else {
			PartsStock partsStock = new PartsStock();
			partsStock.setCompanyId(ShiroUtils.getCompanyId());
			List<PartsStock> partsStocks = partsStockMapper.selectPartsStockList(partsStock);
			mmap.put("partsStock",partsStocks);
		}
		return mmap;
	}

	/**
	 * 作废生产发料单
	 *
	 * @param id 生产发料单id
	 * @return 结果
	 */
	@Override
	public int nullifyMaterielFeedById(Integer id) {
		MaterielFeedDetails materielFeedDetails = new MaterielFeedDetails();
		materielFeedDetails.setFeedId(id);
		// 生产发料详细信息
		List<MaterielFeedDetails> materielFeedDetailsList = materielFeedDetailsMapper.selectMaterielFeedDetailsList(materielFeedDetails);
		for (MaterielFeedDetails feedDetails : materielFeedDetailsList) {
			Integer feedType = feedDetails.getFeedType(); // 发料的类型
			if (StockConstants.DETAILS_TYPE_MATERIEL.equals(feedType)) { // 发料类型为物料
				// 更新物料库存信息
				MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(feedDetails.getFeedDetailId());
				materielStock.setGoodNumber(materielStock.getGoodNumber() + feedDetails.getOutNumber());
				materielStock.setTotalNumber(materielStock.getTotalNumber() + feedDetails.getOutNumber());
				materielStock.setLastUpdate(new Date());
				materielStockMapper.updateMaterielStock(materielStock);
			} else {
				PartsStock partsStock = partsStockMapper.selectPartsStockByPartsId(feedDetails.getFeedDetailId());
				partsStock.setTotalNumber(partsStock.getTotalNumber() + feedDetails.getOutNumber());
				partsStock.setGoodNumber(partsStock.getGoodNumber() + feedDetails.getOutNumber());
				partsStock.setLastUpdate(new Date());
				partsStockMapper.updatePartsStock(partsStock);
			}
			// 作废生产发料明细
			materielFeedDetailsMapper.deleteMaterielFeedDetailsById(feedDetails.getId());
		}
		return materielFeedMapper.deleteMaterielFeedById(id);
	}
}
