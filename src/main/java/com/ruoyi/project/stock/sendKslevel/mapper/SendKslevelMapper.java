package com.ruoyi.project.stock.sendKslevel.mapper;

import com.ruoyi.project.stock.sendKslevel.domain.SendKslevel;
import java.util.List;	

/**
 * 抗渗等级 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendKslevelMapper 
{
	/**
     * 查询抗渗等级信息
     * 
     * @param id 抗渗等级ID
     * @return 抗渗等级信息
     */
	public SendKslevel selectSendKslevelById(Long id);
	
	/**
     * 查询抗渗等级列表
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 抗渗等级集合
     */
	public List<SendKslevel> selectSendKslevelList(SendKslevel sendKslevel);
	
	/**
     * 新增抗渗等级
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 结果
     */
	public int insertSendKslevel(SendKslevel sendKslevel);
	
	/**
     * 修改抗渗等级
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 结果
     */
	public int updateSendKslevel(SendKslevel sendKslevel);
	
	/**
     * 删除抗渗等级
     * 
     * @param id 抗渗等级ID
     * @return 结果
     */
	public int deleteSendKslevelById(Long id);
	
	/**
     * 批量删除抗渗等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendKslevelByIds(String[] ids);
	
}