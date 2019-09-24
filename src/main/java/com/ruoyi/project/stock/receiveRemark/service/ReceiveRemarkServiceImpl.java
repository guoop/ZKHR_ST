package com.ruoyi.project.stock.receiveRemark.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveRemark.mapper.ReceiveRemarkMapper;
import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import com.ruoyi.project.stock.receiveRemark.service.IReceiveRemarkService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 收货备注 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveRemarkServiceImpl implements IReceiveRemarkService 
{
	@Autowired
	private ReceiveRemarkMapper receiveRemarkMapper;

	/**
     * 查询收货备注信息
     * 
     * @param id 收货备注ID
     * @return 收货备注信息
     */
    @Override
	public ReceiveRemark selectReceiveRemarkById(Long id)
	{
	    return receiveRemarkMapper.selectReceiveRemarkById(id);
	}
	
	/**
     * 查询收货备注列表
     * 
     * @param receiveRemark 收货备注信息
     * @return 收货备注集合
     */
	@Override
	public List<ReceiveRemark> selectReceiveRemarkList(ReceiveRemark receiveRemark)
	{
	    return receiveRemarkMapper.selectReceiveRemarkList(receiveRemark);
	}
	
    /**
     * 新增收货备注
     * 
     * @param receiveRemark 收货备注信息
     * @return 结果
     */
	@Override
	public int insertReceiveRemark(ReceiveRemark receiveRemark)
	{
	    return receiveRemarkMapper.insertReceiveRemark(receiveRemark);
	}
	
	/**
     * 修改收货备注
     * 
     * @param receiveRemark 收货备注信息
     * @return 结果
     */
	@Override
	public int updateReceiveRemark(ReceiveRemark receiveRemark)
	{
	    return receiveRemarkMapper.updateReceiveRemark(receiveRemark);
	}

	/**
     * 删除收货备注对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveRemarkByIds(String ids)
	{
		return receiveRemarkMapper.deleteReceiveRemarkByIds(Convert.toStrArray(ids));
	}
	
}
