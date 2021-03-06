package com.ruoyi.project.stock.receivePlace.mapper;

import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import java.util.List;	

/**
 * 产地 数据层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface ReceivePlaceMapper 
{
	/**
     * 查询产地信息
     * 
     * @param id 产地ID
     * @return 产地信息
     */
	public ReceivePlace selectReceivePlaceById(Long id);
	
	/**
     * 查询产地列表
     * 
     * @param receivePlace 产地信息
     * @return 产地集合
     */
	public List<ReceivePlace> selectReceivePlaceList(ReceivePlace receivePlace);
	
	/**
     * 新增产地
     * 
     * @param receivePlace 产地信息
     * @return 结果
     */
	public int insertReceivePlace(ReceivePlace receivePlace);
	
	/**
     * 修改产地
     * 
     * @param receivePlace 产地信息
     * @return 结果
     */
	public int updateReceivePlace(ReceivePlace receivePlace);
	
	/**
     * 删除产地
     * 
     * @param id 产地ID
     * @return 结果
     */
	public int deleteReceivePlaceById(Long id);
	
	/**
     * 批量删除产地
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceivePlaceByIds(String[] ids);
	
}