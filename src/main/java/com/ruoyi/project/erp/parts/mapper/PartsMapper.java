package com.ruoyi.project.erp.parts.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.parts.domain.Parts;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 半成品 数据层
 * 
 * @author zqm
 * @date 2019-04-30
 */
public interface PartsMapper 
{
	/**
     * 查询半成品信息
     * 
     * @param id 半成品ID
     * @return 半成品信息
     */
	@DataSource(value = DataSourceType.ERP)
	public Parts selectPartsById(Integer id);
	
	/**
     * 查询半成品列表
     * 
     * @param parts 半成品信息
     * @return 半成品集合
     */
	@DataSource(value = DataSourceType.ERP)
	public List<Parts> selectPartsList(Parts parts);
	
	/**
     * 新增半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int insertParts(Parts parts);
	
	/**
     * 修改半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int updateParts(Parts parts);
	
	/**
     * 删除半成品
     * 
     * @param id 半成品ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deletePartsById(Integer id);
	
	/**
     * 批量删除半成品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(value = DataSourceType.ERP)
	public int deletePartsByIds(String[] ids);

	/**
	 * 通过半成品编码查询半成品信息
	 * @param companyId 公司主键
	 * @param detIntoCode 编码
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	Parts selectPartsByCode(@Param("companyId") Integer companyId, @Param("detIntoCode") String detIntoCode);

	/**
	 * 查询公司的半成品名称信息，去重查询
	 * @param companyId 公司id
	 * @return 结果
	 */
	@DataSource(value = DataSourceType.ERP)
	List<Parts> selectAllPartsNameByComId(@Param("companyId") Integer companyId);
}