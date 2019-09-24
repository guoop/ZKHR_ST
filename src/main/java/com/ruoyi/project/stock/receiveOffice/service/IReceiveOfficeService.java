package com.ruoyi.project.stock.receiveOffice.service;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import java.util.List;

/**
 * 收货单位 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceiveOfficeService 
{
	/**
     * 查询收货单位信息
     * 
     * @param id 收货单位ID
     * @return 收货单位信息
     */
	public ReceiveOffice selectReceiveOfficeById(Long id);
	
	/**
     * 查询收货单位列表
     * 
     * @param receiveOffice 收货单位信息
     * @return 收货单位集合
     */
	public List<ReceiveOffice> selectReceiveOfficeList(ReceiveOffice receiveOffice);
	
	/**
     * 新增收货单位
     * 
     * @param receiveOffice 收货单位信息
     * @return 结果
     */
	public int insertReceiveOffice(ReceiveOffice receiveOffice);
	
	/**
     * 修改收货单位
     * 
     * @param receiveOffice 收货单位信息
     * @return 结果
     */
	public int updateReceiveOffice(ReceiveOffice receiveOffice);
		
	/**
     * 删除收货单位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveOfficeByIds(String ids);

    ReceiveOffice selectOne(ReceiveOffice receiveOffice);
}
