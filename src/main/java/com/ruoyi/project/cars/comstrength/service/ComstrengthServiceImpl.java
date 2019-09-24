package com.ruoyi.project.cars.comstrength.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.comstrength.mapper.ComstrengthMapper;
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 强度等级 服务层实现
 * 
 * @author admin
 * @date 2019-08-01
 */
@Service
public class ComstrengthServiceImpl implements IComstrengthService 
{
	@Autowired
	private ComstrengthMapper comstrengthMapper;

	/**
     * 查询强度等级信息
     * 
     * @param id 强度等级ID
     * @return 强度等级信息
     */
    @Override
	public Comstrength selectComstrengthById(Long id)
	{
	    return comstrengthMapper.selectComstrengthById(id);
	}
	
	/**
     * 查询强度等级列表
     * 
     * @param comstrength 强度等级信息
     * @return 强度等级集合
     */
	@Override
	public List<Comstrength> selectComstrengthList(Comstrength comstrength)
	{
	    return comstrengthMapper.selectComstrengthList(comstrength);
	}
	
    /**
     * 新增强度等级
     * 
     * @param comstrength 强度等级信息
     * @return 结果
     */
	@Override
	public int insertComstrength(Comstrength comstrength)
	{
	    return comstrengthMapper.insertComstrength(comstrength);
	}
	
	/**
     * 修改强度等级
     * 
     * @param comstrength 强度等级信息
     * @return 结果
     */
	@Override
	public int updateComstrength(Comstrength comstrength)
	{
	    return comstrengthMapper.updateComstrength(comstrength);
	}

	/**
     * 删除强度等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteComstrengthByIds(String ids)
	{
		return comstrengthMapper.deleteComstrengthByIds(Convert.toStrArray(ids));
	}

	@Override
	public Comstrength selectOne(Comstrength comstrength) {
		List<Comstrength> list = comstrengthMapper.selectComstrengthList(comstrength);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
