package com.ruoyi.project.production.devWorkOrder.service;

import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;

import java.util.List;

/**
 * 工单 服务层
 *
 * @author zqm
 * @date 2019-04-12
 */
public interface IDevWorkOrderService {
    /**
     * 查询工单信息
     *
     * @param id 工单ID
     * @return 工单信息
     */
    public DevWorkOrder selectDevWorkOrderById(Integer id);

    /**
     * 查询工单列表
     *
     * @param devWorkOrder 工单信息
     * @return 工单集合
     */
    public List<DevWorkOrder> selectDevWorkOrderList(DevWorkOrder devWorkOrder);

    /**
     * 新增工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    public int insertDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 修改工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    public int updateDevWorkOrder(DevWorkOrder devWorkOrder);

    /**
     * 删除工单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDevWorkOrderByIds(String ids);

    /**
     * 校验工单号是否存在
     * @return
     */
    public String checkWorkOrderNumber(DevWorkOrder devWorkOrder);

    /**
     * 工单开始暂停状态修改，第一次点击开始初始化数据
     * @param id
     * @return
     */
    int editWorkerOrderById(Integer id);

    /**
     * 校验流水线是否只有一个处于生产状态的工单
     * @param lineId
     * @return
     */
    Long checkWorkLineUnique(Integer lineId);

    /**
     * 页面点击完成工单，工单可进行修改
     * @param id
     * @return
     */
    int finishWorkerOrder(Integer id);

    /**
     * 页面点击提交工单，工单状态不可修改和删除
     * @param id
     * @return
     */
    int submitWorkOrder(Integer id);

    /**
     * 根据工单编号查询对应的工单信息
     * @param work_id 工单编号
     * @return
     */
    DevWorkOrder findWorkInfoById(int work_id);

    /**
     * 通过产线Id查询该产线正在生产的工单
     * @param lineId
     * @return
     */
    DevWorkOrder selectWorkOrderBeInByLineId(Integer lineId);

    /**
     * 通过产线id查询已经提交的工单列表
     * @param lineId 产线
     * @return
     */
    List<DevWorkOrder> selectWorkOrderFinishByLineId(int lineId);

    /**
     * 根据产线id或工单id查询工单信息
     * @param lineId
     * @param workOrderId
     * @return 结果
     */
    DevWorkOrder selectWorkOrderFinishByLineIdAndWorkOrderId(int lineId, int workOrderId);

    /**
     * 工单变更
     * @param order
     * @return
     */
    int changeOrder(DevWorkOrder order);

    /**
     * 根据工单id查询对应的ECN信息
     * @param workId 工单id
     * @return
     */
    DevWorkOrder selectWorkOrderEcn(int workId);
}
