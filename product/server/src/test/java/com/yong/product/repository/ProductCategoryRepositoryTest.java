package com.yong.product.repository;

import com.yong.product.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryType() {
        List list = new ArrayList();
        list.add(11);
        list.add(22);
        List<ProductCategory> byCategoryType = productCategoryRepository.findByCategoryTypeIn(list);
        for (ProductCategory productCategory : byCategoryType) {
            System.out.println(productCategory);
        }
    }
}