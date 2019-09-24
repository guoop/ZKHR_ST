package com.ruoyi.project.stock.sendStoneLevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendStoneLevel.mapper.SendStoneLevelMapper;
import com.ruoyi.project.stock.sendStoneLevel.domain.SendStoneLevel;
import com.ruoyi.project.stock.sendStoneLevel.service.ISendStoneLevelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 石子等级 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendStoneLevelServiceImpl implements ISendStoneLevelService 
{
	@Autowired
	private SendStoneLevelMapper sendStoneLevelMapper;

	/**
     * 查询石子等级信息
     * 
     * @param id 石子等级ID
     * @return 石子等级信息
     */
    @Override
	public SendStoneLevel selectSendStoneLevelById(Long id)
	{
	    return sendStoneLevelMapper.selectSendStoneLevelById(id);
	}
	
	/**
     * 查询石子等级列表
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 石子等级集合
     */
	@Override
	public List<SendStoneLevel> selectSendStoneLevelList(SendStoneLevel sendStoneLevel)
	{
	    return sendStoneLevelMapper.selectSendStoneLevelList(sendStoneLevel);
	}
	
    /**
     * 新增石子等级
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 结果
     */
	@Override
	public int insertSendStoneLevel(SendStoneLevel sendStoneLevel)
	{
	    return sendStoneLevelMapper.insertSendStoneLevel(sendStoneLevel);
	}
	
	/**
     * 修改石子等级
     * 
     * @param sendStoneLevel 石子等级信息
     * @return 结果
     */
	@Override
	public int updateSendStoneLevel(SendStoneLevel sendStoneLevel)
	{
	    return sendStoneLevelMapper.updateSendStoneLevel(sendStoneLevel);
	}

	/**
     * 删除石子等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendStoneLevelByIds(String ids)
	{
		return sendStoneLevelMapper.deleteSendStoneLevelByIds(Convert.toStrArray(ids));
	}
	
}
