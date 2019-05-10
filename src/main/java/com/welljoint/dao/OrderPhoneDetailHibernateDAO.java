package com.welljoint.dao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.welljoint.entity.OrderPhoneDetailVO;

@Repository
public class OrderPhoneDetailHibernateDAO extends HibernateDaoSupport implements OrderPhoneDetailDAO_interface{
	private static final String FINDBYORDERNUM="from OrderPhoneDetailVO WHERE ordersNum=?"; 
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

	@Override
	public void update(OrderPhoneDetailVO opdVO) {
		getHibernateTemplate().update(opdVO);
	}

	@Override
	public List<OrderPhoneDetailVO> findByOrderNum(String orderNum) {
		List<OrderPhoneDetailVO> opVOs = (List<OrderPhoneDetailVO>) getHibernateTemplate().find(FINDBYORDERNUM,orderNum);
        return opVOs;
	}

	@Override
	public void insert(OrderPhoneDetailVO opdVO) {
		getHibernateTemplate().saveOrUpdate(opdVO);
	}
	
	@Override
	public void delete(String id) {
		OrderPhoneDetailVO opdVO=getHibernateTemplate().get(OrderPhoneDetailVO.class, id);
		getHibernateTemplate().delete(opdVO);
		
	}

	@Override
	public void insertlists(List<OrderPhoneDetailVO> opdVOlist, String ordernum) {
		// TODO Auto-generated method stub
		
	}




	

		
	
}
