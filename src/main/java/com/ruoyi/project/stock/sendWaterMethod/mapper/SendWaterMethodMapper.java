package com.ruoyi.project.stock.sendWaterMethod.mapper;

import com.ruoyi.project.stock.sendWaterMethod.domain.SendWaterMethod;
import java.util.List;	

/**
 * 浇筑方式 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendWaterMethodMapper 
{
	/**
     * 查询浇筑方式信息
     * 
     * @param id 浇筑方式ID
     * @return 浇筑方式信息
     */
	public SendWaterMethod selectSendWaterMethodById(Long id);
	
	/**
     * 查询浇筑方式列表
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 浇筑方式集合
     */
	public List<SendWaterMethod> selectSendWaterMethodList(SendWaterMethod sendWaterMethod);
	
	/**
     * 新增浇筑方式
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 结果
     */
	public int insertSendWaterMethod(SendWaterMethod sendWaterMethod);
	
	/**
     * 修改浇筑方式
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 结果
     */
	public int updateSendWaterMethod(SendWaterMethod sendWaterMethod);
	
	/**
     * 删除浇筑方式
     * 
     * @param id 浇筑方式ID
     * @return 结果
     */
	public int deleteSendWaterMethodById(Long id);
	
	/**
     * 批量删除浇筑方式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendWaterMethodByIds(String[] ids);
	
}