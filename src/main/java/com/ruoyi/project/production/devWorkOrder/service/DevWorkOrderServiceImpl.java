package com.ruoyi.project.production.devWorkOrder.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.WorkConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.TimeUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.orderDetails.mapper.OrderDetailsMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.mapper.EcnLogMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import com.ruoyi.project.production.workData.domain.WorkData;
import com.ruoyi.project.production.workData.mapper.WorkDataMapper;
import com.ruoyi.project.production.workDayHour.domain.WorkDayHour;
import com.ruoyi.project.production.workDayHour.mapper.WorkDayHourMapper;
import com.ruoyi.project.production.workOrderChange.domain.WorkOrderChange;
import com.ruoyi.project.production.workOrderChange.mapper.WorkOrderChangeMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.common.support.Convert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 工单 服务层实现
 *
 * @author zqm
 * @date 2019-04-12
 */
@Service("workOrder")
public class DevWorkOrderServiceImpl implements IDevWorkOrderService {

    @Autowired
    private DevWorkOrderMapper devWorkOrderMapper;

    @Autowired
    private DevWorkDataMapper devWorkDataMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductionLineMapper productionLineMapper; // 产线

    @Autowired
    private DevProductListMapper productListMapper; // 产品

    @Autowired
    private DevIoMapper devIoMapper;  // IO口

    @Autowired
    private DevListMapper devListMapper;

    @Autowired
    private WorkDataMapper workDataMapper; // 工单数据

    @Autowired
    private WorkDayHourMapper workDayHourMapper; // IO口每小时数据

    @Autowired
    private WorkOrderChangeMapper orderChangeMapper;

    @Autowired
    private EcnLogMapper ecnLogMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;



    /**
     * 查询工单信息
     *
     * @param id 工单ID
     * @return 工单信息
     */
    @Override
    public DevWorkOrder selectDevWorkOrderById(Integer id) {
        DevWorkOrder workOrder = devWorkOrderMapper.selectDevWorkOrderById(id);
        ProductionLine productionLine = productionLineMapper.selectProductionLineById(workOrder.getLineId());
        User  user = userMapper.selectUserInfoById(productionLine.getDeviceLiable());
        productionLine.setDeviceLiableName(user == null?"":user.getUserName());
        user = userMapper.selectUserInfoById(productionLine.getDeviceLiableTow());
        productionLine.setDeviceLiableTowName(user == null?"":user.getUserName());
        workOrder.setProductionLine(productionLine);
        return workOrder;
    }

    /**
     * 查询工单列表
     *
     * @param devWorkOrder 工单信息
     * @return 工单集合
     */
    @Override
    public List<DevWorkOrder> selectDevWorkOrderList(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        User sysUser = JwtUtil.getTokenUser(request);
        if (sysUser == null) {
            return Collections.emptyList();
        }
        if (!User.isSys(sysUser)) {
            devWorkOrder.setCompanyId(sysUser.getCompanyId());
        }
        List<DevWorkOrder> workOrders = devWorkOrderMapper.selectDevWorkOrderList(devWorkOrder);
        for (DevWorkOrder workOrder : workOrders) {
            User user = userMapper.selectUserInfoById(workOrder.getDeviceLiable());
            ProductionLine productionLine = productionLineMapper.selectProductionLineById(workOrder.getLineId());
            if (null != user) {
                workOrder.setUser(user);
            }
            if (null != productionLine) {
                workOrder.setProductionLine(productionLine);
            }
        }
        return workOrders;
    }

    /**
     * 新增工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    @Override
    public int insertDevWorkOrder(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        User u = JwtUtil.getTokenUser(request);
        if(u == null)return  0;
        Integer productId = Integer.valueOf(devWorkOrder.getProductCode());
        DevProductList devProductList = productListMapper.selectDevProductListById(productId);
        // 设置工单产品的名称
        devWorkOrder.setProductName(devProductList ==null?"":devProductList.getProductName());
        // 设置工单产品编码
        devWorkOrder.setProductCode(devProductList ==null?"":devProductList.getProductCode());
        // 设置工单属于哪个公司
        devWorkOrder.setCompanyId(u.getCompanyId());
        // 创建者
        devWorkOrder.setCreateBy(u.getUserName());
        devWorkOrderMapper.insertDevWorkOrder(devWorkOrder);
        if(devWorkOrder.getEcnStatus() == 1 ){//添加ecn备注信息
            // 新增工单变更记录
            EcnLog ecnLog = new EcnLog();
            ecnLog.setCompanyId(u.getCompanyId());
            ecnLog.setSaveId(devWorkOrder.getId());
            ecnLog.setSaveCode(devWorkOrder.getWorkorderNumber());
            ecnLog.setEcnType(2);
            ecnLog.setEcnText(devWorkOrder.getEcnText());
            ecnLog.setCreatePeople(devWorkOrder.getCreateBy());
            ecnLog.setCreateId(u.getUserId().intValue());
            ecnLog.setCreateTime(new Date());
            ecnLogMapper.insertEcnLog(ecnLog);
            // 更新产品ECN备注信息
            if (!devProductList.getEcnText().equals(devWorkOrder.getEcnText())) { // 新建工单时更改了产品ECN
                // 更新ECN日志
                EcnLog proEcnLog = new EcnLog();
                proEcnLog.setCompanyId(u.getCompanyId());
                proEcnLog.setSaveId(devProductList.getId());
                proEcnLog.setSaveCode(devProductList.getProductCode());
                proEcnLog.setEcnType(1);
                proEcnLog.setEcnText(devWorkOrder.getEcnText());
                proEcnLog.setCreatePeople(devWorkOrder.getCreateBy());
                proEcnLog.setCreateId(u.getUserId().intValue());
                proEcnLog.setCreateTime(new Date());
                ecnLogMapper.insertEcnLog(proEcnLog);
                // 更新产品ECN
                devProductList.setEcnText(devWorkOrder.getEcnText());
                productListMapper.updateDevProductList(devProductList);
            }
        }
        return 1;
    }

    /**
     * 修改工单
     *
     * @param devWorkOrder 工单信息
     * @return 结果
     */
    @Override
    public int updateDevWorkOrder(DevWorkOrder devWorkOrder,User user) {
        DevWorkOrder workOrder = devWorkOrderMapper.selectDevWorkOrderById(devWorkOrder.getId());
        Long userId = user.getUserId(); // 登录用户id
        ProductionLine productionLine = productionLineMapper.selectProductionLineById(workOrder.getLineId());
        // 不是工单负责人
        if (productionLine.getDeviceLiable() != userId.intValue() && productionLine.getDeviceLiableTow() != userId.intValue()) {
            throw new BusinessException("不是工单负责人");
        }
        return devWorkOrderMapper.updateDevWorkOrder(devWorkOrder);
    }

    /**
     * 删除工单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteDevWorkOrderByIds(String ids) {
        return devWorkOrderMapper.deleteDevWorkOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 校验工单号是否存在
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public String checkWorkOrderNumber(DevWorkOrder devWorkOrder,HttpServletRequest request) {
        Integer companyId = JwtUtil.getTokenUser(request).getCompanyId();
        Long count = devWorkOrderMapper.checkWorkOrderNumber(devWorkOrder.getWorkorderNumber(), companyId);
        if (count > 0) { // 说明数据库存在工单数据
            return WorkConstants.WORKERORDER_NUMBER_NOT_UNIQUE;
        }
        return WorkConstants.WORKERORDER_NUMBER_UNIQUE;
    }

    /**
     * 工单状态控制 <br>
     * 点击开始，开始工单，数据进行初始化
     *
     * @return
     */
    @Override
    public int editWorkerOrderById(Integer id,HttpServletRequest request) {
        User user = JwtUtil.getTokenUser(request);
        Long userId = user.getUserId(); // 登录用户id
        DevWorkOrder devWorkOrder = devWorkOrderMapper.selectDevWorkOrderById(id);

        ProductionLine productionLine = productionLineMapper.selectProductionLineById(devWorkOrder.getLineId());
        // 不是工单负责人
        if (productionLine.getDeviceLiable() != userId.intValue() && productionLine.getDeviceLiableTow() != userId.intValue()) {
            throw new BusinessException("不是工单负责人");
        }
        if (devWorkOrder.getOperationStatus() == 0) {
            Long count = devWorkOrderMapper.checkWorkLineUnique(devWorkOrder.getLineId());
            // 判断流水线是否只有一个正在进行生产的工单
            if (count > 0) {
                throw new BusinessException("该流水线有工单未完成");
            }
        }

        // 工单生产状态处于进行中
        if (null != devWorkOrder && devWorkOrder.getWorkorderStatus().equals(WorkConstants.WORK_STATUS_STARTING)) {
            // 页面点击暂停按钮暂时暂停工单生产
            if (devWorkOrder.getOperationStatus().equals(WorkConstants.OPERATION_STATUS_STARTING)) {
                devWorkOrder.setOperationStatus(WorkConstants.OPERATION_STATUS_PAUSE);
                devWorkOrder.setUpdateBy(user.getUserName());
                //将其工单对应的数据需要重新记录初始值
                devWorkDataMapper.updateWorkSigInit(devWorkOrder.getId());
            } else if (devWorkOrder.getOperationStatus().equals(WorkConstants.OPERATION_STATUS_PAUSE)) {
                //页面点击开始按钮继续工单生产
                devWorkOrder.setOperationStatus(WorkConstants.OPERATION_STATUS_STARTING);
                devWorkOrder.setUpdateBy(user.getUserName());
            }
        }
        //首次点击开始，工单处于未进行、未开始的状态，页面点击开始按钮
        if (null != devWorkOrder && devWorkOrder.getWorkorderStatus().equals(WorkConstants.WORK_STATUS_NOSTART)) {
            if (devWorkOrder.getOperationStatus().equals(WorkConstants.OPERATION_STATUS_NOSTART)) {
                devWorkOrder.setStartTime(new Date());  // 实际开始时间
            }
            devWorkOrder.setWorkorderStatus(WorkConstants.WORK_STATUS_STARTING);  // 修改工单的状态为进行中
            devWorkOrder.setOperationStatus(WorkConstants.OPERATION_STATUS_STARTING);   // 修改工单的操作状态为正在进行，页面显示暂停按钮
            devWorkOrder.setUpdateBy(user.getUserName());   // 工单的更新者

            // 通过产线id获取io口集合信息
            List<DevIo> devIos = devIoMapper.selectLineDevIO(devWorkOrder.getLineId()); // 对应产线IO口列表
            DevList devList = null;
            WorkData workData = null;
            WorkDayHour workDayHour = null;
            for (DevIo io : devIos) {
                // 通过io口id查询硬件信息
                //devDevice = devDeviceMapper.selectDevDeviceById(io.getDevId());
                devList = devListMapper.selectDevListById(io.getDevId());
                // 初始化工单数据
                workData = new WorkData();
                workData.setWorkId(devWorkOrder.getId()); // 所属工单
                workData.setCompanyId(devWorkOrder.getCompanyId()); // 所属公司
                workData.setLineId(devWorkOrder.getLineId()); // 所属产线
                workData.setDevId(devList.getId()); // 所属硬件
                workData.setDevName(devList.getDeviceName()); // 硬件名称
                workData.setIoId(io.getId()); // 所属IO口id
                workData.setIoName(io.getIoName()); // 所属IO口名称
                workData.setIoOrder(io.getIoOrder()); // 所属IO的排序
                workData.setCreateTime(new Date()); // 创建时间
                workDataMapper.insertWorkData(workData);// 保存工单数据

                // 初始化工单各个IO口每小时数据
                workDayHour = new WorkDayHour();
                workDayHour.setWorkId(devWorkOrder.getId()); // 所属工单
                workDayHour.setCompanyId(devWorkOrder.getCompanyId()); // 所属公司
                workDayHour.setLineId(devWorkOrder.getLineId()); // 所属产线
                workDayHour.setDevId(devList.getId()); // 所属硬件
                workDayHour.setDevName(devList.getDeviceName()); // 硬件名称
                workDayHour.setIoId(io.getId()); // 所属IO口id
                workDayHour.setIoName(io.getIoName()); // 所属IO口名称
                workDayHour.setIoOrder(io.getIoOrder()); // 所属IO的排序
                workDayHour.setDataTime(new Date()); // 创建时间年月日
                workDayHour.setCreateTime(new Date()); // 创建时间年月日时分秒
                workDayHourMapper.insertWorkDayHour(workDayHour); // 保存工单各个IO口每小时数据
            }
        }

        return devWorkOrderMapper.updateDevWorkOrder(devWorkOrder);
    }

    /**
     * 校验流水线是否只有一个处于生产状态的工单
     *
     * @param lineId
     * @return
     */
    @Override
    public Long checkWorkLineUnique(Integer lineId) {
        return devWorkOrderMapper.checkWorkLineUnique(lineId);
    }

    /**
     * 页面点击完成工单，工单可进行修改
     *
     * @param id
     * @return
     */
    @Override
    public int finishWorkerOrder(Integer id,HttpServletRequest request) {
        User tokenUser = JwtUtil.getTokenUser(request);
        Long userId = tokenUser.getUserId(); // 登录用户id
        DevWorkOrder devWorkOrder = devWorkOrderMapper.selectDevWorkOrderById(id);
        ProductionLine productionLine = productionLineMapper.selectProductionLineById(devWorkOrder.getLineId());
        // 不是工单负责人
        if (productionLine.getDeviceLiable() != userId.intValue() && productionLine.getDeviceLiableTow() != userId.intValue()) {
            throw new BusinessException("不是工单负责人");
        }
        devWorkOrder.setWorkorderStatus(WorkConstants.WORK_STATUS_END); // 设置工单的生产状态为已经完成
        devWorkOrder.setOperationStatus(WorkConstants.OPERATION_STATUS_FINISH); // 设置工单的操作状态为结束
        devWorkOrder.setEndTime(new Date()); // 设置结束时间
        devWorkOrder.setUpdateBy(tokenUser.getUserName());
        devWorkOrder.setUpdateTime(new Date());
        return devWorkOrderMapper.updateDevWorkOrder(devWorkOrder); // 更新
    }

    /**
     * 页面点击提交工单，工单状态不可修改和删除
     *
     * @param id
     * @return
     */
    @Override
    public int submitWorkOrder(Integer id,HttpServletRequest request) {
        User tokenUser = JwtUtil.getTokenUser(request);
        Long userId = tokenUser.getUserId(); // 登录用户id
        DevWorkOrder devWorkOrder = devWorkOrderMapper.selectDevWorkOrderById(id);
        if (!devWorkOrder.getWorkorderStatus().equals(WorkConstants.WORK_STATUS_END)) {
            throw new BusinessException("未完成的工单不能提交");
        }
        ProductionLine productionLine = productionLineMapper.selectProductionLineById(devWorkOrder.getLineId());
        // 不是工单负责人
        if (productionLine.getDeviceLiable() != userId.intValue() && productionLine.getDeviceLiableTow() != userId.intValue()) {
            throw new BusinessException("不是工单负责人");
        }
        if (devWorkOrder.getWorkSign() == WorkConstants.WORK_SIGN_YES) {
            throw new BusinessException("该工单已经提交过，不能重复提交");
        }
        devWorkOrder.setWorkSign(WorkConstants.WORK_SIGN_YES); // 设置状态为已确认数据不可进行修改和删除
        devWorkOrder.setUpdateTime(new Date());
        devWorkOrder.setUpdateBy(tokenUser.getUserName());
        return devWorkOrderMapper.updateDevWorkOrder(devWorkOrder);
    }

    /**
     * 查询生产状态处于正在进行的所有工单
     *
     * @return
     */
    public List<DevWorkOrder> selectWorkOrderAllBeIn(Cookie[] cookies) {
        Integer companyId = JwtUtil.getTokenCookie(cookies).getCompanyId();
        return devWorkOrderMapper.selectWorkOrderAllBeIn(companyId);
    }

    /**
     * 查询当天所有工单
     *
     * @return
     */
    public List<DevWorkOrder> selectWorkOrderAllToday(Cookie[] cookies) {
        User user = JwtUtil.getTokenCookie(cookies);
        List<DevWorkOrder> workOrders = devWorkOrderMapper.selectWorkOrderAllToday(user.getCompanyId());
        for (DevWorkOrder workOrder : workOrders) {
            ProductionLine productionLine = productionLineMapper.selectProductionLineById(workOrder.getLineId());
            workOrder.setProductionLine(productionLine); // 工单产线信息
        }
        return workOrders;
    }

    /**
     * 通过产线Id查询该产线正在生产的工单
     *
     * @param lineId
     * @return
     */
    @Override
    public DevWorkOrder selectWorkOrderBeInByLineId(Integer lineId,HttpServletRequest request) {
        return devWorkOrderMapper.selectWorkByCompandAndLine(JwtUtil.getTokenUser(request).getCompanyId(), lineId);
    }

    /**
     * 根据工单编号查询对应的工单信息
     *
     * @param work_id 工单编号
     * @return
     */
    @Override
    public DevWorkOrder findWorkInfoById(int work_id) {
        //查询对应的工单是否存在
        DevWorkOrder order = devWorkOrderMapper.selectDevWorkOrderById(work_id);
        if (order == null || order.getLineId() == null) {
            return null;
        }
        //查询对应的产线信息
        ProductionLine line = productionLineMapper.selectProductionLineById(order.getLineId());
        if (line == null) return null;
        //判断产线是否是手动
        if (line.getManual() == 0) {
            order.setCumulativeNumber(0);//默认为0
            //为自动、查询对应的产线的警戒线标记IO口
            DevIo devIo = devIoMapper.selectLineIsSignDevIo(line.getId());
            if (devIo != null) {
                //查询对应的累计数据
                DevWorkData data = devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(order.getCompanyId(), line.getId(),
                        order.getId(), devIo.getDevId(), devIo.getId());
                if (data != null) order.setCumulativeNumber(data.getCumulativeNum());
            }
        }
        //达成率默认为0
        order.setReachRate(0.0F);
        if (!StringUtils.isEmpty(order.getStartTime()) && order.getCumulativeNumber() != null) {
            //计算分母
            float standardTotal = order.getProductStandardHour() * TimeUtil.getDateDel(order.getStartTime());
            order.setReachRate(standardTotal == 0 ? 0.0F : new BigDecimal(((float) order.getCumulativeNumber() / standardTotal)*100).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue());
        }
        return order;
    }

    /**
     * 查询昨天生产的工单
     *
     * @return
     */
    public List<DevWorkOrder> selectWorkOrderAllYesterday() {
        Integer companyId = ShiroUtils.getSysUser().getCompanyId();
        List<DevWorkOrder> workOrders = devWorkOrderMapper.selectWorkOrderAllYesterday(companyId);
        for (DevWorkOrder workOrder : workOrders) {
            ProductionLine productionLine = productionLineMapper.selectProductionLineById(workOrder.getLineId());
            workOrder.setProductionLine(productionLine); // 工单产线信息
        }
        return workOrders;
    }

    /**
     * 通过产线id查询已经提交的工单列表
     * @param lineId 产线
     * @return 结果
     */
    @Override
    public List<DevWorkOrder> selectWorkOrderFinishByLineId(int lineId,HttpServletRequest request) {
        List<DevWorkOrder> workOrders = devWorkOrderMapper.selectWorkOrderFinishByLineIdOrWorkOrderId(JwtUtil.getTokenUser(request).getCompanyId(), lineId, null);
        for (DevWorkOrder workOrder : workOrders) {
            DevProductList productList = productListMapper.selectDevProductByCode(JwtUtil.getTokenUser(request).getCompanyId(), workOrder.getProductCode());
            workOrder.setProductId(productList.getId());
            workOrder.setProductModel(productList.getProductModel());
        }
        return workOrders;
    }

    /**
     * 根据产线id或工单id查询工单信息
     * @param lineId
     * @param workOrderId
     * @return 结果
     */
    @Override
    public DevWorkOrder selectWorkOrderFinishByLineIdAndWorkOrderId(int lineId, int workOrderId,HttpServletRequest request) {
        return devWorkOrderMapper.selectWorkOrderFinishByLineIdOrWorkOrderId(JwtUtil.getTokenUser(request).getCompanyId(),lineId,workOrderId).get(0);
    }

    /**
     * 工单变更
     * @param order
     * @return
     */
    @Override
    public int changeOrder(DevWorkOrder order,HttpServletRequest request) {
        if(order == null)return 0;
        User user = JwtUtil.getTokenUser(request);
        if(user == null)return 0;
        DevWorkOrder devWorkOrder = devWorkOrderMapper.selectDevWorkOrderById(order.getId());
        if(devWorkOrder == null)return 0;
        //查询对应的产线信息
        ProductionLine line = productionLineMapper.selectProductionLineById(devWorkOrder.getLineId());
        if (line ==null)return 0;
        //保存变更记录
        WorkOrderChange change = new WorkOrderChange();
        change.setCompanyId(user.getCompanyId());
        change.setOrderId(devWorkOrder.getId());
        change.setOrderCode(devWorkOrder.getWorkorderNumber());
        change.setLineId(line.getId());
        change.setLineName(line.getLineName());
        User  u1 = userMapper.selectUserInfoById(line.getDeviceLiable());
        change.setDeviceLiable(u1 ==null?"":u1.getUserName());
        u1 = userMapper.selectUserInfoById(line.getDeviceLiableTow());
        change.setDeviceLiable2(u1==null?"":u1.getUserName());
        change.setProductNumber(devWorkOrder.getProductNumber());
        change.setProductionStart(devWorkOrder.getProductionStart());
        change.setProductionEnd(devWorkOrder.getProductionEnd());
        change.setCreatePeople(user.getUserName());
        change.setCreateTime(new Date());
        change.setRemark(order.getRemark());
        orderChangeMapper.insertWorkOrderChange(change);
        return devWorkOrderMapper.updateDevWorkOrder(order);
    }

    /**
     * 根据工单id查询对应的ECN信息
     * @param workId 工单id
     * @return
     */
    @Override
    public DevWorkOrder selectWorkOrderEcn(int workId) {
        DevWorkOrder order = devWorkOrderMapper.selectDevWorkOrderById(workId);
        if(order != null && !StringUtils.isEmpty(order.getOrderCode()) && !order.getOrderCode().equals("NaN")){
            //查询对应的工单备注信息
            OrderDetails details = orderDetailsMapper.findByOrderCodeAndProductCode(order.getCompanyId(),order.getOrderCode(),order.getProductCode());
            if(details != null){
                order.setOrderRemark(details.getRemark());
            }
            //查询对应的产品信息
            DevProductList productList = productListMapper.findByCompanyIdAndProductCode(order.getCompanyId(),order.getProductCode());
            if(productList != null){
                order.setProductEcn(productList.getEcnStatus());
                //查询对应的产品ecn信息
                if(productList.getEcnStatus() == 1){
                   order.setEcnLog(ecnLogMapper.findByCompanyIdAndProductId(order.getCompanyId(),productList.getId()));
                }
            }
        }
        return order;
    }
}
