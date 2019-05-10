package com.welljoint.service;

import java.sql.Timestamp;

import com.welljoint.entity.MealNum_pofferVO;

public interface MealNum_pofferService_interface {
	public MealNum_pofferVO update(Integer mealnum_current,String counter,
			Timestamp changeStamp);
	public void update_dailyReset();
	public MealNum_pofferVO getOnebyCounter(String counter);
	 public MealNum_pofferVO findByToday();
}
