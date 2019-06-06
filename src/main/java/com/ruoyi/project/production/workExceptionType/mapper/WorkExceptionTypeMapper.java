package com.ruoyi.project.production.workExceptionType.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.workExceptionType.domain.WorkExceptionType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工单工单异常类型 数据层
 * 
 * @author zqm
 * @date 2019-04-16
 */
public interface WorkExceptionTypeMapper 
{
	/**
     * 查询工单工单异常类型信息
     * 
     * @param id 工单工单异常类型ID
     * @return 工单工单异常类型信息
     */
	@DataSource(value = DataSourceType.SLAVE)
	public WorkExceptionType selectWorkExceptionTypeById(Integer id);
	
	/**
     * 查询工单工单异常类型列表
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 工单工单异常类型集合
     */
	@DataSource(value = DataSourceType.SLAVE)
	public List<WorkExceptionType> selectWorkExceptionTypeList(WorkExceptionType workExceptionType);
	
	/**
     * 新增工单工单异常类型
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int insertWorkExceptionType(WorkExceptionType workExceptionType);
	
	/**
     * 修改工单工单异常类型
     * 
     * @param workExceptionType 工单工单异常类型信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int updateWorkExceptionType(WorkExceptionType workExceptionType);
	
	/**
     * 删除工单工单异常类型
     * 
     * @param id 工单工单异常类型ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteWorkExceptionTypeById(Integer id);
	
	/**
     * 批量删除工单工单异常类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteWorkExceptionTypeByIds(String[] ids);

	/**
	 * 查询对应公司的所有异常类型
	 * @param companyId
	 * @return
	 */
	@DataSource(value = DataSourceType.SLAVE)
	List<WorkExceptionType> findExceTypeAll(@Param("companyId") Integer companyId);


	/**
	 * 查询对应公司对应工单的异常信息
	 * @param company_id 公司编号
	 * @param work_id 工单编号
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	List<WorkExceptionType> selectCompanyWork(@Param("company_id")int company_id,@Param("work_id")int work_id);

	/**
	 * 根据公司id和异常类型名称查询对应的异常类型信息
	 * @param company_id 公司id
	 * @param tname 异常类型名称
	 * @return
	 */
	@DataSource(DataSourceType.SLAVE)
	WorkExceptionType selectByCompanyAndTypeName(@Param("company_id")int company_id,@Param("type_name")String tname);
}