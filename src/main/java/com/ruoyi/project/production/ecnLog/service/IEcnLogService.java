package com.ruoyi.project.production.ecnLog.service;

import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import java.util.List;

/**
 * ECN 变更记录 服务层
 * 
 * @author zqm
 * @date 2019-05-16
 */
public interface IEcnLogService 
{
	/**
	 * 查询ECN 变更记录信息
	 *
	 * @param id ECN 变更记录ID
	 * @return ECN 变更记录信息
	 */
	public EcnLog selectEcnLogById(Integer id);
	/**
     * 查询ECN 变更记录列表
     * 
     * @param ecnLog ECN 变更记录信息
     * @return ECN 变更记录集合
     */
	public List<EcnLog> selectEcnLogList(EcnLog ecnLog);
	
	/**
     * 新增ECN 变更记录
     * 
     * @param ecnLog ECN 变更记录信息
     * @return 结果
     */
	public int insertEcnLog(EcnLog ecnLog);

	/**
	 * 修改ECN 变更记录
	 *
	 * @param ecnLog ECN 变更记录信息
	 * @return 结果
	 */
	public int updateEcnLog(EcnLog ecnLog);

	/**
	 * 删除ECN 变更记录信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteEcnLogByIds(String ids);

	/**
	 * 删除ECN变更记录
	 * @param id ECN变更记录id
	 * @return 结果
	 */
	int deleteEcnLogById(Integer id);
}
