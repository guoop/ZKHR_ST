package com.ruoyi.project.stock.receiveGoodsLevel.mapper;

import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import java.util.List;	

/**
 * 货品等级 数据层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface ReceiveGoodsLevelMapper 
{
	/**
     * 查询货品等级信息
     * 
     * @param id 货品等级ID
     * @return 货品等级信息
     */
	public ReceiveGoodsLevel selectReceiveGoodsLevelById(Long id);
	
	/**
     * 查询货品等级列表
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 货品等级集合
     */
	public List<ReceiveGoodsLevel> selectReceiveGoodsLevelList(ReceiveGoodsLevel receiveGoodsLevel);
	
	/**
     * 新增货品等级
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 结果
     */
	public int insertReceiveGoodsLevel(ReceiveGoodsLevel receiveGoodsLevel);
	
	/**
     * 修改货品等级
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 结果
     */
	public int updateReceiveGoodsLevel(ReceiveGoodsLevel receiveGoodsLevel);
	
	/**
     * 删除货品等级
     * 
     * @param id 货品等级ID
     * @return 结果
     */
	public int deleteReceiveGoodsLevelById(Long id);
	
	/**
     * 批量删除货品等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveGoodsLevelByIds(String[] ids);
	
}