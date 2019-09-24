package com.ruoyi.project.duties.taskNotification.mapper;

import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import java.util.List;	

/**
 * 消息 数据层
 * 
 * @author admin
 * @date 2019-04-29
 */
public interface TaskNotificationMapper 
{
	/**
     * 查询消息信息
     * 
     * @param id 消息ID
     * @return 消息信息
     */
	public TaskNotification selectTaskNotificationById(Long id);
	
	/**
     * 查询消息列表
     * 
     * @param taskNotification 消息信息
     * @return 消息集合
     */
	public List<TaskNotification> selectTaskNotificationList(TaskNotification taskNotification);
	
	/**
     * 新增消息
     * 
     * @param taskNotification 消息信息
     * @return 结果
     */
	public int insertTaskNotification(TaskNotification taskNotification);
	
	/**
     * 修改消息
     * 
     * @param taskNotification 消息信息
     * @return 结果
     */
	public int updateTaskNotification(TaskNotification taskNotification);
	
	/**
     * 删除消息
     * 
     * @param id 消息ID
     * @return 结果
     */
	public int deleteTaskNotificationById(Long id);
	
	/**
     * 批量删除消息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaskNotificationByIds(String[] ids);
	
}