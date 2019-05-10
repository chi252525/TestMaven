package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.DiscountVO;

public interface DiscountService_interface {
	public List<DiscountVO> getAll();

	public List<DiscountVO> findByProductId(Integer id);
	
}
