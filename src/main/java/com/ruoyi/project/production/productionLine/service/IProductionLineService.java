package com.ruoyi.project.production.productionLine.service;

import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.system.user.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 生产线 服务层
 * 
 * @author ruoyi
 * @date 2019-04-11
 */
public interface IProductionLineService 
{
	/**
     * 查询生产线信息
     * 
     * @param id 生产线ID
     * @return 生产线信息
     */
	public ProductionLine selectProductionLineById(Integer id);
	
	/**
     * 查询生产线列表
     * 
     * @param productionLine 生产线信息
     * @return 生产线集合
     */
	public List<ProductionLine> selectProductionLineList(ProductionLine productionLine);
	
	/**
     * 新增生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
	public int insertProductionLine(ProductionLine productionLine);
	
	/**
     * 修改生产线
     * 
     * @param productionLine 生产线信息
     * @return 结果
     */
	public int updateProductionLine(ProductionLine productionLine);
		
	/**
     * 删除生产线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductionLineByIds(String ids);

	/**
	 * 查询对应产线已经配置的硬件信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> selectLineDev(int id);

	/**
	 * 保存相关产线IO口配置
	 * @param line
	 * @return
	 */
	int updateLineConfigClear(ProductionLine line);

	/**
	 * 查询对应公司的所有生产线
	 * @return
	 */
	List<ProductionLine> selectAllProductionLineByCompanyId();

	/**
	 * 通过生产线id查询责任人名称
	 * @param lineId
	 * @return
	 */
	Map findDeviceLiableByLineId(Integer lineId);
}
