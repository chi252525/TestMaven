package com.welljoint.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.OrderPhoneDetailDAO_interface;
import com.welljoint.entity.OrderPhoneDetailVO;
@Service
public class OrderPhoneDetailService {
	@Autowired
	private OrderPhoneDetailDAO_interface dao;

    public void insert(OrderPhoneDetailVO opdVO) {
    	dao.insert(opdVO);
	}
    public void update(OrderPhoneDetailVO opdVO) {
    	dao.update(opdVO);
	}
    public List<OrderPhoneDetailVO> findByOrderNum(String orderNum) {
		return dao.findByOrderNum(orderNum);
	}
    public void delete(String id) {
    	dao.delete(id);
    }
   
    
    
}
