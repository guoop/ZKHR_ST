package com.ruoyi.project.duties.tasksCars.service;

import com.ruoyi.api.domain.ApiTasksCars;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.domain.TasksCarsReport;
import com.ruoyi.project.system.user.domain.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * 任务车辆 服务层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface ITasksCarsService 
{
	/**
     * 查询任务车辆信息
     * 
     * @param id 任务车辆ID
     * @return 任务车辆信息
     */
	public TasksCars selectTasksCarsById(Long id);
	
	/**
     * 查询任务车辆列表
     * 
     * @param tasksCars 任务车辆信息
     * @return 任务车辆集合
     */
	public List<TasksCars> selectTasksCarsList(TasksCars tasksCars);

	/**
     * 新增任务车辆
     * 
     * @param tasksCars 任务车辆信息
     * @return 结果
     */
	public int insertTasksCars(TasksCars tasksCars);
	
	/**
     * 修改任务车辆
     * 
     * @param tasksCars 任务车辆信息
     * @return 结果
     */
	public int updateTasksCars(TasksCars tasksCars);
		
	/**
     * 删除任务车辆信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksCarsByIds(String ids);

	BigDecimal queryCurFliangBytask(String taskId);

    TasksCars selectOneGoodCar(List<Long> ids);

	TasksCars selectOneBadCar(List<Long> ids);

	AjaxResult removeOne(Long id, User user);

    AjaxResult arrived(Long id, User sysUser);

	List<TasksCars> selectTodayLog(List<Long> ids);

    List<TasksCarsReport> selectTaskCarsReport(TasksCars tasksCarsReport);
}
