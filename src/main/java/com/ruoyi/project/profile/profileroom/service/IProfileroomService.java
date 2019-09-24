package com.ruoyi.project.profile.profileroom.service;

import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import java.util.List;

/**
 * 任务资料 服务层
 * 
 * @author admin
 * @date 2019-05-30
 */
public interface IProfileroomService 
{
	/**
     * 查询任务资料信息
     * 
     * @param id 任务资料ID
     * @return 任务资料信息
     */
	public Profileroom selectProfileroomById(Long id);
	
	/**
     * 查询任务资料列表
     * 
     * @param profileroom 任务资料信息
     * @return 任务资料集合
     */
	public List<Profileroom> selectProfileroomList(Profileroom profileroom);
	
	/**
     * 新增任务资料
     * 
     * @param profileroom 任务资料信息
     * @return 结果
     */
	public int insertProfileroom(Profileroom profileroom);
	
	/**
     * 修改任务资料
     * 
     * @param profileroom 任务资料信息
     * @return 结果
     */
	public int updateProfileroom(Profileroom profileroom);
		
	/**
     * 删除任务资料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProfileroomByIds(String ids);
	
}
