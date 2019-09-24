package com.ruoyi.project.cemslink.hopper.mapper;

import com.ruoyi.project.cemslink.hopper.domain.Hopper;
import java.util.List;	

/**
 * V形送料斗 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface HopperMapper 
{
	/**
     * 查询V形送料斗信息
     * 
     * @param iD V形送料斗ID
     * @return V形送料斗信息
     */
	public Hopper selectHopperById(Long iD);
	
	/**
     * 查询V形送料斗列表
     * 
     * @param hopper V形送料斗信息
     * @return V形送料斗集合
     */
	public List<Hopper> selectHopperList(Hopper hopper);
	
	/**
     * 新增V形送料斗
     * 
     * @param hopper V形送料斗信息
     * @return 结果
     */
	public int insertHopper(Hopper hopper);
	
	/**
     * 修改V形送料斗
     * 
     * @param hopper V形送料斗信息
     * @return 结果
     */
	public int updateHopper(Hopper hopper);
	
	/**
     * 删除V形送料斗
     * 
     * @param iD V形送料斗ID
     * @return 结果
     */
	public int deleteHopperById(Long iD);
	
	/**
     * 批量删除V形送料斗
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteHopperByIds(String[] iDs);
	
}