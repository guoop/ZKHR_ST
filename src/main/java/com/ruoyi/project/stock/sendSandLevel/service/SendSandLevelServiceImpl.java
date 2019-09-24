package com.ruoyi.project.stock.sendSandLevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendSandLevel.mapper.SendSandLevelMapper;
import com.ruoyi.project.stock.sendSandLevel.domain.SendSandLevel;
import com.ruoyi.project.stock.sendSandLevel.service.ISendSandLevelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 沙子等级 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendSandLevelServiceImpl implements ISendSandLevelService 
{
	@Autowired
	private SendSandLevelMapper sendSandLevelMapper;

	/**
     * 查询沙子等级信息
     * 
     * @param id 沙子等级ID
     * @return 沙子等级信息
     */
    @Override
	public SendSandLevel selectSendSandLevelById(Long id)
	{
	    return sendSandLevelMapper.selectSendSandLevelById(id);
	}
	
	/**
     * 查询沙子等级列表
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 沙子等级集合
     */
	@Override
	public List<SendSandLevel> selectSendSandLevelList(SendSandLevel sendSandLevel)
	{
	    return sendSandLevelMapper.selectSendSandLevelList(sendSandLevel);
	}
	
    /**
     * 新增沙子等级
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 结果
     */
	@Override
	public int insertSendSandLevel(SendSandLevel sendSandLevel)
	{
	    return sendSandLevelMapper.insertSendSandLevel(sendSandLevel);
	}
	
	/**
     * 修改沙子等级
     * 
     * @param sendSandLevel 沙子等级信息
     * @return 结果
     */
	@Override
	public int updateSendSandLevel(SendSandLevel sendSandLevel)
	{
	    return sendSandLevelMapper.updateSendSandLevel(sendSandLevel);
	}

	/**
     * 删除沙子等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendSandLevelByIds(String ids)
	{
		return sendSandLevelMapper.deleteSendSandLevelByIds(Convert.toStrArray(ids));
	}
	
}
