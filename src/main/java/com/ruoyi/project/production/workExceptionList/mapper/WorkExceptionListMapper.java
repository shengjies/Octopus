package com.ruoyi.project.production.workExceptionList.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.workExceptionList.domain.WorkExceptionList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 各个工单异常情况记录 数据层
 * 
 * @author zqm
 * @date 2019-04-16
 */
public interface WorkExceptionListMapper 
{
	/**
     * 查询各个工单异常情况记录信息
     * 
     * @param id 各个工单异常情况记录ID
     * @return 各个工单异常情况记录信息
     */
	@DataSource(value = DataSourceType.SLAVE)
	public WorkExceptionList selectWorkExceptionListById(Integer id);
	
	/**
     * 查询各个工单异常情况记录列表
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 各个工单异常情况记录集合
     */
	@DataSource(value = DataSourceType.SLAVE)
	public List<WorkExceptionList> selectWorkExceptionListList(WorkExceptionList workExceptionList);
	
	/**
     * 新增各个工单异常情况记录
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int insertWorkExceptionList(WorkExceptionList workExceptionList);
	
	/**
     * 修改各个工单异常情况记录
     * 
     * @param workExceptionList 各个工单异常情况记录信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int updateWorkExceptionList(WorkExceptionList workExceptionList);
	
	/**
     * 删除各个工单异常情况记录
     * 
     * @param id 各个工单异常情况记录ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteWorkExceptionListById(Integer id);
	
	/**
     * 批量删除各个工单异常情况记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteWorkExceptionListByIds(String[] ids);

	/**
	 * 查看当前公司当天的异常记录
	 * @return
	 */
	@DataSource(value = DataSourceType.SLAVE)
    List<WorkExceptionList> selectWorkExcToday(@Param("companyId") Integer companyId);

	/**
	 * 查询工单组的异常事件
	 * @param workIds 工单编号数组
	 * @return
	 */
	@DataSource(value = DataSourceType.SLAVE)
	List<WorkExceptionList> selectWorkExceByWorkId(@Param("workIds")String workIds);
}