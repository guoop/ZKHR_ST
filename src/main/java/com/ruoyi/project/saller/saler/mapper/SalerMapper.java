package com.ruoyi.project.saller.saler.mapper;

import com.ruoyi.project.saller.saler.domain.Saler;
import java.util.List;	

/**
 * 业务员 数据层
 * 
 * @author admin
 * @date 2019-05-28
 */
public interface SalerMapper 
{
	/**
     * 查询业务员信息
     * 
     * @param id 业务员ID
     * @return 业务员信息
     */
	public Saler selectSalerById(Long id);
	
	/**
     * 查询业务员列表
     * 
     * @param saler 业务员信息
     * @return 业务员集合
     */
	public List<Saler> selectSalerList(Saler saler);
	
	/**
     * 新增业务员
     * 
     * @param saler 业务员信息
     * @return 结果
     */
	public int insertSaler(Saler saler);
	
	/**
     * 修改业务员
     * 
     * @param saler 业务员信息
     * @return 结果
     */
	public int updateSaler(Saler saler);
	
	/**
     * 删除业务员
     * 
     * @param id 业务员ID
     * @return 结果
     */
	public int deleteSalerById(Long id);
	
	/**
     * 批量删除业务员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSalerByIds(String[] ids);
	
}