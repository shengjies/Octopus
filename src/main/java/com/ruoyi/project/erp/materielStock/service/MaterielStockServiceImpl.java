package com.ruoyi.project.erp.materielStock.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import com.ruoyi.project.erp.materielStock.domain.MaterielStock;
import com.ruoyi.project.erp.materielStock.service.IMaterielStockService;
import com.ruoyi.common.support.Convert;

/**
 * 物料库存 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielStockServiceImpl implements IMaterielStockService {
    @Autowired
    private MaterielStockMapper materielStockMapper;

    /**
     * 查询物料库存信息
     *
     * @param id 物料库存ID
     * @return 物料库存信息
     */
    @Override
    public MaterielStock selectMaterielStockById(Integer id) {
        return materielStockMapper.selectMaterielStockById(id);
    }

    /**
     * 查询物料库存列表
     *
     * @param materielStock 物料库存信息
     * @return 物料库存集合
     */
    @Override
    public List<MaterielStock> selectMaterielStockList(MaterielStock materielStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null ) {
            return Collections.emptyList();
        }
        materielStock.setCompanyId(user.getCompanyId());
        return materielStockMapper.selectMaterielStockList(materielStock);
    }

    /**
     * 新增物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    @Override
    public int insertMaterielStock(MaterielStock materielStock) {
        return materielStockMapper.insertMaterielStock(materielStock);
    }

    /**
     * 修改物料库存
     *
     * @param materielStock 物料库存信息
     * @return 结果
     */
    @Override
    public int updateMaterielStock(MaterielStock materielStock) {
        return materielStockMapper.updateMaterielStock(materielStock);
    }

    /**
     * 删除物料库存对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielStockByIds(String ids) {
        return materielStockMapper.deleteMaterielStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过物料id查询物料库存信息
     * @param attrId 物料id
     * @return 结果
     */
    @Override
    public MaterielStock selectMaterielStockByMatId(Integer attrId) {
        return materielStockMapper.selectMaterielStockByMaterielId(attrId);
    }
}
