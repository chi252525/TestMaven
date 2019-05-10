package com.welljoint.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.welljoint.entity.DiscountVO;

@Repository
public class DiscountHibernateDAO extends HibernateDaoSupport implements DiscountDAO_interface {
	private static final String GET_ALL_STMT = "from DiscountVO where status =1 ";
	private static final String FINDBYPRODUCTID = " from DiscountVO where listSKU like '%'+CONVERT(varchar(10), ?)+'%'and status=1";
	private static final String FINDBYEFFECTIVE = " from DiscountVO where discode= ? and status=1";
	// 用來注入sessionFactory（不注入會報錯）
		@Resource(name = "sessionFactory")
		public void setSessionFactoryOverride(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}
	@Override
	public List<DiscountVO> findByProductId(Integer id) {
		List<DiscountVO> dVO = (List<DiscountVO>) getHibernateTemplate().find(FINDBYPRODUCTID, id);
		return dVO;
	}

	@Override
	public List<DiscountVO> getAll() {
		List<DiscountVO> dVOs = (List<DiscountVO>) getHibernateTemplate().find(GET_ALL_STMT);
		return dVOs;
	}

	public static void main(String[] args) {
	}

}
