package com.ruoyi.project.erp.materielIntoStock.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielIntoStock.domain.MaterielIntoStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料入库 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielIntoStockMapper {
    /**
     * 查询物料入库信息
     *
     * @param id 物料入库ID
     * @return 物料入库信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielIntoStock selectMaterielIntoStockById(Integer id);

    /**
     * 查询物料入库列表
     *
     * @param materielIntoStock 物料入库信息
     * @return 物料入库集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielIntoStock> selectMaterielIntoStockList(MaterielIntoStock materielIntoStock);

    /**
     * 新增物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielIntoStock(MaterielIntoStock materielIntoStock);

    /**
     * 修改物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielIntoStock(MaterielIntoStock materielIntoStock);

    /**
     * 作废物料入库单
     *
     * @param id 物料入库ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielIntoStockById(Integer id);

    /**
     * 批量删除物料入库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielIntoStockByIds(String[] ids);

    /**
     * 查询物料入库信息
     *
     * @param intoCode 物料入库单号
     * @return 物料入库信息
     */
    @DataSource(value = DataSourceType.ERP)
    MaterielIntoStock selectMaterielIntoStockByIntoCode(@Param("intoCode") String intoCode);

}