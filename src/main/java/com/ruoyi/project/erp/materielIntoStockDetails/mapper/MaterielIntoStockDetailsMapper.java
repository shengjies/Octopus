package com.ruoyi.project.erp.materielIntoStockDetails.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.materielIntoStockDetails.domain.MaterielIntoStockDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 物料入库清单 数据层
 *
 * @author zqm
 * @date 2019-04-30
 */
public interface MaterielIntoStockDetailsMapper {
    /**
     * 查询物料入库清单信息
     *
     * @param id 物料入库清单ID
     * @return 物料入库清单信息
     */
    @DataSource(value = DataSourceType.ERP)
    public MaterielIntoStockDetails selectMaterielIntoStockDetailsById(Integer id);

    /**
     * 查询物料入库清单列表
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 物料入库清单集合
     */
    @DataSource(value = DataSourceType.ERP)
    public List<MaterielIntoStockDetails> selectMaterielIntoStockDetailsList(MaterielIntoStockDetails materielIntoStockDetails);

    /**
     * 新增物料入库清单
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int insertMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails);

    /**
     * 修改物料入库清单
     *
     * @param materielIntoStockDetails 物料入库清单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int updateMaterielIntoStockDetails(MaterielIntoStockDetails materielIntoStockDetails);

    /**
     * 删除物料入库清单
     *
     * @param id 物料入库清单ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielIntoStockDetailsById(Integer id);

    /**
     * 批量删除物料入库清单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @DataSource(value = DataSourceType.ERP)
    public int deleteMaterielIntoStockDetailsByIds(String[] ids);

    /**
     * 根据公司id和供应商id查询对应时间段内物料的入库详情
     * @param company_id 公司id
     * @param sid 供应商id
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @DataSource(DataSourceType.ERP)
    List<MaterielIntoStockDetails>  selectIntoStockDetailsByCompanyIdAndSid(@Param("company_id")int company_id, @Param("sid")int sid,
                                                                            @Param("bTime")Date bTime,@Param("eTime")Date eTime);

}