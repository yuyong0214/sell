package com.yong.product.controller;

import com.yong.product.common.DecreaseStockInput;
import com.yong.product.dataobject.ProductCategory;
import com.yong.product.dataobject.ProductInfo;
import com.yong.product.server.ProductCategoryService;
import com.yong.product.server.ProductService;
import com.yong.product.vo.ProductInfoVO;
import com.yong.product.vo.ProductVO;
import com.yong.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ResultVO<ProductVO> list() {
        // 获取所有在架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 获取所有商品类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 查询类目
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("操作成功");
        resultVO.setData(productVOList);
        return resultVO;
    }

    /**
     * 订单端调用接口
     * @param productId
     * @return
     */
    @RequestMapping("/listForOrder")
    public List<ProductInfo> ListForOrder(@RequestBody List<String> productId) {
        List<ProductInfo> productInfoList = productService.findByProductId(productId);
        return productInfoList;
    }

    @RequestMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);
    }
}
