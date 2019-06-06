package com.ruoyi.project.erp.lineIntoStockDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产线入库明细 数据层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface LineIntoStockDetailsMapper {
    /**
     * 查询产线入库明细信息
     *
     * @param id 产线入库明细ID
     * @return 产线入库明细信息
     */
    @DataSource(value = DataSourceType.ERP)
    public LineIntoStockDetails selectLineIntoStockDetailsById(Integer id);

    /**
     * 查询产线入库明细列表
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 产线入库明细集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<LineIntoStockDetails> selectLineIntoStockDetailsList(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 新增产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 修改产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 删除产线入库明细
     *
     * @param id 产线入库明细ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteLineIntoStockDetailsById(Integer id);

    /**
     * 批量删除产线入库明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteLineIntoStockDetailsByIds(String[] ids);

    /**
     * 通过生产入库主键id查询生产入库明细
     * @param lineIntoId 生产入库主键id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<LineIntoStockDetails> selectLineIntoStockDetailsByLineIntoId(@Param("lineIntoId") Integer lineIntoId);
}