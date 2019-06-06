package com.ruoyi.project.erp.materiel.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materiel.domain.Materiel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielMapper {
    /**
     * 查询物料信息
     *
     * @param id 物料ID
     * @return 物料信息
     */
    @DataSource(value = DataSourceType.ERP)
    public Materiel selectMaterielById(Integer id);

    /**
     * 查询物料列表
     *
     * @param materiel 物料信息
     * @return 物料集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<Materiel> selectMaterielList(Materiel materiel);

    /**
     * 新增物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMateriel(Materiel materiel);

    /**
     * 修改物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMateriel(Materiel materiel);

    /**
     * 删除物料
     *
     * @param id 物料ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielById(Integer id);

    /**
     * 批量删除物料
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielByIds(String[] ids);

    /**
     * 通过物料编码查询物料信息
     *
     * @param materielCode 物料编码
     * @param companyId    公司id
     * @return
     */
    @DataSource(value = DataSourceType.ERP)
    public Materiel selectMaterielByMaterielCode(@Param("materielCode") String materielCode, @Param("companyId") Integer companyId);

    /**
     * 通过物料编码更新物料信息
     *
     * @param materiel
     */
    @DataSource(value = DataSourceType.ERP)
    int updateMaterielByMaterielCode(Materiel materiel);

    /**
     * 根据供应商id查询对应的物料信息
     *
     * @param sid 供应商id
     * @return
     */
    List<Materiel> selectMaterielBySupplierId(@Param("cid") int cid, @Param("sid") int sid);

    /**
     * 查询物料信息
     *
     * @param companyId 公司id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<Materiel> selectAllMatByComId(@Param("companyId") Integer companyId);

    /**
     * 查询物料名称信息
     *
     * @param companyId 公司id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<Materiel> selectAllMatNameByComId(@Param("companyId") Integer companyId);
}