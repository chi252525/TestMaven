package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.OrdersPhoneVO;

public interface OrdersPhoneDAO_interface {
	public void insert(OrdersPhoneVO opVO);
	public void update(OrdersPhoneVO opVO);
	public void updateTakeTime(String ordersNum, Long takeTime);
	public void updateProcessStatus(String ordersNum, Integer processStatus);
    public void delete(String ordersNum);
    public OrdersPhoneVO findByPrimaryKey(String ordersNum);
    public List<OrdersPhoneVO> getAll();
    public List<OrdersPhoneVO> getTodayByProcessStatus(Integer processStatus);
    public List<OrdersPhoneVO> getTodayUnhandled();
    public OrdersPhoneVO findByUuid(String uuid);
    public OrdersPhoneVO findbyinnerNumqrcode(String innerNumqrcode);
    
}
