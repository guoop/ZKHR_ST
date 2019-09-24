package com.ruoyi.project.stock.receivePeople.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receivePeople.mapper.ReceivePeopleMapper;
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import com.ruoyi.project.stock.receivePeople.service.IReceivePeopleService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 收货人员 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceivePeopleServiceImpl implements IReceivePeopleService 
{
	@Autowired
	private ReceivePeopleMapper receivePeopleMapper;

	/**
     * 查询收货人员信息
     * 
     * @param id 收货人员ID
     * @return 收货人员信息
     */
    @Override
	public ReceivePeople selectReceivePeopleById(Long id)
	{
	    return receivePeopleMapper.selectReceivePeopleById(id);
	}
	
	/**
     * 查询收货人员列表
     * 
     * @param receivePeople 收货人员信息
     * @return 收货人员集合
     */
	@Override
	public List<ReceivePeople> selectReceivePeopleList(ReceivePeople receivePeople)
	{
	    return receivePeopleMapper.selectReceivePeopleList(receivePeople);
	}
	
    /**
     * 新增收货人员
     * 
     * @param receivePeople 收货人员信息
     * @return 结果
     */
	@Override
	public int insertReceivePeople(ReceivePeople receivePeople)
	{
	    return receivePeopleMapper.insertReceivePeople(receivePeople);
	}
	
	/**
     * 修改收货人员
     * 
     * @param receivePeople 收货人员信息
     * @return 结果
     */
	@Override
	public int updateReceivePeople(ReceivePeople receivePeople)
	{
	    return receivePeopleMapper.updateReceivePeople(receivePeople);
	}

	/**
     * 删除收货人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceivePeopleByIds(String ids)
	{
		return receivePeopleMapper.deleteReceivePeopleByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceivePeople selectOne(ReceivePeople receivePeople) {
		List<ReceivePeople> list = receivePeopleMapper.selectReceivePeopleList(receivePeople);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
