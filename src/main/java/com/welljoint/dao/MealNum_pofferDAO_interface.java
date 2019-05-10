package com.welljoint.dao;

import com.welljoint.entity.MealNum_pofferVO;

public interface MealNum_pofferDAO_interface {
	void update(MealNum_pofferVO mpoVO);
	void update_dailyReset();
	public MealNum_pofferVO getOnebyCounter(String counter);
	public MealNum_pofferVO findByToday();

}
