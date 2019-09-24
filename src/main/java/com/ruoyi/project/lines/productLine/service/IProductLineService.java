package com.ruoyi.project.lines.productLine.service;

import com.ruoyi.project.lines.productLine.domain.ProductLine;
import java.util.List;

/**
 * 产线 服务层
 * 
 * @author admin
 * @date 2019-06-02
 */
public interface IProductLineService 
{
	/**
     * 查询产线信息
     * 
     * @param id 产线ID
     * @return 产线信息
     */
	public ProductLine selectProductLineById(Long id);
	
	/**
     * 查询产线列表
     * 
     * @param productLine 产线信息
     * @return 产线集合
     */
	public List<ProductLine> selectProductLineList(ProductLine productLine);
	
	/**
     * 新增产线
     * 
     * @param productLine 产线信息
     * @return 结果
     */
	public int insertProductLine(ProductLine productLine);
	
	/**
     * 修改产线
     * 
     * @param productLine 产线信息
     * @return 结果
     */
	public int updateProductLine(ProductLine productLine);
		
	/**
     * 删除产线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductLineByIds(String ids);
	
}
