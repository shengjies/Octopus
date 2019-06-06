package com.ruoyi.project.erp.parts.service;

import java.text.CollationElementIterator;
import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.partsStock.domain.PartsStock;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.parts.mapper.PartsMapper;
import com.ruoyi.project.erp.parts.domain.Parts;
import com.ruoyi.project.erp.parts.service.IPartsService;
import com.ruoyi.common.support.Convert;

/**
 * 半成品 服务层实现
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Service(value = "parts")
public class PartsServiceImpl implements IPartsService 
{
	@Autowired
	private PartsMapper partsMapper;

	/**
     * 查询半成品信息
     * 
     * @param id 半成品ID
     * @return 半成品信息
     */
    @Override
	public Parts selectPartsById(Integer id)
	{
	    return partsMapper.selectPartsById(id);
	}
	
	/**
     * 查询半成品列表
     * 
     * @param parts 半成品信息
     * @return 半成品集合
     */
	@Override
	public List<Parts> selectPartsList(Parts parts)
	{
	    return partsMapper.selectPartsList(parts);
	}
	
    /**
     * 新增半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	@Override
	public int insertParts(Parts parts)
	{
	    return partsMapper.insertParts(parts);
	}
	
	/**
     * 修改半成品
     * 
     * @param parts 半成品信息
     * @return 结果
     */
	@Override
	public int updateParts(Parts parts)
	{
	    return partsMapper.updateParts(parts);
	}

	/**
     * 删除半成品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePartsByIds(String ids)
	{
		return partsMapper.deletePartsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询公司的半成品信息
	 * @return 半成品列表
	 */
	@Override
	public List<Parts> selectAllPartsByComId() {
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
		    return Collections.emptyList();
		}
		Parts parts = new Parts();
		parts.setCompanyId(user.getCompanyId());
		return partsMapper.selectPartsList(parts);
	}

	/**
	 * 查询公司的半成品名称信息，去重查询
	 * @return 半成品列表
	 */
	@Override
	public List<Parts> selectAllPartsNameByComId() {
		User user = ShiroUtils.getSysUser();
		if (user == null) {
		    return Collections.emptyList();
		}
		return partsMapper.selectAllPartsNameByComId(user.getCompanyId());
	}
}
