package com.welljoint.dao;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.welljoint.entity.MealNum_pofferVO;


@Repository
public class MealNum_pofferHibernateDAO extends HibernateDaoSupport implements MealNum_pofferDAO_interface {
	private static final String GET_ONE_STMT = "from MealNum_pofferVO where counter=?";
	private static final String FINDBYTODAY = "from MealNum_pofferVO";
	private static final String UPDATE_DAILYRESET = "UPDATE MealNum_pofferVO SET date=CONVERT(datetime, CONVERT(varchar, GETDATE(), 101)),mealnum_current=0,Mealnum_initial=99,ChangeStamp=GETDATE() WHERE counter='31'";
	private static final String UPDATE_STMT = "UPDATE MealNum_pofferVO SET mealnum_current=?,changestamp=? WHERE counter=?";

	// 用來注入sessionFactory（不注入會報錯）
	@Resource(name = "sessionFactory")
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void update(MealNum_pofferVO mpoVO) {
		getHibernateTemplate().update(mpoVO);
	}

	@Override
	public void update_dailyReset() {
		getHibernateTemplate().find(UPDATE_DAILYRESET);
	}

	@Override
	public MealNum_pofferVO getOnebyCounter(String counter) {
		MealNum_pofferVO mpVO = (MealNum_pofferVO) getHibernateTemplate().find(GET_ONE_STMT, counter);
		return mpVO;
	}

	@Override
	public MealNum_pofferVO findByToday() {
		MealNum_pofferVO mpVO = (MealNum_pofferVO) getHibernateTemplate().find(FINDBYTODAY);
		return mpVO;
	}

	public static void main(String[] args) {
	}
}
