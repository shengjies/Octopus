package com.ruoyi.project.erp.materielOutStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielOutStock.domain.MaterielOutStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料出库 数据层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface MaterielOutStockMapper {
    /**
     * 查询物料出库信息
     *
     * @param id 物料出库ID
     * @return 物料出库信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielOutStock selectMaterielOutStockById(Integer id);

    /**
     * 查询物料出库列表
     *
     * @param materielOutStock 物料出库信息
     * @return 物料出库集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielOutStock> selectMaterielOutStockList(MaterielOutStock materielOutStock);

    /**
     * 新增物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielOutStock(MaterielOutStock materielOutStock);

    /**
     * 修改物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielOutStock(MaterielOutStock materielOutStock);

    /**
     * 删除物料出库
     *
     * @param id 物料出库ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielOutStockById(Integer id);

    /**
     * 批量删除物料出库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielOutStockByIds(String[] ids);

    /**
     * 通过出库单号查询物料出库单信息
     * @param outCode 出库单号
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    MaterielOutStock selectMaterielOutStockByOutCode(@Param("outCode") String outCode);
}