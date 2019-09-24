package com.ruoyi.project.cemslink.stock.service;

import com.ruoyi.project.cemslink.stock.domain.Stock;
import java.util.List;

/**
 * 库存数据 服务层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface IStockService 
{
	/**
     * 查询库存数据信息
     * 
     * @param sID 库存数据ID
     * @return 库存数据信息
     */
	public Stock selectStockById(Long sID);
	
	/**
     * 查询库存数据列表
     * 
     * @param stock 库存数据信息
     * @return 库存数据集合
     */
	public List<Stock> selectStockList(Stock stock);
	
	/**
     * 新增库存数据
     * 
     * @param stock 库存数据信息
     * @return 结果
     */
	public int insertStock(Stock stock);
	
	/**
     * 修改库存数据
     * 
     * @param stock 库存数据信息
     * @return 结果
     */
	public int updateStock(Stock stock);
		
	/**
     * 删除库存数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStockByIds(String ids);
	
}
