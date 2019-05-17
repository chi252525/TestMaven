package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.StoreInformationVO;

public interface StoreInformationService_interface {
	public StoreInformationVO getOneStoreInformation(Integer id);
	public List<StoreInformationVO> getAll();
	public StoreInformationVO findByStatus(Boolean status);
}
