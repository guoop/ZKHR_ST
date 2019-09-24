package com.ruoyi.project.cars.tasksMixformula.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.tasksMixformula.mapper.TasksMixformulaMapper;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 任务配比产线关联 服务层实现
 * 
 * @author admin
 * @date 2019-07-04
 */
@Service
public class TasksMixformulaServiceImpl implements ITasksMixformulaService 
{
	@Autowired
	private TasksMixformulaMapper tasksMixformulaMapper;

	/**
     * 查询任务配比产线关联信息
     * 
     * @param id 任务配比产线关联ID
     * @return 任务配比产线关联信息
     */
    @Override
	public TasksMixformula selectTasksMixformulaById(Long id)
	{
	    return tasksMixformulaMapper.selectTasksMixformulaById(id);
	}
	
	/**
     * 查询任务配比产线关联列表
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 任务配比产线关联集合
     */
	@Override
	public List<TasksMixformula> selectTasksMixformulaList(TasksMixformula tasksMixformula)
	{
	    return tasksMixformulaMapper.selectTasksMixformulaList(tasksMixformula);
	}
	
    /**
     * 新增任务配比产线关联
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 结果
     */
	@Override
	public int insertTasksMixformula(TasksMixformula tasksMixformula)
	{
	    return tasksMixformulaMapper.insertTasksMixformula(tasksMixformula);
	}
	
	/**
     * 修改任务配比产线关联
     * 
     * @param tasksMixformula 任务配比产线关联信息
     * @return 结果
     */
	@Override
	public int updateTasksMixformula(TasksMixformula tasksMixformula)
	{
	    return tasksMixformulaMapper.updateTasksMixformula(tasksMixformula);
	}

	/**
     * 删除任务配比产线关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTasksMixformulaByIds(String ids)
	{
		return tasksMixformulaMapper.deleteTasksMixformulaByIds(Convert.toStrArray(ids));
	}

	@Override
	public TasksMixformula selectOne(TasksMixformula tasksMixformula) {
		return tasksMixformulaMapper.selectOne(tasksMixformula);
	}

	@Override
	public void updateTasksMixformulaMap(Map param) {
		tasksMixformulaMapper.updateTasksMixformulaMap(param);
	}

}
