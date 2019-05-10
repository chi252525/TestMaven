package com.welljoint.dao;

import java.util.List;
import com.welljoint.entity.OrderPhoneDetailVO;
public interface OrderPhoneDetailDAO_interface {
	public void update(OrderPhoneDetailVO opdVO);
	public List<OrderPhoneDetailVO> findByOrderNum(String orderNum);
	public void insert(OrderPhoneDetailVO opdVO);
	void delete(String id);
	public void insertlists(List<OrderPhoneDetailVO> opdVOlist,String ordernum);
	
	
}
