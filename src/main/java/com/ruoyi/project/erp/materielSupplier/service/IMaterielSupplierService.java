package com.ruoyi.project.erp.materielSupplier.service;

import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;

import java.util.List;

/**
 * 物料供应商关联 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielSupplierService {
    /**
     * 查询物料供应商关联信息
     *
     * @param id 物料供应商关联ID
     * @return 物料供应商关联信息
     */
    public MaterielSupplier selectMaterielSupplierById(Integer id);

    /**
     * 查询物料供应商关联列表
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 物料供应商关联集合
     */
    public List<MaterielSupplier> selectMaterielSupplierList(MaterielSupplier materielSupplier);

    /**
     * 新增物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    public int insertMaterielSupplier(MaterielSupplier materielSupplier);

    /**
     * 修改物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    public int updateMaterielSupplier(MaterielSupplier materielSupplier);

    /**
     * 删除物料供应商关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielSupplierByIds(String ids);

    /**
     * 通过物料id查询物料供应商关联列表
     * @param materielId
     * @return 关联列表
     */
    List<MaterielSupplier> selectMaterielSupplierListByMaterielId(Integer materielId);

    /**
     * 根据物料id和供应商id查询供应商编码
     * @param mid 物料id
     * @param sid 供应商id
     * @return
     */
    MaterielSupplier findSupplierCodeByMaterielId(int mid,int sid);

    /**
     * 查询物料退货采购单信息
     * @param mid  物料id
     * @param sid 供应商id
     * @return 结果
     */
    MaterielSupplier matOutStockByMatIdAndSupId(int mid, int sid);
}
