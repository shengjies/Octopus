package com.ruoyi.project.erp.materiel.service;

import com.ruoyi.project.erp.materiel.domain.Materiel;

import java.util.List;

/**
 * 物料 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielService {
    /**
     * 查询物料信息
     *
     * @param id 物料ID
     * @return 物料信息
     */
    public Materiel selectMaterielById(Integer id);

    /**
     * 查询物料列表
     *
     * @param materiel 物料信息
     * @return 物料集合
     */
    public List<Materiel> selectMaterielList(Materiel materiel);

    /**
     * 新增物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    public int insertMateriel(Materiel materiel);

    /**
     * 修改物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    public int updateMateriel(Materiel materiel);

    /**
     * 删除物料信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielByIds(String ids);

    /**
     * 导入物料列表
     * @param materielList 物料列表
     * @param updateSupport  是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importMateriel(List<Materiel> materielList, boolean updateSupport);

    /**
     * 通过供应商id查询和供应商关联的物料列表
     * @param supplierId 供应商id
     * @return 结果
     */
    public List<Materiel> materielListBySupplierId(Integer supplierId);

    /**
     * 通过物料id和供应商id查询物料信息
     *
     * @param supplierId 供应商id
     * @param materielId 物料id
     * @return 结果
     */
    Materiel materielListByMaterielId(Integer supplierId,Integer materielId);

    /**
     * 根据供应商id查询对应的物料信息
     * @param sid 供应商id
     * @return 物料信息集合
     */
    List<Materiel> selectMaterielBySupplierId(int sid);

    /**
     * 校验物料编码唯一性
     * @param materiel 物料
     * @return 结果
     */
    String checkMaterielCodeUnique(Materiel materiel);

    /**
     * 查询各公司的物料信息
     * @return 结果
     */
    List<Materiel> selectAllMatByComId();

    /**
     * 查询公司的物料名称信息
     * @return 结果
     */
    List<Materiel> selectAllMatNameByComId();
}
