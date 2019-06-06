package com.ruoyi.project.production.workExceptionList.service;

import com.ruoyi.project.production.workExceptionList.domain.WorkExceptionList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各个工单异常情况记录 服务层
 * 
 * @author zqm
 * @date 2019-04-16
 */
public interface IWorkExceptionListService 
{
	/**
     * 查询各个工单异常情况记录信息
     * 
     * @param id 各个工单异常情况记录ID
     * @return 各个工单异常情况记录信息
     */
	public WorkExceptionList selectWorkExceptionListById(Integer id);
	
	/**
     * 查询各个工单异常情况记录列表
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 各个工单异常情况记录集合
     */
	public List<WorkExceptionList> selectWorkExceptionListList(WorkExceptionList workExceptionList, HttpServletRequest request);
	
	/**
     * 新增各个工单异常情况记录
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 结果
     */
	public int insertWorkExceptionList(WorkExceptionList workExceptionList,HttpServletRequest request);
	
	/**
     * 修改各个工单异常情况记录
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 结果
     */
	public int updateWorkExceptionList(WorkExceptionList workExceptionList);
		
	/**
     * 删除各个工单异常情况记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorkExceptionListByIds(String ids);

	/**
	 * 执行工单异常操作
	 * @return
	 */
	public int handleWorkExcp(WorkExceptionList workExceptionList,HttpServletRequest request);

	/**
	 * 解决完工单异常操作
	 * @return
	 */
	public int finishWorkExcp(Integer id);
}
