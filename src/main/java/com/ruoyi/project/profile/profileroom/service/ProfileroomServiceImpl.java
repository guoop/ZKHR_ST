package com.ruoyi.project.profile.profileroom.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.profile.profileroom.mapper.ProfileroomMapper;
import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import com.ruoyi.project.profile.profileroom.service.IProfileroomService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 任务资料 服务层实现
 * 
 * @author admin
 * @date 2019-05-30
 */
@Service
public class ProfileroomServiceImpl implements IProfileroomService 
{
	@Autowired
	private ProfileroomMapper profileroomMapper;

	/**
     * 查询任务资料信息
     * 
     * @param id 任务资料ID
     * @return 任务资料信息
     */
    @Override
	public Profileroom selectProfileroomById(Long id)
	{
	    return profileroomMapper.selectProfileroomById(id);
	}
	
	/**
     * 查询任务资料列表
     * 
     * @param profileroom 任务资料信息
     * @return 任务资料集合
     */
	@Override
	public List<Profileroom> selectProfileroomList(Profileroom profileroom)
	{
	    return profileroomMapper.selectProfileroomList(profileroom);
	}
	
    /**
     * 新增任务资料
     * 
     * @param profileroom 任务资料信息
     * @return 结果
     */
	@Override
	public int insertProfileroom(Profileroom profileroom)
	{
	    return profileroomMapper.insertProfileroom(profileroom);
	}
	
	/**
     * 修改任务资料
     * 
     * @param profileroom 任务资料信息
     * @return 结果
     */
	@Override
	public int updateProfileroom(Profileroom profileroom)
	{
	    return profileroomMapper.updateProfileroom(profileroom);
	}

	/**
     * 删除任务资料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProfileroomByIds(String ids)
	{
		return profileroomMapper.deleteProfileroomByIds(Convert.toStrArray(ids));
	}
	
}
