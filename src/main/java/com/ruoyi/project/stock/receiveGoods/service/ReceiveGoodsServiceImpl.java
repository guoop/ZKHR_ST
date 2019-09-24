package com.ruoyi.project.stock.receiveGoods.service;

import java.util.List;

import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveStock.domain.GoodsStoreReport;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.mapper.ReceiveStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveGoods.mapper.ReceiveGoodsMapper;
import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveGoods.service.IReceiveGoodsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 货品 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveGoodsServiceImpl implements IReceiveGoodsService 
{
	@Autowired
	private ReceiveGoodsMapper receiveGoodsMapper;
	@Autowired
	private ReceiveStockMapper receiveStockMapper;

	/**
     * 查询货品信息
     * 
     * @param goodsid 货品ID
     * @return 货品信息
     */
    @Override
	public ReceiveGoods selectReceiveGoodsById(Long goodsid)
	{
	    return receiveGoodsMapper.selectReceiveGoodsById(goodsid);
	}
	
	/**
     * 查询货品列表
     * 
     * @param receiveGoods 货品信息
     * @return 货品集合
     */
	@Override
	public List<ReceiveGoods> selectReceiveGoodsList(ReceiveGoods receiveGoods)
	{
	    return receiveGoodsMapper.selectReceiveGoodsList(receiveGoods);
	}
	
    /**
     * 新增货品
     * 
     * @param receiveGoods 货品信息
     * @return 结果
     */
	@Override
	public int insertReceiveGoods(ReceiveGoods receiveGoods)
	{
	    return receiveGoodsMapper.insertReceiveGoods(receiveGoods);
	}
	
	/**
     * 修改货品
     * 
     * @param receiveGoods 货品信息
     * @return 结果
     */
	@Override
	public int updateReceiveGoods(ReceiveGoods receiveGoods)
	{
	    return receiveGoodsMapper.updateReceiveGoods(receiveGoods);
	}

	/**
     * 删除货品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveGoodsByIds(String ids)
	{
		return receiveGoodsMapper.deleteReceiveGoodsByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceiveGoods selectOne(ReceiveGoods receiveGoods) {
		List<ReceiveGoods> list = receiveGoodsMapper.selectReceiveGoodsList(receiveGoods);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
