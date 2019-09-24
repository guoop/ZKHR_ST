package com.ruoyi.project.cemslink.preproduction.mapper;

import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;

import java.util.List;

/**
 * 生产指令数据 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface PreproductionMapper 
{
	/**
     * 查询生产指令数据信息
     * 
     * @param sID 生产指令数据ID
     * @return 生产指令数据信息
     */
	public Preproduction selectPreproductionById(Long sID);
	
	/**
     * 查询生产指令数据列表
     * 
     * @param preproduction 生产指令数据信息
     * @return 生产指令数据集合
     */
	public List<Preproduction> selectPreproductionList(Preproduction preproduction);
	
	/**
     * 新增生产指令数据
     * 
     * @param preproduction 生产指令数据信息
     * @return 结果
     */
	public int insertPreproduction(Preproduction preproduction);
	
	/**
     * 修改生产指令数据
     * 
     * @param preproduction 生产指令数据信息
     * @return 结果
     */
	public int updatePreproduction(Preproduction preproduction);
	
	/**
     * 删除生产指令数据
     * 
     * @param sID 生产指令数据ID
     * @return 结果
     */
	public int deletePreproductionById(Long sID);
	
	/**
     * 批量删除生产指令数据
     * 
     * @param sIDs 需要删除的数据ID
     * @return 结果
     */
	public int deletePreproductionByIds(String[] sIDs);

    List<PreproductionQueue> getSortedPreproductionCars(String[] ProductLines);

    List<PreproductionQueue> getAllSortedPreproductionCars();

    void updateMasterBuildingStatus();

	Preproduction selectOneProductLine2do(List<Integer> productList);

    Preproduction selectOneProducting();
}