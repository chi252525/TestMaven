package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.StoreInformationVO;

public interface StoreInformationDAO_interface {
    public void insert(StoreInformationVO storeInformationVO);
    public void update(StoreInformationVO storeInformationVO);
    public void delete(Integer id);
    public StoreInformationVO findByPrimaryKey(Integer id);
    public StoreInformationVO findByStatus(Boolean status);
    public List<StoreInformationVO> getAll();
}
