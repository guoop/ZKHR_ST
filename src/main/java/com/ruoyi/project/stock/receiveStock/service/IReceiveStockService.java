package com.ruoyi.project.stock.receiveStock.service;

import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStockReport;

import java.util.List;

/**
 * 进货库存 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceiveStockService 
{
	/**
     * 查询进货库存信息
     * 
     * @param id 进货库存ID
     * @return 进货库存信息
     */
	public ReceiveStock selectReceiveStockById(Long id);
	
	/**
     * 查询进货库存列表
     * 
     * @param receiveStock 进货库存信息
     * @return 进货库存集合
     */
	public List<ReceiveStock> selectReceiveStockList(ReceiveStock receiveStock);
	
	/**
     * 新增进货库存
     * 
     * @param receiveStock 进货库存信息
     * @return 结果
     */
	public int insertReceiveStock(ReceiveStock receiveStock);
	
	/**
     * 修改进货库存
     * 
     * @param receiveStock 进货库存信息
     * @return 结果
     */
	public int updateReceiveStock(ReceiveStock receiveStock);
		
	/**
     * 删除进货库存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveStockByIds(String ids);

    List<ReceiveStockReport> selectReceiveStockReport(ReceiveStock receiveStock);

	ConsumptionVO selectConsumptionNow(ReceiveStock receiveStock);
}
