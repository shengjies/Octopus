package com.ruoyi.project.erp.materielSupplier.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.materiel.mapper.MaterielMapper;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import com.ruoyi.project.erp.purchase.mapper.PurchaseMapper;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.mapper.PurchaseDetailsMapper;
import com.ruoyi.project.erp.supplier.mapper.SupplierMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielSupplier.mapper.MaterielSupplierMapper;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.service.IMaterielSupplierService;
import com.ruoyi.common.support.Convert;

/**
 * 物料供应商关联 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielSupplierServiceImpl implements IMaterielSupplierService {
    @Autowired
    private MaterielSupplierMapper materielSupplierMapper;

    @Autowired
    private MaterielMapper materielMapper; // 物料数据层

    @Autowired
    private SupplierMapper supplierMapper; // 供应商数据层

    @Autowired
    private PurchaseDetailsMapper purchaseDetailsMapper; // 采购单明细信息

    @Autowired
    private PurchaseMapper purchaseMapper; // 采购单数据层

    /**
     * 查询物料供应商关联信息
     *
     * @param id 物料供应商关联ID
     * @return 物料供应商关联信息
     */
    @Override
    public MaterielSupplier selectMaterielSupplierById(Integer id) {
        return materielSupplierMapper.selectMaterielSupplierById(id);
    }

    /**
     * 查询物料供应商关联列表
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 物料供应商关联集合
     */
    @Override
    public List<MaterielSupplier> selectMaterielSupplierList(MaterielSupplier materielSupplier) {
        List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierList(materielSupplier);
        for (MaterielSupplier supplier : materielSuppliers) {
            supplier.setSupplier(supplierMapper.selectSupplierById(supplier.getSupplierId())); // 供应商信息
            supplier.setMateriel(materielMapper.selectMaterielById(supplier.getMaterielId())); // 物料信息
        }
        return materielSuppliers;
    }

    /**
     * 新增物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    @Override
    public int insertMaterielSupplier(MaterielSupplier materielSupplier) {
        // 判断供应商是否存在相同的编码
        int count = materielSupplierMapper.checkSupplierCodeUnique(materielSupplier.getSupplierId(),materielSupplier.getSupplierCode());
        if (count > 0) { // 数据库存在记录
            throw new BusinessException("该供应商已经存在该物料编码");
        }
        // 判断该物料是否已经录入过
        int count1 = materielSupplierMapper.checkMaterielUnique(materielSupplier.getMaterielId(),materielSupplier.getSupplierId());
        if (count1 > 0) { // 数据库存在记录
            throw new BusinessException("该物料已经关联过该供应商");
        }
        materielSupplier.setCreateId(ShiroUtils.getUserId().intValue()); // 创建者id
        materielSupplier.setCreateTime(new Date()); // 创建时间
        return materielSupplierMapper.insertMaterielSupplier(materielSupplier);
    }

    /**
     * 修改物料供应商关联
     *
     * @param materielSupplier 物料供应商关联信息
     * @return 结果
     */
    @Override
    public int updateMaterielSupplier(MaterielSupplier materielSupplier) {
        return materielSupplierMapper.updateMaterielSupplier(materielSupplier);
    }

    /**
     * 删除物料供应商关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielSupplierByIds(String ids) {
        return materielSupplierMapper.deleteMaterielSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过物料id查询物料供应商关联列表
     * @param materielId
     * @return 关联列表
     */
    @Override
    public List<MaterielSupplier> selectMaterielSupplierListByMaterielId(Integer materielId) {
        return materielSupplierMapper.selectMaterielSupplierListByMaterielId(materielId);
    }

    /**
     * 根据物料id和供应商id查询供应商编码
     * @param mid 物料id
     * @param sid 供应商id
     * @return
     */
    @Override
    public MaterielSupplier findSupplierCodeByMaterielId(int mid, int sid) {
        // 查询物料信息
        Materiel materiel = materielMapper.selectMaterielById(mid);
        MaterielSupplier materielSupplier = materielSupplierMapper.findSupplierCodeByMaterielId(mid, sid);
        // 查询对应供应商已审核未完成交付的采购单信息
        List<Purchase> purchaseList = purchaseMapper.selectPurchaseBySupIdAndComId(sid,ShiroUtils.getCompanyId());
        List<PurchaseDetails> purchaseDetails = null;
        for (Purchase purchase : purchaseList) {
            // 查询对应供应商对应物料的所有采购单信息
            purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsListBySidAndMatCode(ShiroUtils.getCompanyId(),sid,purchase.getId(),materiel.getMaterielCode());
            materielSupplier.setPurchaseDetailsList(purchaseDetails);
        }
        return materielSupplier;
    }

    /**
     * 查询物料退货采购单信息
     * @param mid  物料id
     * @param sid 供应商id
     * @return 结果
     */
    @Override
    public MaterielSupplier matOutStockByMatIdAndSupId(int mid, int sid) {
        // 查询物料信息
        Materiel materiel = materielMapper.selectMaterielById(mid);
        MaterielSupplier materielSupplier = materielSupplierMapper.findSupplierCodeByMaterielId(mid, sid);

        Purchase purchase = new Purchase();
        purchase.setSupplierId(sid);
        purchase.setCompanyId(ShiroUtils.getCompanyId());
        /**
         * 先查询还没交付的订单
         */
        List<PurchaseDetails> purchaseDetails = null; // 采购单明细
        purchase.setPurchaseStatut(StockConstants.ORDER_STATUS_TWO);
        List<Purchase> purchaseList1 = purchaseMapper.selectPurchaseList(purchase);
        if (!StringUtils.isEmpty(purchaseList1)) {
            for (Purchase purchase1 : purchaseList1) {
                // 查询对应供应商对应物料的所有采购单信息
                purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsListBySidAndMatCode(ShiroUtils.getCompanyId(),sid,purchase1.getId(),materiel.getMaterielCode());
                materielSupplier.setPurchaseDetailsList(purchaseDetails);
            }
        } else { // 采购单全部都已经交付，则查询最近的一条已经交付的订单
            Purchase purchase2 = purchaseMapper.selectPurchaseListByStatusInThree(ShiroUtils.getCompanyId(),sid,StockConstants.ORDER_STATUS_THREE);
            // 查询对应供应商对应物料的所有采购单信息
            purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsListBySidAndMatCode(ShiroUtils.getCompanyId(),sid,purchase2.getId(),materiel.getMaterielCode());
            materielSupplier.setPurchaseDetailsList(purchaseDetails);
        }
        return materielSupplier;
    }
}
