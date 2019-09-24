package com.ruoyi.project.cemslink.consumption.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionReport;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.consumption.mapper.ConsumptionMapper;
import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.service.IConsumptionService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产消耗数据 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class ConsumptionServiceImpl implements IConsumptionService 
{
	@Autowired
	private ConsumptionMapper consumptionMapper;

	/**
     * 查询生产消耗数据信息
     * 
     * @param sID 生产消耗数据ID
     * @return 生产消耗数据信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Consumption selectConsumptionById(Long sID)
	{
	    return consumptionMapper.selectConsumptionById(sID);
	}
	
	/**
     * 查询生产消耗数据列表
     * 
     * @param consumption 生产消耗数据信息
     * @return 生产消耗数据集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Consumption> selectConsumptionList(Consumption consumption)
	{
	    return consumptionMapper.selectConsumptionList(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAGDesignDosage3Report(Consumption consumption) {
		return consumptionMapper.selectAGDesignDosage3Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAGDesignDosage2Report(Consumption consumption) {
		return consumptionMapper.selectAGDesignDosage2Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectMixDesignDosage1Report(Consumption consumption) {
		return consumptionMapper.selectMixDesignDosage1Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectMixDesignDosage2Report(Consumption consumption) {
		return consumptionMapper.selectMixDesignDosage2Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAGDesignDosage4Report(Consumption consumption) {
		return consumptionMapper.selectAGDesignDosage4Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAGDesignDosage5Report(Consumption consumption) {
		return consumptionMapper.selectAGDesignDosage5Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAGDesignDosage1Report(Consumption consumption) {
		return consumptionMapper.selectAGDesignDosage1Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectWaterDesignDosage1Report(Consumption consumption) {
		return consumptionMapper.selectWaterDesignDosage1Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectAdditiveDesignDosage4Report(Consumption consumption) {
		return consumptionMapper.selectAdditiveDesignDosage4Report(consumption);
	}

	@Override
	public List<ConsumptionReport> selectMixDesignDosage3Report(Consumption consumption) {
		return consumptionMapper.selectMixDesignDosage3Report(consumption);
	}

	/**
     * 新增生产消耗数据
     * 
     * @param consumption 生产消耗数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertConsumption(Consumption consumption)
	{
	    return consumptionMapper.insertConsumption(consumption);
	}
	
	/**
     * 修改生产消耗数据
     * 
     * @param consumption 生产消耗数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateConsumption(Consumption consumption)
	{
	    return consumptionMapper.updateConsumption(consumption);
	}

	/**
     * 删除生产消耗数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteConsumptionByIds(String ids)
	{
		return consumptionMapper.deleteConsumptionByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<ConsumptionReport> selectConsumptionReports(Consumption consumption) {
		return consumptionMapper.selectConsumptionReports(consumption);
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public ConsumptionVO selectConsumptionNow(Consumption consumption) {
		return consumptionMapper.selectConsumptionNow(consumption);
	}

}
