package com.ruoyi.project.cemslink.operationlogs.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.operationlogs.mapper.OperationlogsMapper;
import com.ruoyi.project.cemslink.operationlogs.domain.Operationlogs;
import com.ruoyi.project.cemslink.operationlogs.service.IOperationlogsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * operationlogs 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class OperationlogsServiceImpl implements IOperationlogsService 
{
	@Autowired
	private OperationlogsMapper operationlogsMapper;

	/**
     * 查询operationlogs信息
     * 
     * @param sID operationlogsID
     * @return operationlogs信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Operationlogs selectOperationlogsById(Long sID)
	{
	    return operationlogsMapper.selectOperationlogsById(sID);
	}
	
	/**
     * 查询operationlogs列表
     * 
     * @param operationlogs operationlogs信息
     * @return operationlogs集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Operationlogs> selectOperationlogsList(Operationlogs operationlogs)
	{
	    return operationlogsMapper.selectOperationlogsList(operationlogs);
	}
	
    /**
     * 新增operationlogs
     * 
     * @param operationlogs operationlogs信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertOperationlogs(Operationlogs operationlogs)
	{
	    return operationlogsMapper.insertOperationlogs(operationlogs);
	}
	
	/**
     * 修改operationlogs
     * 
     * @param operationlogs operationlogs信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateOperationlogs(Operationlogs operationlogs)
	{
	    return operationlogsMapper.updateOperationlogs(operationlogs);
	}

	/**
     * 删除operationlogs对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteOperationlogsByIds(String ids)
	{
		return operationlogsMapper.deleteOperationlogsByIds(Convert.toStrArray(ids));
	}
	
}
