package com.ruoyi.project.duties.tasks.mapper;

import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.profile.profileroom.domain.Profileroom;

import java.util.List;
import java.util.Map;

/**
 * 任务单 数据层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface TasksMapper 
{
	/**
     * 查询任务单信息
     * 
     * @param id 任务单ID
     * @return 任务单信息
     */
	public Tasks selectTasksById(String id);
	
	/**
     * 查询任务单列表
     * 
     * @param tasks 任务单信息
     * @return 任务单集合
     */
	public List<Tasks> selectTasksList(Tasks tasks);
	
	/**
     * 新增任务单
     * 
     * @param tasks 任务单信息
     * @return 结果
     */
	public int insertTasks(Tasks tasks);
	
	/**
     * 修改任务单
     * 
     * @param tasks 任务单信息
     * @return 结果
     */
	public int updateTasks(Tasks tasks);
	
	/**
     * 删除任务单
     * 
     * @param id 任务单ID
     * @return 结果
     */
	public int deleteTasksById(String id);
	
	/**
     * 批量删除任务单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksByIds(String[] ids);

    Tasks selectOneToTask(Map<String, String> prams);

    Profileroom selectProfileRoom(String id);

    List<Tasks> selectFirstTasksToDispath(Integer taskCntToDispath);

    List<Tasks> selectDispathList();

	int updateTopping(Map<String, String> prams);

	void updateTasksSyncstatusByNumber(String taskNumber);

	List<Tasks> selectTasks4Dispath();

    List<Tasks> selectTaskNameList(Tasks tasks);

	List<Car> selectCarByTask(String taskId);
}