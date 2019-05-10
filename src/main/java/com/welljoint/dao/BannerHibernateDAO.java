package com.welljoint.dao;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.BannerVO;

@Repository
public class BannerHibernateDAO extends HibernateDaoSupport  implements BannerDAO_interface{
	private static final String GET_ALL_STMT = "from BannerVO order by sequence asc";
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public List<BannerVO> getAll() {
		List<BannerVO> bVOs = (List<BannerVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return bVOs;
	}
	public static void main(String[] args) {}
}
