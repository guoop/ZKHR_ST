package com.ruoyi.project.stock.sendCementlevel.mapper;

import com.ruoyi.project.stock.sendCementlevel.domain.SendCementlevel;
import java.util.List;	

/**
 * 水泥标号 数据层
 * 
 * @author admin
 * @date 2019-08-14
 */
public interface SendCementlevelMapper 
{
	/**
     * 查询水泥标号信息
     * 
     * @param id 水泥标号ID
     * @return 水泥标号信息
     */
	public SendCementlevel selectSendCementlevelById(Long id);
	
	/**
     * 查询水泥标号列表
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 水泥标号集合
     */
	public List<SendCementlevel> selectSendCementlevelList(SendCementlevel sendCementlevel);
	
	/**
     * 新增水泥标号
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 结果
     */
	public int insertSendCementlevel(SendCementlevel sendCementlevel);
	
	/**
     * 修改水泥标号
     * 
     * @param sendCementlevel 水泥标号信息
     * @return 结果
     */
	public int updateSendCementlevel(SendCementlevel sendCementlevel);
	
	/**
     * 删除水泥标号
     * 
     * @param id 水泥标号ID
     * @return 结果
     */
	public int deleteSendCementlevelById(Long id);
	
	/**
     * 批量删除水泥标号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSendCementlevelByIds(String[] ids);
	
}