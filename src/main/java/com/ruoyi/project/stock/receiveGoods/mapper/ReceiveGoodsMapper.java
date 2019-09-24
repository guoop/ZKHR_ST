package com.ruoyi.project.stock.receiveGoods.mapper;

import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveStock.domain.GoodsStoreReport;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;

import java.util.List;

/**
 * 货品 数据层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface ReceiveGoodsMapper 
{
	/**
     * 查询货品信息
     * 
     * @param goodsid 货品ID
     * @return 货品信息
     */
	public ReceiveGoods selectReceiveGoodsById(Long goodsid);
	
	/**
     * 查询货品列表
     * 
     * @param receiveGoods 货品信息
     * @return 货品集合
     */
	public List<ReceiveGoods> selectReceiveGoodsList(ReceiveGoods receiveGoods);
	
	/**
     * 新增货品
     * 
     * @param receiveGoods 货品信息
     * @return 结果
     */
	public int insertReceiveGoods(ReceiveGoods receiveGoods);
	
	/**
     * 修改货品
     * 
     * @param receiveGoods 货品信息
     * @return 结果
     */
	public int updateReceiveGoods(ReceiveGoods receiveGoods);
	
	/**
     * 删除货品
     * 
     * @param goodsid 货品ID
     * @return 结果
     */
	public int deleteReceiveGoodsById(Long goodsid);
	
	/**
     * 批量删除货品
     * 
     * @param goodsids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveGoodsByIds(String[] goodsids);

    List<GoodsStoreReport> selectGoodsStoreReport(ReceiveStock receiveStock);
}