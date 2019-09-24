package com.ruoyi.project.duties.tasksDispather.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.tasksDispather.mapper.TasksDispatherMapper;
import com.ruoyi.project.duties.tasksDispather.domain.TasksDispather;
import com.ruoyi.project.duties.tasksDispather.service.ITasksDispatherService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 调度室 服务层实现
 * 
 * @author admin
 * @date 2019-04-25
 */
@Service
public class TasksDispatherServiceImpl implements ITasksDispatherService 
{
	@Autowired
	private TasksDispatherMapper tasksDispatherMapper;

	/**
     * 查询调度室信息
     * 
     * @param id 调度室ID
     * @return 调度室信息
     */
    @Override
	public TasksDispather selectTasksDispatherById(Long id)
	{
	    return tasksDispatherMapper.selectTasksDispatherById(id);
	}
	
	/**
     * 查询调度室列表
     * 
     * @param tasksDispather 调度室信息
     * @return 调度室集合
     */
	@Override
	public List<TasksDispather> selectTasksDispatherList(TasksDispather tasksDispather)
	{
	    return tasksDispatherMapper.selectTasksDispatherList(tasksDispather);
	}
	
    /**
     * 新增调度室
     * 
     * @param tasksDispather 调度室信息
     * @return 结果
     */
	@Override
	public int insertTasksDispather(TasksDispather tasksDispather)
	{
	    return tasksDispatherMapper.insertTasksDispather(tasksDispather);
	}
	
	/**
     * 修改调度室
     * 
     * @param tasksDispather 调度室信息
     * @return 结果
     */
	@Override
	public int updateTasksDispather(TasksDispather tasksDispather)
	{
	    return tasksDispatherMapper.updateTasksDispather(tasksDispather);
	}

	/**
     * 删除调度室对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTasksDispatherByIds(String ids)
	{
		return tasksDispatherMapper.deleteTasksDispatherByIds(Convert.toStrArray(ids));
	}
	
}
