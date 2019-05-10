package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.OrderPhoneDetailVO;
import com.welljoint.entity.OrdersPhoneVO;

public interface OrdersPhoneService_interface {
	

	public void insert(OrdersPhoneVO opVO);

	public OrdersPhoneVO update(OrdersPhoneVO opVO);

	public List<OrdersPhoneVO> getAll();

	public OrdersPhoneVO findByPrimaryKey(String ordersNum);

	public void delete(String ordersNum);


	public List<OrdersPhoneVO> getTodayByProcessStatus(Integer processStatus);

	public List<OrdersPhoneVO> getTodayUnhandled();

	public OrdersPhoneVO findByUuid(String uuid);

	public void updateTakeTime(String ordersNum, Long takeTime);

	public void updateProcessStatus(String ordersNum, Integer processStatus);
}
