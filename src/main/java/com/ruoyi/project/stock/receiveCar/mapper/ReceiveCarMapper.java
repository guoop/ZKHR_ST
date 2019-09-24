package com.ruoyi.project.stock.receiveCar.mapper;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import java.util.List;	

/**
 * 送货车号 数据层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface ReceiveCarMapper 
{
	/**
     * 查询送货车号信息
     * 
     * @param id 送货车号ID
     * @return 送货车号信息
     */
	public ReceiveCar selectReceiveCarById(Long id);
	
	/**
     * 查询送货车号列表
     * 
     * @param receiveCar 送货车号信息
     * @return 送货车号集合
     */
	public List<ReceiveCar> selectReceiveCarList(ReceiveCar receiveCar);
	
	/**
     * 新增送货车号
     * 
     * @param receiveCar 送货车号信息
     * @return 结果
     */
	public int insertReceiveCar(ReceiveCar receiveCar);
	
	/**
     * 修改送货车号
     * 
     * @param receiveCar 送货车号信息
     * @return 结果
     */
	public int updateReceiveCar(ReceiveCar receiveCar);
	
	/**
     * 删除送货车号
     * 
     * @param id 送货车号ID
     * @return 结果
     */
	public int deleteReceiveCarById(Long id);
	
	/**
     * 批量删除送货车号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveCarByIds(String[] ids);
	
}