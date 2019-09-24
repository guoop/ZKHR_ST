package com.ruoyi.project.cars.comstrength.mapper;

import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import java.util.List;	

/**
 * 强度等级 数据层
 * 
 * @author admin
 * @date 2019-08-01
 */
public interface ComstrengthMapper 
{
	/**
     * 查询强度等级信息
     * 
     * @param id 强度等级ID
     * @return 强度等级信息
     */
	public Comstrength selectComstrengthById(Long id);
	
	/**
     * 查询强度等级列表
     * 
     * @param comstrength 强度等级信息
     * @return 强度等级集合
     */
	public List<Comstrength> selectComstrengthList(Comstrength comstrength);
	
	/**
     * 新增强度等级
     * 
     * @param comstrength 强度等级信息
     * @return 结果
     */
	public int insertComstrength(Comstrength comstrength);
	
	/**
     * 修改强度等级
     * 
     * @param comstrength 强度等级信息
     * @return 结果
     */
	public int updateComstrength(Comstrength comstrength);
	
	/**
     * 删除强度等级
     * 
     * @param id 强度等级ID
     * @return 结果
     */
	public int deleteComstrengthById(Long id);
	
	/**
     * 批量删除强度等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteComstrengthByIds(String[] ids);
	
}