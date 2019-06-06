package com.ruoyi.project.erp.materielOutStock.service;

import com.ruoyi.project.erp.materielOutStock.domain.MaterielOutStock;

import java.util.Date;
import java.util.List;

/**
 * 物料出库 服务层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface IMaterielOutStockService {
    /**
     * 查询物料出库信息
     *
     * @param id 物料出库ID
     * @return 物料出库信息
     */
    public MaterielOutStock selectMaterielOutStockById(Integer id);

    /**
     * 查询物料出库列表
     *
     * @param materielOutStock 物料出库信息
     * @return 物料出库集合
     */
    public List<MaterielOutStock> selectMaterielOutStockList(MaterielOutStock materielOutStock);

    /**
     * 新增物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    public int insertMaterielOutStock(MaterielOutStock materielOutStock);

    /**
     * 修改物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    public int updateMaterielOutStock(MaterielOutStock materielOutStock);

    /**
     * 删除物料出库信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielOutStockByIds(String ids);

    /**
     * 作废物料退货信息
     *
     * @param id 需要作废的物料退货id
     * @return 结果
     */
    int nullifyMaterielOutStockByIds(Integer id);
}
