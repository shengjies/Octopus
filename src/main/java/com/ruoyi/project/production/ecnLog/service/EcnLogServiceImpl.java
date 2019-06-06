package com.ruoyi.project.production.ecnLog.service;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.production.ecnLog.mapper.EcnLogMapper;
import com.ruoyi.project.production.ecnLog.domain.EcnLog;
import com.ruoyi.project.production.ecnLog.service.IEcnLogService;
import com.ruoyi.common.support.Convert;

/**
 * ECN 变更记录 服务层实现
 * 
 * @author zqm
 * @date 2019-05-16
 */
@Service
public class EcnLogServiceImpl implements IEcnLogService 
{
	@Autowired
	private EcnLogMapper ecnLogMapper;

	/**
	 * 查询ECN 变更记录信息
	 *
	 * @param id ECN 变更记录ID
	 * @return ECN 变更记录信息
	 */
	@Override
	public EcnLog selectEcnLogById(Integer id)
	{
		return ecnLogMapper.selectEcnLogById(id);
	}

	/**
     * 查询ECN 变更记录列表
     * 
     * @param ecnLog ECN 变更记录信息
     * @return ECN 变更记录集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<EcnLog> selectEcnLogList(EcnLog ecnLog)
	{
		User u = ShiroUtils.getSysUser();
		if(u == null)return Collections.emptyList();
		ecnLog.setCompanyId(u.getCompanyId());
	    return ecnLogMapper.selectEcnLogList(ecnLog);
	}
	
    /**
     * 新增ECN 变更记录
     * 
     * @param ecnLog ECN 变更记录信息
     * @return 结果
     */
	@Override
	public int insertEcnLog(EcnLog ecnLog)
	{
	    return ecnLogMapper.insertEcnLog(ecnLog);
	}

	/**
	 * 修改ECN 变更记录
	 *
	 * @param ecnLog ECN 变更记录信息
	 * @return 结果
	 */
	@Override
	public int updateEcnLog(EcnLog ecnLog)
	{
		return ecnLogMapper.updateEcnLog(ecnLog);
	}

	/**
	 * 删除ECN 变更记录对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteEcnLogByIds(String ids)
	{
		return ecnLogMapper.deleteEcnLogByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除ECN变更记录
	 * @param id ECN变更记录id
	 * @return 结果
	 */
	@Override
	public int deleteEcnLogById(Integer id) {
		return ecnLogMapper.deleteEcnLogById(id);
	}
}
