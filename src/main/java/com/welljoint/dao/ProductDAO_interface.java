package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.ProductVO;


public interface ProductDAO_interface {
	List<ProductVO> getAll();
	List<ProductVO> getProductKeys();
	List<ProductVO> findbyProductClassKey(String productClassKey);
	void insert(ProductVO productVO);
	void update(ProductVO productVO);
	public void delete(String id);
	public ProductVO findbyProductName(String productName);
	public ProductVO findbyId(Integer id);
	
}
