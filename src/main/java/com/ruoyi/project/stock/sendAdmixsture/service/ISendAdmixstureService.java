package com.ruoyi.project.stock.sendAdmixsture.service;

import com.ruoyi.project.stock.sendAdmixsture.domain.SendAdmixsture;
import java.util.List;

/**
 * 外加剂 服务层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface ISendAdmixstureService 
{
	/**
     * 查询外加剂信息
     * 
     * @param id 外加剂ID
     * @return 外加剂信息
     */
	public SendAdmixsture selectSendAdmixstureById(Long id);
	
	/**
     * 查询外加剂列表
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 外加剂集合
     */
	public List<SendAdmixsture> selectSendAdmixstureList(SendAdmixsture sendAdmixsture);
	
	/**
     * 新增外加剂
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 结果
     */
	public int insertSendAdmixsture(SendAdmixsture sendAdmixsture);
	
	/**
     * 修改外加剂
     * 
     * @param sendAdmixsture 外加剂信息
     * @return 结果
     */
	public int updateSendAdmixsture(SendAdmixsture sendAdmixsture);
		
	/**
     * 删除外加剂信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendAdmixstureByIds(String ids);
	
}
