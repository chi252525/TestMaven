package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.BannerDAO_interface;
import com.welljoint.entity.BannerVO;

@Service
public class BannerService implements BannerService_interface{
	@Autowired
	private BannerDAO_interface dao;
    
    public List<BannerVO> getAll(){
		return dao.getAll();
	}
}
