package com.ruoyi.project.erp.lineIntoStockDetails.service;

import java.util.List;

import com.ruoyi.project.erp.lineIntoStock.domain.LineIntoStock;
import com.ruoyi.project.erp.lineIntoStock.mapper.LineIntoStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.lineIntoStockDetails.mapper.LineIntoStockDetailsMapper;
import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;
import com.ruoyi.project.erp.lineIntoStockDetails.service.ILineIntoStockDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 产线入库明细 服务层实现
 *
 * @author zqm
 * @date 2019-05-07
 */
@Service
public class LineIntoStockDetailsServiceImpl implements ILineIntoStockDetailsService {
    @Autowired
    private LineIntoStockDetailsMapper lineIntoStockDetailsMapper;

    @Autowired
    private LineIntoStockMapper lineIntoStockMapper; // 生产入库数据层

    /**
     * 查询产线入库明细信息
     *
     * @param id 产线入库明细ID
     * @return 产线入库明细信息
     */
    @Override
    public LineIntoStockDetails selectLineIntoStockDetailsById(Integer id) {
        return lineIntoStockDetailsMapper.selectLineIntoStockDetailsById(id);
    }

    /**
     * 查询产线入库明细列表
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 产线入库明细集合
     */
    @Override
    public List<LineIntoStockDetails> selectLineIntoStockDetailsList(LineIntoStockDetails lineIntoStockDetails) {
        List<LineIntoStockDetails> lineIntoStockDetailsList = lineIntoStockDetailsMapper.selectLineIntoStockDetailsList(lineIntoStockDetails);
        for (LineIntoStockDetails intoStockDetails : lineIntoStockDetailsList) {
            LineIntoStock lineIntoStock = lineIntoStockMapper.selectLineIntoStockById(intoStockDetails.getLineIntoId());
            intoStockDetails.setIntoName(lineIntoStock.getCreateName());
            intoStockDetails.setLineName(lineIntoStock.getLineName());
        }
        return lineIntoStockDetailsList;
    }

    /**
     * 新增产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    @Override
    public int insertLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails) {
        return lineIntoStockDetailsMapper.insertLineIntoStockDetails(lineIntoStockDetails);
    }

    /**
     * 修改产线入库明细
     *
     * @param lineIntoStockDetails 产线入库明细信息
     * @return 结果
     */
    @Override
    public int updateLineIntoStockDetails(LineIntoStockDetails lineIntoStockDetails) {
        return lineIntoStockDetailsMapper.updateLineIntoStockDetails(lineIntoStockDetails);
    }

    /**
     * 删除产线入库明细对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLineIntoStockDetailsByIds(String ids) {
        return lineIntoStockDetailsMapper.deleteLineIntoStockDetailsByIds(Convert.toStrArray(ids));
    }

}
