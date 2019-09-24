package com.ruoyi.project.duties.contract.mapper;

import com.ruoyi.project.duties.contract.domain.Contract;
import java.util.List;	

/**
 * 合同 数据层
 * 
 * @author admin
 * @date 2019-08-07
 */
public interface ContractMapper 
{
	/**
     * 查询合同信息
     * 
     * @param conId 合同ID
     * @return 合同信息
     */
	public Contract selectContractById(Long conId);
	
	/**
     * 查询合同列表
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
	
	/**
     * 删除合同
     * 
     * @param conId 合同ID
     * @return 结果
     */
	public int deleteContractById(Long conId);
	
	/**
     * 批量删除合同
     * 
     * @param conIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteContractByIds(String[] conIds);
	
}