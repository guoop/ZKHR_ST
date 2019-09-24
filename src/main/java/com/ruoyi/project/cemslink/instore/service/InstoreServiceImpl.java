package com.ruoyi.project.cemslink.instore.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.instore.mapper.InstoreMapper;
import com.ruoyi.project.cemslink.instore.domain.Instore;
import com.ruoyi.project.cemslink.instore.service.IInstoreService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 库存 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class InstoreServiceImpl implements IInstoreService 
{
	@Autowired
	private InstoreMapper instoreMapper;

	/**
     * 查询库存信息
     * 
     * @param sID 库存ID
     * @return 库存信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Instore selectInstoreById(Long sID)
	{
	    return instoreMapper.selectInstoreById(sID);
	}
	
	/**
     * 查询库存列表
     * 
     * @param instore 库存信息
     * @return 库存集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Instore> selectInstoreList(Instore instore)
	{
	    return instoreMapper.selectInstoreList(instore);
	}
	
    /**
     * 新增库存
     * 
     * @param instore 库存信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertInstore(Instore instore)
	{
	    return instoreMapper.insertInstore(instore);
	}
	
	/**
     * 修改库存
     * 
     * @param instore 库存信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateInstore(Instore instore)
	{
	    return instoreMapper.updateInstore(instore);
	}

	/**
     * 删除库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteInstoreByIds(String ids)
	{
		return instoreMapper.deleteInstoreByIds(Convert.toStrArray(ids));
	}
	
}
