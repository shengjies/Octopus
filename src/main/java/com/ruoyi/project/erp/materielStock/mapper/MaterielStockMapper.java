package com.ruoyi.project.erp.materielStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料库存 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielStockMapper {
    /**
     * 查询物料库存信息
     *
     * @param id 物料库存ID
     * @return 物料库存信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielStock selectMaterielStockById(Integer id);

    /**
     * 查询物料库存列表
     *
     * @param materielStock 物料库存信息
     * @return 物料库存集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielStock> selectMaterielStockList(MaterielStock materielStock);

    /**
     * 新增物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielStock(MaterielStock materielStock);

    /**
     * 修改物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielStock(MaterielStock materielStock);

    /**
     * 删除物料库存
     *
     * @param id 物料库存ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielStockById(Integer id);

    /**
     * 批量删除物料库存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielStockByIds(String[] ids);

    /**
     * 通过物料id查询物料库存信息
     * @param materielId 物料id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    MaterielStock selectMaterielStockByMaterielId(@Param("materielId") Integer materielId);

    /**
     * 查询物料库存记录
     * @param materielCode 物料编码
     * @param companyId 公司id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    MaterielStock selectMaterielStockByMatCodeAndComId(@Param("materielCode") String materielCode, @Param("companyId") Integer companyId);
}