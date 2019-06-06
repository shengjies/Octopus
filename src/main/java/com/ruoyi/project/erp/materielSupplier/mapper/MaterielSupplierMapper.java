package com.ruoyi.project.erp.materielSupplier.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料供应商关联 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielSupplierMapper {
    /**
     * 查询物料供应商关联信息
     *
     * @param id 物料供应商关联ID
     * @return 物料供应商关联信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielSupplier selectMaterielSupplierById(Integer id);

    /**
     * 查询物料供应商关联列表
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 物料供应商关联集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielSupplier> selectMaterielSupplierList(MaterielSupplier materielSupplier);

    /**
     * 新增物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielSupplier(MaterielSupplier materielSupplier);

    /**
     * 修改物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielSupplier(MaterielSupplier materielSupplier);

    /**
     * 删除物料供应商关联
     *
     * @param id 物料供应商关联ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielSupplierById(Integer id);

    /**
     * 批量删除物料供应商关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielSupplierByIds(String[] ids);

    /**
     * 通过物料id查询物料供应商关联列表
     *
     * @param materielId
     * @return 关联列表
     */
    @DataSource(value = DataSourceType.ERP)
    List<MaterielSupplier> selectMaterielSupplierListByMaterielId(@Param("materielId") Integer materielId);

    /**
     * 校验对应供应商的编码唯一性
     *
     * @param materielId   物料id
     * @param supplierCode 供应商编码
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int checkSupplierCodeUnique(@Param("supplierId") Integer supplierId, @Param("supplierCode") String supplierCode);

    /**
     * 通过供应商id和物料id查询该供应商所有关联的物料
     *
     * @param materielId 物料id
     * @param supplierId 供应商id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    List<MaterielSupplier> selectMaterielSupplierListByMatIdAndSupId(@Param("materielId") Integer materielId, @Param("supplierId") Integer supplierId);

    /**
     * 校验供应商是否关联该物料
     *
     * @param materielId 物料id主键
     * @param supplierId 供应商主键id
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    int checkMaterielUnique(@Param("materielId") Integer materielId, @Param("supplierId") Integer supplierId);

    /**
     * 根据物料id和供应商id查询供应商编码
     * @param mid 物料id
     * @param sid 供应商id
     * @return
     */
    @DataSource(value = DataSourceType.ERP)
    MaterielSupplier findSupplierCodeByMaterielId(@Param("mid") int mid,@Param("sid") int sid);
}