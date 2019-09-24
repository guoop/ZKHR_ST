package com.ruoyi.project.cemslink.preproduction.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.preproduction.mapper.PreproductionMapper;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产指令数据 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class PreproductionServiceImpl implements IPreproductionService 
{
	@Autowired(required = false)
	private PreproductionMapper preproductionMapper;

	/**
     * 查询生产指令数据信息
     * 
     * @param sID 生产指令数据ID
     * @return 生产指令数据信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Preproduction selectPreproductionById(Long sID)
	{
	    return preproductionMapper.selectPreproductionById(sID);
	}
	
	/**
     * 查询生产指令数据列表
     * 
     * @param preproduction 生产指令数据信息
     * @return 生产指令数据集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Preproduction> selectPreproductionList(Preproduction preproduction)
	{
	    return preproductionMapper.selectPreproductionList(preproduction);
	}
	
    /**
     * 新增生产指令数据
     * 
     * @param preproduction 生产指令数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertPreproduction(Preproduction preproduction)
	{
	    return preproductionMapper.insertPreproduction(preproduction);
	}
	
	/**
     * 修改生产指令数据
     * 
     * @param preproduction 生产指令数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updatePreproduction(Preproduction preproduction)
	{
	    return preproductionMapper.updatePreproduction(preproduction);
	}

	/**
     * 删除生产指令数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deletePreproductionByIds(String ids)
	{
		return preproductionMapper.deletePreproductionByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<PreproductionQueue> getSortedPreproductionCars(String ids) {
		return preproductionMapper.getSortedPreproductionCars(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<PreproductionQueue> getAllSortedPreproductionCars() {
		return preproductionMapper.getAllSortedPreproductionCars();
	}

	@Override
    @DataSource(DataSourceType.SLAVE)
	public void updateMasterBuildingStatus() {
		preproductionMapper.updateMasterBuildingStatus();
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public Preproduction selectOneProductLine2do(List<Integer> productList) {
		return preproductionMapper.selectOneProductLine2do(productList);
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public Preproduction selectOneProducting() {
		return preproductionMapper.selectOneProducting();
	}

}
