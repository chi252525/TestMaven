package com.welljoint.service;

import java.util.List;

import com.welljoint.entity.InnerNumVO;

public interface InnerNumService_interface {
	public InnerNumVO getOneInnerNum(Integer id);

	public List<InnerNumVO> getAllEffective();
}
