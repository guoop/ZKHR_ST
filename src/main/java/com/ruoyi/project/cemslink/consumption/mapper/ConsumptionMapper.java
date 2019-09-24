package com.ruoyi.project.cemslink.consumption.mapper;

import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionReport;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;

import java.util.List;

/**
 * 生产消耗数据 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface ConsumptionMapper 
{
	/**
     * 查询生产消耗数据信息
     * 
     * @param sID 生产消耗数据ID
     * @return 生产消耗数据信息
     */
	public Consumption selectConsumptionById(Long sID);
	
	/**
     * 查询生产消耗数据列表
     * 
     * @param consumption 生产消耗数据信息
     * @return 生产消耗数据集合
     */
	public List<Consumption> selectConsumptionList(Consumption consumption);
	
	/**
     * 新增生产消耗数据
     * 
     * @param consumption 生产消耗数据信息
     * @return 结果
     */
	public int insertConsumption(Consumption consumption);
	
	/**
     * 修改生产消耗数据
     * 
     * @param consumption 生产消耗数据信息
     * @return 结果
     */
	public int updateConsumption(Consumption consumption);
	
	/**
     * 删除生产消耗数据
     * 
     * @param sID 生产消耗数据ID
     * @return 结果
     */
	public int deleteConsumptionById(Long sID);
	
	/**
     * 批量删除生产消耗数据
     * 
     * @param sIDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteConsumptionByIds(String[] sIDs);

    List<ConsumptionReport> selectAGDesignDosage3Report(Consumption consumption);

	List<ConsumptionReport> selectAGDesignDosage2Report(Consumption consumption);

	List<ConsumptionReport> selectMixDesignDosage1Report(Consumption consumption);

	List<ConsumptionReport> selectMixDesignDosage2Report(Consumption consumption);

	List<ConsumptionReport> selectAGDesignDosage4Report(Consumption consumption);

	List<ConsumptionReport> selectAGDesignDosage5Report(Consumption consumption);

	List<ConsumptionReport> selectAGDesignDosage1Report(Consumption consumption);

	List<ConsumptionReport> selectWaterDesignDosage1Report(Consumption consumption);

	List<ConsumptionReport> selectAdditiveDesignDosage4Report(Consumption consumption);

	List<ConsumptionReport> selectMixDesignDosage3Report(Consumption consumption);

	List<ConsumptionReport> selectConsumptionReports(Consumption consumption);

    ConsumptionVO selectConsumptionNow(Consumption consumption);
}