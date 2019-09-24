package com.ruoyi.project.stock.sendTanluodu.mapper;

import com.ruoyi.project.stock.sendTanluodu.domain.SendTanluodu;
import java.util.List;	

/**
 * 坍落度 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendTanluoduMapper 
{
	/**
     * 查询坍落度信息
     * 
     * @param id 坍落度ID
     * @return 坍落度信息
     */
	public SendTanluodu selectSendTanluoduById(Long id);
	
	/**
     * 查询坍落度列表
     * 
     * @param sendTanluodu 坍落度信息
     * @return 坍落度集合
     */
	public List<SendTanluodu> selectSendTanluoduList(SendTanluodu sendTanluodu);
	
	/**
     * 新增坍落度
     * 
     * @param sendTanluodu 坍落度信息
     * @return 结果
     */
	public int insertSendTanluodu(SendTanluodu sendTanluodu);
	
	/**
     * 修改坍落度
     * 
     * @param sendTanluodu 坍落度信息
     * @return 结果
     */
	public int updateSendTanluodu(SendTanluodu sendTanluodu);
	
	/**
     * 删除坍落度
     * 
     * @param id 坍落度ID
     * @return 结果
     */
	public int deleteSendTanluoduById(Long id);
	
	/**
     * 批量删除坍落度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendTanluoduByIds(String[] ids);
	
}