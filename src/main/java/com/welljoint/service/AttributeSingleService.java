package com.welljoint.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.welljoint.dao.AttributeSingleDAO_interface;
import com.welljoint.entity.AttributeSingleVO;
@Service
public class AttributeSingleService implements AttributeSingleService_interface{
	@Autowired
	private AttributeSingleDAO_interface dao;
    public AttributeSingleVO findByAttributeName(String attributename) {
        return dao.findByAttributeName(attributename);
    }
    
    public AttributeSingleVO getOneAttributeSingle(String id) {
        return dao.findByPrimaryKey(id);
    }

    public List<AttributeSingleVO> getAll() {
        return dao.getAll();
    }
    
}
