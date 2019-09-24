package com.ruoyi.project.cemslink.instore.service;

import com.ruoyi.project.cemslink.instore.domain.Instore;
import java.util.List;

/**
 * 库存 服务层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface IInstoreService 
{
	/**
     * 查询库存信息
     * 
     * @param sID 库存ID
     * @return 库存信息
     */
	public Instore selectInstoreById(Long sID);
	
	/**
     * 查询库存列表
     * 
     * @param instore 库存信息
     * @return 库存集合
     */
	public List<Instore> selectInstoreList(Instore instore);
	
	/**
     * 新增库存
     * 
     * @param instore 库存信息
     * @return 结果
     */
	public int insertInstore(Instore instore);
	
	/**
     * 修改库存
     * 
     * @param instore 库存信息
     * @return 结果
     */
	public int updateInstore(Instore instore);
		
	/**
     * 删除库存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInstoreByIds(String ids);
	
}
