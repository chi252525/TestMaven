package com.welljoint.dao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.InnerNumVO;


@Repository
public class InnerNumHibernateDAO extends HibernateDaoSupport implements InnerNumDAO_interface {
	private static final String GET_ALL_EFFECTIVE_STMT = "from InnerNumVO  where status=1 order by sequence";

	// 用來注入sessionFactory（不注入會報錯）
	@Resource(name = "sessionFactory")
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insert(InnerNumVO innerNumVO) {
		getHibernateTemplate().saveOrUpdate(innerNumVO);
	}

	@Override
	public void update(InnerNumVO innerNumVO) {
		getHibernateTemplate().update(innerNumVO);

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public InnerNumVO findByPrimaryKey(Integer id) {
		InnerNumVO innerNumVO = getHibernateTemplate().get(InnerNumVO.class, id);
		return innerNumVO;
	}

	@Override
	public List<InnerNumVO> getAllEffective() {
		List<InnerNumVO> inVOs = (List<InnerNumVO>) getHibernateTemplate().find(GET_ALL_EFFECTIVE_STMT);
		return inVOs;
	}

	public static void main(String[] args) {
	}

}
