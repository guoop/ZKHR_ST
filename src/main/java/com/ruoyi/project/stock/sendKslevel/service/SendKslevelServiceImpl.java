package com.ruoyi.project.stock.sendKslevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendKslevel.mapper.SendKslevelMapper;
import com.ruoyi.project.stock.sendKslevel.domain.SendKslevel;
import com.ruoyi.project.stock.sendKslevel.service.ISendKslevelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 抗渗等级 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendKslevelServiceImpl implements ISendKslevelService 
{
	@Autowired
	private SendKslevelMapper sendKslevelMapper;

	/**
     * 查询抗渗等级信息
     * 
     * @param id 抗渗等级ID
     * @return 抗渗等级信息
     */
    @Override
	public SendKslevel selectSendKslevelById(Long id)
	{
	    return sendKslevelMapper.selectSendKslevelById(id);
	}
	
	/**
     * 查询抗渗等级列表
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 抗渗等级集合
     */
	@Override
	public List<SendKslevel> selectSendKslevelList(SendKslevel sendKslevel)
	{
	    return sendKslevelMapper.selectSendKslevelList(sendKslevel);
	}
	
    /**
     * 新增抗渗等级
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 结果
     */
	@Override
	public int insertSendKslevel(SendKslevel sendKslevel)
	{
	    return sendKslevelMapper.insertSendKslevel(sendKslevel);
	}
	
	/**
     * 修改抗渗等级
     * 
     * @param sendKslevel 抗渗等级信息
     * @return 结果
     */
	@Override
	public int updateSendKslevel(SendKslevel sendKslevel)
	{
	    return sendKslevelMapper.updateSendKslevel(sendKslevel);
	}

	/**
     * 删除抗渗等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendKslevelByIds(String ids)
	{
		return sendKslevelMapper.deleteSendKslevelByIds(Convert.toStrArray(ids));
	}
	
}
