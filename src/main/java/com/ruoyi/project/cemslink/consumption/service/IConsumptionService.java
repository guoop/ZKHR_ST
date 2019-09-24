package com.ruoyi.project.cemslink.consumption.service;

import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionReport;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;

import java.util.List;

/**
 * 生产消耗数据 服务层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface IConsumptionService 
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
	 * 根据条件查询0-5石子报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAGDesignDosage3Report(Consumption consumption);

	/**
	 * 根据条件查询1-2石子报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAGDesignDosage2Report(Consumption consumption);


	/**
	 * 根据条件查询粉煤灰石子报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectMixDesignDosage1Report(Consumption consumption);


	/**
	 * 根据条件查询矿粉报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectMixDesignDosage2Report(Consumption consumption);


	/**
	 * 根据条件查询[米石]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAGDesignDosage4Report(Consumption consumption);


	/**
	 * 根据条件查询[面沙]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAGDesignDosage5Report(Consumption consumption);

	/**
	 * 根据条件查询[石粉]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAGDesignDosage1Report(Consumption consumption);

	/**
	 * 根据条件查询[水M1]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectWaterDesignDosage1Report(Consumption consumption);

	/**
	 * 根据条件查询[减胶剂2]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectAdditiveDesignDosage4Report(Consumption consumption);

	/**
	 * 根据条件查询[污水]报表
	 *
	 * @param consumption 生产消耗数据信息
	 * @return 报表数据
	 */
	public List<ConsumptionReport> selectMixDesignDosage3Report(Consumption consumption);
	
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
     * 删除生产消耗数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteConsumptionByIds(String ids);

	public List<ConsumptionReport> selectConsumptionReports(Consumption consumption);

    ConsumptionVO selectConsumptionNow(Consumption consumption);
}
