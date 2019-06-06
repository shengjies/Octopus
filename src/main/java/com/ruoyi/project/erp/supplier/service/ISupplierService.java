package com.ruoyi.project.erp.supplier.service;

import com.ruoyi.project.erp.supplier.domain.Supplier;

import java.util.List;

/**
 * 供应商数据 服务层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface ISupplierService {
    /**
     * 查询供应商数据信息
     *
     * @param id 供应商数据ID
     * @return 供应商数据信息
     */
    public Supplier selectSupplierById(Integer id);

    /**
     * 查询供应商数据列表
     *
     * @param supplier 供应商数据信息
     * @return 供应商数据集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商数据
     *
     * @param supplier 供应商数据信息
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商数据
     *
     * @param supplier 供应商数据信息
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 删除供应商数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierByIds(String ids);

}
