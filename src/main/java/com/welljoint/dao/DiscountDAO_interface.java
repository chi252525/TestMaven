package com.welljoint.dao;

import java.util.List;

import com.welljoint.entity.DiscountVO;

public interface DiscountDAO_interface {
    public List<DiscountVO> findByProductId(Integer id);
    public List<DiscountVO> getAll();
}
