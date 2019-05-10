package com.welljoint.service;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.welljoint.dao.MealNum_pofferDAO_interface;
import com.welljoint.entity.MealNum_pofferVO;

@Service
public class MealNum_pofferService implements MealNum_pofferService_interface {
	@Autowired
	private MealNum_pofferDAO_interface dao;

	public MealNum_pofferVO update(Integer mealnum_current, String counter, Timestamp changeStamp) {
		MealNum_pofferVO mpoVO = new MealNum_pofferVO();
		mpoVO.setMealnum_current(mealnum_current);
		mpoVO.setCounter(counter);
		mpoVO.setChangeStamp(changeStamp);
		dao.update(mpoVO);
		return mpoVO;
	};

	public void update_dailyReset() {
		dao.update_dailyReset();
	};

	public MealNum_pofferVO getOnebyCounter(String counter) {
		return dao.getOnebyCounter(counter);
	}

	public MealNum_pofferVO findByToday() {
		return dao.findByToday();
	}

}
