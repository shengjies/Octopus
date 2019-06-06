package com.ruoyi.project.erp.parts.service;

import com.ruoyi.project.erp.parts.domain.Parts;
import java.util.List;

/**
 * 半成品 服务层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface IPartsService 
{
	/**
     * 查询半成品信息
     * 
     * @param id 半成品ID
     * @return 半成品信息
     */
	public Parts selectPartsById(Integer id);
	
	/**
     * 查询半成品列表
     * 
     * @param parts 半成品信息
     * @return 半成品集合
     */
	public List<Parts> selectPartsList(Parts parts);
	
	/**
     * 新增半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	public int insertParts(Parts parts);
	
	/**
     * 修改半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	public int updateParts(Parts parts);
		
	/**
     * 删除半成品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartsByIds(String ids);

	/**
	 * 查询公司的半成品信息
	 * @return 半成品列表
	 */
	public List<Parts> selectAllPartsByComId();

	/**
	 * 查询公司的半成品名称信息，去重查询
	 * @return 半成品列表
	 */
	public List<Parts> selectAllPartsNameByComId();
	
}
