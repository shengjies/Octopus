package com.ruoyi.project.erp.materiel.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.MaterielConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.fileSourceInfo.mapper.FileSourceInfoMapper;
import com.ruoyi.project.erp.materiel.controller.MaterielController;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.materielSupplier.mapper.MaterielSupplierMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materiel.mapper.MaterielMapper;
import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.materiel.service.IMaterielService;
import com.ruoyi.common.support.Convert;

/**
 * 物料 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service("materiel")
public class MaterielServiceImpl implements IMaterielService {
    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private MaterielSupplierMapper materielSupplierMapper; // 供应商物料关联数据层

    @Autowired
    private MaterielStockMapper materielStockMapper; // 物料库存数据层

    @Autowired
    private FileSourceInfoMapper fileSourceInfoMapper; // 文件资源数据层

    /**
     * 查询物料信息
     *
     * @param id 物料ID
     * @return 物料信息
     */
    @Override
    public Materiel selectMaterielById(Integer id) {
        return materielMapper.selectMaterielById(id);
    }

    /**
     * 查询物料列表
     *
     * @param materiel 物料信息
     * @return 物料集合
     */
    @Override
    public List<Materiel> selectMaterielList(Materiel materiel) {
        User sysUser = ShiroUtils.getSysUser();
        if (sysUser == null) return Collections.emptyList();
        if (!User.isSys(sysUser)) {
            materiel.setCompanyId(sysUser.getCompanyId()); // 查询自己公司的物料
        }
        List<Materiel> materielList = materielMapper.selectMaterielList(materiel);

        for (Materiel mat : materielList) {
            // 查询物料是否关联了供应商信息
            List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierListByMatIdAndSupId(mat.getId(), null);
            if (!StringUtils.isEmpty(materielSuppliers)) { // 物料关联了供应商信息
                mat.setMaterielSupplier(materielSuppliers.get(0));
            }
            // 查询物料是否上传了文件
            List<FileSourceInfo> fileSourceInfoList = fileSourceInfoMapper.selectFileSourceInfoBySaveIdAndComId(mat.getId(), sysUser.getCompanyId());
            if (!StringUtils.isEmpty(fileSourceInfoList)) {
                mat.setFileSourceInfo(fileSourceInfoList.get(0));
            }
        }
        return materielList;
    }

    /**
     * 新增物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    @Override
    public int insertMateriel(Materiel materiel) {
        User user = ShiroUtils.getSysUser();
        materiel.setCompanyId(user.getCompanyId()); // 所属公司
        materiel.setCreateId(user.getUserId().intValue()); // 创建者ID
        materiel.setCreateName(user.getUserName()); // 创建者名称
        materiel.setCreateTime(new Date()); // 创建时间
        if (materiel.getPriceImport() != null && materiel.getPriceImport() != 0.00f) {
            materiel.setPrice(new BigDecimal(materiel.getPriceImport()));
        }
        int i = materielMapper.insertMateriel(materiel);
        /**
         * 新增物料更新库存记录
         */
        // 查询物料库存是否存在记录
        //MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(materiel.getId());
        MaterielStock materielStock = materielStockMapper.selectMaterielStockByMatCodeAndComId(materiel.getMaterielCode(), user.getCompanyId());
        if (materielStock == null) { // 不存在记录，新增库存记录信息
            materielStock = new MaterielStock();
            materielStock.setCompanyId(user.getCompanyId());
            materielStock.setLastUpdate(new Date());
            materielStock.setMaterielName(materiel.getMaterielName());
            materielStock.setMaterielCode(materiel.getMaterielCode());
            materielStock.setMaterielModel(materiel.getMaterielModel());
            materielStock.setMaterielId(materiel.getId());
            materielStock.setCreateTime(new Date());
            materielStockMapper.insertMaterielStock(materielStock);
        }

        return i;
    }

    /**
     * 修改物料
     *
     * @param materiel 物料信息
     * @return 结果
     */
    @Override
    public int updateMateriel(Materiel materiel) {
        // 更新物料库存的物料信息
        updateMatlStockInfo(materiel);
        return materielMapper.updateMateriel(materiel);
    }

    public int updateMaterielByMaterielCode(Materiel materiel) {
        Materiel materiel1 = materielMapper.selectMaterielByMaterielCode(materiel.getMaterielCode(), ShiroUtils.getCompanyId());
        materiel.setId(materiel1.getId());
        // 更新物料库存的物料信息
        updateMatlStockInfo(materiel);
        if (materiel.getPriceImport() != 0.00f) {
            materiel.setPrice(new BigDecimal(materiel.getPriceImport()));
        }
        materiel.setCompanyId(ShiroUtils.getCompanyId());
        return materielMapper.updateMaterielByMaterielCode(materiel);
    }

    /**
     * 更新物料库存信息
     *
     * @param materiel 物料信息
     */
    private void updateMatlStockInfo(Materiel materiel) {
        MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(materiel.getId());
        if (!StringUtils.isNull(materielStock)) {
            materielStock.setMaterielCode(materiel.getMaterielCode());
            materielStock.setMaterielModel(materiel.getMaterielModel());
            materielStock.setMaterielName(materiel.getMaterielName());
            materielStockMapper.updateMaterielStock(materielStock);
        }
    }

    /**
     * 删除物料对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielByIds(String ids) {
        Integer[] materielIds = Convert.toIntArray(ids);
        Materiel materiel = null;
        for (Integer materielId : materielIds) {
            materiel = materielMapper.selectMaterielById(materielId);
            // 校验是否有相关联的物料文件未删除
            List<FileSourceInfo> fileSourceInfos = fileSourceInfoMapper.selectFileSourceInfoBySaveIdAndComId(materielId, ShiroUtils.getCompanyId());
            if (!StringUtils.isEmpty(fileSourceInfos)) {
                throw new BusinessException("请先删除" + materiel.getMaterielCode() + "的关联文件");
            }
            // 校验是否有相关的供应商关联信息
            List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierListByMaterielId(materielId);
            if (!StringUtils.isEmpty(materielSuppliers)) {
                throw new BusinessException("请先删除" + materiel.getMaterielCode() + "的供应商关联");
            }
            // 查询对应物料库存记录
            MaterielStock materielStock = materielStockMapper.selectMaterielStockByMaterielId(materielId);
            if (!StringUtils.isNull(materielStock)) {
                throw new BusinessException(materiel.getMaterielCode() + "存在库存记录不允许删除");
            }
        }
        return materielMapper.deleteMaterielByIds(Convert.toStrArray(ids));
    }

    /**
     * 导入物料列表
     *
     * @param list            物料列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return
     */
    @Override
    public String importMateriel(List<Materiel> list, boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new BusinessException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        User currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isNull(currentUser)) {
            throw new BusinessException("操作异常!");
        }
        for (Materiel materiel : list) {
            try {
                // 验证物料是否为空或者未输入编码
                if (materiel == null || StringUtils.isEmpty(materiel.getMaterielCode().trim())) {
                    throw new Exception("导入的物料或者物料编码不能为空！");
                }
                // 验证物料是否存在
                Materiel m = materielMapper.selectMaterielByMaterielCode(materiel.getMaterielCode().trim(), currentUser.getCompanyId());
                if (StringUtils.isNull(m)) {
                    this.insertMateriel(materiel);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、物料 " + materiel.getMaterielCode() + " 导入成功");
                } else if (isUpdateSupport) {
                    this.updateMaterielByMaterielCode(materiel); // 通过编码更新物料信息
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、物料 " + materiel.getMaterielCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料 " + materiel.getMaterielCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、物料 " + materiel.getMaterielCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 通过供应商id查询和供应商关联的物料列表
     *
     * @param supplierId 供应商id
     * @return 结果
     */
    @Override
    public List<Materiel> materielListBySupplierId(Integer supplierId) {
        List<Materiel> materielList = new ArrayList<>();
        Materiel materiel = null;
        List<MaterielSupplier> materielSupplierList = materielSupplierMapper.selectMaterielSupplierListByMatIdAndSupId(null, supplierId);
        for (MaterielSupplier materielSupplier : materielSupplierList) {
            materiel = materielMapper.selectMaterielById(materielSupplier.getMaterielId());
            materiel.setSupplierCode(materielSupplier.getSupplierCode() == null ? "" : materielSupplier.getSupplierCode());
            materielList.add(materiel);
        }
        // 查询物料不良品数量
        for (Materiel mat : materielList) {
            mat.setBadNumber(materielStockMapper.selectMaterielStockByMatCodeAndComId(mat.getMaterielCode(), ShiroUtils.getCompanyId()).getBadNumber());
        }
        return materielList;
    }

    /**
     * 通过供应商id和物料id查询物料信息
     *
     * @param supplierId 供应商id
     * @param materielId 物料id
     * @return
     */
    @Override
    public Materiel materielListByMaterielId(Integer supplierId, Integer materielId) {
        Materiel materiel = null;
        List<MaterielSupplier> materielSuppliers = materielSupplierMapper.selectMaterielSupplierListByMatIdAndSupId(materielId, supplierId);
        if (!StringUtils.isEmpty(materielSuppliers)) {
            MaterielSupplier materielSupplier = materielSuppliers.get(0);
            materiel = materielMapper.selectMaterielById(materielId);
            materiel.setSupplierCode(materielSupplier.getSupplierCode());
        }
        return materiel;
    }

    /**
     * 根据供应商id查询对应的物料信息
     *
     * @param sid 供应商id
     * @return
     */
    @Override
    @DataSource(DataSourceType.ERP)
    public List<Materiel> selectMaterielBySupplierId(int sid) {
        return materielMapper.selectMaterielBySupplierId(ShiroUtils.getCompanyId(), sid);
    }

    /**
     * 校验物料编码唯一性
     *
     * @param materiel 物料
     * @return 结果
     */
    @Override
    public String checkMaterielCodeUnique(Materiel materiel) {
        Materiel materielUnique = materielMapper.selectMaterielByMaterielCode(materiel.getMaterielCode(), ShiroUtils.getCompanyId());
        if (!StringUtils.isNull(materielUnique) && materiel.getId() != materielUnique.getId()) { // 存在相同的编码信息
            return MaterielConstants.PRODUCT_CODE_NOT_UNIQUE;
        }
        return MaterielConstants.PRODUCT_CODE_UNIQUE;
    }

    /**
     * 通过公司id查询供公司物料信息
     *
     * @return
     */
    @Override
    public List<Materiel> selectAllMatByComId() {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return materielMapper.selectAllMatByComId(user.getCompanyId());
    }

    /**
     * 查询公司的物料名称信息
     *
     * @return 结果
     */
    @Override
    public List<Materiel> selectAllMatNameByComId() {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return materielMapper.selectAllMatNameByComId(user.getCompanyId());
    }
}
