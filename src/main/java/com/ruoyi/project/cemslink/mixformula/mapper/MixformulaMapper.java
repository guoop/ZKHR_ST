package com.ruoyi.project.cemslink.mixformula.mapper;

import com.ruoyi.project.cemslink.mixformula.domain.Mixformula;
import java.util.List;	

/**
 * 配合比数据 数据层
 * 
 * @author admin
 * @date 2019-05-31
 */
public interface MixformulaMapper 
{
	/**
     * 查询配合比数据信息
     * 
     * @param sID 配合比数据ID
     * @return 配合比数据信息
     */
	public Mixformula selectMixformulaById(Long sID);
	
	/**
     * 查询配合比数据列表
     * 
     * @param mixformula 配合比数据信息
     * @return 配合比数据集合
     */
	public List<Mixformula> selectMixformulaList(Mixformula mixformula);
	
	/**
     * 新增配合比数据
     * 
     * @param mixformula 配合比数据信息
     * @return 结果
     */
	public int insertMixformula(Mixformula mixformula);
	
	/**
     * 修改配合比数据
     * 
     * @param mixformula 配合比数据信息
     * @return 结果
     */
	public int updateMixformula(Mixformula mixformula);
	
	/**
     * 删除配合比数据
     * 
     * @param sID 配合比数据ID
     * @return 结果
     */
	public int deleteMixformulaById(Long sID);
	
	/**
     * 批量删除配合比数据
     * 
     * @param sIDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteMixformulaByIds(String[] sIDs);

    List<Mixformula> selectDistinctList();
}