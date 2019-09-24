package com.ruoyi.project.heavlywork.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.heavlywork.entity.Product;
import com.ruoyi.project.heavlywork.mapper.ProductMapper;
import com.ruoyi.project.heavlywork.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Override
    @DataSource(DataSourceType.SLAVE)
    public int insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }
}
