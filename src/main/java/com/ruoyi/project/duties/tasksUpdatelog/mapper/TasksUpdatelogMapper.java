package com.ruoyi.project.duties.tasksUpdatelog.mapper;

import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import java.util.List;	

/**
 * 任务单修改记录 数据层
 * 
 * @author admin
 * @date 2019-06-14
 */
public interface TasksUpdatelogMapper 
{
	/**
     * 查询任务单修改记录信息
     * 
     * @param id 任务单修改记录ID
     * @return 任务单修改记录信息
     */
	public TasksUpdatelog selectTasksUpdatelogById(Long id);
	
	/**
     * 查询任务单修改记录列表
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 任务单修改记录集合
     */
	public List<TasksUpdatelog> selectTasksUpdatelogList(TasksUpdatelog tasksUpdatelog);
	
	/**
     * 新增任务单修改记录
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 结果
     */
	public int insertTasksUpdatelog(TasksUpdatelog tasksUpdatelog);
	
	/**
     * 修改任务单修改记录
     * 
     * @param tasksUpdatelog 任务单修改记录信息
     * @return 结果
     */
	public int updateTasksUpdatelog(TasksUpdatelog tasksUpdatelog);
	
	/**
     * 删除任务单修改记录
     * 
     * @param id 任务单修改记录ID
     * @return 结果
     */
	public int deleteTasksUpdatelogById(Long id);
	
	/**
     * 批量删除任务单修改记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksUpdatelogByIds(String[] ids);
	
}