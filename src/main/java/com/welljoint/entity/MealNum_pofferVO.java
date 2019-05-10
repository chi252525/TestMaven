package com.welljoint.entity;

import java.sql.Timestamp;
import java.sql.Date;

public class MealNum_pofferVO {
	private Integer id;
	private Timestamp date;
	private Integer mealnum_current;
	private Integer mealnum_initial;
	private String counter;
	private	Timestamp changeStamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Integer getMealnum_current() {
		return mealnum_current;
	}
	public void setMealnum_current(Integer mealnum_current) {
		this.mealnum_current = mealnum_current;
	}
	public Integer getMealnum_initial() {
		return mealnum_initial;
	}
	public void setMealnum_initial(Integer mealnum_initial) {
		this.mealnum_initial = mealnum_initial;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public Timestamp getChangeStamp() {
		return changeStamp;
	}
	public void setChangeStamp(Timestamp changeStamp) {
		this.changeStamp = changeStamp;
	}
	
}
