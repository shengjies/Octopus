package com.ruoyi.project.page.pageInfo.service;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.PageTypeConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.DevId;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import com.ruoyi.project.page.pageInfoConfig.mapper.PageInfoConfigMapper;
import com.ruoyi.project.production.devWorkData.domain.DevWorkData;
import com.ruoyi.project.production.devWorkData.mapper.DevWorkDataMapper;
import com.ruoyi.project.production.devWorkDayHour.domain.DevWorkDayHour;
import com.ruoyi.project.production.devWorkDayHour.mapper.DevWorkDayHourMapper;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;
import com.ruoyi.project.production.devWorkOrder.mapper.DevWorkOrderMapper;
import com.ruoyi.project.production.productionLine.domain.ProductionLine;
import com.ruoyi.project.production.productionLine.mapper.ProductionLineMapper;
import com.ruoyi.project.production.workExceptionType.domain.WorkExceptionType;
import com.ruoyi.project.production.workExceptionType.mapper.WorkExceptionTypeMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.project.page.pageInfo.mapper.PageInfoMapper;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import com.ruoyi.common.support.Convert;
import org.springframework.util.StringUtils;

/**
 * 页面管理 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
@Service("page")
public class PageInfoServiceImpl implements IPageInfoService 
{
	@Autowired
	private PageInfoMapper pageInfoMapper;

	@Autowired
	private ProductionLineMapper productionLineMapper;

	@Autowired
	private PageInfoConfigMapper pageInfoConfigMapper;

	@Autowired
	private DevIoMapper devIoMapper;

	@Autowired
	private DevCompanyMapper devCompanyMapper;

	@Autowired
	private DevWorkOrderMapper devWorkOrderMapper;

	@Autowired
	private DevWorkDataMapper devWorkDataMapper;

	@Autowired
	private DevWorkDayHourMapper devWorkDayHourMapper;

	@Autowired
	private WorkExceptionTypeMapper workExceptionTypeMapper;

	@Value("${page.url}")
	private String pageUrl;

	/**
     * 查询页面管理信息
     * 
     * @param id 页面管理ID
     * @return 页面管理信息
     */
    @Override
	public PageInfo selectPageInfoById(Integer id)
	{
		PageInfo info =pageInfoMapper.selectPageInfoById(id);
		if(info != null && (info.getPageType() == PageTypeConstants.PAGE_TYPE_GG || info.getPageType()== PageTypeConstants.PAGE_TYPE_SCPH)){
			List<PageInfoConfig> configs = pageInfoConfigMapper.selectPageConfigByPageId(info.getId());
			for (PageInfoConfig config : configs) {
				if(!StringUtils.isEmpty(config.getDevIoConfig())){
					JSONObject jsonObject = JSON.parseObject(config.getDevIoConfig());
					config.setIoConfig(jsonObject);
				}
			}
			info.setConfigs(configs);
		}
	    return info;
	}

	/**
	 * 根据页面编号查询页面基本信息
	 * @param id 页面编号
	 * @return
	 */
	@Override
	public PageInfo selectPageInfoByPageId(Integer id) {
		return pageInfoMapper.selectPageInfoById(id);
	}

	/**
     * 查询页面管理列表
     * 
     * @param pageInfo 页面管理信息
     * @return 页面管理集合
     */
	@Override
	public List<PageInfo> selectPageInfoList(PageInfo pageInfo)
	{
	    User user = ShiroUtils.getSysUser();
	    if(user == null)return Collections.emptyList();
	    if(!User.isSys(user)){
	    	pageInfo.setCompanyId(user.getCompanyId());
		}
		List<PageInfo> infos = pageInfoMapper.selectPageInfoList(pageInfo);
		for (PageInfo info : infos) {
			info.setDevCompany(devCompanyMapper.selectDevCompanyById(info.getCompanyId()));
		}
		return infos;
	}
	
    /**
     * 新增页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	@Override
	public int insertPageInfo(PageInfo pageInfo)
	{
		//获取对应的公司
		User user = ShiroUtils.getSysUser();
		if(user == null)return  0;
		pageInfo.setCompanyId(user.getCompanyId());
		pageInfo.setCreateBy(user.getUserName());
		//获取对应的页面编号
		String pageCode = DevId.getPageCode();
		if(StringUtils.isEmpty(pageCode))return 0;
		//查询对应的页面编号是否存在
		PageInfo info = pageInfoMapper.selectPageInfoByCode(pageCode);
		if(info == null){
			pageInfo.setPageId(pageCode);
			pageInfo.setPageUrl(pageUrl+pageCode);
			pageInfo.setCreateTime(new Date());
			pageInfoMapper.insertPageInfo(pageInfo);
			savePageConfig(pageInfo);
			return 1;
		}
	    return 1;
	}
	
	/**
     * 修改页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	@Override
	public int updatePageInfo(PageInfo pageInfo)
	{
		//删除也能相关的配置信息
		String[] ids ={pageInfo.getId()+""};
		pageInfoConfigMapper.deletePageInfoConfigByPIds(ids);
		//更新页面信息
		pageInfoMapper.updatePageInfo(pageInfo);
		//保存相关配置信息
		savePageConfig(pageInfo);
	    return 1;
	}

	/**
	 * 添加页面配置信息
	 * @param pageInfo
	 */
	private void savePageConfig(PageInfo pageInfo){
		if(pageInfo.getPageType() == PageTypeConstants.PAGE_TYPE_GG && pageInfo.getConfigs() != null){ //宫格布局
			for (PageInfoConfig config : pageInfo.getConfigs()) {
				config.setPId(pageInfo.getId());
				//查询对应的硬件信息
				if(config.getIoId() != null){
					DevIo devIo = devIoMapper.selectDevListAndIOByIoId(config.getIoId());
					if(devIo != null){
						config.setIoName(devIo.getIoName());
						config.setDevId(devIo.getDevId());
						config.setDevName(devIo.getRemark());
					}
				}
				if(config.getIoId1() != null){
					DevIo devIo = devIoMapper.selectDevListAndIOByIoId(config.getIoId1());
					if(devIo != null){
						config.setIoName1(devIo.getIoName());
						config.setDevId1(devIo.getDevId());
						config.setDevName1(devIo.getRemark());
					}
				}
				config.setCreateTime(new Date());
				pageInfoConfigMapper.insertPageInfoConfig(config);
			}
		}else if(pageInfo.getConfigs() != null && (pageInfo.getPageType() == PageTypeConstants.PAGE_TYPE_LB ||
				pageInfo.getPageType() == PageTypeConstants.PAGE_TYPE_SCPH || pageInfo.getPageType() == PageTypeConstants.PAGE_TYPE_TZLB) ){//轮播布局 或者 平衡布局
			for (PageInfoConfig config : pageInfo.getConfigs()) {
				config.setPId(pageInfo.getId());
				config.setCreateTime(new Date());
				pageInfoConfigMapper.insertPageInfoConfig(config);
			}
		}
	}

	/**
     * 删除页面管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePageInfoByIds(String ids)
	{
		String[] idAttray = Convert.toStrArray(ids);
		pageInfoConfigMapper.deletePageInfoConfigByPIds(idAttray);
		return pageInfoMapper.deletePageInfoByIds(idAttray);
	}

	/**
	 * 查询对应公司页面初始数据
	 * @return
	 */
	@Override
	public Map<String, Object> selectPageInitInfo(int a) {
		Map<String,Object> map = new HashMap<>();
		User user = ShiroUtils.getSysUser();
		if(user == null )return null;
		//查询对应的公司下的所以的产线
		List<ProductionLine> lines = productionLineMapper.selectAllDef0(user.getCompanyId());
		map.put("line",lines);
		if(lines != null && lines.size() >=1 && a == 0){
			List<DevIo> ios = devIoMapper.selectLineDevIO(lines.get(0).getId());
			map.put("ios",ios);
		}else if(lines != null && lines.size() >=1 && a == 1){
			for (ProductionLine line : lines) {
				List<DevIo> ios = devIoMapper.selectLineDevIO(line.getId());
				map.put(line.getId()+"",ios);
			}
		}
		return map;
	}

	/**
	 * 查询对应公司所以非轮播页面
	 * @return
	 */
	@Override
	public List<PageInfo> selectAllPage(int a,int p_id) {
		User user = ShiroUtils.getSysUser();
		if(user == null)return null;
		List<PageInfo> infos = pageInfoMapper.selectAllPage(user.getCompanyId());
		if(a == 1){
			for (PageInfo info : infos) {
				//查询对应轮播的页面是否配置该页面
				PageInfoConfig config = pageInfoConfigMapper.selectPageConfigOtherPage(p_id,info.getId());
				if(config != null) info.setExist(true);
			}
		}
		return infos;
	}

	/**
	 * 根据页面编辑查询对应的页面信息
	 * @param code 页面编号
	 * @return
	 */
	@Override
	public PageInfo selectPageByCode(String code) {
		PageInfo info =  pageInfoMapper.selectPageInfoByCode(code);
		if(info != null){
			//查询对应公司
			info.setDevCompany(devCompanyMapper.selectDevCompanyById(info.getCompanyId()));
			//查询相关配置数据
			List<PageInfoConfig> configs = pageInfoConfigMapper.selectPageConfigByPageId(info.getId());
			//宫格布局 和 平衡布局
			if(info.getPageType() == PageTypeConstants.PAGE_TYPE_GG || info.getPageType() == PageTypeConstants.PAGE_TYPE_SCPH){
				for (PageInfoConfig config : configs) {
					//查询对应产线
					ProductionLine line = productionLineMapper.selectProductionLineById(config.getLineId());
					if(line == null){continue;}
					config.setLine(line);
					//查询对应的产线真正进行中的工单
					DevWorkOrder order = devWorkOrderMapper.selectWorkByCompandAndLine(info.getCompanyId(),config.getLineId());
					if(!StringUtils.isEmpty(config.getDevIoConfig())){
						JSONObject jsonObject = JSON.parseObject(config.getDevIoConfig());
						selectExceOrder(jsonObject,info.getCompanyId(),order == null?0:order.getId(),config);
						if(info.getPageType() == PageTypeConstants.PAGE_TYPE_SCPH){ //平衡布局
							if(jsonObject.containsKey("io")){
								JSONArray ios = jsonObject.getJSONArray("io");
								config.setPh(selectBalanceConfigData(ios,order,info.getCompanyId(),config.getLineId()));
							}
						}
					}
					if(order == null){ continue; }
					if(!StringUtils.isEmpty(order.getParamConfig())){
						JSONArray array = JSON.parseArray(order.getParamConfig());
						order.setpConfig(array);
					}
					config.setDevWorkOrder(order);

					if(info.getPageType() == PageTypeConstants.PAGE_TYPE_GG ){ //宫格布局
						//查询对应配置的累计产量
						DevWorkData data =  devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(info.getCompanyId(),config.getLineId(),
								order.getId(),config.getDevId()==null?0:config.getDevId(),config.getIoId()==null?0:config.getIoId());
						if(data != null) config.setIdTotal(data.getCumulativeNum());
						DevWorkData data1 =  devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(info.getCompanyId(),config.getLineId(),
								order.getId(),config.getDevId1()==null?0:config.getDevId1(),config.getIoId1()==null?0:config.getIoId1());
						if(data1 != null) config.setIdTotal1(data1.getCumulativeNum());
					}
				}
			}
			info.setConfigs(configs);
			if(info.getPageType() == PageTypeConstants.PAGE_TYPE_TZLB && configs != null && configs.size() >0 ){
				//查询产线信息
				ProductionLine line = productionLineMapper.selectProductionLineById(configs.get(0).getLineId());
				if(line != null){
					configs.get(0).setLine(line);
					//拿取对应的产线所以未完成和正在进行的工单信息
					List<DevWorkOrder> workOrders = devWorkOrderMapper.selectWorkDataByCompanyIdAndLineId(info.getCompanyId(),line.getId());
					info.setWorkOrder(workOrders);
				}
			}
		}
		return info;
	}

	/**
	 * 查询折线图的24小时数据
	 * @param order
	 * @param companyId
	 * @param lineId
	 * @return
	 */
	@Override
	public List<Map<String,Object>> selectBalanceConfigData(JSONArray ios,DevWorkOrder order,int companyId,int lineId){
		Integer lineDefaultData[] ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map =null;
		for (Object io : ios) {
			//查询对应相关名称数据
			map = new HashMap<>();
			try {
				Integer io_id = Integer.parseInt(io.toString());
				DevIo devIo = devIoMapper.selectDevListAndIOByIoId(io_id);
				if(devIo ==null)continue;
				map.put("id",devIo.getId());
				map.put("name",devIo.getRemark()+"-"+devIo.getIoName());
				map.put("total",0);
				map.put("data",lineDefaultData);
				if(order != null){
					//查询对应累计产量
					DevWorkData data =  devWorkDataMapper.selectWorkDataByCompanyLineWorkDev(companyId,lineId,
							order.getId(),devIo.getDevId(),devIo.getId());
					if(data != null) map.put("total",data.getCumulativeNum());
					//查询24小时数据
					DevWorkDayHour hour = devWorkDayHourMapper.selectInfoByCompanyLineWorkDevIo(companyId,lineId,order.getId(),devIo.getDevId(),devIo.getId());
					if(hour != null){
						Integer lineData[] ={
								isNull(hour.getHour1()),
								isNull(hour.getHour2()),
								isNull(hour.getHour3()),
								isNull(hour.getHour4()),
								isNull(hour.getHour5()),
								isNull(hour.getHour6()),
								isNull(hour.getHour7()),
								isNull(hour.getHour8()),
								isNull(hour.getHour9()),
								isNull(hour.getHour10()),
								isNull(hour.getHour11()),
								isNull(hour.getHour12()),
								isNull(hour.getHour13()),
								isNull(hour.getHour14()),
								isNull(hour.getHour15()),
								isNull(hour.getHour16()),
								isNull(hour.getHour17()),
								isNull(hour.getHour18()),
								isNull(hour.getHour19()),
								isNull(hour.getHour20()),
								isNull(hour.getHour21()),
								isNull(hour.getHour22()),
								isNull(hour.getHour23()),
								isNull(hour.getHour24()),
						};
						map.put("data",lineData);
					}
				}
				list.add(map);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return  list;
	}

	/**
	 *
	 * @param object 是否包含对应的key
	 * @param company_id 公司编号
	 * @param order_id 工单编号
	 * @param config 对应配置
	 */
	@Override
	public void selectExceOrder(JSONObject object, int company_id, int order_id, PageInfoConfig config) {
		if(object.containsKey("ycqk")){
			//查询对应的异常情况
			config.setWorkExceptionTypes(workExceptionTypeMapper.selectCompanyWork(company_id,order_id));
		}
		if(object.containsKey("dtgdjh")){
			//查询对应的查询当天的排版计划
			config.setWorkOrderList(devWorkOrderMapper.selectDayWorkOrder(company_id,config.getLineId()));
		}
		config.setIoConfig(object);
	}

	/**
	 * 判断24小时的数据是否为空
	 * @param hour
	 * @return
	 */
	private int isNull(Integer hour){
		return hour == null?0:hour;
	}

	/**
	 * 重置页面密码
	 * @param info 页面信息
	 * @return
	 */
	@Override
	public int savePwd(PageInfo info) {
		return pageInfoMapper.updatePageInfo(info);
	}
}
