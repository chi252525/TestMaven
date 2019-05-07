package com.welljoint.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.StoreInformationVO;
@Repository
public class StoreInformationHibernateDAO extends HibernateDaoSupport implements StoreInformationDAO_interface{
	private static final String GET_ALL_STMT = "from StoreInformationVO where status =1 order by id";
	private static final String FINBYPRIMARYKEY="from StoreInformationVO where id=?";
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
	public void insert(StoreInformationVO storeInformationVO) {
		getHibernateTemplate().saveOrUpdate(storeInformationVO);
	}

    @Override
	public void update(StoreInformationVO storeInformationVO) {
		getHibernateTemplate().update(storeInformationVO);
		
	}

    @Override
	public void delete(Integer id) {
		StoreInformationVO storeInformationVO=getHibernateTemplate().get(StoreInformationVO.class, id);
		getHibernateTemplate().delete(storeInformationVO);
		
	}

    @Override
	public StoreInformationVO findByPrimaryKey(Integer id) {
		StoreInformationVO storeInformationVO = (StoreInformationVO) getHibernateTemplate().find(FINBYPRIMARYKEY,id);
        return storeInformationVO;
	}

    @Override
	public List<StoreInformationVO> getAll() {
		List<StoreInformationVO> storeInformationVOs = (List<StoreInformationVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return storeInformationVOs;
	}
	public static void main(String[] args) {}
}
