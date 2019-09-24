package com.ruoyi.project.saller.saler.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.saller.saler.mapper.SalerMapper;
import com.ruoyi.project.saller.saler.domain.Saler;
import com.ruoyi.project.saller.saler.service.ISalerService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 业务员 服务层实现
 * 
 * @author admin
 * @date 2019-05-28
 */
@Service
public class SalerServiceImpl implements ISalerService 
{
	@Autowired
	private SalerMapper salerMapper;

	/**
     * 查询业务员信息
     * 
     * @param id 业务员ID
     * @return 业务员信息
     */
    @Override
	public Saler selectSalerById(Long id)
	{
	    return salerMapper.selectSalerById(id);
	}
	
	/**
     * 查询业务员列表
     * 
     * @param saler 业务员信息
     * @return 业务员集合
     */
	@Override
	public List<Saler> selectSalerList(Saler saler)
	{
	    return salerMapper.selectSalerList(saler);
	}
	
    /**
     * 新增业务员
     * 
     * @param saler 业务员信息
     * @return 结果
     */
	@Override
	public int insertSaler(Saler saler)
	{
	    return salerMapper.insertSaler(saler);
	}
	
	/**
     * 修改业务员
     * 
     * @param saler 业务员信息
     * @return 结果
     */
	@Override
	public int updateSaler(Saler saler)
	{
	    return salerMapper.updateSaler(saler);
	}

	/**
     * 删除业务员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSalerByIds(String ids)
	{
		return salerMapper.deleteSalerByIds(Convert.toStrArray(ids));
	}
	
}
