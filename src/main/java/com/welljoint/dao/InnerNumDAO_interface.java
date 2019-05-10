package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.InnerNumVO;

public interface InnerNumDAO_interface {
    public void insert(InnerNumVO innerNumVO);
    public void update(InnerNumVO innerNumVO);
    public void delete(Integer id);
    public InnerNumVO findByPrimaryKey(Integer id);
    public List<InnerNumVO> getAllEffective();
}
