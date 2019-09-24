package com.ruoyi.project.stock.receiveCar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveCar.mapper.ReceiveCarMapper;
import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveCar.service.IReceiveCarService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 送货车号 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveCarServiceImpl implements IReceiveCarService 
{
	@Autowired
	private ReceiveCarMapper receiveCarMapper;

	/**
     * 查询送货车号信息
     * 
     * @param id 送货车号ID
     * @return 送货车号信息
     */
    @Override
	public ReceiveCar selectReceiveCarById(Long id)
	{
	    return receiveCarMapper.selectReceiveCarById(id);
	}
	
	/**
     * 查询送货车号列表
     * 
     * @param receiveCar 送货车号信息
     * @return 送货车号集合
     */
	@Override
	public List<ReceiveCar> selectReceiveCarList(ReceiveCar receiveCar)
	{
	    return receiveCarMapper.selectReceiveCarList(receiveCar);
	}

	@Override
	public ReceiveCar selectOne(ReceiveCar receiveCar) {
		List<ReceiveCar> list = receiveCarMapper.selectReceiveCarList(receiveCar);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
     * 新增送货车号
     * 
     * @param receiveCar 送货车号信息
     * @return 结果
     */
	@Override
	public int insertReceiveCar(ReceiveCar receiveCar)
	{
	    return receiveCarMapper.insertReceiveCar(receiveCar);
	}
	
	/**
     * 修改送货车号
     * 
     * @param receiveCar 送货车号信息
     * @return 结果
     */
	@Override
	public int updateReceiveCar(ReceiveCar receiveCar)
	{
	    return receiveCarMapper.updateReceiveCar(receiveCar);
	}

	/**
     * 删除送货车号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveCarByIds(String ids)
	{
		return receiveCarMapper.deleteReceiveCarByIds(Convert.toStrArray(ids));
	}
	
}
