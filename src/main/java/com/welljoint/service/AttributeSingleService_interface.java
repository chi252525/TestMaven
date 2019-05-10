package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.AttributeSingleVO;

public interface AttributeSingleService_interface {
	public AttributeSingleVO findByAttributeName(String attributename);

	public AttributeSingleVO getOneAttributeSingle(String id);

	public List<AttributeSingleVO> getAll();

}
