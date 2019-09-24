package com.ruoyi.project.duties.taskNotification.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.taskNotification.mapper.TaskNotificationMapper;
import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import com.ruoyi.project.duties.taskNotification.service.ITaskNotificationService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 消息 服务层实现
 * 
 * @author admin
 * @date 2019-04-29
 */
@Service
public class TaskNotificationServiceImpl implements ITaskNotificationService 
{
	@Autowired
	private TaskNotificationMapper taskNotificationMapper;

	/**
     * 查询消息信息
     * 
     * @param id 消息ID
     * @return 消息信息
     */
    @Override
	public TaskNotification selectTaskNotificationById(Long id)
	{
	    return taskNotificationMapper.selectTaskNotificationById(id);
	}
	
	/**
     * 查询消息列表
     * 
     * @param taskNotification 消息信息
     * @return 消息集合
     */
	@Override
	public List<TaskNotification> selectTaskNotificationList(TaskNotification taskNotification)
	{
	    return taskNotificationMapper.selectTaskNotificationList(taskNotification);
	}
	
    /**
     * 新增消息
     * 
     * @param taskNotification 消息信息
     * @return 结果
     */
	@Override
	public int insertTaskNotification(TaskNotification taskNotification)
	{
	    return taskNotificationMapper.insertTaskNotification(taskNotification);
	}
	
	/**
     * 修改消息
     * 
     * @param taskNotification 消息信息
     * @return 结果
     */
	@Override
	public int updateTaskNotification(TaskNotification taskNotification)
	{
	    return taskNotificationMapper.updateTaskNotification(taskNotification);
	}

	/**
     * 删除消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTaskNotificationByIds(String ids)
	{
		return taskNotificationMapper.deleteTaskNotificationByIds(Convert.toStrArray(ids));
	}
	
}
