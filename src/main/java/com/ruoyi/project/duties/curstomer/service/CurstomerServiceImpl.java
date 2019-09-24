package com.ruoyi.project.duties.curstomer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.curstomer.mapper.CurstomerMapper;
import com.ruoyi.project.duties.curstomer.domain.Curstomer;
import com.ruoyi.project.duties.curstomer.service.ICurstomerService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 客户 服务层实现
 * 
 * @author admin
 * @date 2019-08-07
 */
@Service
public class CurstomerServiceImpl implements ICurstomerService 
{
	@Autowired
	private CurstomerMapper curstomerMapper;

	/**
     * 查询客户信息
     * 
     * @param cusId 客户ID
     * @return 客户信息
     */
    @Override
	public Curstomer selectCurstomerById(Long cusId)
	{
	    return curstomerMapper.selectCurstomerById(cusId);
	}
	
	/**
     * 查询客户列表
     * 
     * @param curstomer 客户信息
     * @return 客户集合
     */
	@Override
	public List<Curstomer> selectCurstomerList(Curstomer curstomer)
	{
	    return curstomerMapper.selectCurstomerList(curstomer);
	}
	
    /**
     * 新增客户
     * 
     * @param curstomer 客户信息
     * @return 结果
     */
	@Override
	public int insertCurstomer(Curstomer curstomer)
	{
	    return curstomerMapper.insertCurstomer(curstomer);
	}
	
	/**
     * 修改客户
     * 
     * @param curstomer 客户信息
     * @return 结果
     */
	@Override
	public int updateCurstomer(Curstomer curstomer)
	{
	    return curstomerMapper.updateCurstomer(curstomer);
	}

	/**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCurstomerByIds(String ids)
	{
		return curstomerMapper.deleteCurstomerByIds(Convert.toStrArray(ids));
	}
	
}
