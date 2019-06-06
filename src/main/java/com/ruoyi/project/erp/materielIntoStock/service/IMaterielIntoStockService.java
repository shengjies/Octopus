package com.ruoyi.project.erp.materielIntoStock.service;

import com.ruoyi.project.erp.materielIntoStock.domain.MaterielIntoStock;

import java.util.Date;
import java.util.List;

/**
 * 物料入库 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IMaterielIntoStockService {
    /**
     * 查询物料入库信息
     *
     * @param id 物料入库ID
     * @return 物料入库信息
     */
    public MaterielIntoStock selectMaterielIntoStockById(Integer id);

    /**
     * 查询物料入库列表
     *
     * @param materielIntoStock 物料入库信息
     * @return 物料入库集合
     */
    public List<MaterielIntoStock> selectMaterielIntoStockList(MaterielIntoStock materielIntoStock);

    /**
     * 新增物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    public int insertMaterielIntoStock(MaterielIntoStock materielIntoStock);

    /**
     * 修改物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    public int updateMaterielIntoStock(MaterielIntoStock materielIntoStock);

    /**
     * 删除物料入库信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielIntoStockByIds(String ids);

    /**
     * 作废物料入库单
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    int nullifyMaterielIntoStockByIds(Integer id);
}
