package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.AttributeGroupVO;

public interface AttributeGroupDAO_interface {
    public AttributeGroupVO findByPrimaryKey(String id);
    public List<AttributeGroupVO> getAll();
    public List<AttributeGroupVO> getProductAttrglist(List<String> pro_attrglist);
}
