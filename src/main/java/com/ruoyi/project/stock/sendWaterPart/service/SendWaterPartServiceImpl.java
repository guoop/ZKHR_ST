package com.ruoyi.project.stock.sendWaterPart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendWaterPart.mapper.SendWaterPartMapper;
import com.ruoyi.project.stock.sendWaterPart.domain.SendWaterPart;
import com.ruoyi.project.stock.sendWaterPart.service.ISendWaterPartService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 工程部位 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendWaterPartServiceImpl implements ISendWaterPartService 
{
	@Autowired
	private SendWaterPartMapper sendWaterPartMapper;

	/**
     * 查询工程部位信息
     * 
     * @param id 工程部位ID
     * @return 工程部位信息
     */
    @Override
	public SendWaterPart selectSendWaterPartById(Long id)
	{
	    return sendWaterPartMapper.selectSendWaterPartById(id);
	}
	
	/**
     * 查询工程部位列表
     * 
     * @param sendWaterPart 工程部位信息
     * @return 工程部位集合
     */
	@Override
	public List<SendWaterPart> selectSendWaterPartList(SendWaterPart sendWaterPart)
	{
	    return sendWaterPartMapper.selectSendWaterPartList(sendWaterPart);
	}
	
    /**
     * 新增工程部位
     * 
     * @param sendWaterPart 工程部位信息
     * @return 结果
     */
	@Override
	public int insertSendWaterPart(SendWaterPart sendWaterPart)
	{
	    return sendWaterPartMapper.insertSendWaterPart(sendWaterPart);
	}
	
	/**
     * 修改工程部位
     * 
     * @param sendWaterPart 工程部位信息
     * @return 结果
     */
	@Override
	public int updateSendWaterPart(SendWaterPart sendWaterPart)
	{
	    return sendWaterPartMapper.updateSendWaterPart(sendWaterPart);
	}

	/**
     * 删除工程部位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendWaterPartByIds(String ids)
	{
		return sendWaterPartMapper.deleteSendWaterPartByIds(Convert.toStrArray(ids));
	}
	
}
