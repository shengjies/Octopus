package com.ruoyi.project.erp.contractContent.service;

import com.ruoyi.project.erp.contractContent.domain.ContractContent;
import java.util.List;

/**
 * 合同内容 服务层
 * 
 * @author zqm
 * @date 2019-05-10
 */
public interface IContractContentService 
{
	/**
     * 查询合同内容信息
     * 
     * @param id 合同内容ID
     * @return 合同内容信息
     */
	public ContractContent selectContractContentById(Integer id);
	
	/**
     * 查询合同内容列表
     * 
     * @param contractContent 合同内容信息
     * @return 合同内容集合
     */
	public List<ContractContent> selectContractContentList(ContractContent contractContent);
	
	/**
     * 新增合同内容
     * 
     * @param contractContent 合同内容信息
     * @return 结果
     */
	public int insertContractContent(ContractContent contractContent);
	
	/**
     * 修改合同内容
     * 
     * @param contractContent 合同内容信息
     * @return 结果
     */
	public int updateContractContent(ContractContent contractContent);
		
	/**
     * 删除合同内容信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteContractContentByIds(String ids);
	
}
