package com.ruoyi.project.erp.partsStock.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.partsStock.mapper.PartsStockMapper;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.erp.partsStock.service.IPartsStockService;
import com.ruoyi.common.support.Convert;

/**
 * 半成品库存 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class PartsStockServiceImpl implements IPartsStockService {
    @Autowired
    private PartsStockMapper partsStockMapper;

    /**
     * 查询半成品库存信息
     *
     * @param id 半成品库存ID
     * @return 半成品库存信息
     */
    @Override
    public PartsStock selectPartsStockById(Integer id) {
        return partsStockMapper.selectPartsStockById(id);
    }

    /**
     * 查询半成品库存列表
     *
     * @param partsStock 半成品库存信息
     * @return 半成品库存集合
     */
    @Override
    public List<PartsStock> selectPartsStockList(PartsStock partsStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        partsStock.setCompanyId(user.getCompanyId());
        return partsStockMapper.selectPartsStockList(partsStock);
    }

    /**
     * 新增半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    @Override
    public int insertPartsStock(PartsStock partsStock) {
        return partsStockMapper.insertPartsStock(partsStock);
    }

    /**
     * 修改半成品库存
     *
     * @param partsStock 半成品库存信息
     * @return 结果
     */
    @Override
    public int updatePartsStock(PartsStock partsStock) {
        return partsStockMapper.updatePartsStock(partsStock);
    }

    /**
     * 删除半成品库存对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePartsStockByIds(String ids) {
        return partsStockMapper.deletePartsStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过半成品id查询库存信息
     * @param attrId 半成品id
     * @return 结果
     */
    @Override
    public PartsStock selectPartsStockByParId(Integer attrId) {
        return partsStockMapper.selectPartsStockByPartsId(attrId);
    }
}
