package com.ruoyi.project.cemslink.hopper.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.hopper.mapper.HopperMapper;
import com.ruoyi.project.cemslink.hopper.domain.Hopper;
import com.ruoyi.project.cemslink.hopper.service.IHopperService;
import com.ruoyi.common.utils.text.Convert;

/**
 * V形送料斗 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class HopperServiceImpl implements IHopperService 
{
	@Autowired
	private HopperMapper hopperMapper;

	/**
     * 查询V形送料斗信息
     * 
     * @param iD V形送料斗ID
     * @return V形送料斗信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Hopper selectHopperById(Long iD)
	{
	    return hopperMapper.selectHopperById(iD);
	}
	
	/**
     * 查询V形送料斗列表
     * 
     * @param hopper V形送料斗信息
     * @return V形送料斗集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Hopper> selectHopperList(Hopper hopper)
	{
	    return hopperMapper.selectHopperList(hopper);
	}
	
    /**
     * 新增V形送料斗
     * 
     * @param hopper V形送料斗信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertHopper(Hopper hopper)
	{
	    return hopperMapper.insertHopper(hopper);
	}
	
	/**
     * 修改V形送料斗
     * 
     * @param hopper V形送料斗信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateHopper(Hopper hopper)
	{
	    return hopperMapper.updateHopper(hopper);
	}

	/**
     * 删除V形送料斗对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteHopperByIds(String ids)
	{
		return hopperMapper.deleteHopperByIds(Convert.toStrArray(ids));
	}
	
}
