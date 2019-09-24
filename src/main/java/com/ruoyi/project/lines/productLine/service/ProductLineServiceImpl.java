package com.ruoyi.project.lines.productLine.service;

import java.util.List;

import com.ruoyi.api.RedisDomainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.lines.productLine.mapper.ProductLineMapper;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.lines.productLine.service.IProductLineService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 产线 服务层实现
 * 
 * @author admin
 * @date 2019-06-02
 */
@Service
public class ProductLineServiceImpl implements IProductLineService 
{
	@Autowired
	private ProductLineMapper productLineMapper;


	/**
     * 查询产线信息
     * 
     * @param id 产线ID
     * @return 产线信息
     */
    @Override
	public ProductLine selectProductLineById(Long id)
	{
	    return productLineMapper.selectProductLineById(id);
	}
	
	/**
     * 查询产线列表
     * 
     * @param productLine 产线信息
     * @return 产线集合
     */
	@Override
	public List<ProductLine> selectProductLineList(ProductLine productLine)
	{
	    return productLineMapper.selectProductLineList(productLine);
	}
	
    /**
     * 新增产线
     * 
     * @param productLine 产线信息
     * @return 结果
     */
	@Override
	public int insertProductLine(ProductLine productLine)
	{
		int ret = productLineMapper.insertProductLine(productLine);
		RedisDomainUtils.setRedisProductLineDomain(productLine);
	    return ret;
	}
	
	/**
     * 修改产线
     * 
     * @param productLine 产线信息
     * @return 结果
     */
	@Override
	public int updateProductLine(ProductLine productLine)
	{
		int ret = productLineMapper.updateProductLine(productLine);
		productLine = productLineMapper.selectProductLineById(productLine.getId());
		RedisDomainUtils.setRedisProductLineDomain(productLine);
	    return ret;
	}

	/**
     * 删除产线对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductLineByIds(String ids)
	{
		return productLineMapper.deleteProductLineByIds(Convert.toStrArray(ids));
	}
	
}
