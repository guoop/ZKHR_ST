package com.ruoyi.project.stock.sendSandLevel.mapper;

import com.ruoyi.project.stock.sendSandLevel.domain.SendSandLevel;
import java.util.List;	

/**
 * 沙子等级 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendSandLevelMapper 
{
	/**
     * 查询沙子等级信息
     * 
     * @param id 沙子等级ID
     * @return 沙子等级信息
     */
	public SendSandLevel selectSendSandLevelById(Long id);
	
	/**
     * 查询沙子等级列表
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 沙子等级集合
     */
	public List<SendSandLevel> selectSendSandLevelList(SendSandLevel sendSandLevel);
	
	/**
     * 新增沙子等级
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 结果
     */
	public int insertSendSandLevel(SendSandLevel sendSandLevel);
	
	/**
     * 修改沙子等级
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 结果
     */
	public int updateSendSandLevel(SendSandLevel sendSandLevel);
	
	/**
     * 删除沙子等级
     * 
     * @param id 沙子等级ID
     * @return 结果
     */
	public int deleteSendSandLevelById(Long id);
	
	/**
     * 批量删除沙子等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendSandLevelByIds(String[] ids);
	
}