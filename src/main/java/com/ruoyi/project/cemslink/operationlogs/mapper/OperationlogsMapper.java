package com.ruoyi.project.cemslink.operationlogs.mapper;

import com.ruoyi.project.cemslink.operationlogs.domain.Operationlogs;
import java.util.List;	

/**
 * operationlogs 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface OperationlogsMapper 
{
	/**
     * 查询operationlogs信息
     * 
     * @param sID operationlogsID
     * @return operationlogs信息
     */
	public Operationlogs selectOperationlogsById(Long sID);
	
	/**
     * 查询operationlogs列表
     * 
     * @param operationlogs operationlogs信息
     * @return operationlogs集合
     */
	public List<Operationlogs> selectOperationlogsList(Operationlogs operationlogs);
	
	/**
     * 新增operationlogs
     * 
     * @param operationlogs operationlogs信息
     * @return 结果
     */
	public int insertOperationlogs(Operationlogs operationlogs);
	
	/**
     * 修改operationlogs
     * 
     * @param operationlogs operationlogs信息
     * @return 结果
     */
	public int updateOperationlogs(Operationlogs operationlogs);
	
	/**
     * 删除operationlogs
     * 
     * @param sID operationlogsID
     * @return 结果
     */
	public int deleteOperationlogsById(Long sID);
	
	/**
     * 批量删除operationlogs
     * 
     * @param sIDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteOperationlogsByIds(String[] sIDs);
	
}