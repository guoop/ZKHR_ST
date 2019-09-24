package com.ruoyi.project.cems.queue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cems.queue.mapper.QueueMapper;
import com.ruoyi.project.cems.queue.domain.Queue;
import com.ruoyi.project.cems.queue.service.IQueueService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 排队 服务层实现
 * 
 * @author admin
 * @date 2019-05-13
 */
@Service
public class QueueServiceImpl implements IQueueService 
{
	@Autowired
	private QueueMapper queueMapper;

	/**
     * 查询排队信息
     * 
     * @param id 排队ID
     * @return 排队信息
     */
    @Override
	public Queue selectQueueById(Long id)
	{
	    return queueMapper.selectQueueById(id);
	}
	
	/**
     * 查询排队列表
     * 
     * @param queue 排队信息
     * @return 排队集合
     */
	@Override
	public List<Queue> selectQueueList(Queue queue)
	{
	    return queueMapper.selectQueueList(queue);
	}
	
    /**
     * 新增排队
     * 
     * @param queue 排队信息
     * @return 结果
     */
	@Override
	public int insertQueue(Queue queue)
	{
	    return queueMapper.insertQueue(queue);
	}
	
	/**
     * 修改排队
     * 
     * @param queue 排队信息
     * @return 结果
     */
	@Override
	public int updateQueue(Queue queue)
	{
	    return queueMapper.updateQueue(queue);
	}

	/**
     * 删除排队对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteQueueByIds(String ids)
	{
		return queueMapper.deleteQueueByIds(Convert.toStrArray(ids));
	}
	
}
