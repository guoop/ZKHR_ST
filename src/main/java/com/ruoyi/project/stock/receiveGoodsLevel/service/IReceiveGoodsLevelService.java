package com.ruoyi.project.stock.receiveGoodsLevel.service;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import java.util.List;

/**
 * 货品等级 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceiveGoodsLevelService 
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
     * 删除货品等级信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveGoodsLevelByIds(String ids);

	ReceiveGoodsLevel selectOne(ReceiveGoodsLevel receiveGoodsLevel);
}
