package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.AttributeSingleVO;

public interface AttributeSingleDAO_interface {
  
    public AttributeSingleVO findByPrimaryKey(String id);
    public AttributeSingleVO findByAttributeName(String attributeName);
    public List<AttributeSingleVO> getAll();
}
