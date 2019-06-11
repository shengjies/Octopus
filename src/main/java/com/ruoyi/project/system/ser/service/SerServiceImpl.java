package com.ruoyi.project.system.ser.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.project.system.serPort.mapper.SerPortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.ser.mapper.SerMapper;
import com.ruoyi.project.system.ser.domain.Ser;
import com.ruoyi.project.system.ser.service.ISerService;
import com.ruoyi.common.support.Convert;

/**
 * 服务器管理 服务层实现
 * 
 * @author ruoyi
 * @date 2019-06-03
 */
@Service
public class SerServiceImpl implements ISerService 
{
	@Autowired
	private SerMapper serMapper;

	@Autowired
	private SerPortMapper serPortMapper;

	/**
     * 查询服务器管理信息
     * 
     * @param id 服务器管理ID
     * @return 服务器管理信息
     */
    @Override
	public Ser selectSerById(Integer id)
	{
	    return serMapper.selectSerById(id);
	}
	
	/**
     * 查询服务器管理列表
     * 
     * @param ser 服务器管理信息
     * @return 服务器管理集合
     */
	@Override
	public List<Ser> selectSerList(Ser ser)
	{
	    return serMapper.selectSerList(ser);
	}
	
    /**
     * 新增服务器管理
     * 
     * @param ser 服务器管理信息
     * @return 结果
     */
	@Override
	public int insertSer(Ser ser)
	{
		ser.setSpwd(UUID.randomUUID().toString());
		ser.setCreateTime(new Date());
		ser.setSuserNum(0);
		ser.setSpath("http://"+ser.getSip());
	    return serMapper.insertSer(ser);
	}
	
	/**
     * 修改服务器管理
     * 
     * @param ser 服务器管理信息
     * @return 结果
     */
	@Override
	public int updateSer(Ser ser)
	{
		ser.setSpath("http://"+ser.getSip());  
	    return serMapper.updateSer(ser);
	}

	/**
     * 删除服务器管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSerByIds(String ids)
	{
		return serMapper.deleteSerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询对应的服务器端口配置数量是否大于等于服务器的最多用户数
	 * @param sid 服务器id
	 * @return
	 */
	@Override
	public boolean findMax(int sid) {
		Ser ser = serMapper.selectSerById(sid);
		if(ser != null){
			//查询对应的端口配置信息
			int count  =  serPortMapper.countSerPort(sid);
			return ser.getMaxNum() > count;
		}
		return false;
	}
}
