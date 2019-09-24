package com.ruoyi.project.duties.tasksCarsLogs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.tasksCarsLogs.mapper.TasksCarsLogsMapper;
import com.ruoyi.project.duties.tasksCarsLogs.domain.TasksCarsLogs;
import com.ruoyi.project.duties.tasksCarsLogs.service.ITasksCarsLogsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 车辆行驶记录 服务层实现
 * 
 * @author admin
 * @date 2019-05-28
 */
@Service
public class TasksCarsLogsServiceImpl implements ITasksCarsLogsService 
{
	@Autowired
	private TasksCarsLogsMapper tasksCarsLogsMapper;

	/**
     * 查询车辆行驶记录信息
     * 
     * @param taskCarId 车辆行驶记录ID
     * @return 车辆行驶记录信息
     */
    @Override
	public TasksCarsLogs selectTasksCarsLogsById(Long taskCarId)
	{
	    return tasksCarsLogsMapper.selectTasksCarsLogsById(taskCarId);
	}
	
	/**
     * 查询车辆行驶记录列表
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 车辆行驶记录集合
     */
	@Override
	public List<TasksCarsLogs> selectTasksCarsLogsList(TasksCarsLogs tasksCarsLogs)
	{
	    return tasksCarsLogsMapper.selectTasksCarsLogsList(tasksCarsLogs);
	}
	
    /**
     * 新增车辆行驶记录
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 结果
     */
	@Override
	public int insertTasksCarsLogs(TasksCarsLogs tasksCarsLogs)
	{
	    return tasksCarsLogsMapper.insertTasksCarsLogs(tasksCarsLogs);
	}
	
	/**
     * 修改车辆行驶记录
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 结果
     */
	@Override
	public int updateTasksCarsLogs(TasksCarsLogs tasksCarsLogs)
	{
	    return tasksCarsLogsMapper.updateTasksCarsLogs(tasksCarsLogs);
	}

	/**
     * 删除车辆行驶记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTasksCarsLogsByIds(String ids)
	{
		return tasksCarsLogsMapper.deleteTasksCarsLogsByIds(Convert.toStrArray(ids));
	}
	
}
