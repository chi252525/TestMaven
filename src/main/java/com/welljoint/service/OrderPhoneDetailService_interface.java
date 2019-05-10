package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.OrderPhoneDetailVO;

public interface OrderPhoneDetailService_interface {
	public void insert(OrderPhoneDetailVO opdVO);
	public OrderPhoneDetailVO update(OrderPhoneDetailVO opdVO);
	public List<OrderPhoneDetailVO> findByOrderNum(String orderNum);
	public void delete(String id);
}
