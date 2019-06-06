package com.ruoyi.project.production.devWorkOrder.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 工单 数据层
 *
 * @author zqm
 * @date 2019-04-12
 */
public interface DevWorkOrderMapper {
    /**
     * 查询工单信息
     *
     * @param id 工单ID
     * @return 工单信息
     */
    @DataSource(value = DataSourceType.SLAVE)
    public DevWorkOrder selectDevWorkOrderById(Integer id);

    /**
     * 查询工单列表
     *
     * @param devWorkOrder 工单信息
     * @return 工单集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    public List<DevWorkOrder> selectDevWorkOrderList(DevWorkOrder devWorkOrder);

    /**
     * 新增工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    public int insertDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 修改工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    public int updateDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 删除工单
     *
     * @param id 工单ID
     * @return 结果
     */
    public int deleteDevWorkOrderById(Integer id);

    /**
     * 批量删除工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevWorkOrderByIds(String[] ids);

    /**
     * 校验工单号是否存在
     *
     * @return
     */
    public Long checkWorkOrderNumber(@Param("workorderNumber") String workorderNumber, @Param("companyId") Integer companyId);

    /**
     * 查询对应公司对应产线正在进行中的工单
     *
     * @param compand_id 公司编号
     * @param line_id    产线编号
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    DevWorkOrder selectWorkByCompandAndLine(@Param("compand_id") int compand_id, @Param("line_id") int line_id);

    @DataSource(DataSourceType.SLAVE)
    DevWorkOrder selectWorkByCompandAndLine2(@Param("compand_id") int compand_id, @Param("line_id") int line_id);

    /**
     * 查询当天对应公司对应产线的工单计划的工单编号
     *
     * @param company_id 公司编号
     * @param line_id    查询编号
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    List<String> selectDayWorkOrder(@Param("company_id") int company_id, @Param("line_id") int line_id);


    /**
     * 判断流水线是否只有一个正在进行状态的工单
     *
     * @param lineId
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    Long checkWorkLineUnique(@Param("lineId") Integer lineId);

    /**
     * 查询生产状态处于正在进行的所有工单
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderAllBeIn(@Param("companyId") Integer companyId);

    /**
     * 查询当天所有的工单
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderAllToday(@Param("companyId") Integer companyId);

    /**
     * 查询对应时间段内产线已经提交的所以工单数据
     *
     * @param line_id   产线编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    List<DevWorkOrder> selectOrderByLineIsSubmit(@Param("company_id") int company_id,
                                                 @Param("productCode")String productCode,
                                                 @Param("line_id") int line_id,
                                                 @Param("startTime") String startTime,
                                                 @Param("endTime") String endTime);


    /**
     * 查询昨天生产的工单
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderAllYesterday(@Param("companyId") Integer companyId);

    /**
     * 查询当天正在生产或者已经完成的所有工单
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderTodayBeInOrFinish();

    /**
     * 查询所有正在生产或者已经完成的工单
     *
     * @return
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderAllBeInOrFinish();

    /**
     * 通过产线id或者工单id查询已经提交的工单列表
     *
     * @param companyId 公司id
     * @param lineId    产线
     * @param workOrderId 工单id
     * @return 结果
     */
    @DataSource(value = DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkOrderFinishByLineIdOrWorkOrderId(@Param("companyId") Integer companyId,
                                                                  @Param("lineId") Integer lineId,
                                                                  @Param("workOrderId") Integer workOrderId);

    /**
     * 修改对应公司对应产品未进行的工单的ecn状态
     * @param companyId 公司id
     * @param productCode 产品编码
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    int editCompanyProductWorkOrderEcn(@Param("companyId")int companyId,@Param("productCode")String productCode);

    /**
     * 根据公司id产线id查询对应的数据
     * @param companyId 公司id
     * @param lineId 产线id
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    List<DevWorkOrder> selectWorkDataByCompanyIdAndLineId(@Param("companyId")int companyId,@Param("lineId")int lineId);

    /**
     * 查询最近完成工单信息
     * @param compand_id 公司id
     * @param line_id 产线id
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    DevWorkOrder selectLatelyCompleteWork(@Param("compand_id") int compand_id, @Param("line_id") int line_id);

    /**
     * 修改已经完成的工单标记
     * @param workid
     * @return
     */
    @DataSource(DataSourceType.SLAVE)
    int editLatelyCompleteWorkSign(@Param("workid")int workid);
}