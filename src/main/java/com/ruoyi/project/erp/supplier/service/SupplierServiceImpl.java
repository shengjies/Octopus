package com.ruoyi.project.erp.supplier.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.supplier.mapper.SupplierMapper;
import com.ruoyi.project.erp.supplier.domain.Supplier;
import com.ruoyi.project.erp.supplier.service.ISupplierService;
import com.ruoyi.common.support.Convert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 供应商数据 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service(value = "supplier")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询供应商数据信息
     *
     * @param id 供应商数据ID
     * @return 供应商数据信息
     */
    @Override
    public Supplier selectSupplierById(Integer id) {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询供应商数据列表
     *
     * @param supplier 供应商数据信息
     * @return 供应商数据集合
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier, HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        if (sysUser == null) return Collections.emptyList();
        if (!User.isSys(sysUser)) {
            supplier.setCompanyId(sysUser.getCompanyId()); // 查询自己公司的供应商
        }
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商数据
     *
     * @param supplier 供应商数据信息
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier,HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        supplier.setCompanyId(sysUser.getCompanyId()); // 所属公司
        supplier.setCreateId(sysUser.getUserId().intValue()); // 创建者ID
        supplier.setCreateName(sysUser.getUserName()); // 创建者名称
        supplier.setCreateTime(new Date()); // 创建时间
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商数据
     *
     * @param supplier 供应商数据信息
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 删除供应商数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(String ids) {
        return supplierMapper.deleteSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询对应公司所有的供应商
     * @return
     */
    public List<Supplier> selectSupplierAllByCompanyId(Cookie[] cookies){
        Supplier supplier = new Supplier();
        supplier.setCompanyId(JwtUtil.getTokenCookie(cookies).getCompanyId());
        return supplierMapper.selectSupplierList(supplier);
    }

}
