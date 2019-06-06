package com.ruoyi.project.erp.materielOutStockDetails.service;

import java.util.List;

import com.ruoyi.project.erp.materielOutStock.domain.MaterielOutStock;
import com.ruoyi.project.erp.materielOutStock.mapper.MaterielOutStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielOutStockDetails.mapper.MaterielOutStockDetailsMapper;
import com.ruoyi.project.erp.materielOutStockDetails.domain.MaterielOutStockDetails;
import com.ruoyi.project.erp.materielOutStockDetails.service.IMaterielOutStockDetailsService;
import com.ruoyi.common.support.Convert;

/**
 * 物料出库清单 服务层实现
 *
 * @author zqm
 * @date 2019-05-07
 */
@Service
public class MaterielOutStockDetailsServiceImpl implements IMaterielOutStockDetailsService {
    @Autowired
    private MaterielOutStockDetailsMapper materielOutStockDetailsMapper;

    @Autowired
    private MaterielOutStockMapper materielOutStockMapper; // 供应商物料退货单数据层

    /**
     * 查询物料出库清单信息
     *
     * @param id 物料出库清单ID
     * @return 物料出库清单信息
     */
    @Override
    public MaterielOutStockDetails selectMaterielOutStockDetailsById(Integer id) {
        return materielOutStockDetailsMapper.selectMaterielOutStockDetailsById(id);
    }

    /**
     * 查询物料出库清单列表
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 物料出库清单集合
     */
    @Override
    public List<MaterielOutStockDetails> selectMaterielOutStockDetailsList(MaterielOutStockDetails materielOutStockDetails) {
        MaterielOutStock materielOutStock = null;
        List<MaterielOutStockDetails> materielOutStockDetailsList = materielOutStockDetailsMapper.selectMaterielOutStockDetailsList(materielOutStockDetails);
        for (MaterielOutStockDetails outStockDetails : materielOutStockDetailsList) {
            materielOutStock = materielOutStockMapper.selectMaterielOutStockById(outStockDetails.getOutId());
            outStockDetails.setSupplierName(materielOutStock.getSupplierName());
            outStockDetails.setOutName(materielOutStock.getCreateName());
        }
        return materielOutStockDetailsList;
    }

    /**
     * 新增物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    @Override
    public int insertMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails) {
        return materielOutStockDetailsMapper.insertMaterielOutStockDetails(materielOutStockDetails);
    }

    /**
     * 修改物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    @Override
    public int updateMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails) {
        return materielOutStockDetailsMapper.updateMaterielOutStockDetails(materielOutStockDetails);
    }

    /**
     * 删除物料出库清单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielOutStockDetailsByIds(String ids) {
        return materielOutStockDetailsMapper.deleteMaterielOutStockDetailsByIds(Convert.toStrArray(ids));
    }

}
