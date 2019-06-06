package com.ruoyi.project.erp.materielOutStockDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielOutStockDetails.domain.MaterielOutStockDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 物料出库清单 数据层
 *
 * @author zqm
 * @date 2019-05-07
 */
public interface MaterielOutStockDetailsMapper {
    /**
     * 查询物料出库清单信息
     *
     * @param id 物料出库清单ID
     * @return 物料出库清单信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielOutStockDetails selectMaterielOutStockDetailsById(Integer id);

    /**
     * 查询物料出库清单列表
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 物料出库清单集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielOutStockDetails> selectMaterielOutStockDetailsList(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 新增物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 修改物料出库清单
     *
     * @param materielOutStockDetails 物料出库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielOutStockDetails(MaterielOutStockDetails materielOutStockDetails);

    /**
     * 删除物料出库清单
     *
     * @param id 物料出库清单ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielOutStockDetailsById(Integer id);

    /**
     * 批量删除物料出库清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielOutStockDetailsByIds(String[] ids);

    /**
     * 根据公司id和供应商id查询对应时间内未结款的物料出库详情数据
     * @param company_id 公司id
     * @param sid 供应商id
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<MaterielOutStockDetails> selectOutStockDetailsByCompanyIdAndSid(@Param("company_id")int company_id, @Param("sid")int sid,
                                                                         @Param("bTime")Date bTime,@Param("eTime")Date eTime);

}