package com.ruoyi.project.page.pageInfoConfig.service;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.PageTypeConstants;
import com.ruoyi.common.utils.TimeUtil;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import com.ruoyi.project.page.pageInfo.mapper.PageInfoMapper;
import com.ruoyi.project.page.pageInfo.service.IPageInfoService;
import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import com.ruoyi.project.page.pageInfoConfig.mapper.PageInfoConfigMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.devWorkDayHour.mapper.DevWorkDayHourMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;
import org.springframework.util.StringUtils;

/**
 * 页面配置 服务层实现
 * 
 * @author zqm
 * @date 2019-04-13
 */
@Service
public class PageInfoConfigServiceImpl implements IPageInfoConfigService
{
	@Autowired
	private PageInfoConfigMapper pageInfoConfigMapper;

	@Autowired
	private ProductionLineMapper productionLineMapper;

	@Autowired
	private PageInfoMapper pageInfoMapper;

	@Autowired
	private DevWorkOrderMapper devWorkOrderMapper;

	@Autowired
	private DevWorkDataMapper devWorkDataMapper;

	@Autowired
	private DevIoMapper devIoMapper;

	@Autowired
	private DevWorkDayHourMapper devWorkDayHourMapper;

	@Autowired
	private IPageInfoService pageInfoService;

	/**
     * 查询页面配置信息
     * 
     * @param id 页面配置ID
     * @return 页面配置信息
     */
    @Override
	public PageInfoConfig selectPageInfoConfigById(Integer id)
	{
	    return pageInfoConfigMapper.selectPageInfoConfigById(id);
	}
	
	/**
     * 查询页面配置列表
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 页面配置集合
     */
	@Override
	public List<PageInfoConfig> selectPageInfoConfigList(PageInfoConfig pageInfoConfig)
	{
	    return pageInfoConfigMapper.selectPageInfoConfigList(pageInfoConfig);
	}
	
    /**
     * 新增页面配置
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 结果
     */
	@Override
	public int insertPageInfoConfig(PageInfoConfig pageInfoConfig)
	{
	    return pageInfoConfigMapper.insertPageInfoConfig(pageInfoConfig);
	}
	
	/**
     * 修改页面配置
     * 
     * @param pageInfoConfig 页面配置信息
     * @return 结果
     */
	@Override
	public int updatePageInfoConfig(PageInfoConfig pageInfoConfig)
	{
	    return pageInfoConfigMapper.updatePageInfoConfig(pageInfoConfig);
	}

	/**
     * 删除页面配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePageInfoConfigByIds(String ids)
	{
		return pageInfoConfigMapper.deletePageInfoConfigByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据页面编号定时查询对应的宫格刷新数据
	 * @param code 页面编号
	 * @return
	 */
	@Override
	public List<PageInfoConfig> selectPageConfigByPageCode(String code) {
		//查询对应的页面是否存在
		PageInfo info = pageInfoMapper.selectPageInfoByCode(code);
		if(info == null) return null;
		//查询对应页面配置信息
		 List<PageInfoConfig> configs =  pageInfoConfigMapper.selectPageConfigByPageId(info.getId());
		for (PageInfoConfig config : configs) {
			config.setIdTotal(0);
			config.setIdTotal1(0);
			//查询对应公司对应产线正在进行的工单信息
			DevWorkOrder order = devWorkOrderMapper.selectWorkByCompandAndLine(info.getCompanyId(),config.getLineId());
			if(order != null){
				config.setDevWorkOrder(order);
				//判断是否需要进行警戒判断
				if(TimeUtil.getTimeDifference(new Date(),order.getStartTime()) > TimeUtil.DEL_TIME){
					//查询对应产线警戒线IO
					DevIo devIo = devIoMapper.selectLineIsSignDevIo(config.getLineId());
					if(devIo != null){
						//查询当前工单，当前IO口上个小时的数据
						int hour = TimeUtil.getHour();
						int hourNum = devWorkDayHourMapper.sumCompanyLineWorkDevIoHour(info.getCompanyId(),config.getLineId(),
								order.getId(),devIo.getDevId(),devIo.getId(),hour == 0?24:hour);
						if(hourNum < order.getProductStandardHour()){//小于标准小时产量
							config.setVigilance(true);
						}
					}
				}
				if(!StringUtils.isEmpty(order.getParamConfig())){
					JSONArray array = JSON.parseArray(order.getParamConfig());
					order.setpConfig(array);
				}
				if(!StringUtils.isEmpty(config.getDevIoConfig())) {
					JSONObject jsonObject = JSON.parseObject(config.getDevIoConfig());
					pageInfoService.selectExceOrder(jsonObject, info.getCompanyId(),order.getId(), config);
				}
				//查询对应产线
				ProductionLine line = productionLineMapper.selectProductionLineById(config.getLineId());
				if(line == null){continue;}
				config.setLine(line);
				//查询对应公司对应产线对应硬件对应IO口的数据
				DevWorkData data =  devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(info.getCompanyId(),config.getLineId(),
						order.getId(),config.getDevId()==null?0:config.getDevId(),config.getIoId()==null?0:config.getIoId());
				if(data != null) config.setIdTotal(data.getCumulativeNum());

				DevWorkData data1 =  devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(info.getCompanyId(),config.getLineId(),
						order.getId(),config.getDevId1()==null?0:config.getDevId1(),config.getIoId1()==null?0:config.getIoId1());
				if(data1 != null) config.setIdTotal1(data1.getCumulativeNum());
			}
		}
		return configs;
	}

	/**
	 * 根据页面编号定时查询生产平衡报表数据进行刷新
	 * @param code 页面编号
	 * @return
	 */
	@Override
	public PageInfoConfig selectBalanceConfigDataByPageCode(String code) {
		if(StringUtils.isEmpty(code))return  null;
		//查询对应的页面是否存在
		PageInfo info = pageInfoMapper.selectPageInfoByCode(code);
		if(info == null) return null;
		//查询对应页面配置信息
		List<PageInfoConfig> configs =  pageInfoConfigMapper.selectPageConfigByPageId(info.getId());
		if(configs != null && configs.size() >0){
			//查询对应公司对应产线正在进行的工单信息
			PageInfoConfig config = configs.get(0);
			DevWorkOrder order = devWorkOrderMapper.selectWorkByCompandAndLine(info.getCompanyId(),config.getLineId());
			if(order == null)return null;
			//查询对应的产线信息
			ProductionLine line = productionLineMapper.selectProductionLineById(order.getLineId());
			if(line == null)return null;
			config.setLine(line);
			config.setDevWorkOrder(order);
			if(StringUtils.isEmpty(config.getDevIoConfig())){ return null;}
			JSONObject object = JSON.parseObject(config.getDevIoConfig());
			pageInfoService.selectExceOrder(object,info.getCompanyId(),order.getId(),config);
			if(object.containsKey("io") && line.getManual() == 0){
				JSONArray ios = object.getJSONArray("io");
				config.setPh(pageInfoService.selectBalanceConfigData(ios,order,info.getCompanyId(),config.getLineId()));
			}else{
				List<Map<String,Object>> list = new ArrayList<>();
				Map<String,Object> map = new HashMap<>();
				map.put("id",0);
				map.put("name",line.getLineName()+"-累计产量");
				map.put("total",order.getCumulativeNumber());
				map.put("data",null);
				list.add(map);
				config.setPh(list);
			}
			return config;
		}
		return null;
	}
}
