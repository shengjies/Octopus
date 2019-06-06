package com.ruoyi.project.erp.lineIntoStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.lineIntoStock.domain.LineIntoStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产线入库 数据层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface LineIntoStockMapper {
    /**
     * 查询产线入库信息
     *
     * @param id 产线入库ID
     * @return 产线入库信息
     */
    @DataSource(value = DataSourceType.ERP)
    public LineIntoStock selectLineIntoStockById(Integer id);

    /**
     * 查询产线入库列表
     *
     * @param lineIntoStock 产线入库信息
     * @return 产线入库集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<LineIntoStock> selectLineIntoStockList(LineIntoStock lineIntoStock);

    /**
     * 新增产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertLineIntoStock(LineIntoStock lineIntoStock);

    /**
     * 修改产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateLineIntoStock(LineIntoStock lineIntoStock);

    /**
     * 删除产线入库
     *
     * @param id 产线入库ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteLineIntoStockById(Integer id);

    /**
     * 批量删除产线入库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteLineIntoStockByIds(String[] ids);

    /**
     * 通过入库单号查询入库信息
     * @param companyId 公司id
     * @param intoCode 入库单号
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    LineIntoStock selectLineIntoStockByCode(@Param("companyId") Integer companyId,@Param("intoCode") String intoCode);
}