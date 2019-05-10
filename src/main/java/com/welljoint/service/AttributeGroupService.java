package com.welljoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welljoint.dao.AttributeGroupDAO_interface;
import com.welljoint.entity.AttributeGroupVO;
@Service
public class AttributeGroupService implements AttributeGroupService_interface{
	@Autowired
    private AttributeGroupDAO_interface dao;


    public AttributeGroupVO getOneAttibuteGroup(String id) {
        return dao.findByPrimaryKey(id);
    }

    public List<AttributeGroupVO> getAll() {
        return dao.getAll();
    }
    
    public List<AttributeGroupVO> getProductAttrglist(List<String> pro_attrglist){
    	return dao.getProductAttrglist(pro_attrglist);
    }
}
