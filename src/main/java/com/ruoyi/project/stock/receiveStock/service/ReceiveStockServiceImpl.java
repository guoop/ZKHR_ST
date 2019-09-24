package com.ruoyi.project.stock.receiveStock.service;

import java.util.List;

import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStockReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveStock.mapper.ReceiveStockMapper;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.service.IReceiveStockService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 进货库存 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveStockServiceImpl implements IReceiveStockService 
{
	@Autowired
	private ReceiveStockMapper receiveStockMapper;

	/**
     * 查询进货库存信息
     * 
     * @param id 进货库存ID
     * @return 进货库存信息
     */
    @Override
	public ReceiveStock selectReceiveStockById(Long id)
	{
	    return receiveStockMapper.selectReceiveStockById(id);
	}
	
	/**
     * 查询进货库存列表
     * 
     * @param receiveStock 进货库存信息
     * @return 进货库存集合
     */
	@Override
	public List<ReceiveStock> selectReceiveStockList(ReceiveStock receiveStock)
	{
	    return receiveStockMapper.selectReceiveStockList(receiveStock);
	}
	
    /**
     * 新增进货库存
     * 
     * @param receiveStock 进货库存信息
     * @return 结果
     */
	@Override
	public int insertReceiveStock(ReceiveStock receiveStock)
	{
	    return receiveStockMapper.insertReceiveStock(receiveStock);
	}
	
	/**
     * 修改进货库存
     * 
     * @param receiveStock 进货库存信息
     * @return 结果
     */
	@Override
	public int updateReceiveStock(ReceiveStock receiveStock)
	{
	    return receiveStockMapper.updateReceiveStock(receiveStock);
	}

	/**
     * 删除进货库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveStockByIds(String ids)
	{
		return receiveStockMapper.deleteReceiveStockByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ReceiveStockReport> selectReceiveStockReport(ReceiveStock receiveStock) {
		return receiveStockMapper.selectReceiveStockReport(receiveStock);
	}

	@Override
	public ConsumptionVO selectConsumptionNow(ReceiveStock receiveStock) {
		return receiveStockMapper.selectConsumptionNow(receiveStock);
	}

}
