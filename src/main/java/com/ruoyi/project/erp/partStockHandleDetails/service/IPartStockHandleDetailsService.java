package com.ruoyi.project.erp.partStockHandleDetails.service;

import com.ruoyi.project.erp.partStockHandleDetails.domain.PartStockHandleDetails;
import java.util.List;

/**
 * 半成品库存内部调整清单 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IPartStockHandleDetailsService 
{
	/**
     * 查询半成品库存内部调整清单信息
     * 
     * @param id 半成品库存内部调整清单ID
     * @return 半成品库存内部调整清单信息
     */
	public PartStockHandleDetails selectPartStockHandleDetailsById(Integer id);
	
	/**
     * 查询半成品库存内部调整清单列表
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 半成品库存内部调整清单集合
     */
	public List<PartStockHandleDetails> selectPartStockHandleDetailsList(PartStockHandleDetails partStockHandleDetails);
	
	/**
     * 新增半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	public int insertPartStockHandleDetails(PartStockHandleDetails partStockHandleDetails);
	
	/**
     * 修改半成品库存内部调整清单
     * 
     * @param partStockHandleDetails 半成品库存内部调整清单信息
     * @return 结果
     */
	public int updatePartStockHandleDetails(PartStockHandleDetails partStockHandleDetails);
		
	/**
     * 删除半成品库存内部调整清单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartStockHandleDetailsByIds(String ids);

	/**
	 * 清空半成品库存
	 * @param id 半成品库存id
	 * @return 结果
	 */
    int handleScrap(int id);
}
