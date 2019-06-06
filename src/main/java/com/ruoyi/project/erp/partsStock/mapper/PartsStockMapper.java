package com.ruoyi.project.erp.partsStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 半成品库存 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface PartsStockMapper {
    /**
     * 查询半成品库存信息
     *
     * @param id 半成品库存ID
     * @return 半成品库存信息
     */
    @DataSource(value = DataSourceType.ERP)
    public PartsStock selectPartsStockById(Integer id);

    /**
     * 查询半成品库存列表
     *
     * @param partsStock 半成品库存信息
     * @return 半成品库存集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<PartsStock> selectPartsStockList(PartsStock partsStock);

    /**
     * 新增半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertPartsStock(PartsStock partsStock);

    /**
     * 修改半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updatePartsStock(PartsStock partsStock);

    /**
     * 删除半成品库存
     *
     * @param id 半成品库存ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deletePartsStockById(Integer id);

    /**
     * 批量删除半成品库存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deletePartsStockByIds(String[] ids);

    /**
     * 查询半成品库存信息
     * @param companyId 公司id
     * @param detIntoCode 半成品编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    PartsStock selectPartsStockByProCode(@Param("companyId") Integer companyId,@Param("detIntoCode") String detIntoCode);

    /**
     * 查询半成品库存信息
     * @param partId 半成品id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    PartsStock selectPartsStockByPartsId(@Param("partId") Integer partId);
}