package com.ruoyi.project.page.pageInfo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.page.pageInfo.domain.PageInfo;
import com.ruoyi.project.page.pageInfoConfig.domain.PageInfoConfig;
import com.ruoyi.project.production.devWorkOrder.domain.DevWorkOrder;

import java.util.List;
import java.util.Map;

/**
 * 页面管理 服务层
 * 
 * @author ruoyi
 * @date 2019-04-10
 */
public interface IPageInfoService 
{
	/**
     * 查询页面管理信息
     * 
     * @param id 页面管理ID
     * @return 页面管理信息
     */
	public PageInfo selectPageInfoById(Integer id);

	/**
	 * 根据页面编号查询页面基本信息
	 * @param id 页面编号
	 * @return
	 */
	PageInfo selectPageInfoByPageId(Integer id);
	
	/**
     * 查询页面管理列表
     * 
     * @param pageInfo 页面管理信息
     * @return 页面管理集合
     */
	public List<PageInfo> selectPageInfoList(PageInfo pageInfo);
	
	/**
     * 新增页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	public int insertPageInfo(PageInfo pageInfo);
	
	/**
     * 修改页面管理
     * 
     * @param pageInfo 页面管理信息
     * @return 结果
     */
	public int updatePageInfo(PageInfo pageInfo);
		
	/**
     * 删除页面管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePageInfoByIds(String ids);

	/**
	 * 查询对应公司页面初始数据
	 * @return
	 */
	Map<String,Object> selectPageInitInfo(int a);

	/**
	 * 查询对应公司所以非轮播页面
	 * @return
	 */
	List<PageInfo> selectAllPage(int a,int p_id);

	/**
	 * 根据页面编辑查询对应的页面信息
	 * @param code 页面编号
	 * @return
	 */
	PageInfo selectPageByCode(String code);

	List<Map<String,Object>> selectBalanceConfigData(JSONArray ios, DevWorkOrder order, int companyId, int lineId);

	/**
	 * 查询对应异常和当天排版
	 * @param object 是否包含对应的key
	 * @param company_id 公司编号
	 * @param order_id 工单编号
	 */
	void selectExceOrder(JSONObject object, int company_id, int order_id, PageInfoConfig config);
	/**
	 * 重置页面密码
	 * @param info 页面信息
	 * @return
	 */
	int savePwd(PageInfo info);
}
