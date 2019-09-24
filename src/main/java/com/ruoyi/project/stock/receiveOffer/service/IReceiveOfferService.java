package com.ruoyi.project.stock.receiveOffer.service;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import java.util.List;

/**
 * 供货单位 服务层
 * 
 * @author admin
 * @date 2019-08-09
 */
public interface IReceiveOfferService 
{
	/**
     * 查询供货单位信息
     * 
     * @param id 供货单位ID
     * @return 供货单位信息
     */
	public ReceiveOffer selectReceiveOfferById(Long id);
	
	/**
     * 查询供货单位列表
     * 
     * @param receiveOffer 供货单位信息
     * @return 供货单位集合
     */
	public List<ReceiveOffer> selectReceiveOfferList(ReceiveOffer receiveOffer);
	
	/**
     * 新增供货单位
     * 
     * @param receiveOffer 供货单位信息
     * @return 结果
     */
	public int insertReceiveOffer(ReceiveOffer receiveOffer);
	
	/**
     * 修改供货单位
     * 
     * @param receiveOffer 供货单位信息
     * @return 结果
     */
	public int updateReceiveOffer(ReceiveOffer receiveOffer);
		
	/**
     * 删除供货单位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiveOfferByIds(String ids);

    ReceiveOffer selectOne(ReceiveOffer receiveOffer);
}
