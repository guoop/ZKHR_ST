package com.ruoyi.project.cems.queue.service;

import com.ruoyi.project.cems.queue.domain.Queue;
import java.util.List;

/**
 * 排队 服务层
 * 
 * @author admin
 * @date 2019-05-13
 */
public interface IQueueService 
{
	/**
     * 查询排队信息
     * 
     * @param id 排队ID
     * @return 排队信息
     */
	public Queue selectQueueById(Long id);
	
	/**
     * 查询排队列表
     * 
     * @param queue 排队信息
     * @return 排队集合
     */
	public List<Queue> selectQueueList(Queue queue);
	
	/**
     * 新增排队
     * 
     * @param queue 排队信息
     * @return 结果
     */
	public int insertQueue(Queue queue);
	
	/**
     * 修改排队
     * 
     * @param queue 排队信息
     * @return 结果
     */
	public int updateQueue(Queue queue);
		
	/**
     * 删除排队信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteQueueByIds(String ids);
	
}
