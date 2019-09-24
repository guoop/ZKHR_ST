package com.ruoyi.project.stock.sendCementlevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.sendCementlevel.mapper.SendCementlevelMapper;
import com.ruoyi.project.stock.sendCementlevel.domain.SendCementlevel;
import com.ruoyi.project.stock.sendCementlevel.service.ISendCementlevelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 水泥标号 服务层实现
 * 
 * @author admin
 * @date 2019-08-14
 */
@Service
public class SendCementlevelServiceImpl implements ISendCementlevelService 
{
	@Autowired
	private SendCementlevelMapper sendCementlevelMapper;

	/**
     * 查询水泥标号信息
     * 
     * @param id 水泥标号ID
     * @return 水泥标号信息
     */
    @Override
	public SendCementlevel selectSendCementlevelById(Long id)
	{
	    return sendCementlevelMapper.selectSendCementlevelById(id);
	}
	
	/**
     * 查询水泥标号列表
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 水泥标号集合
     */
	@Override
	public List<SendCementlevel> selectSendCementlevelList(SendCementlevel sendCementlevel)
	{
	    return sendCementlevelMapper.selectSendCementlevelList(sendCementlevel);
	}
	
    /**
     * 新增水泥标号
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 结果
     */
	@Override
	public int insertSendCementlevel(SendCementlevel sendCementlevel)
	{
	    return sendCementlevelMapper.insertSendCementlevel(sendCementlevel);
	}
	
	/**
     * 修改水泥标号
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 结果
     */
	@Override
	public int updateSendCementlevel(SendCementlevel sendCementlevel)
	{
	    return sendCementlevelMapper.updateSendCementlevel(sendCementlevel);
	}

	/**
     * 删除水泥标号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSendCementlevelByIds(String ids)
	{
		return sendCementlevelMapper.deleteSendCementlevelByIds(Convert.toStrArray(ids));
	}
	
}
