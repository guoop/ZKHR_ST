package com.ruoyi.project.stock.receiveRemark.service;

import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import java.util.List;

/**
 * 收货备注 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceiveRemarkService 
{
	/**
     * 查询收货备注信息
     * 
     * @param id 收货备注ID
     * @return 收货备注信息
     */
	public ReceiveRemark selectReceiveRemarkById(Long id);
	
	/**
     * 查询收货备注列表
     * 
     * @param receiveRemark 收货备注信息
     * @return 收货备注集合
     */
	public List<ReceiveRemark> selectReceiveRemarkList(ReceiveRemark receiveRemark);
	
	/**
     * 新增收货备注
     * 
     * @param receiveRemark 收货备注信息
     * @return 结果
     */
	public int insertReceiveRemark(ReceiveRemark receiveRemark);
	
	/**
     * 修改收货备注
     * 
     * @param receiveRemark 收货备注信息
     * @return 结果
     */
	public int updateReceiveRemark(ReceiveRemark receiveRemark);
		
	/**
     * 删除收货备注信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveRemarkByIds(String ids);
	
}
