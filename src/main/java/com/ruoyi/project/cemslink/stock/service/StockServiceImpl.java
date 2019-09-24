package com.ruoyi.project.cemslink.stock.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.stock.mapper.StockMapper;
import com.ruoyi.project.cemslink.stock.domain.Stock;
import com.ruoyi.project.cemslink.stock.service.IStockService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 库存数据 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class StockServiceImpl implements IStockService 
{
	@Autowired
	private StockMapper stockMapper;

	/**
     * 查询库存数据信息
     * 
     * @param sID 库存数据ID
     * @return 库存数据信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Stock selectStockById(Long sID)
	{
	    return stockMapper.selectStockById(sID);
	}
	
	/**
     * 查询库存数据列表
     * 
     * @param stock 库存数据信息
     * @return 库存数据集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Stock> selectStockList(Stock stock)
	{
	    return stockMapper.selectStockList(stock);
	}
	
    /**
     * 新增库存数据
     * 
     * @param stock 库存数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertStock(Stock stock)
	{
	    return stockMapper.insertStock(stock);
	}
	
	/**
     * 修改库存数据
     * 
     * @param stock 库存数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateStock(Stock stock)
	{
	    return stockMapper.updateStock(stock);
	}

	/**
     * 删除库存数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteStockByIds(String ids)
	{
		return stockMapper.deleteStockByIds(Convert.toStrArray(ids));
	}
	
}
