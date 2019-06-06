package com.ruoyi.project.production.workExceptionType.service;

import com.ruoyi.project.production.workExceptionType.domain.WorkExceptionType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工单工单异常类型 服务层
 * 
 * @author zqm
 * @date 2019-04-16
 */
public interface IWorkExceptionTypeService 
{
	/**
     * 查询工单工单异常类型信息
     * 
     * @param id 工单工单异常类型ID
     * @return 工单工单异常类型信息
     */
	public WorkExceptionType selectWorkExceptionTypeById(Integer id);
	
	/**
     * 查询工单工单异常类型列表
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 工单工单异常类型集合
     */
	public List<WorkExceptionType> selectWorkExceptionTypeList(WorkExceptionType workExceptionType, HttpServletRequest request);
	
	/**
     * 新增工单工单异常类型
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 结果
     */
	public int insertWorkExceptionType(WorkExceptionType workExceptionType,HttpServletRequest request);
	
	/**
     * 修改工单工单异常类型
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 结果
     */
	public int updateWorkExceptionType(WorkExceptionType workExceptionType);
		
	/**
     * 删除工单工单异常类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorkExceptionTypeByIds(String ids);
	
}
