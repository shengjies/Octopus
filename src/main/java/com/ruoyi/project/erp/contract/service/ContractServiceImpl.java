package com.ruoyi.project.erp.contract.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.contractContent.domain.ContractContent;
import com.ruoyi.project.erp.contractContent.mapper.ContractContentMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.contract.mapper.ContractMapper;
import com.ruoyi.project.erp.contract.domain.Contract;

/**
 * 合同 服务层实现
 * 
 * @author zqm
 * @date 2019-05-10
 */
@Service
public class ContractServiceImpl implements IContractService 
{
	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ContractContentMapper contentMapper;

	/**
     * 查询合同信息
     * 
     * @param id 合同ID
     * @return 合同信息
     */
    @Override
	public Contract selectContractById(Integer id)
	{
	    return contractMapper.selectContractById(id);
	}

	/**
	 * 根据公司查询对应的合同
	 * @return
	 */
	@Override
	@DataSource(DataSourceType.ERP)
	public Contract selectContractByCompanyId() {
		User user = ShiroUtils.getSysUser();
		if(user ==null) return  null;
		Contract contract = contractMapper.selectContractByCompanyId(user.getCompanyId());
		if(contract != null){
			ContractContent contractContent = new ContractContent();
			contractContent.setCId(contract.getId());
			contract.setContractContents(contentMapper.selectContractContentList(contractContent));
		}
		return contract;
	}

	/**
     * 查询合同列表
     * 
     * @param contract 合同信息
     * @return 合同集合
     */
	@Override
	public List<Contract> selectContractList(Contract contract)
	{
	    return contractMapper.selectContractList(contract);
	}

	/**
	 * 新增合同
	 * @param contract 合同信息
	 * @return
	 * @throws Exception
	 */
	@Override
	@DataSource(DataSourceType.ERP)
	public Contract insertContract(Contract contract) throws Exception {
		User user = ShiroUtils.getSysUser();
		if(user ==null)
			throw  new Exception("操作异常");
		contract.setCompanyId(user.getCompanyId());
		contract.setCreateTime(new Date());
		contractMapper.insertContract(contract);
		return contract;
	}

	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public Contract updateContract(Contract contract)
	{
		contractMapper.updateContract(contract);
		return contract;
	}

	
}
