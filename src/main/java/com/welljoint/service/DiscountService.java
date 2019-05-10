package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.DiscountDAO_interface;
import com.welljoint.entity.DiscountVO;
@Service
public class DiscountService implements DiscountService_interface{
	@Autowired
	private DiscountDAO_interface dao;

    
    public List<DiscountVO> getAll(){
		return dao.getAll();
	}
    public List<DiscountVO> findByProductId(Integer id) {
		return dao.findByProductId(id);
	}
}
