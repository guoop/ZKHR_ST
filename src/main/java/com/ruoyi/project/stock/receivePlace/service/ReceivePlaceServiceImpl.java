package com.ruoyi.project.stock.receivePlace.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receivePlace.mapper.ReceivePlaceMapper;
import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import com.ruoyi.project.stock.receivePlace.service.IReceivePlaceService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 产地 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceivePlaceServiceImpl implements IReceivePlaceService 
{
	@Autowired
	private ReceivePlaceMapper receivePlaceMapper;

	/**
     * 查询产地信息
     * 
     * @param id 产地ID
     * @return 产地信息
     */
    @Override
	public ReceivePlace selectReceivePlaceById(Long id)
	{
	    return receivePlaceMapper.selectReceivePlaceById(id);
	}
	
	/**
     * 查询产地列表
     * 
     * @param receivePlace 产地信息
     * @return 产地集合
     */
	@Override
	public List<ReceivePlace> selectReceivePlaceList(ReceivePlace receivePlace)
	{
	    return receivePlaceMapper.selectReceivePlaceList(receivePlace);
	}
	
    /**
     * 新增产地
     * 
     * @param receivePlace 产地信息
     * @return 结果
     */
	@Override
	public int insertReceivePlace(ReceivePlace receivePlace)
	{
	    return receivePlaceMapper.insertReceivePlace(receivePlace);
	}
	
	/**
     * 修改产地
     * 
     * @param receivePlace 产地信息
     * @return 结果
     */
	@Override
	public int updateReceivePlace(ReceivePlace receivePlace)
	{
	    return receivePlaceMapper.updateReceivePlace(receivePlace);
	}

	/**
     * 删除产地对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceivePlaceByIds(String ids)
	{
		return receivePlaceMapper.deleteReceivePlaceByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceivePlace selectOne(ReceivePlace receivePlace) {
		List<ReceivePlace> list = receivePlaceMapper.selectReceivePlaceList(receivePlace);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
