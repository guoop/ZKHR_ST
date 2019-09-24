package com.ruoyi.project.stock.sendWaterPart.mapper;

import com.ruoyi.project.stock.sendWaterPart.domain.SendWaterPart;
import java.util.List;	

/**
 * 工程部位 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendWaterPartMapper 
{
	/**
     * 查询工程部位信息
     * 
     * @param id 工程部位ID
     * @return 工程部位信息
     */
	public SendWaterPart selectSendWaterPartById(Long id);
	
	/**
     * 查询工程部位列表
     * 
     * @param sendWaterPart 工程部位信息
     * @return 工程部位集合
     */
	public List<SendWaterPart> selectSendWaterPartList(SendWaterPart sendWaterPart);
	
	/**
     * 新增工程部位
     * 
     * @param sendWaterPart 工程部位信息
     * @return 结果
     */
	public int insertSendWaterPart(SendWaterPart sendWaterPart);
	
	/**
     * 修改工程部位
     * 
     * @param sendWaterPart 工程部位信息
     * @return 结果
     */
	public int updateSendWaterPart(SendWaterPart sendWaterPart);
	
	/**
     * 删除工程部位
     * 
     * @param id 工程部位ID
     * @return 结果
     */
	public int deleteSendWaterPartById(Long id);
	
	/**
     * 批量删除工程部位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendWaterPartByIds(String[] ids);
	
}