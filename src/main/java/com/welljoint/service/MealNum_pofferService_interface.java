package com.welljoint.service;

import java.sql.Timestamp;

import com.welljoint.entity.MealNum_pofferVO;

public interface MealNum_pofferService_interface {
	public void update_dailyReset();
	public void update(MealNum_pofferVO mpoVO);
	public MealNum_pofferVO getOnebyCounter(String counter);
	 public MealNum_pofferVO findByToday();
}
