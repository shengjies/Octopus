package com.ruoyi.project.erp.materielIntoStock.service;

import com.mchange.lang.ShortUtils;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.materiel.mapper.MaterielMapper;
import com.ruoyi.project.erp.materielIntoStock.domain.MaterielIntoStock;
import com.ruoyi.project.erp.materielIntoStock.mapper.MaterielIntoStockMapper;
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import com.ruoyi.project.erp.materielIntoStockDetails.mapper.MaterielIntoStockDetailsMapper;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import com.ruoyi.project.erp.materielStockIqc.mapper.MaterielStockIqcMapper;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.mapper.MaterielSupplierMapper;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import com.ruoyi.project.erp.purchase.domain.PurchaseResult;
import com.ruoyi.project.erp.purchase.mapper.PurchaseMapper;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.mapper.PurchaseDetailsMapper;
import com.ruoyi.project.erp.supplier.mapper.SupplierMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 物料入库 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielIntoStockServiceImpl implements IMaterielIntoStockService {
    @Autowired
    private MaterielIntoStockMapper materielIntoStockMapper;

    @Autowired
    private MaterielIntoStockDetailsMapper materielIntoStockDetailsMapper; // 物料入库清单数据层

    @Autowired
    private MaterielStockMapper materielStockMapper; // 物料库存数据层

    @Autowired
    private PurchaseMapper purchaseMapper; // 采购单数据层

    @Autowired
    private PurchaseDetailsMapper purchaseDetailsMapper; // 采购单详情数据层

    @Autowired
    private MaterielSupplierMapper materielSupplierMapper; // 物料供应商关联数据层

    @Autowired
    private MaterielStockIqcMapper materielStockIqcMapper; // 物料IQC数据层

    /**
     * 查询物料入库信息
     *
     * @param id 物料入库ID
     * @return 物料入库信息
     */
    @Override
    public MaterielIntoStock selectMaterielIntoStockById(Integer id) {
        MaterielIntoStock materielIntoStock = materielIntoStockMapper.selectMaterielIntoStockById(id);
        //  查询物料入库明细
        MaterielIntoStockDetails materielIntoStockDetails = new MaterielIntoStockDetails();
        materielIntoStockDetails.setIntoId(id);
        List<MaterielIntoStockDetails> materielIntoStockDetailsList = materielIntoStockDetailsMapper.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        for (MaterielIntoStockDetails intoStockDetails : materielIntoStockDetailsList) {
            if (!StringUtils.isEmpty(intoStockDetails.getPurchaseCode()) && !"-1".equals(intoStockDetails.getPurchaseCode())) {
                // 获取采购单明细
                PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(null,intoStockDetails.getPurchaseCode(),
                        intoStockDetails.getSupplierCode(),intoStockDetails.getMaterielCode());
                intoStockDetails.setPurchaseDetails(purchaseDetails);
            }
        }
        materielIntoStock.setMaterielIntoStockDetails(materielIntoStockDetailsList);
        return materielIntoStock;
    }

    /**
     * 查询物料入库列表
     *
     * @param materielIntoStock 物料入库信息
     * @return 物料入库集合
     */
    @Override
    public List<MaterielIntoStock> selectMaterielIntoStockList(MaterielIntoStock materielIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        materielIntoStock.setCompanyId(user.getCompanyId());
        return materielIntoStockMapper.selectMaterielIntoStockList(materielIntoStock);
    }

    /**
     * 新增物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    @Override
    public int insertMaterielIntoStock(MaterielIntoStock materielIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null) return 0;
        /**
         * 物料入库
         */
        String matIntoStockCode = CodeUtils.getMatIntoStockCode();
        materielIntoStock.setIntoCode(matIntoStockCode);
        materielIntoStock.setCompanyId(user.getCompanyId());
        materielIntoStock.setCreateId(user.getUserId().intValue());
        materielIntoStock.setCreateTime(new Date());
        materielIntoStock.setCreateName(user.getUserName());
        int i = materielIntoStockMapper.insertMaterielIntoStock(materielIntoStock);
        if (!StringUtils.isEmpty(materielIntoStock.getMaterielIntoStockDetails())) {
            BigDecimal intoNumber = null;
            List<MaterielIntoStockDetails> materielIntoStockDetails = materielIntoStock.getMaterielIntoStockDetails();
            for (MaterielIntoStockDetails materielIntoStockDetail : materielIntoStockDetails) {
                /**
                 * 物料入库详情
                 */
                // 查询物料对应供应商价格信息
                List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierListByMatIdAndSupId(materielIntoStockDetail.getMaterielId(), materielIntoStock.getSupplierId());
                if (!StringUtils.isEmpty(materielSuppliers)) { // 物料供应商关联信息
                    MaterielSupplier materielSupplier = materielSuppliers.get(0);
                    intoNumber = new BigDecimal(materielIntoStockDetail.getIntoNumber());
                    materielIntoStockDetail.setPrice(materielSupplier.getSupplierPrice());
                    materielIntoStockDetail.setTotalPrice(materielSupplier.getSupplierPrice().multiply(intoNumber));
                }
                materielIntoStockDetail.setIntoId(materielIntoStock.getId()); // 物料入库主键
                materielIntoStockDetail.setIntoCode(matIntoStockCode); // 物料入库单号
                materielIntoStockDetail.setCreateTime(new Date()); // 物料入库时间

                materielIntoStockDetailsMapper.insertMaterielIntoStockDetails(materielIntoStockDetail);

                MaterielStockIqc materielStockIqc = materielStockIqcMapper.selectMaterielStockIqcByComId(user.getCompanyId());
                // 物料内部库存记录
                MaterielStock materielStock = materielStockMapper.selectMaterielStockByMatCodeAndComId(materielIntoStockDetail.getMaterielCode(),user.getCompanyId());

                /**
                 * 库存操作
                 * 判断公司IQC的开启状态，开启，物料全部录入临时仓库
                 */
                // 开启物料IQC
                if (!StringUtils.isNull(materielStockIqc) && StockConstants.IQC_YES.equals(materielStockIqc.getStockIqc())) {
                    /**
                     * 物料录入到临时仓库
                     */
                    if (StringUtils.isNull(materielStock)) { // 库存不存在，新增库存记录
                        materielStock = new MaterielStock();
                        materielStock.setCompanyId(ShiroUtils.getCompanyId()); // 公司id
                        materielStock.setCreateTime(new Date()); // 创建时间
                        materielStock.setLastUpdate(new Date()); // 最后一次更新时间
                        materielStock.setMaterielId(materielIntoStockDetail.getMaterielId());
                        materielStock.setMaterielModel(materielIntoStockDetail.getMaterielModel());
                        materielStock.setMaterielCode(materielIntoStockDetail.getMaterielCode());
                        materielStock.setMaterielName(materielIntoStockDetail.getMaterielName());
                        materielStock.setTemporaryNumber(materielIntoStockDetail.getIntoNumber()); // 更新临时仓库数量
                        materielStock.setTotalNumber(materielIntoStockDetail.getIntoNumber());

                        materielStockMapper.insertMaterielStock(materielStock); // 新增
                    } else {
                        materielStock.setTotalNumber(materielStock.getTotalNumber() + materielIntoStockDetail.getIntoNumber());
                        materielStock.setTemporaryNumber(materielStock.getTemporaryNumber() + materielIntoStockDetail.getIntoNumber());
                        materielStock.setLastUpdate(new Date());
                        materielStockMapper.updateMaterielStock(materielStock); // 更新
                    }
                    /**
                     * 采购单明细预收仓库新增数量
                     * 采购单总数不变
                     */
                    PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(null,materielIntoStockDetail.getPurchaseCode(),
                            materielIntoStockDetail.getSupplierCode(),materielIntoStockDetail.getMaterielCode());
                    purchaseDetails.setPrereceiveNumber(purchaseDetails.getPrereceiveNumber() + materielIntoStockDetail.getIntoNumber());
                    purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);

                } else {   // 没有开启IQC，物料直接录入到良品仓库
                    if (StringUtils.isNull(materielStock)) { // 库存不存在，新增库存记录
                        materielStock = new MaterielStock();
                        materielStock.setCompanyId(ShiroUtils.getCompanyId()); // 公司id
                        materielStock.setCreateTime(new Date()); // 创建时间
                        materielStock.setLastUpdate(new Date()); // 最后一次更新时间
                        materielStock.setMaterielId(materielIntoStockDetail.getMaterielId());
                        materielStock.setMaterielModel(materielIntoStockDetail.getMaterielModel());
                        materielStock.setMaterielCode(materielIntoStockDetail.getMaterielCode());
                        materielStock.setMaterielName(materielIntoStockDetail.getMaterielName());
                        materielStock.setGoodNumber(materielIntoStockDetail.getIntoNumber());
                        materielStock.setTotalNumber(materielIntoStockDetail.getIntoNumber());

                        materielStockMapper.insertMaterielStock(materielStock); // 新增
                    } else {
                        materielStock.setTotalNumber(materielStock.getTotalNumber() + materielIntoStockDetail.getIntoNumber());
                        materielStock.setGoodNumber(materielStock.getGoodNumber() + materielIntoStockDetail.getIntoNumber());
                        materielStock.setLastUpdate(new Date());
                        materielStockMapper.updateMaterielStock(materielStock); // 更新
                    }
                    /**
                     * 采购单操作
                     */
                    // 获取采购单号
                    String purchaseCode = materielIntoStockDetail.getPurchaseCode();
                    if (!StringUtils.isNull(purchaseCode) && !purchaseCode.equals("-1")) { // 选择了采购单
                        /**
                         * 采购单增加总数
                         */
                        // 获取采购单信息
                        Purchase purchase = purchaseMapper.selectPurchaseBySupIdAndPuraseCode(user.getCompanyId(),materielIntoStock.getSupplierId(),purchaseCode);
                        purchase.setDeliverTotalNum(purchase.getDeliverTotalNum() + materielIntoStockDetail.getIntoNumber());
                        purchaseMapper.updatePurchase(purchase);
                        /**
                         * 采购单详情增加已采购数量
                         */
                        PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(purchase.getId(),purchase.getPurchaseCode(),
                                materielIntoStockDetail.getSupplierCode(),materielIntoStockDetail.getMaterielCode());
                        purchaseDetails.setDeliverNum(purchaseDetails.getDeliverNum() + materielIntoStockDetail.getIntoNumber());
                        purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);
                    }
                }
            }
        }
        return i;
    }

    /**
     * 修改物料入库
     *
     * @param materielIntoStock 物料入库信息
     * @return 结果
     */
    @Override
    public int updateMaterielIntoStock(MaterielIntoStock materielIntoStock) {
        return materielIntoStockMapper.updateMaterielIntoStock(materielIntoStock);
    }

    /**
     * 删除物料入库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielIntoStockByIds(String ids) {
        return materielIntoStockMapper.deleteMaterielIntoStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 作废物料入库单
     * @param id 物料入库id
     * @return 结果
     */
    @Override
    public int nullifyMaterielIntoStockByIds(Integer id) {
        MaterielIntoStock materielIntoStock = materielIntoStockMapper.selectMaterielIntoStockById(id);
        // 采购单数据回滚
        // 查询作废入库单详情信息
        MaterielIntoStockDetails materielIntoStockDetails = new MaterielIntoStockDetails();
        materielIntoStockDetails.setIntoId(id);
        List<MaterielIntoStockDetails> materielIntoStockDetailsList = materielIntoStockDetailsMapper.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        // 物料iqc状态
        MaterielStockIqc materielStockIqc = materielStockIqcMapper.selectMaterielStockIqcByComId(ShiroUtils.getCompanyId());
        MaterielStock materielStock = null;
        for (MaterielIntoStockDetails intoStockDetails : materielIntoStockDetailsList) {
            // 库存数据回滚
            materielStock = materielStockMapper.selectMaterielStockByMatCodeAndComId(intoStockDetails.getMaterielCode(),ShiroUtils.getCompanyId());
            /**
             * 开启iqc的情况，库存回滚临时仓库数量
             */
            if (!StringUtils.isNull(materielStockIqc) && StockConstants.IQC_YES.equals(materielStockIqc.getStockIqc())) { // 开启物料IQC
                materielStock.setTemporaryNumber(materielStock.getTemporaryNumber() - intoStockDetails.getIntoNumber());
                materielStock.setLastUpdate(new Date());
                materielStockMapper.updateMaterielStock(materielStock);

                /**
                 * 采购单明细数据回滚，回滚预收仓库数量
                 */
                // 采购单明细数据回滚
                PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(null, intoStockDetails.getPurchaseCode(),
                        intoStockDetails.getSupplierCode(), intoStockDetails.getMaterielCode());
                purchaseDetails.setPrereceiveNumber(purchaseDetails.getPrereceiveNumber() - intoStockDetails.getIntoNumber());
                purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);

            } else  { // 未开启IQC的情况
                materielStock.setTotalNumber(materielStock.getTotalNumber() - intoStockDetails.getIntoNumber());
                materielStock.setGoodNumber(materielStock.getGoodNumber() - intoStockDetails.getIntoNumber());
                materielStock.setLastUpdate(new Date());
                materielStockMapper.updateMaterielStock(materielStock);
                // 物料入库关联的采购单详情信息
                if (!StringUtils.isEmpty(intoStockDetails.getPurchaseCode()) && !"-1".equals(intoStockDetails.getPurchaseCode())) {
                    // 采购单数据回滚
                    Purchase purchase = purchaseMapper.selectPurchaseBySupIdAndPuraseCode(ShiroUtils.getCompanyId(), materielIntoStock.getSupplierId(), intoStockDetails.getPurchaseCode());
                    purchase.setDeliverTotalNum(purchase.getDeliverTotalNum() - intoStockDetails.getIntoNumber());
                    purchaseMapper.updatePurchase(purchase);

                    // 采购单明细数据回滚
                    PurchaseDetails purchaseDetails = purchaseDetailsMapper.selectPurchaseDetailsByCode(purchase.getId(), purchase.getPurchaseCode(),
                            intoStockDetails.getSupplierCode(), intoStockDetails.getMaterielCode());
                    purchaseDetails.setDeliverNum(purchaseDetails.getDeliverNum() - intoStockDetails.getIntoNumber());
                    purchaseDetailsMapper.updatePurchaseDetails(purchaseDetails);
                }
            }

            // 物料入库明细删除状态更新
            materielIntoStockDetailsMapper.deleteMaterielIntoStockDetailsById(intoStockDetails.getId());
        }
        return materielIntoStockMapper.deleteMaterielIntoStockById(id);
    }
}
