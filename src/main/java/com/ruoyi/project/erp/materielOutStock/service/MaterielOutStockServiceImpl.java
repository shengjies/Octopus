package com.ruoyi.project.erp.materielOutStock.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materiel.mapper.MaterielMapper;
import com.ruoyi.project.erp.materielIntoStock.service.MaterielIntoStockServiceImpl;
import com.ruoyi.project.erp.materielOutStock.domain.MaterielOutStock;
import com.ruoyi.project.erp.materielOutStock.mapper.MaterielOutStockMapper;
import com.ruoyi.project.erp.materielOutStockDetails.domain.MaterielOutStockDetails;
import com.ruoyi.project.erp.materielOutStockDetails.mapper.MaterielOutStockDetailsMapper;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.mapper.MaterielSupplierMapper;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import com.ruoyi.project.erp.purchase.mapper.PurchaseMapper;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.mapper.PurchaseDetailsMapper;
import com.ruoyi.project.erp.supplier.mapper.SupplierMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 物料出库 服务层实现
 *
 * @author zqm
 * @date 2019-05-07
 */
@Service
public class MaterielOutStockServiceImpl implements IMaterielOutStockService {
    @Autowired
    private MaterielOutStockMapper materielOutStockMapper;

    @Autowired
    private MaterielStockMapper materielStockMapper; // 物料库存数据层

    @Autowired
    private MaterielOutStockDetailsMapper materielOutStockDetailsMapper; // 物料出库清单数据层

    @Autowired
    private PurchaseMapper purchaseMapper; // 采购单数据层

    @Autowired
    private PurchaseDetailsMapper purchaseDetailsMapper; // 采购单详情数据层

    @Autowired
    private MaterielSupplierMapper materielSupplierMapper; // 物料供应商关联数据层


    /**
     * 查询物料出库信息
     *
     * @param id 物料出库ID
     * @return 物料出库信息
     */
    @Override
    public MaterielOutStock selectMaterielOutStockById(Integer id) {
        MaterielOutStock materielOutStock = materielOutStockMapper.selectMaterielOutStockById(id);
        MaterielOutStockDetails materielOutStockDetails = new MaterielOutStockDetails();
        materielOutStockDetails.setOutId(id);
        List<MaterielOutStockDetails> materielOutStockDetailsList = materielOutStockDetailsMapper.selectMaterielOutStockDetailsList(materielOutStockDetails);
        for (MaterielOutStockDetails outStockDetails : materielOutStockDetailsList) {
            String purchaseCode = outStockDetails.getPurchaseCode(); // 采购单编号
            if (!StringUtils.isEmpty(purchaseCode) && !"-1".equals(purchaseCode)) {
                PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(null, purchaseCode, outStockDetails.getSupplierCode(),
                        outStockDetails.getMaterielCode());
                outStockDetails.setPurchaseDetails(purchaseDetails);
            }
        }
        materielOutStock.setMaterielOutStockDetailsList(materielOutStockDetailsList);
        return materielOutStock;
    }

    /**
     * 查询物料出库列表
     *
     * @param materielOutStock 物料出库信息
     * @return 物料出库集合
     */
    @Override
    public List<MaterielOutStock> selectMaterielOutStockList(MaterielOutStock materielOutStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        materielOutStock.setCompanyId(user.getCompanyId());
        return materielOutStockMapper.selectMaterielOutStockList(materielOutStock);
    }

    /**
     * 新增物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    @Override
    public int insertMaterielOutStock(MaterielOutStock materielOutStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) return 0;
        /**
         * 物料退货
         */
        String matOutStockCode = CodeUtils.getMatOutStockCode(); // 物料出库单自动生成
        materielOutStock.setOutCode(matOutStockCode);
        materielOutStock.setCompanyId(user.getCompanyId());
        materielOutStock.setCreateId(user.getUserId().intValue());
        materielOutStock.setCreateName(user.getUserName());
        materielOutStock.setCreateTime(new Date());
        int i = materielOutStockMapper.insertMaterielOutStock(materielOutStock);
        if (!StringUtils.isEmpty(materielOutStock.getMaterielOutStockDetailsList())) { // 存在物料退货明细
            /**
             * 物料退货明细
             */
            BigDecimal outNumber = null;
            List<MaterielOutStockDetails> materielOutStockDetailsList = materielOutStock.getMaterielOutStockDetailsList();
            for (MaterielOutStockDetails outStockDetails : materielOutStockDetailsList) {
                /**
                 * 物料库存操作
                 */
                MaterielStock materielStock = materielStockMapper.selectMaterielStockByMatCodeAndComId(outStockDetails.getMaterielCode(),ShiroUtils.getCompanyId());
                if (StringUtils.isNull(materielStock)) { // 库存中没有改物料信息
                    throw new BusinessException("库存中没有"+outStockDetails.getMaterielCode()+"物料信息");
                } else {
                    /**
                     * 减库存不良品数量
                     */
                    if (outStockDetails.getOutNumber() > materielStock.getBadNumber()) { // 退货数量大于仓库不良品数量
                        throw new BusinessException("库存不良品数量不足");
                    }
                    materielStock.setBadNumber(materielStock.getBadNumber() - outStockDetails.getOutNumber());
                    materielStock.setTotalNumber(materielStock.getTotalNumber() - outStockDetails.getOutNumber());
                    materielStock.setLastUpdate(new Date());
                    materielStockMapper.updateMaterielStock(materielStock);
                }

                /**
                 * 物料退货明细
                 */
                // 查询物料供应商关联价格信息
                List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierListByMatIdAndSupId(outStockDetails.getMaterielId(), materielOutStock.getSupplierId());
                if (!StringUtils.isEmpty(materielSuppliers)) { // 物料供应商关联信息
                    outNumber = new BigDecimal(outStockDetails.getOutNumber());
                    MaterielSupplier materielSupplier = materielSuppliers.get(0);
                    outStockDetails.setPrice(materielSupplier.getSupplierPrice()); // 设置单价
                    outStockDetails.setTotalPrice(materielSupplier.getSupplierPrice().multiply(outNumber)); // 设置总价格
                }
                outStockDetails.setOutId(materielOutStock.getId());
                outStockDetails.setOutCode(matOutStockCode);
                outStockDetails.setCreateTime(new Date());

                materielOutStockDetailsMapper.insertMaterielOutStockDetails(outStockDetails);


                /**
                 * 采购单操作
                 */
                String purchaseCode = outStockDetails.getPurchaseCode(); // 物料退货当中采购单号信息
                if (!StringUtils.isEmpty(purchaseCode) && !"-1".equals(purchaseCode)) { // 是选择了采购单退货
                    /**
                     * 采购单操作
                     */
                    Purchase purchase = purchaseMapper.selectPurchaseBySupIdAndPuraseCode(ShiroUtils.getCompanyId(), materielOutStock.getSupplierId(), outStockDetails.getPurchaseCode());
                    purchase.setDeliverTotalNum(purchase.getDeliverTotalNum() - outStockDetails.getOutNumber());
                    purchase.setPurchaseStatut(StockConstants.ORDER_STATUS_TWO); // 采购单总数减少，交付状态变成未交付
                    purchaseMapper.updatePurchase(purchase);
                    /**
                     * 采购单详情，已采购数量
                     */
                    PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(purchase.getId(), purchase.getPurchaseCode(),
                            outStockDetails.getSupplierCode(), outStockDetails.getMaterielCode());
                    purchaseDetails.setDeliverNum(purchaseDetails.getDeliverNum() - outStockDetails.getOutNumber());
                    purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);
                }
            }
        }
        return i;
    }

    /**
     * 修改物料出库
     *
     * @param materielOutStock 物料出库信息
     * @return 结果
     */
    @Override
    public int updateMaterielOutStock(MaterielOutStock materielOutStock) {
        return materielOutStockMapper.updateMaterielOutStock(materielOutStock);
    }

    /**
     * 删除物料出库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielOutStockByIds(String ids) {
        return materielOutStockMapper.deleteMaterielOutStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 作废物料退货信息
     *
     * @param id 需要作废的物料退货id
     * @return 结果
     */
    @Override
    public int nullifyMaterielOutStockByIds(Integer id) {
        // 物料退货单
        MaterielOutStock materielOutStock = materielOutStockMapper.selectMaterielOutStockById(id);
        MaterielOutStockDetails materielOutStockDetails = new MaterielOutStockDetails();
        materielOutStockDetails.setOutId(materielOutStock.getId());
        // 物料退货明细
        List<MaterielOutStockDetails> materielOutStockDetailsList = materielOutStockDetailsMapper.selectMaterielOutStockDetailsList(materielOutStockDetails);
        for (MaterielOutStockDetails outStockDetails : materielOutStockDetailsList) {
            /**
             * 库存数量回滚
             */
            MaterielStock materielStock = materielStockMapper.selectMaterielStockByMatCodeAndComId(outStockDetails.getMaterielCode(),ShiroUtils.getCompanyId());
            materielStock.setTotalNumber(materielStock.getTotalNumber() + outStockDetails.getOutNumber());
            materielStock.setBadNumber(materielStock.getBadNumber() + outStockDetails.getOutNumber());
            materielStock.setLastUpdate(new Date());
            materielStockMapper.updateMaterielStock(materielStock);
            /**
             * 采购单操作
             */
            String purchaseCode = outStockDetails.getPurchaseCode(); // 物料退货当中采购单号信息
            if (!StringUtils.isEmpty(purchaseCode) && !"-1".equals(purchaseCode)) { // 是选择了采购单退货
                /**
                 * 采购单操作
                 */
                Purchase purchase = purchaseMapper.selectPurchaseBySupIdAndPuraseCode(ShiroUtils.getCompanyId(), materielOutStock.getSupplierId(), outStockDetails.getPurchaseCode());
                purchase.setDeliverTotalNum(purchase.getDeliverTotalNum() + outStockDetails.getOutNumber());
                purchaseMapper.updatePurchase(purchase);
                /**
                 * 采购单详情，已采购数量
                 */
                PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(purchase.getId(), purchase.getPurchaseCode(),
                        outStockDetails.getSupplierCode(), outStockDetails.getMaterielCode());
                purchaseDetails.setDeliverNum(purchaseDetails.getDeliverNum() + outStockDetails.getOutNumber());
                purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);
            }
            // 物料退货明细删除状态更新为已作废
            materielOutStockDetailsMapper.deleteMaterielOutStockDetailsById(outStockDetails.getId());
        }
        return materielOutStockMapper.deleteMaterielOutStockById(id);
    }
}
