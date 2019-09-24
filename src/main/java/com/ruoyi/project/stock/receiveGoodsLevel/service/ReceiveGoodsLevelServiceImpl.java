package com.ruoyi.project.stock.receiveGoodsLevel.service;

import java.util.List;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveGoodsLevel.mapper.ReceiveGoodsLevelMapper;
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import com.ruoyi.project.stock.receiveGoodsLevel.service.IReceiveGoodsLevelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 货品等级 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveGoodsLevelServiceImpl implements IReceiveGoodsLevelService 
{
	@Autowired
	private ReceiveGoodsLevelMapper receiveGoodsLevelMapper;

	/**
     * 查询货品等级信息
     * 
     * @param id 货品等级ID
     * @return 货品等级信息
     */
    @Override
	public ReceiveGoodsLevel selectReceiveGoodsLevelById(Long id)
	{
	    return receiveGoodsLevelMapper.selectReceiveGoodsLevelById(id);
	}
	
	/**
     * 查询货品等级列表
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 货品等级集合
     */
	@Override
	public List<ReceiveGoodsLevel> selectReceiveGoodsLevelList(ReceiveGoodsLevel receiveGoodsLevel)
	{
	    return receiveGoodsLevelMapper.selectReceiveGoodsLevelList(receiveGoodsLevel);
	}
	
    /**
     * 新增货品等级
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 结果
     */
	@Override
	public int insertReceiveGoodsLevel(ReceiveGoodsLevel receiveGoodsLevel)
	{
	    return receiveGoodsLevelMapper.insertReceiveGoodsLevel(receiveGoodsLevel);
	}
	
	/**
     * 修改货品等级
     * 
     * @param receiveGoodsLevel 货品等级信息
     * @return 结果
     */
	@Override
	public int updateReceiveGoodsLevel(ReceiveGoodsLevel receiveGoodsLevel)
	{
	    return receiveGoodsLevelMapper.updateReceiveGoodsLevel(receiveGoodsLevel);
	}

	/**
     * 删除货品等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveGoodsLevelByIds(String ids)
	{
		return receiveGoodsLevelMapper.deleteReceiveGoodsLevelByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceiveGoodsLevel selectOne(ReceiveGoodsLevel receiveGoodsLevel) {
		List<ReceiveGoodsLevel> list = receiveGoodsLevelMapper.selectReceiveGoodsLevelList(receiveGoodsLevel);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
