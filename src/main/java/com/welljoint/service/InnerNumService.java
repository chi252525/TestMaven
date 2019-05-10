package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.InnerNumDAO_interface;
import com.welljoint.entity.InnerNumVO;
@Service
public class InnerNumService implements InnerNumService_interface{
	@Autowired
	private InnerNumDAO_interface dao;

    public InnerNumVO getOneInnerNum(Integer id) {
        return dao.findByPrimaryKey(id);
    }

    public List<InnerNumVO> getAllEffective() {
        return dao.getAllEffective();
    }
}
