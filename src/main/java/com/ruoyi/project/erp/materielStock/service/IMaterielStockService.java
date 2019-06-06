package com.ruoyi.project.erp.materielStock.service;

import com.ruoyi.project.erp.materielStock.domain.MaterielStock;

import java.util.List;

/**
 * 物料库存 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielStockService {
    /**
     * 查询物料库存信息
     *
     * @param id 物料库存ID
     * @return 物料库存信息
     */
    public MaterielStock selectMaterielStockById(Integer id);

    /**
     * 查询物料库存列表
     *
     * @param materielStock 物料库存信息
     * @return 物料库存集合
     */
    public List<MaterielStock> selectMaterielStockList(MaterielStock materielStock);

    /**
     * 新增物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    public int insertMaterielStock(MaterielStock materielStock);

    /**
     * 修改物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    public int updateMaterielStock(MaterielStock materielStock);

    /**
     * 删除物料库存信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielStockByIds(String ids);

    /**
     * 通过物料id查询物料库存信息
     * @param attrId 物料id
     * @return 结果
     */
    MaterielStock selectMaterielStockByMatId(Integer attrId);
}
