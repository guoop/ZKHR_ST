package com.ruoyi.project.cars.tasksMixformula.mapper;

import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import java.util.List;
import java.util.Map;

/**
 * 任务配比产线关联 数据层
 * 
 * @author admin
 * @date 2019-07-04
 */
public interface TasksMixformulaMapper 
{
	/**
     * 查询任务配比产线关联信息
     * 
     * @param id 任务配比产线关联ID
     * @return 任务配比产线关联信息
     */
	public TasksMixformula selectTasksMixformulaById(Long id);
	
	/**
     * 查询任务配比产线关联列表
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 任务配比产线关联集合
     */
	public List<TasksMixformula> selectTasksMixformulaList(TasksMixformula tasksMixformula);
	
	/**
     * 新增任务配比产线关联
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 结果
     */
	public int insertTasksMixformula(TasksMixformula tasksMixformula);
	
	/**
     * 修改任务配比产线关联
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 结果
     */
	public int updateTasksMixformula(TasksMixformula tasksMixformula);
	
	/**
     * 删除任务配比产线关联
     * 
     * @param id 任务配比产线关联ID
     * @return 结果
     */
	public int deleteTasksMixformulaById(Long id);
	
	/**
     * 批量删除任务配比产线关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTasksMixformulaByIds(String[] ids);

    TasksMixformula selectOne(TasksMixformula tasksMixformula);

	void updateTasksMixformulaMap(Map param);
}