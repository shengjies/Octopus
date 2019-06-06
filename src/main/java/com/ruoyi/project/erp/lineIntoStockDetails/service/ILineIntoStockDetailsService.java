package com.ruoyi.project.erp.lineIntoStockDetails.service;

import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;

import java.util.List;

/**
 * 产线入库明细 服务层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface ILineIntoStockDetailsService {
    /**
     * 查询产线入库明细信息
     *
     * @param id 产线入库明细ID
     * @return 产线入库明细信息
     */
    public LineIntoStockDetails selectLineIntoStockDetailsById(Integer id);

    /**
     * 查询产线入库明细列表
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 产线入库明细集合
     */
    public List<LineIntoStockDetails> selectLineIntoStockDetailsList(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 新增产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    public int insertLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 修改产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    public int updateLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails);

    /**
     * 删除产线入库明细信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLineIntoStockDetailsByIds(String ids);

}
