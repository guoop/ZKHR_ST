package com.ruoyi.project.cemslink.mixformula.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cemslink.mixformula.mapper.MixformulaMapper;
import com.ruoyi.project.cemslink.mixformula.domain.Mixformula;
import com.ruoyi.project.cemslink.mixformula.service.IMixformulaService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 配合比数据 服务层实现
 * 
 * @author admin
 * @date 2019-05-31
 */
@Service
public class MixformulaServiceImpl implements IMixformulaService 
{
	@Autowired
	private MixformulaMapper mixformulaMapper;

	/**
     * 查询配合比数据信息
     * 
     * @param sID 配合比数据ID
     * @return 配合比数据信息
     */
    @Override
	@DataSource(DataSourceType.SLAVE)
	public Mixformula selectMixformulaById(Long sID)
	{
	    return mixformulaMapper.selectMixformulaById(sID);
	}
	
	/**
     * 查询配合比数据列表
     * 
     * @param mixformula 配合比数据信息
     * @return 配合比数据集合
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Mixformula> selectMixformulaList(Mixformula mixformula)
	{
	    return mixformulaMapper.selectMixformulaList(mixformula);
	}
	
    /**
     * 新增配合比数据
     * 
     * @param mixformula 配合比数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int insertMixformula(Mixformula mixformula)
	{
	    return mixformulaMapper.insertMixformula(mixformula);
	}
	
	/**
     * 修改配合比数据
     * 
     * @param mixformula 配合比数据信息
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int updateMixformula(Mixformula mixformula)
	{
	    return mixformulaMapper.updateMixformula(mixformula);
	}

	/**
     * 删除配合比数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(DataSourceType.SLAVE)
	public int deleteMixformulaByIds(String ids)
	{
		return mixformulaMapper.deleteMixformulaByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SLAVE)
	public List<Mixformula> selectDistinctList() {
		return mixformulaMapper.selectDistinctList();
	}

}
