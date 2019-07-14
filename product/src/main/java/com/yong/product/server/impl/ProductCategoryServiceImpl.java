package com.yong.product.server.impl;

import com.yong.product.dataobject.ProductCategory;
import com.yong.product.repository.ProductCategoryRepository;
import com.yong.product.server.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(categoryType);
        return byCategoryTypeIn;
    }
}
