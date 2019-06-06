package com.ruoyi.project.erp.partsStock.service;

import com.ruoyi.project.erp.partsStock.domain.PartsStock;

import java.util.List;

/**
 * 半成品库存 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface IPartsStockService {
    /**
     * 查询半成品库存信息
     *
     * @param id 半成品库存ID
     * @return 半成品库存信息
     */
    public PartsStock selectPartsStockById(Integer id);

    /**
     * 查询半成品库存列表
     *
     * @param partsStock 半成品库存信息
     * @return 半成品库存集合
     */
    public List<PartsStock> selectPartsStockList(PartsStock partsStock);

    /**
     * 新增半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    public int insertPartsStock(PartsStock partsStock);

    /**
     * 修改半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    public int updatePartsStock(PartsStock partsStock);

    /**
     * 删除半成品库存信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePartsStockByIds(String ids);

    /**
     * 通过半成品id查询库存信息
     * @param attrId 半成品id
     * @return 结果
     */
    PartsStock selectPartsStockByParId(Integer attrId);
}
