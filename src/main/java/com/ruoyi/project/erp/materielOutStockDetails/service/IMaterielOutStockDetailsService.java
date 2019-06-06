package com.ruoyi.project.erp.materielOutStockDetails.service;

import com.ruoyi.project.erp.materielOutStockDetails.domain.MaterielOutStockDetails;

import java.util.List;

/**
 * 物料出库清单 服务层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface IMaterielOutStockDetailsService {
    /**
     * 查询物料出库清单信息
     *
     * @param id 物料出库清单ID
     * @return 物料出库清单信息
     */
    public MaterielOutStockDetails selectMaterielOutStockDetailsById(Integer id);

    /**
     * 查询物料出库清单列表
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 物料出库清单集合
     */
    public List<MaterielOutStockDetails> selectMaterielOutStockDetailsList(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 新增物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    public int insertMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 修改物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    public int updateMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 删除物料出库清单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterielOutStockDetailsByIds(String ids);

}
