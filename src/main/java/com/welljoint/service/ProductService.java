package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.ProductDAO_interface;
import com.welljoint.entity.ProductVO;
@Service
public class ProductService {
	
	@Autowired
	private ProductDAO_interface dao;

    public ProductVO addProduct(String productClass,String productClasskey,String productionName,String subordinate_Name
    		,String productionContent0,String productionContent1,String productionContent2 ,Double prices
    		,Double discountPrice,String productImg,String productImg1,Boolean productExist,Integer productAmount
    		,String choice_Item1,String choice_Item2,String choice_Item3,
    		Boolean orderDisplay,Integer sequence,Double calories,String week
    		,String timeInterval,String tax_Type,Double tax_Rate,String description) {

        ProductVO pVO = new ProductVO();
		pVO.setProductClass(productClass);
		pVO.setProductClasskey(productClasskey);
		pVO.setProductionName(productionName);
		pVO.setSubordinate_Name(subordinate_Name);
		pVO.setProductionContent0(productionContent0);
		pVO.setProductionContent1(productionContent1);
		pVO.setProductionContent2(productionContent2);
		pVO.setPrices(prices);
		pVO.setDiscountPrice(discountPrice);
		pVO.setProductImg(productImg);
		pVO.setProductImg1(productImg1);
		pVO.setProductExist(productExist);
		pVO.setProductAmount(productAmount);
		pVO.setChoice_Item1(choice_Item1);
		pVO.setChoice_Item2(choice_Item2);
		pVO.setChoice_Item3(choice_Item3);
		pVO.setOrderDisplay(orderDisplay);
		pVO.setSequence(sequence);
		pVO.setCalories(calories);
		pVO.setWeek(week);
		pVO.setTimeInterval(timeInterval);
		pVO.setTax_Type(tax_Type);
		pVO.setTax_Rate(tax_Rate);
		pVO.setDescription(description);
        dao.insert(pVO);
        return pVO;
    }
    
    public ProductVO insert(ProductVO pVO) {
    	  dao.insert(pVO);
          return pVO;
    }
    public List<ProductVO> findbyProductClassKey(String productClassKey) {
		return dao.findbyProductClassKey(productClassKey);
	}
    public List<ProductVO> getAll(){
		return dao.getAll();
	}
    public void update(ProductVO productVO) {
    	 dao.update(productVO);
    }
    
    public List<ProductVO> getProductKeys(){
		return dao.getProductKeys();
	}
    
    public ProductVO findbyProductName(String productName) {
		return dao.findbyProductName(productName);
	}
    
    public ProductVO findbyId(Integer id) {
		return dao.findbyId(id);
	}

}
