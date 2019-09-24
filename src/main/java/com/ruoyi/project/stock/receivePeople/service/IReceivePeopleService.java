package com.ruoyi.project.stock.receivePeople.service;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import java.util.List;

/**
 * 收货人员 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceivePeopleService 
{
	/**
     * 查询收货人员信息
     * 
     * @param id 收货人员ID
     * @return 收货人员信息
     */
	public ReceivePeople selectReceivePeopleById(Long id);
	
	/**
     * 查询收货人员列表
     * 
     * @param receivePeople 收货人员信息
     * @return 收货人员集合
     */
	public List<ReceivePeople> selectReceivePeopleList(ReceivePeople receivePeople);
	
	/**
     * 新增收货人员
     * 
     * @param receivePeople 收货人员信息
     * @return 结果
     */
	public int insertReceivePeople(ReceivePeople receivePeople);
	
	/**
     * 修改收货人员
     * 
     * @param receivePeople 收货人员信息
     * @return 结果
     */
	public int updateReceivePeople(ReceivePeople receivePeople);
		
	/**
     * 删除收货人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceivePeopleByIds(String ids);

    ReceivePeople selectOne(ReceivePeople receivePeople);
}
