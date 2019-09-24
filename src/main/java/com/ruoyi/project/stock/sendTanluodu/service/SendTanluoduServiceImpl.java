package com.ruoyi.project.stock.sendTanluodu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendTanluodu.mapper.SendTanluoduMapper;
import com.ruoyi.project.stock.sendTanluodu.domain.SendTanluodu;
import com.ruoyi.project.stock.sendTanluodu.service.ISendTanluoduService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 坍落度 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendTanluoduServiceImpl implements ISendTanluoduService 
{
	@Autowired
	private SendTanluoduMapper sendTanluoduMapper;

	/**
     * 查询坍落度信息
     * 
     * @param id 坍落度ID
     * @return 坍落度信息
     */
    @Override
	public SendTanluodu selectSendTanluoduById(Long id)
	{
	    return sendTanluoduMapper.selectSendTanluoduById(id);
	}
	
	/**
     * 查询坍落度列表
     * 
     * @param sendTanluodu 坍落度信息
     * @return 坍落度集合
     */
	@Override
	public List<SendTanluodu> selectSendTanluoduList(SendTanluodu sendTanluodu)
	{
	    return sendTanluoduMapper.selectSendTanluoduList(sendTanluodu);
	}
	
    /**
     * 新增坍落度
     * 
     * @param sendTanluodu 坍落度信息
     * @return 结果
     */
	@Override
	public int insertSendTanluodu(SendTanluodu sendTanluodu)
	{
	    return sendTanluoduMapper.insertSendTanluodu(sendTanluodu);
	}
	
	/**
     * 修改坍落度
     * 
     * @param sendTanluodu 坍落度信息
     * @return 结果
     */
	@Override
	public int updateSendTanluodu(SendTanluodu sendTanluodu)
	{
	    return sendTanluoduMapper.updateSendTanluodu(sendTanluodu);
	}

	/**
     * 删除坍落度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendTanluoduByIds(String ids)
	{
		return sendTanluoduMapper.deleteSendTanluoduByIds(Convert.toStrArray(ids));
	}
	
}
