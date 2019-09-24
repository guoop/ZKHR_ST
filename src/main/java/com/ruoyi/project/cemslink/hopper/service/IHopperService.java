package com.ruoyi.project.cemslink.hopper.service;

import com.ruoyi.project.cemslink.hopper.domain.Hopper;
import java.util.List;

/**
 * V形送料斗 服务层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface IHopperService 
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
     * 删除V形送料斗信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHopperByIds(String ids);
	
}
