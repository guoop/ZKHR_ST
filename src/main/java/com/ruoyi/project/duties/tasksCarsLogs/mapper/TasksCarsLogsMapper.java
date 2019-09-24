package com.ruoyi.project.duties.tasksCarsLogs.mapper;

import com.ruoyi.project.duties.tasksCarsLogs.domain.TasksCarsLogs;
import java.util.List;	

/**
 * 车辆行驶记录 数据层
 * 
 * @author admin
 * @date 2019-05-28
 */
public interface TasksCarsLogsMapper 
{
	/**
     * 查询车辆行驶记录信息
     * 
     * @param taskCarId 车辆行驶记录ID
     * @return 车辆行驶记录信息
     */
	public TasksCarsLogs selectTasksCarsLogsById(Long taskCarId);
	
	/**
     * 查询车辆行驶记录列表
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 车辆行驶记录集合
     */
	public List<TasksCarsLogs> selectTasksCarsLogsList(TasksCarsLogs tasksCarsLogs);
	
	/**
     * 新增车辆行驶记录
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 结果
     */
	public int insertTasksCarsLogs(TasksCarsLogs tasksCarsLogs);
	
	/**
     * 修改车辆行驶记录
     * 
     * @param tasksCarsLogs 车辆行驶记录信息
     * @return 结果
     */
	public int updateTasksCarsLogs(TasksCarsLogs tasksCarsLogs);
	
	/**
     * 删除车辆行驶记录
     * 
     * @param taskCarId 车辆行驶记录ID
     * @return 结果
     */
	public int deleteTasksCarsLogsById(Long taskCarId);
	
	/**
     * 批量删除车辆行驶记录
     * 
     * @param taskCarIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksCarsLogsByIds(String[] taskCarIds);
	
}