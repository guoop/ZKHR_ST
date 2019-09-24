package com.ruoyi.project.stock.sendAdmixsture.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendAdmixsture.mapper.SendAdmixstureMapper;
import com.ruoyi.project.stock.sendAdmixsture.domain.SendAdmixsture;
import com.ruoyi.project.stock.sendAdmixsture.service.ISendAdmixstureService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外加剂 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendAdmixstureServiceImpl implements ISendAdmixstureService 
{
	@Autowired
	private SendAdmixstureMapper sendAdmixstureMapper;

	/**
     * 查询外加剂信息
     * 
     * @param id 外加剂ID
     * @return 外加剂信息
     */
    @Override
	public SendAdmixsture selectSendAdmixstureById(Long id)
	{
	    return sendAdmixstureMapper.selectSendAdmixstureById(id);
	}
	
	/**
     * 查询外加剂列表
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 外加剂集合
     */
	@Override
	public List<SendAdmixsture> selectSendAdmixstureList(SendAdmixsture sendAdmixsture)
	{
	    return sendAdmixstureMapper.selectSendAdmixstureList(sendAdmixsture);
	}
	
    /**
     * 新增外加剂
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 结果
     */
	@Override
	public int insertSendAdmixsture(SendAdmixsture sendAdmixsture)
	{
	    return sendAdmixstureMapper.insertSendAdmixsture(sendAdmixsture);
	}
	
	/**
     * 修改外加剂
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 结果
     */
	@Override
	public int updateSendAdmixsture(SendAdmixsture sendAdmixsture)
	{
	    return sendAdmixstureMapper.updateSendAdmixsture(sendAdmixsture);
	}

	/**
     * 删除外加剂对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendAdmixstureByIds(String ids)
	{
		return sendAdmixstureMapper.deleteSendAdmixstureByIds(Convert.toStrArray(ids));
	}
	
}
