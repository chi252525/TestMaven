package com.welljoint.service;

import java.util.List;
import java.util.Map;

import com.welljoint.entity.ProductVO;

public interface ProductService_interface {
    public ProductVO addProduct(String productClass,String productClasskey,String productionName,String subordinate_Name
    		,String productionContent0,String productionContent1,String productionContent2 ,Double prices
    		,Double discountPrice,String productImg,String productImg1,Boolean productExist,Integer productAmount
    		,String choice_Item1,String choice_Item2,String choice_Item3,
    		Boolean orderDisplay,Integer sequence,Double calories,String week
    		,String timeInterval,String tax_Type,Double tax_Rate,String description);
    public ProductVO insert(ProductVO pVO);
    public List<ProductVO> findbyProductClassKey(String productClassKey);
    public List<ProductVO> getAll();
    public void update(ProductVO productVO);
    public List<String> getProductKeys();
    public ProductVO findbyProductName(String productName);
    public ProductVO findbyId(Integer id);
}
