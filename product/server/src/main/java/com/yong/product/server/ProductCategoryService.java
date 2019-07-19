package com.yong.product.server;

import com.yong.product.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
