package com.ruoyi.project.duties.tasks.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import com.ruoyi.project.system.dict.domain.DictData;

import java.util.List;
import java.util.Map;

/**
 * 任务单 服务层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface ITasksService 
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
     * 删除任务单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksByIds(String ids);

    AjaxResult updateTaskStatus(String id, String pause);

    Tasks selectOneToTask(Map<String, String> prams);

    int stopTasks(String id);

    List<DictData> selectCartypeList(String id);

    Profileroom selectProfileRoom(String id);

    List<Tasks> selectFirstTasksToDispath(Integer taskCntToDispath);

	List<Tasks> selectDispathList();

    int updateTopping(Map<String, String> prams);

    void updateTasksSyncstatusByNumber(String taskNumber);

    void Mixformula(String mixNumber, int productLine,Long taskId);

	List<Tasks> selectTasks4Dispath();
	List<Tasks> selectTaskNameList(Tasks tasks);

	List<Car> selectCarByTask(String taskId);


}
