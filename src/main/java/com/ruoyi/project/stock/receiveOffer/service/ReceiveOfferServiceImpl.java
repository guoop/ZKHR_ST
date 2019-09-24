package com.ruoyi.project.stock.receiveOffer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveOffer.mapper.ReceiveOfferMapper;
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import com.ruoyi.project.stock.receiveOffer.service.IReceiveOfferService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 供货单位 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveOfferServiceImpl implements IReceiveOfferService 
{
	@Autowired
	private ReceiveOfferMapper receiveOfferMapper;

	/**
     * 查询供货单位信息
     * 
     * @param id 供货单位ID
     * @return 供货单位信息
     */
    @Override
	public ReceiveOffer selectReceiveOfferById(Long id)
	{
	    return receiveOfferMapper.selectReceiveOfferById(id);
	}
	
	/**
     * 查询供货单位列表
     * 
     * @param receiveOffer 供货单位信息
     * @return 供货单位集合
     */
	@Override
	public List<ReceiveOffer> selectReceiveOfferList(ReceiveOffer receiveOffer)
	{
	    return receiveOfferMapper.selectReceiveOfferList(receiveOffer);
	}
	
    /**
     * 新增供货单位
     * 
     * @param receiveOffer 供货单位信息
     * @return 结果
     */
	@Override
	public int insertReceiveOffer(ReceiveOffer receiveOffer)
	{
	    return receiveOfferMapper.insertReceiveOffer(receiveOffer);
	}
	
	/**
     * 修改供货单位
     * 
     * @param receiveOffer 供货单位信息
     * @return 结果
     */
	@Override
	public int updateReceiveOffer(ReceiveOffer receiveOffer)
	{
	    return receiveOfferMapper.updateReceiveOffer(receiveOffer);
	}

	/**
     * 删除供货单位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveOfferByIds(String ids)
	{
		return receiveOfferMapper.deleteReceiveOfferByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceiveOffer selectOne(ReceiveOffer receiveOffer) {
		List<ReceiveOffer> list = receiveOfferMapper.selectReceiveOfferList(receiveOffer);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
