package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.StoreInformationDAO_interface;
import com.welljoint.entity.StoreInformationVO;
@Service
public class StoreInformationService implements StoreInformationService_interface {
	@Autowired
    private StoreInformationDAO_interface dao;

    public StoreInformationVO getOneStoreInformation(Integer id) {
        return dao.findByPrimaryKey(id);
    }

    public List<StoreInformationVO> getAll() {
        return dao.getAll();
    }

	@Override
	public StoreInformationVO findByStatus(Boolean status) {
		return dao.findByStatus(status);
	}
}
