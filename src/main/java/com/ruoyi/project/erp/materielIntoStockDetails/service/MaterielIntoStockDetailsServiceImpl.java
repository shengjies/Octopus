package com.ruoyi.project.erp.materielIntoStockDetails.service;

import java.util.List;

import com.ruoyi.project.erp.materielIntoStock.domain.MaterielIntoStock;
import com.ruoyi.project.erp.materielIntoStock.mapper.MaterielIntoStockMapper;
import com.ruoyi.project.erp.materielStock.mapper.MaterielStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielIntoStockDetails.mapper.MaterielIntoStockDetailsMapper;
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import com.ruoyi.project.erp.materielIntoStockDetails.service.IMaterielIntoStockDetailsService;
import com.ruoyi.common.support.Convert;
import org.springframework.util.StringUtils;

/**
 * 物料入库清单 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielIntoStockDetailsServiceImpl implements IMaterielIntoStockDetailsService {
    @Autowired
    private MaterielIntoStockDetailsMapper materielIntoStockDetailsMapper;

    @Autowired
    private MaterielIntoStockMapper materielIntoStockMapper; // 物料入库单数据层

    /**
     * 查询物料入库清单信息
     *
     * @param id 物料入库清单ID
     * @return 物料入库清单信息
     */
    @Override
    public MaterielIntoStockDetails selectMaterielIntoStockDetailsById(Integer id) {
        return materielIntoStockDetailsMapper.selectMaterielIntoStockDetailsById(id);
    }

    /**
     * 查询物料入库清单列表
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 物料入库清单集合
     */
    @Override
    public List<MaterielIntoStockDetails> selectMaterielIntoStockDetailsList(MaterielIntoStockDetails materielIntoStockDetails) {
        MaterielIntoStock materielIntoStock = null;
        List<MaterielIntoStockDetails> materielIntoStockDetailsList = materielIntoStockDetailsMapper.selectMaterielIntoStockDetailsList(materielIntoStockDetails);
        for (MaterielIntoStockDetails intoStockDetail : materielIntoStockDetailsList) {
            materielIntoStock = materielIntoStockMapper.selectMaterielIntoStockById(intoStockDetail.getIntoId());
            // 添加入库人姓名
            intoStockDetail.setInToName( materielIntoStock.getCreateName());
            intoStockDetail.setSupplierName(materielIntoStock.getSupplierName());
        }
        return materielIntoStockDetailsList;
    }

    /**
     * 新增物料入库清单
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
    @Override
    public int insertMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails) {
        return materielIntoStockDetailsMapper.insertMaterielIntoStockDetails(materielIntoStockDetails);
    }

    /**
     * 修改物料入库清单
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
    @Override
    public int updateMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails) {
        return materielIntoStockDetailsMapper.updateMaterielIntoStockDetails(materielIntoStockDetails);
    }

    /**
     * 删除物料入库清单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterielIntoStockDetailsByIds(String ids) {
        return materielIntoStockDetailsMapper.deleteMaterielIntoStockDetailsByIds(Convert.toStrArray(ids));
    }

}
