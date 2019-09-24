package com.ruoyi.project.stock.sendStoneLevel.mapper;

import com.ruoyi.project.stock.sendStoneLevel.domain.SendStoneLevel;
import java.util.List;	

/**
 * 石子等级 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendStoneLevelMapper 
{
	/**
     * 查询石子等级信息
     * 
     * @param id 石子等级ID
     * @return 石子等级信息
     */
	public SendStoneLevel selectSendStoneLevelById(Long id);
	
	/**
     * 查询石子等级列表
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 石子等级集合
     */
	public List<SendStoneLevel> selectSendStoneLevelList(SendStoneLevel sendStoneLevel);
	
	/**
     * 新增石子等级
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 结果
     */
	public int insertSendStoneLevel(SendStoneLevel sendStoneLevel);
	
	/**
     * 修改石子等级
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 结果
     */
	public int updateSendStoneLevel(SendStoneLevel sendStoneLevel);
	
	/**
     * 删除石子等级
     * 
     * @param id 石子等级ID
     * @return 结果
     */
	public int deleteSendStoneLevelById(Long id);
	
	/**
     * 批量删除石子等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendStoneLevelByIds(String[] ids);
	
}