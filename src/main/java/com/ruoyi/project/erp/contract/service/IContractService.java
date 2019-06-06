package com.ruoyi.project.erp.contract.service;

import com.ruoyi.project.erp.contract.domain.Contract;
import java.util.List;

/**
 * 合同 服务层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface IContractService 
{
	/**
     * 查询合同信息
     * 
     * @param id 合同ID
     * @return 合同信息
     */
	public Contract selectContractById(Integer id);

	/**
	 * 根据公司查询对应的合同
	 * @return
	 */
	Contract selectContractByCompanyId();
	
	/**
     * 查询合同列表
     * 
     * @param contract 合同信息
     * @return 合同集合
     */
	public List<Contract> selectContractList(Contract contract);
	
	/**
     * 新增合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	public Contract insertContract(Contract contract) throws Exception;
	
	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	public Contract updateContract(Contract contract);

	
}
