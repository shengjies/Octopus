package com.ruoyi.project.erp.contract.mapper;

import com.ruoyi.project.erp.contract.domain.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合同 数据层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface ContractMapper 
{
	/**
     * 查询合同信息
     * 
     * @param id 合同ID
     * @return 合同信息
     */
	public Contract selectContractById(Integer id);

	/**
	 * 根据公司查询对应合同
	 * @param cid 公司id
	 * @return
	 */
	Contract selectContractByCompanyId(@Param("cid")int cid);
	
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
	public int insertContract(Contract contract);
	
	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	public int updateContract(Contract contract);

	
}