package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.AttributeGroupVO;

public interface AttributeGroupService_interface {
	public AttributeGroupVO getOneAttibuteGroup(String id);
	public List<AttributeGroupVO> getAll();
	 public List<AttributeGroupVO> getProductAttrglist(List<String> pro_attrglist);
}
