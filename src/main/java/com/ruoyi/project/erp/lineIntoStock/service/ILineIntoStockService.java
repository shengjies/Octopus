package com.ruoyi.project.erp.lineIntoStock.service;

import com.ruoyi.project.erp.lineIntoStock.domain.LineIntoStock;

import java.util.List;

/**
 * 产线入库 服务层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface ILineIntoStockService {
    /**
     * 查询产线入库信息
     *
     * @param id 产线入库ID
     * @return 产线入库信息
     */
    public LineIntoStock selectLineIntoStockById(Integer id);

    /**
     * 查询产线入库列表
     *
     * @param lineIntoStock 产线入库信息
     * @return 产线入库集合
     */
    public List<LineIntoStock> selectLineIntoStockList(LineIntoStock lineIntoStock);

    /**
     * 新增产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    public int insertLineIntoStock(LineIntoStock lineIntoStock);

    /**
     * 修改产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    public int updateLineIntoStock(LineIntoStock lineIntoStock);

    /**
     * 删除产线入库信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLineIntoStockByIds(String ids);

    /**
     * 作废生产入库单
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    int nullifyLineIntoStockByIds(Integer id);
}
