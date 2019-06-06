package com.ruoyi.project.product.list.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.ProductConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.erp.fileSourceInfo.domain.FileSourceInfo;
import com.ruoyi.project.erp.fileSourceInfo.mapper.FileSourceInfoMapper;
import com.ruoyi.project.erp.lineIntoStockDetails.mapper.LineIntoStockDetailsMapper;
import com.ruoyi.project.erp.materiel.domain.Materiel;
import com.ruoyi.project.erp.materielSupplier.domain.MaterielSupplier;
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import com.ruoyi.project.erp.productCustomer.mapper.ProductCustomerMapper;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import com.ruoyi.project.erp.productIntoStockDetails.mapper.ProductIntoStockDetailsMapper;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import com.ruoyi.project.erp.productOutStockDetails.mapper.ProductOutStockDetailsMapper;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.erp.stockHandleDetails.domain.StockHandleDetails;
import com.ruoyi.project.erp.stockHandleDetails.mapper.StockHandleDetailsMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.mapper.EcnLogMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;

/**
 * 产品管理 服务层实现
 *
 * @author ruoyi
 * @date 2019-04-10
 */
@Service("product")
public class DevProductListServiceImpl implements IDevProductListService {
    @Autowired
    private DevProductListMapper devProductListMapper;

    @Autowired
    private DevCompanyMapper devCompanyMapper;

    @Autowired
    private ProductCustomerMapper productCustomerMapper; // 产品客户关联数据层

    @Autowired
    private EcnLogMapper ecnLogMapper;

    @Autowired
    private DevWorkOrderMapper workOrderMapper;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private FileSourceInfoMapper fileSourceInfoMapper;

    public boolean isSys() {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return false;
        }
        return User.isSys(user);
    }

    /**
     * 查询产品管理信息
     *
     * @param id 产品管理ID
     * @return 产品管理信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public DevProductList selectDevProductListById(Integer id) {
        return devProductListMapper.selectDevProductListById(id);
    }

    /**
     * 查询产品管理列表
     *
     * @param devProductList 产品管理信息
     * @return 产品管理集合
     */
    @Override
    public List<DevProductList> selectDevProductListList(DevProductList devProductList) {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }
        if (!User.isSys(user)) {
            devProductList.setCompanyId(user.getCompanyId());
        }
        List<DevProductList> list = devProductListMapper.selectDevProductListList(devProductList);
        for (DevProductList productList : list) {
            if (productList.getCompanyId() == null) continue;
            DevCompany devCompany = devCompanyMapper.selectDevCompanyById(productList.getCompanyId());
            if (devCompany != null) productList.setComName(devCompany.getComName());
            // 查询产品是否关联过客户
            List<ProductCustomer> productCustomers = productCustomerMapper.selectProductCustomerByProIdOrCusId(productList.getId(), null);
            if (!StringUtils.isEmpty(productCustomers)) {
                productList.setProductCustomer(productCustomers.get(0));
            }
            // 查询产品是否上传过文件
            List<FileSourceInfo> fileSourceInfos = fileSourceInfoMapper.selectFileSourceInfoBySaveIdAndComId(productList.getId(), user.getCompanyId());
            if (!StringUtils.isEmpty(fileSourceInfos)) {
                productList.setFileSourceInfo(fileSourceInfos.get(0));
            }
        }
        return list;
    }


    /**
     * 新增产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @Override
    public int insertDevProductList(DevProductList devProductList) {
        User currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isNull(currentUser)) {
            return 0;
        }
        DevProductList product = devProductListMapper.selectDevProductByCode(currentUser.getCompanyId(), devProductList.getProductCode());
        if (product != null) return 0;
        if (devProductList.getPriceImport() != 0.0f) {
            devProductList.setPrice(new BigDecimal(devProductList.getPriceImport())); // 设置导入价格
        }
        devProductList.setCompanyId(currentUser.getCompanyId());
        devProductList.setCreate_by(currentUser.getUserName());
        int i = devProductListMapper.insertDevProductList(devProductList);
        /**
         * 新增产品时值增加库存记录信息
         */
        ProductStock productStock = productStockMapper.selectProductStockByProCode(currentUser.getCompanyId(), devProductList.getProductCode());
        if (productStock == null) {
            productStock = new ProductStock();
            productStock.setCompanyId(currentUser.getCompanyId());
            productStock.setLastUpdate(new Date());
            productStock.setCreateTime(new Date());
            productStock.setProductCode(devProductList.getProductCode());
            productStock.setProductModel(devProductList.getProductModel());
            productStock.setProductName(devProductList.getProductName());
            productStock.setProductId(devProductList.getId());
            productStockMapper.insertProductStock(productStock);
        }
        return i;
    }

    /**
     * 修改产品管理
     *
     * @param devProductList 产品管理信息
     * @return 结果
     */
    @Override
    public int updateDevProductList(DevProductList devProductList) {
        if (devProductList.getPriceImport() != 0.0f) {
            devProductList.setPrice(new BigDecimal(devProductList.getPriceImport())); // 设置导入价格
        }
        devProductList.setCompanyId(ShiroUtils.getCompanyId());
        // 更新产品库存记录的产品信息
        ProductStock productStock = productStockMapper.selectProductStockByProId(devProductList.getId());
        if (!StringUtils.isNull(productStock)) {
            productStock.setProductCode(devProductList.getProductCode());
            productStock.setProductName(devProductList.getProductName());
            productStock.setProductModel(devProductList.getProductModel());
            productStockMapper.updateProductStock(productStock);
        }
        return devProductListMapper.updateDevProductList(devProductList);
    }

    /**
     * 删除产品管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDevProductListByIds(String ids) {
        Integer[] productIds = Convert.toIntArray(ids);
        DevProductList product = null;
        for (Integer productId : productIds) {
            product = devProductListMapper.selectDevProductListById(productId);
            // 校验是否有相关联的产品文件未删除
            List<FileSourceInfo> fileSourceInfos = fileSourceInfoMapper.selectFileSourceInfoBySaveIdAndComId(productId, ShiroUtils.getCompanyId());
            if (!StringUtils.isEmpty(fileSourceInfos)) {
                throw new BusinessException("请先删除" + product.getProductCode() + "的关联文件");
            }
            // 校验是否有相关的客户关联信息
            List<ProductCustomer> productCustomers = productCustomerMapper.selectProductCustomerByProIdOrCusId(productId, null);
            if (!StringUtils.isEmpty(productCustomers)) {
                throw new BusinessException("请先删除" + product.getProductCode() + "的客户关联");
            }
            /**
             * 查询对应产品是否存在库存记录，存在库存记录则不允许删除
             */
            ProductStock productStock = productStockMapper.selectProductStockByProId(productId);
            if (!StringUtils.isNull(productStock)) {  // 存在库存记录
                throw new BusinessException(product.getProductCode() + "存在库存记录不允许删除");
            }
        }
        return devProductListMapper.deleteDevProductListByIds(Convert.toStrArray(ids));
    }

    @Override
    public String importProduct(List<DevProductList> list, boolean isUpdateSupport) {
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new BusinessException("导入产品数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        User currentUser = ShiroUtils.getSysUser();
        if (StringUtils.isNull(currentUser)) {
            throw new BusinessException("操作异常!");
        }
        for (DevProductList product : list) {
            try {
                // 验证是否存在这个用户
                if (product == null || StringUtils.isEmpty(product.getProductCode().trim())) {
                    throw new Exception("导入的产品或者产品编码不能为空！");
                }
                DevProductList devProductList = devProductListMapper.selectDevProductByCode(currentUser.getCompanyId(), product.getProductCode().trim());
                if (StringUtils.isNull(devProductList)) {
                    product.setDef_id(0);
                    successNum++;
                    this.insertDevProductList(product);
                    successMsg.append("<br/>" + successNum + "、产品 " + product.getProductCode() + " 导入成功");
                } else if (isUpdateSupport) {
                    product.setId(devProductList.getId());
                    this.updateDevProductList(product);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品 " + product.getProductCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品 " + product.getProductCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品 " + product.getProductCode() + " 导入失败：";
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
     * 查询所属公司所有的产品
     *
     * @return
     */
    @Override
    public List<DevProductList> selectProductAllByCompanyId() {
        User user = ShiroUtils.getSysUser();
        if (user == null) return Collections.emptyList();
        return devProductListMapper.selectProductAllByCompanyId(user.getCompanyId());
    }

    /**
     * 通过产品id查询产品信息
     *
     * @param productId
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public DevProductList findProductInfo(Integer productId) {
        DevProductList productInfo = devProductListMapper.findProductInfo(productId);
        EcnLog ecnLog = ecnLogMapper.findByCompanyIdAndProductId(ShiroUtils.getCompanyId(), productId);
        if (!StringUtils.isNull(ecnLog)) {
            productInfo.setEcnText(ecnLog.getEcnText());
        }
        return productInfo;
    }

    /**
     * 检验产品编码是否唯一
     *
     * @param product
     * @return
     */
    @Override
    public String checkProductCodeUnique(DevProductList product) {
        Integer companyId = ShiroUtils.getSysUser().getCompanyId();
        DevProductList productUnique = devProductListMapper.checkProductCodeUnique(product.getProductCode(), companyId);
        if (!StringUtils.isNull(productUnique) && product.getId() != productUnique.getId()) { //公司存在该产品编码
            return ProductConstants.PRODUCT_CODE_NOT_UNIQUE;
        }
        return ProductConstants.PRODUCT_CODE_UNIQUE;
    }


    /**
     * 根据客户编号查询对应产品
     *
     * @param customerId 客户编号
     * @return
     */
    @Override
    public List<DevProductList> selectProductByCustomerId(int customerId) {
        //查询产品id
        List<Integer> pids = productCustomerMapper.selectProductIdByCustomerId(customerId);
        if (StringUtils.isEmpty(pids)) { // 没有产品关联
            return Collections.emptyList();
        }
        //查询客户关联的产品
        List<DevProductList> productList = devProductListMapper.findCustomerProduct(pids);
        for (DevProductList product : productList) {
            ProductStock productStock = productStockMapper.selectProductStockByProId(product.getId());
            product.setGoodNumber(productStock.getGoodNumber());
        }
        return productList;
    }

    /**
     * 通过客户id查询相关联的产品信息
     *
     * @param customerId 客户id
     * @return 结果
     */
    @Override
    public List<DevProductList> findProductByCustomerId(Integer customerId) {

        List<DevProductList> productList = new ArrayList<>();
        DevProductList product = null;
        List<ProductCustomer> productCustomerList = productCustomerMapper.selectProductCustomerByProIdOrCusId(null, customerId);
        for (ProductCustomer productCustomer : productCustomerList) {
            // 查询出产品信息
            product = devProductListMapper.selectDevProductListById(productCustomer.getProductId());
            if (product == null) continue;
            // 设置客户产品编码
            product.setCustomerCode(StringUtils.isEmpty(productCustomer.getCustomerCode()) ? "" : productCustomer.getCustomerCode());
            productList.add(product);
        }
        return productList;
    }

    /**
     * 通过产品id和客户id查询产品信息
     *
     * @param productId  产品id
     * @param customerId 客户id
     * @return 结果
     */
    @Override
    public DevProductList findProductByProIdAndCusId(Integer productId, Integer customerId) {
        DevProductList product = null;
        List<ProductCustomer> productCustomers = productCustomerMapper.selectProductCustomerByProIdOrCusId(productId, customerId);
        if (!StringUtils.isEmpty(productCustomers)) {
            ProductCustomer productCustomer = productCustomers.get(0);
            product = devProductListMapper.selectDevProductListById(productCustomer.getProductId()); // 获取产品信息
            product.setCustomerCode(productCustomer.getCustomerCode()); // 供应商对应的产品编码
        }
        return product;
    }

    /**
     * ecn 信息操作
     *
     * @param productList
     * @return
     */
    @Override
    public int ecnChange(DevProductList productList) {
        if (productList == null) return 0;
        User u = ShiroUtils.getSysUser();
        if (u == null) return 0;
        // 查询产品信息
        DevProductList product = devProductListMapper.selectDevProductListById(productList.getId());
        if (productList.getEcnStatus() == 1) {
            //保存相关ecn信息
            EcnLog ecnLog = new EcnLog();
            ecnLog.setCompanyId(u.getCompanyId());
            ecnLog.setCreateId(u.getUserId().intValue());
            ecnLog.setCreatePeople(u.getUserName());
            ecnLog.setEcnText(productList.getEcnText());
            ecnLog.setEcnType(1);
            ecnLog.setSaveCode(product.getProductCode());
            ecnLog.setSaveId(productList.getId());
            ecnLog.setCreateTime(new Date());
            ecnLogMapper.insertEcnLog(ecnLog);
            //修改对应公司对应产品下未进行的工单的ecn状态
            workOrderMapper.editCompanyProductWorkOrderEcn(u.getCompanyId(), productList.getProductCode());
        }
        return devProductListMapper.updateDevProductList(productList);
    }

    /**
     * 根据订单id查询对应的产品信息
     *
     * @param orderId 订单id
     * @return
     */
    @Override
    public List<DevProductList> selectProductAllByOrderId(int orderId) {
        if (orderId == -1) {
            return this.selectProductAllByCompanyId();
        } else {
            return devProductListMapper.findProductByOrderId(orderId);
        }
    }

    /**
     * 查询各公司产品名称信息
     * 名称可能存在重复情况
     *
     * @return 结果
     */
    @Override
    public List<DevProductList> selectProNameAllByComId() {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }
        return devProductListMapper.selectProNameAllByComId(user.getCompanyId());
    }

    /**
     * 根据公司id和产品编号查询对应的产品信息
     * @param companyId 公司id
     * @param code 产品编号
     * @return
     */
    @Override
    public DevProductList selectProductByCompanyIdAndCode(int companyId, String code) {
        return devProductListMapper.checkProductCodeUnique(code,companyId);
    }
}
