package com.ruoyi.project.stock.receiveOffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.stock.receiveOffice.mapper.ReceiveOfficeMapper;
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import com.ruoyi.project.stock.receiveOffice.service.IReceiveOfficeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 收货单位 服务层实现
 * 
 * @author admin
 * @date 2019-08-09
 */
@Service
public class ReceiveOfficeServiceImpl implements IReceiveOfficeService 
{
	@Autowired
	private ReceiveOfficeMapper receiveOfficeMapper;

	/**
     * 查询收货单位信息
     * 
     * @param id 收货单位ID
     * @return 收货单位信息
     */
    @Override
	public ReceiveOffice selectReceiveOfficeById(Long id)
	{
	    return receiveOfficeMapper.selectReceiveOfficeById(id);
	}
	
	/**
     * 查询收货单位列表
     * 
     * @param receiveOffice 收货单位信息
     * @return 收货单位集合
     */
	@Override
	public List<ReceiveOffice> selectReceiveOfficeList(ReceiveOffice receiveOffice)
	{
	    return receiveOfficeMapper.selectReceiveOfficeList(receiveOffice);
	}
	
    /**
     * 新增收货单位
     * 
     * @param receiveOffice 收货单位信息
     * @return 结果
     */
	@Override
	public int insertReceiveOffice(ReceiveOffice receiveOffice)
	{
	    return receiveOfficeMapper.insertReceiveOffice(receiveOffice);
	}
	
	/**
     * 修改收货单位
     * 
     * @param receiveOffice 收货单位信息
     * @return 结果
     */
	@Override
	public int updateReceiveOffice(ReceiveOffice receiveOffice)
	{
	    return receiveOfficeMapper.updateReceiveOffice(receiveOffice);
	}

	/**
     * 删除收货单位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReceiveOfficeByIds(String ids)
	{
		return receiveOfficeMapper.deleteReceiveOfficeByIds(Convert.toStrArray(ids));
	}

	@Override
	public ReceiveOffice selectOne(ReceiveOffice receiveOffice) {
		List<ReceiveOffice> list = receiveOfficeMapper.selectReceiveOfficeList(receiveOffice);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
