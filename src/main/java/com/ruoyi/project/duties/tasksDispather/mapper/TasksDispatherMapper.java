package com.ruoyi.project.duties.tasksDispather.mapper;

import com.ruoyi.project.duties.tasksDispather.domain.TasksDispather;
import java.util.List;	

/**
 * 调度室 数据层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface TasksDispatherMapper 
{
	/**
     * 查询调度室信息
     * 
     * @param id 调度室ID
     * @return 调度室信息
     */
	public TasksDispather selectTasksDispatherById(Long id);
	
	/**
     * 查询调度室列表
     * 
     * @param tasksDispather 调度室信息
     * @return 调度室集合
     */
	public List<TasksDispather> selectTasksDispatherList(TasksDispather tasksDispather);
	
	/**
     * 新增调度室
     * 
     * @param tasksDispather 调度室信息
     * @return 结果
     */
	public int insertTasksDispather(TasksDispather tasksDispather);
	
	/**
     * 修改调度室
     * 
     * @param tasksDispather 调度室信息
     * @return 结果
     */
	public int updateTasksDispather(TasksDispather tasksDispather);
	
	/**
     * 删除调度室
     * 
     * @param id 调度室ID
     * @return 结果
     */
	public int deleteTasksDispatherById(Long id);
	
	/**
     * 批量删除调度室
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksDispatherByIds(String[] ids);
	
}