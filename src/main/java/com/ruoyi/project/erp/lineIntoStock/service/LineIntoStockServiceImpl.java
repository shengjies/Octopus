package com.ruoyi.project.erp.lineIntoStock.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.lineIntoStockDetails.domain.LineIntoStockDetails;
import com.ruoyi.project.erp.lineIntoStockDetails.mapper.LineIntoStockDetailsMapper;
import com.ruoyi.project.erp.parts.domain.Parts;
import com.ruoyi.project.erp.parts.mapper.PartsMapper;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.mapper.PartsStockMapper;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.lineIntoStock.mapper.LineIntoStockMapper;
import com.ruoyi.project.erp.lineIntoStock.domain.LineIntoStock;
import com.ruoyi.project.erp.lineIntoStock.service.ILineIntoStockService;
import com.ruoyi.common.support.Convert;
import springfox.documentation.spring.web.json.Json;

/**
 * 产线入库 服务层实现
 *
 * @author zqm
 * @date 2019-05-07
 */
@Service
public class LineIntoStockServiceImpl implements ILineIntoStockService {
    @Autowired
    private LineIntoStockMapper lineIntoStockMapper;

    @Autowired
    private LineIntoStockDetailsMapper lineIntoStockDetailsMapper; // 生产入库明细数据层

    @Autowired
    private ProductStockMapper productStockMapper; // 成品库存数据层

    @Autowired
    private PartsStockMapper partsStockMapper; // 半成品库存数据层

    @Autowired
    private DevProductListMapper productMapper; // 产品数据层

    @Autowired
    private PartsMapper partsMapper; // 半成品数据层

    @Autowired
    private DevWorkOrderMapper workOrderMapper; // 工单数据层

    /**
     * 查询产线入库信息
     *
     * @param id 产线入库ID
     * @return 产线入库信息
     */
    @Override
    public LineIntoStock selectLineIntoStockById(Integer id) {
        LineIntoStock lineIntoStock = lineIntoStockMapper.selectLineIntoStockById(id);
        // 生产入库明细
        List<LineIntoStockDetails> lineIntoStockDetailList = lineIntoStockDetailsMapper.selectLineIntoStockDetailsByLineIntoId(id);
        for (LineIntoStockDetails intoStockDetails : lineIntoStockDetailList) {
            Integer workOrderId = intoStockDetails.getWorkOrderId(); // 获取的工单主键信息
            if (workOrderId != -1) { // 选择了工单
                DevWorkOrder workOrder = workOrderMapper.selectDevWorkOrderById(workOrderId);
                intoStockDetails.setWorkOrder(workOrder);
            }
        }
        lineIntoStock.setLineIntoStockDetails(lineIntoStockDetailList);
        return lineIntoStock;
    }

    /**
     * 查询产线入库列表
     *
     * @param lineIntoStock 产线入库信息
     * @return 产线入库集合
     */
    @Override
    public List<LineIntoStock> selectLineIntoStockList(LineIntoStock lineIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        lineIntoStock.setCompanyId(user.getCompanyId());
        return lineIntoStockMapper.selectLineIntoStockList(lineIntoStock);
    }

    /**
     * 新增产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    @Override
    public int insertLineIntoStock(LineIntoStock lineIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null) return 0;
        // 产线入库单
        try {
            String matIntoStockCode = CodeUtils.getMatIntoStockCode(); // 自动生成生产入库单
            lineIntoStock.setIntoCode(matIntoStockCode);
            lineIntoStock.setCompanyId(user.getCompanyId());
            lineIntoStock.setCreateId(user.getUserId().intValue());
            lineIntoStock.setCreateName(user.getUserName());
            lineIntoStock.setCreateTime(new Date());
            lineIntoStockMapper.insertLineIntoStock(lineIntoStock);

            if (!StringUtils.isEmpty(lineIntoStock.getDetails())) {
                // 成品入库明细
                List<LineIntoStockDetails> lineIntoStockDetails = JSON.parseArray(lineIntoStock.getDetails(), LineIntoStockDetails.class);
                for (LineIntoStockDetails lineIntoStockDetail : lineIntoStockDetails) {
                    Integer workOrderId = lineIntoStockDetail.getWorkOrderId(); // 工单id
                    if (workOrderId != -1) { // 选择了工单进行入库
                        /**
                         * 更新工单的实际入库数量
                         */
                        DevWorkOrder workOrder = workOrderMapper.selectDevWorkOrderById(lineIntoStockDetail.getWorkOrderId());
                        workOrder.setActualWarehouseNum(workOrder.getActualWarehouseNum() + lineIntoStockDetail.getDetIntoNum());
                        workOrderMapper.updateDevWorkOrder(workOrder);
                    }
                    lineIntoStockDetail.setLineIntoId(lineIntoStock.getId());
                    lineIntoStockDetail.setIntoCode(matIntoStockCode);
                    lineIntoStockDetail.setCreateTime(new Date());

                    /**
                     * 不同的入库类型不同的库存操作
                     */
                    // 入库类型
                    Integer intoType = lineIntoStockDetail.getIntoType();
                    if (StockConstants.DETAILS_TYPE_PRODUCT.equals(intoType)) { // 入库类型属于成品 状态 0
                        // 判断库存是否存在
                        ProductStock productStock = productStockMapper.selectProductStockByProCode(user.getCompanyId(), lineIntoStockDetail.getDetIntoCode());
                        if (StringUtils.isNull(productStock)) { // 不存在记录新增
                            productStock = new ProductStock();
                            productStock.setCompanyId(user.getCompanyId());
                            productStock.setCreateTime(new Date());
                            productStock.setLastUpdate(new Date());
                            productStock.setGoodNumber(lineIntoStockDetail.getDetIntoNum()); // 良品数量
                            productStock.setProductId(lineIntoStockDetail.getDetIntoId()); // 产品id
                            productStock.setProductCode(lineIntoStockDetail.getDetIntoCode()); // 编码
                            productStock.setTotalNumber(lineIntoStockDetail.getDetIntoNum()); // 总数
                            productStock.setProductName(lineIntoStockDetail.getDetIntoName()); // 名称
                            productStock.setProductModel(lineIntoStockDetail.getDetIntoModel()); // 型号

                            productStockMapper.insertProductStock(productStock); // 新增产品库存
                        } else {
                            productStock.setTotalNumber(productStock.getTotalNumber() + lineIntoStockDetail.getDetIntoNum()); // 更新总数
                            productStock.setGoodNumber(productStock.getGoodNumber() + lineIntoStockDetail.getDetIntoNum()); // 更新良品
                            productStock.setLastUpdate(new Date());

                            productStockMapper.updateProductStock(productStock); // 更新产品库存
                        }
                    } else {  // 入库类型属于半成品
                        // 判断库存是否存在
                        PartsStock PartsStock = partsStockMapper.selectPartsStockByProCode(user.getCompanyId(), lineIntoStockDetail.getDetIntoCode());
                        if (StringUtils.isNull(PartsStock)) { // 不存在半成品库存记录新增

                            // 半成品信息
                            Parts parts = partsMapper.selectPartsByCode(user.getCompanyId(), lineIntoStockDetail.getDetIntoCode());
                            if (StringUtils.isNull(parts)) { // 不存在半成品记录
                                // 新增半成品信息
                                parts = new Parts();
                                parts.setCompanyId(user.getCompanyId());
                                parts.setPartsCode(lineIntoStockDetail.getDetIntoCode());
                                parts.setPartsName(lineIntoStockDetail.getDetIntoName());
                                parts.setCreateId(user.getUserId().intValue());
                                parts.setCreateName(user.getUserName());
                                parts.setCreateTime(new Date());
                                partsMapper.insertParts(parts);
                            }
                            PartsStock = new PartsStock();
                            PartsStock.setPartId(parts.getId());
                            PartsStock.setCompanyId(user.getCompanyId());
                            PartsStock.setCreateTime(new Date());
                            PartsStock.setLastUpdate(new Date());
                            PartsStock.setGoodNumber(lineIntoStockDetail.getDetIntoNum()); // 良品数量
                            PartsStock.setTotalNumber(lineIntoStockDetail.getDetIntoNum()); // 总数
                            PartsStock.setPartCode(lineIntoStockDetail.getDetIntoCode()); // 编码
                            PartsStock.setPartName(lineIntoStockDetail.getDetIntoName()); // 名称
                            lineIntoStockDetail.setDetIntoId(parts.getId());

                            partsStockMapper.insertPartsStock(PartsStock); // 新增半成品库存
                        } else {
                            PartsStock.setTotalNumber(PartsStock.getTotalNumber() + lineIntoStockDetail.getDetIntoNum()); // 更新总数
                            PartsStock.setGoodNumber(PartsStock.getGoodNumber() + lineIntoStockDetail.getDetIntoNum()); // 更新良品
                            PartsStock.setLastUpdate(new Date());
                            lineIntoStockDetail.setDetIntoId(PartsStock.getPartId());
                            partsStockMapper.updatePartsStock(PartsStock); // 更新半成品库存
                        }
                    }

                    lineIntoStockDetailsMapper.insertLineIntoStockDetails(lineIntoStockDetail); // 更新生产入库明细

                }
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 修改产线入库
     *
     * @param lineIntoStock 产线入库信息
     * @return 结果
     */
    @Override
    public int updateLineIntoStock(LineIntoStock lineIntoStock) {
        return lineIntoStockMapper.updateLineIntoStock(lineIntoStock);
    }

    /**
     * 删除产线入库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLineIntoStockByIds(String ids) {
        return lineIntoStockMapper.deleteLineIntoStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 作废生产入库单
     *
     * @param id 生产入库主键id
     * @return 结果
     */
    @Override
    public int nullifyLineIntoStockByIds(Integer id) {
        /**
         * 查询出所有工单，更新实际入库数量以及产品库存信息
         */
        LineIntoStock lineIntoStock = lineIntoStockMapper.selectLineIntoStockById(id);

        List<LineIntoStockDetails> lineIntoStockDetails = lineIntoStockDetailsMapper.selectLineIntoStockDetailsByLineIntoId(id);
        if (!StringUtils.isEmpty(lineIntoStockDetails)) {
            DevWorkOrder devWorkOrder = null;
            ProductStock productStock = null;
            PartsStock partsStock = null;
            for (LineIntoStockDetails lineIntoStockDetail : lineIntoStockDetails) {
                // 更新工单实际入库数量 回滚
                devWorkOrder = workOrderMapper.selectDevWorkOrderById(lineIntoStockDetail.getWorkOrderId());
                devWorkOrder.setActualWarehouseNum(devWorkOrder.getActualWarehouseNum() - lineIntoStockDetail.getDetIntoNum());
                workOrderMapper.updateDevWorkOrder(devWorkOrder);
                Integer intoType = lineIntoStockDetail.getIntoType(); // 入口类型
                // 入库类型为成品更新成品库存
                if (intoType.equals(StockConstants.DETAILS_TYPE_PRODUCT)) {
                    // 更新产品实际库存 回滚
                    productStock = productStockMapper.selectProductStockByProCode(ShiroUtils.getCompanyId(), lineIntoStockDetail.getDetIntoCode());
                    productStock.setTotalNumber(productStock.getTotalNumber() - lineIntoStockDetail.getDetIntoNum());
                    productStock.setGoodNumber(productStock.getGoodNumber() - lineIntoStockDetail.getDetIntoNum());
                    productStock.setLastUpdate(new Date());
                    productStockMapper.updateProductStock(productStock); // 更新产品库存
                } else { // 入库类型为半成品
                    partsStock = partsStockMapper.selectPartsStockByProCode(ShiroUtils.getCompanyId(), lineIntoStockDetail.getDetIntoCode());
                    partsStock.setTotalNumber(partsStock.getTotalNumber() - lineIntoStockDetail.getDetIntoNum());
                    partsStock.setGoodNumber(partsStock.getGoodNumber() - lineIntoStockDetail.getDetIntoNum());
                    partsStock.setLastUpdate(new Date());
                    partsStockMapper.updatePartsStock(partsStock);
                }
                // 作废生产入库明细
                lineIntoStockDetailsMapper.deleteLineIntoStockDetailsById(lineIntoStockDetail.getId());
            }
        }
        return lineIntoStockMapper.deleteLineIntoStockById(id);
    }
}
