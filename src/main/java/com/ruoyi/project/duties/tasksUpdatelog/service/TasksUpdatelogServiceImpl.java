package com.ruoyi.project.duties.tasksUpdatelog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.tasksUpdatelog.mapper.TasksUpdatelogMapper;
import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import com.ruoyi.project.duties.tasksUpdatelog.service.ITasksUpdatelogService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 任务单修改记录 服务层实现
 * 
 * @author admin
 * @date 2019-06-14
 */
@Service
public class TasksUpdatelogServiceImpl implements ITasksUpdatelogService 
{
	@Autowired
	private TasksUpdatelogMapper tasksUpdatelogMapper;

	/**
     * 查询任务单修改记录信息
     * 
     * @param id 任务单修改记录ID
     * @return 任务单修改记录信息
     */
    @Override
	public TasksUpdatelog selectTasksUpdatelogById(Long id)
	{
	    return tasksUpdatelogMapper.selectTasksUpdatelogById(id);
	}
	
	/**
     * 查询任务单修改记录列表
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 任务单修改记录集合
     */
	@Override
	public List<TasksUpdatelog> selectTasksUpdatelogList(TasksUpdatelog tasksUpdatelog)
	{
	    return tasksUpdatelogMapper.selectTasksUpdatelogList(tasksUpdatelog);
	}
	
    /**
     * 新增任务单修改记录
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 结果
     */
	@Override
	public int insertTasksUpdatelog(TasksUpdatelog tasksUpdatelog)
	{
	    return tasksUpdatelogMapper.insertTasksUpdatelog(tasksUpdatelog);
	}
	
	/**
     * 修改任务单修改记录
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 结果
     */
	@Override
	public int updateTasksUpdatelog(TasksUpdatelog tasksUpdatelog)
	{
	    return tasksUpdatelogMapper.updateTasksUpdatelog(tasksUpdatelog);
	}

	/**
     * 删除任务单修改记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTasksUpdatelogByIds(String ids)
	{
		return tasksUpdatelogMapper.deleteTasksUpdatelogByIds(Convert.toStrArray(ids));
	}
	
}
