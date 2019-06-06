package com.ruoyi.project.device.devList.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.DevConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.DevId;
import com.ruoyi.project.device.devIo.domain.DevIo;
import com.ruoyi.project.device.devIo.mapper.DevIoMapper;
import com.ruoyi.project.device.devModel.domain.DevModel;
import com.ruoyi.project.device.devModel.mapper.DevModelMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.device.devList.mapper.DevListMapper;
import com.ruoyi.project.device.devList.domain.DevList;
import com.ruoyi.common.support.Convert;

/**
 * 硬件 服务层实现
 * 
 * @author ruoyi
 * @date 2019-04-08
 */
@Service("devList")
public class DevListServiceImpl implements IDevListService 
{
	@Autowired
	private DevListMapper devListMapper;

	@Autowired
	private DevModelMapper devModelMapper;

	@Autowired
	private DevIoMapper devIoMapper;

	/**
     * 查询硬件信息
     * 
     * @param id 硬件ID
     * @return 硬件信息
     */
    @Override
	public DevList selectDevListById(Integer id)
	{
	    return devListMapper.selectDevListById(id);
	}
	
	/**
     * 查询硬件列表
     * 
     * @param devList 硬件信息
     * @return 硬件集合
     */
	@Override
	public List<DevList> selectDevListList(DevList devList)
	{
		User user = ShiroUtils.getSysUser();
		if(user == null){
			return Collections.emptyList();
		}
		if(!User.isSys(user)){ devList.setCompanyId(user.getCompanyId()); }
		return devListMapper.selectDevListList(devList);
	}
	
    /**
     * 新增硬件
     * 
     * @param
     * @return 结果
     */
	@Override
	public int insertDevList(int devModelId)
	{

		DevModel devModel = devModelMapper.selectDevModelById(devModelId);
		if(devModel == null)return  0;
		DevList devList =null;
		DevIo devIo = null;
		int i =1;
		while (i<=30){
			String devId = DevId.getPageCode();
			if(StringUtils.isEmpty(devId)){
				continue;
			}
			devList = new DevList();
			devList.setDeviceId(devId);
			devList.setDeviceStatus(1);
			devList.setDefId(0);
			devList.setDeviceUploadTime(15);
			devList.setDevModelId(devModelId);
			devList.setCreateDate(new Date());
			DevList dev = devListMapper.selectDevListByDevId(devId);
			if(dev == null){
				devListMapper.insertDevList(devList);
				for(int j =1;j <=4;j++){
					devIo = new DevIo();
					devIo.setDevId(devList.getId());
					devIo.setIoOrder(j);
					devIo.setIsSign(0);
					devIo.setLineId(0);
					devIo.setRemark("上传数据");
					devIo.setCreateTime(new Date());
					devIoMapper.insertDevIo(devIo);
				}
				i++;
			}
		}
	    return 1;
	}
	
	/**
     * 修改硬件
     * 
     * @param devList 硬件信息
     * @return 结果
     */
	@Override
	public int updateDevList(DevList devList)
	{
	    return devListMapper.updateDevList(devList);
	}

	/**
	 * 用户添加硬件信息
	 * @param devList 硬件信息
	 * @return
	 */
	@Override
	public int addSave(DevList devList) {
		return devListMapper.addSave(devList);
	}

	/**
     * 删除硬件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDevListByIds(String ids)
	{
		return devListMapper.deleteDevListByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询对应的硬件信息和对应的IO数据
	 * @param id
	 * @return
	 */
	@Override
	public DevList selectDevListAndIoById(Integer id) {
		return devListMapper.selectDevListAndIoById(id);
	}

	/**
	 * 获取前20个未配置的硬件编号
	 * @return
	 */
	@Override
	public List<String> selectNoConfigDevice() {
		return devListMapper.selectNoConfigDevice();
	}

	@Override
	public List<DevList> selectAll() {
		User user = ShiroUtils.getSysUser();
		if(user == null){
			return Collections.emptyList();
		}
		return devListMapper.selectAll(user.getCompanyId());
	}

	/**
	 * 根据硬件编号验证对应的硬件是否存在
	 * @param code 硬件编号
	 * @return
	 */
	@Override
	public int deviceValidate(String code) {
		DevList devList = devListMapper.selectDevListByCode(code);
		if(devList == null){
			return DevConstants.DEV_NOT_EXSIT;
		}else if(devList.getCompanyId() != null && devList.getCompanyId() >0){
			return DevConstants.DEV_BEING_USE;
		}
		return DevConstants.DEV_VALIDATE_TRUE;
	}
}
