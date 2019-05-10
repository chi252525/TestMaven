package com.welljoint.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.welljoint.dao.OrdersPhoneDAO_interface;
import com.welljoint.entity.OrdersPhoneVO;

@Service
public class OrdersPhoneService {
	@Autowired
	private OrdersPhoneDAO_interface dao;
    
    public void update(OrdersPhoneVO opVO) {
    	dao.update(opVO);
	}

    public List<OrdersPhoneVO> getAll(){
		return dao.getAll();
	}
    public OrdersPhoneVO findByPrimaryKey(String ordersNum) {
		return dao.findByPrimaryKey(ordersNum);
	}
    public void delete(String ordersNum) {
		dao.delete(ordersNum);
	}

    public List<OrdersPhoneVO> getTodayByProcessStatus(Integer processStatus) {
        return dao.getTodayByProcessStatus(processStatus);
    }

    public List<OrdersPhoneVO> getTodayUnhandled() {
        return dao.getTodayUnhandled();
    }

    public OrdersPhoneVO findByUuid(String uuid) {
		return dao.findByUuid(uuid);
	}

    public void updateTakeTime(String ordersNum, Long takeTime) {
        dao.updateTakeTime(ordersNum, takeTime);
    }

    public void updateProcessStatus(String ordersNum, Integer processStatus) {
        dao.updateProcessStatus(ordersNum, processStatus);
    }

	public void insert(OrdersPhoneVO opVO) {
		dao.insert(opVO);
	}
}
