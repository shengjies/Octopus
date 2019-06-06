package com.ruoyi.project.production.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.poi.PdfUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import com.ruoyi.project.production.workExceptionList.domain.WorkExceptionList;
import com.ruoyi.project.production.workExceptionList.mapper.WorkExceptionListMapper;
import com.ruoyi.project.system.user.domain.User;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ProductionLineMapper productionLineMapper;

    @Autowired
    private DevCompanyMapper devCompanyMapper;

    @Autowired
    private DevWorkOrderMapper devWorkOrderMapper;

    @Autowired
    private WorkExceptionListMapper workExceptionListMapper;

    @Override
    public int exportReport(int lineId, String productCode,String startTime, String endTime,
                            HttpServletResponse response, HttpServletRequest request) {
        User user = ShiroUtils.getSysUser();
        String path = "test_A4.jrxml";
        String pdfName = "全局";
        Map<String,Object> param = new HashMap<>();
        param.put("company_name","xxx");
        param.put("line","xxx");
        param.put("startTime",startTime);
        param.put("endTime", startTime);
        int totalActualNum =0;//总入库数量
        int totalScrapNum =0;//总报废数量
        float totalStandardHour =0F;//标准总工时
        float totalHour =0F;//实际总工时
        float totalReachRate = 0F;//总平均达成率
        float reachRateNum = 0F;//总达成率
        float totalZcHour = 0F;//正常总工时
        float totalWork1 = 0F;//1.5
        float totalWork2 = 0F;//2
        float totalWork3 = 0F;//3
        int totalCumulativeNumber = 0;//总累计产量
        StringBuffer sb = new StringBuffer();
        if(user != null){
            //查询对应的公司名称
            DevCompany company = devCompanyMapper.selectDevCompanyById(user.getCompanyId());
            if(company != null){
                param.put("company_name",company.getComName());
            }
            //查询对应产线
                if(lineId > 0){
                    ProductionLine line = productionLineMapper.selectProductionLineById(lineId);
                    pdfName = line.getLineName();
                }
                if(!StringUtils.isEmpty(productCode)){
                    pdfName = "产品："+productCode;
                }
                if(lineId >0 && !StringUtils.isEmpty(productCode)){
                    ProductionLine line = productionLineMapper.selectProductionLineById(lineId);
                    pdfName = line.getLineName()+",产品："+productCode;
                }
                param.put("line",pdfName);
                //查询产线在该时间段内已经提交的所以工单数据
               List<DevWorkOrder> orders = devWorkOrderMapper.selectOrderByLineIsSubmit(user.getCompanyId(),productCode,
                       lineId,startTime+" 00:00:00",endTime+" 23:59:59");
                for (DevWorkOrder order : orders) {
                    float wh = order.getWorkingHour() == null?0:order.getWorkingHour();
                    float mh = order.getManualTime()==null?0:order.getManualTime();
                    order.setWorkingHour( wh+mh );
                    sb.append(order.getId());
                    sb.append(",");
                    totalActualNum += order.getActualWarehouseNum() ==null?0:order.getActualWarehouseNum();
                    totalScrapNum += order.getScrapNum() == null?0:order.getScrapNum();
                    totalZcHour += order.getWorkingHour() ==null?0F:order.getWorkingHour();
                    totalHour += order.getWorkingHour() ==null?0F:order.getWorkingHour();
                    totalHour += order.getOvertimeHour() ==null?0F:order.getOvertimeHour();
                    totalCumulativeNumber += order.getCumulativeNumber() ==null?0:order.getCumulativeNumber();
                    if(order.getOvertimeRace() != null && order.getOvertimeRace() >0){
                        if(order.getOvertimeRace() == 1.5){
                            order.setWork1(order.getOvertimeRace()+"");
                            totalWork1 += order.getOvertimeRace();
                        }else if(order.getOvertimeRace() == 2){
                            order.setWork2(order.getOvertimeRace()+"");
                            totalWork2 += order.getOvertimeRace();
                        }else if(order.getOvertimeRace() == 3){
                            order.setWork3(order.getOvertimeHour()+"");
                            totalWork3 += order.getOvertimeHour();
                        }
                    }
                    //直通率 = 入库数量 / 累计产量
                    order.setDirectPassRate(0F);
                    if(order.getCumulativeNumber() != null && order.getActualWarehouseNum() != null){
                        order.setDirectPassRate(order.getCumulativeNumber() ==0?0:getFloat3(((float)order.getActualWarehouseNum()/order.getCumulativeNumber()))*100);
                    }
                    //达成率
                    order.setReachRate(0F);
                    if(order.getCumulativeNumber() != null && order.getProductStandardHour() != null){
                        //计算分母
                        float w = order.getWorkingHour()==null?0:order.getWorkingHour();
                        float o =  order.getOvertimeHour()==null?0:order.getOvertimeHour();
                      float total =  order.getProductStandardHour()*(w +o);
                        order.setReachRate(total==0?0:getFloat3(((float)order.getCumulativeNumber()/total))*100);
                    }
                    reachRateNum+=order.getReachRate();
                    //标准工时
                    float standardHour = 0F;
                    if(order.getProductStandardHour() != null && order.getProductStandardHour() >0){
                        standardHour = (float)order.getProductNumber()/order.getProductStandardHour();
                    }
                    totalStandardHour +=standardHour;
                    if(!StringUtils.isEmpty(order.getParamConfig())){
                        JSONArray array = JSON.parseArray(order.getParamConfig());
                        for (int i =0;i<array.size();i++){
                            JSONObject o = array.getJSONObject(i);
                            if(i == 0){
                                order.setParam1(o.get("k").toString()+o.get("v"));
                            }
                            if(i == 1){
                                order.setParam2(o.get("k").toString()+o.get("v"));
                            }
                            if(i == 2){
                                order.setParam3(o.get("k").toString()+o.get("v"));
                            }
                            if(i == 3){
                                order.setParam4(o.get("k").toString()+o.get("v"));
                            }
                            if(i == 4){
                                order.setParam5(o.get("k").toString()+o.get("v"));
                            }
                        }
                    }
                }
                if(orders.size() >0){
                    totalReachRate = getFloat3(reachRateNum/orders.size());
                }
                JRDataSource dataSource = new JRBeanCollectionDataSource(orders);
                param.put("orderDataTable",dataSource);

        }
        //查询所以的异常事件
        if(sb.toString().length()>0){
            List<WorkExceptionList> exceptionLists = workExceptionListMapper.selectWorkExceByWorkId(sb.toString().substring(0,sb.length()-1));
            JRDataSource dataSource = new JRBeanCollectionDataSource(exceptionLists);
            param.put("exceptionData",dataSource);
        }
        param.put("totalActualNum",totalActualNum);
        param.put("totalScrapNum",totalScrapNum);
        param.put("totalStandardHour",new BigDecimal(totalStandardHour).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue());
        param.put("totalHour",totalHour);
        param.put("totalReachRate",totalReachRate);
        param.put("totalZcHour",totalZcHour);
        param.put("totalWork1",totalWork1);
        param.put("totalWork2",totalWork2);
        param.put("totalWork3",totalWork3);
        //直通率
        param.put("totalDirectPassRate",totalCumulativeNumber == 0?0:getFloat3(((float)totalActualNum/totalCumulativeNumber))*100);
        PdfUtil util = new PdfUtil();
        util.exportPdf(path,pdfName,resourceLoader,response,request,param);
        return 1;
    }

    private float getFloat3(float val){
        return  new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
