package com.ruoyi.project.cemslink.task.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.task.mapper.TaskMapper;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.cemslink.task.service.ITaskService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 任务单数据 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class TaskServiceImpl implements ITaskService 
{
	@Autowired
	private TaskMapper taskMapper;

	/**
     * 查询任务单数据信息
     * 
     * @param sID 任务单数据ID
     * @return 任务单数据信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Task selectTaskById(Long sID)
	{
	    return taskMapper.selectTaskById(sID);
	}
	
	/**
     * 查询任务单数据列表
     * 
     * @param task 任务单数据信息
     * @return 任务单数据集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Task> selectTaskList(Task task)
	{
	    return taskMapper.selectTaskList(task);
	}
	
    /**
     * 新增任务单数据
     * 
     * @param task 任务单数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertTask(Task task)
	{
	    return taskMapper.insertTask(task);
	}
	
	/**
     * 修改任务单数据
     * 
     * @param task 任务单数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateTask(Task task)
	{
	    return taskMapper.updateTask(task);
	}

	/**
     * 删除任务单数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteTaskByIds(String ids)
	{
		return taskMapper.deleteTaskByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public Task checkExistTask(Map<String,String> para) {
		return taskMapper.checkExistTask(para);
	}

}
