package com.ruoyi.project.erp.materielFeedDetails.service;

import java.util.List;

import com.ruoyi.project.erp.materielFeed.domain.MaterielFeed;
import com.ruoyi.project.erp.materielFeed.mapper.MaterielFeedMapper;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielFeedDetails.mapper.MaterielFeedDetailsMapper;
import com.ruoyi.project.erp.materielFeedDetails.domain.MaterielFeedDetails;
import com.ruoyi.project.erp.materielFeedDetails.service.IMaterielFeedDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 物料发料清单 服务层实现
 * 
 * @author zqm
 * @date 2019-05-13
 */
@Service
public class MaterielFeedDetailsServiceImpl implements IMaterielFeedDetailsService 
{
	@Autowired
	private MaterielFeedDetailsMapper materielFeedDetailsMapper;

	@Autowired
	private MaterielFeedMapper materielFeedMapper;

	@Autowired
	private UserMapper userMapper;

	/**
     * 查询物料发料清单信息
     * 
     * @param id 物料发料清单ID
     * @return 物料发料清单信息
     */
    @Override
	public MaterielFeedDetails selectMaterielFeedDetailsById(Integer id)
	{
	    return materielFeedDetailsMapper.selectMaterielFeedDetailsById(id);
	}
	
	/**
     * 查询物料发料清单列表
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 物料发料清单集合
     */
	@Override
	public List<MaterielFeedDetails> selectMaterielFeedDetailsList(MaterielFeedDetails materielFeedDetails)
	{
		List<MaterielFeedDetails> materielFeedDetailsList = materielFeedDetailsMapper.selectMaterielFeedDetailsList(materielFeedDetails);
		for (MaterielFeedDetails feedDetails : materielFeedDetailsList) {
			MaterielFeed materielFeed = materielFeedMapper.selectMaterielFeedById(feedDetails.getFeedId());
			feedDetails.setFeedLine(materielFeed.getFeedLine());
			feedDetails.setLineReceive(materielFeed.getLineReceive());
			feedDetails.setFeedCode(materielFeed.getFeedCode());
			feedDetails.setOutName(userMapper.selectUserInfoById(feedDetails.getCreateId()).getUserName());
		}
		return materielFeedDetailsList;
	}
	
    /**
     * 新增物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	@Override
	public int insertMaterielFeedDetails(MaterielFeedDetails materielFeedDetails)
	{
	    return materielFeedDetailsMapper.insertMaterielFeedDetails(materielFeedDetails);
	}
	
	/**
     * 修改物料发料清单
     * 
     * @param materielFeedDetails 物料发料清单信息
     * @return 结果
     */
	@Override
	public int updateMaterielFeedDetails(MaterielFeedDetails materielFeedDetails)
	{
	    return materielFeedDetailsMapper.updateMaterielFeedDetails(materielFeedDetails);
	}

	/**
     * 删除物料发料清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMaterielFeedDetailsByIds(String ids)
	{
		return materielFeedDetailsMapper.deleteMaterielFeedDetailsByIds(Convert.toStrArray(ids));
	}
	
}
