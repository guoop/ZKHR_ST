package com.ruoyi.project.cemslink.task.mapper;

import com.ruoyi.project.cemslink.task.domain.Task;
import java.util.List;
import java.util.Map;

/**
 * 任务单数据 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface TaskMapper 
{
	/**
     * 查询任务单数据信息
     * 
     * @param sID 任务单数据ID
     * @return 任务单数据信息
     */
	public Task selectTaskById(Long sID);
	
	/**
     * 查询任务单数据列表
     * 
     * @param task 任务单数据信息
     * @return 任务单数据集合
     */
	public List<Task> selectTaskList(Task task);
	
	/**
     * 新增任务单数据
     * 
     * @param task 任务单数据信息
     * @return 结果
     */
	public int insertTask(Task task);
	
	/**
     * 修改任务单数据
     * 
     * @param task 任务单数据信息
     * @return 结果
     */
	public int updateTask(Task task);
	
	/**
     * 删除任务单数据
     * 
     * @param sID 任务单数据ID
     * @return 结果
     */
	public int deleteTaskById(Long sID);
	
	/**
     * 批量删除任务单数据
     * 
     * @param sIDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaskByIds(String[] sIDs);

    Task checkExistTask(Map<String,String> para);
}