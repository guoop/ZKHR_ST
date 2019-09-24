package com.ruoyi.project.duties.curstomer.service;

import com.ruoyi.project.duties.curstomer.domain.Curstomer;
import java.util.List;

/**
 * 客户 服务层
 * 
 * @author admin
 * @date 2019-08-07
 */
public interface ICurstomerService 
{
	/**
     * 查询客户信息
     * 
     * @param cusId 客户ID
     * @return 客户信息
     */
	public Curstomer selectCurstomerById(Long cusId);
	
	/**
     * 查询客户列表
     * 
     * @param curstomer 客户信息
     * @return 客户集合
     */
	public List<Curstomer> selectCurstomerList(Curstomer curstomer);
	
	/**
     * 新增客户
     * 
     * @param curstomer 客户信息
     * @return 结果
     */
	public int insertCurstomer(Curstomer curstomer);
	
	/**
     * 修改客户
     * 
     * @param curstomer 客户信息
     * @return 结果
     */
	public int updateCurstomer(Curstomer curstomer);
		
	/**
     * 删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCurstomerByIds(String ids);
	
}
