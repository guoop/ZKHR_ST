package com.ruoyi.project.stock.sendWaterMethod.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendWaterMethod.mapper.SendWaterMethodMapper;
import com.ruoyi.project.stock.sendWaterMethod.domain.SendWaterMethod;
import com.ruoyi.project.stock.sendWaterMethod.service.ISendWaterMethodService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 浇筑方式 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendWaterMethodServiceImpl implements ISendWaterMethodService 
{
	@Autowired
	private SendWaterMethodMapper sendWaterMethodMapper;

	/**
     * 查询浇筑方式信息
     * 
     * @param id 浇筑方式ID
     * @return 浇筑方式信息
     */
    @Override
	public SendWaterMethod selectSendWaterMethodById(Long id)
	{
	    return sendWaterMethodMapper.selectSendWaterMethodById(id);
	}
	
	/**
     * 查询浇筑方式列表
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 浇筑方式集合
     */
	@Override
	public List<SendWaterMethod> selectSendWaterMethodList(SendWaterMethod sendWaterMethod)
	{
	    return sendWaterMethodMapper.selectSendWaterMethodList(sendWaterMethod);
	}
	
    /**
     * 新增浇筑方式
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 结果
     */
	@Override
	public int insertSendWaterMethod(SendWaterMethod sendWaterMethod)
	{
	    return sendWaterMethodMapper.insertSendWaterMethod(sendWaterMethod);
	}
	
	/**
     * 修改浇筑方式
     * 
     * @param sendWaterMethod 浇筑方式信息
     * @return 结果
     */
	@Override
	public int updateSendWaterMethod(SendWaterMethod sendWaterMethod)
	{
	    return sendWaterMethodMapper.updateSendWaterMethod(sendWaterMethod);
	}

	/**
     * 删除浇筑方式对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendWaterMethodByIds(String ids)
	{
		return sendWaterMethodMapper.deleteSendWaterMethodByIds(Convert.toStrArray(ids));
	}
	
}
