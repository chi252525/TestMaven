package com.welljoint.dao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.AttributeGroupVO;

@Repository
public class AttributeGroupHibernateDAO extends HibernateDaoSupport implements AttributeGroupDAO_interface{
	private static final String GET_ALL_STMT = "from AttributeGroupVO order by sequence asc";
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public AttributeGroupVO findByPrimaryKey(String id) {
		AttributeGroupVO agVO = (AttributeGroupVO) getHibernateTemplate().get(AttributeGroupVO.class,id);
        return agVO;
	}

	@Override
	public List<AttributeGroupVO> getAll() {
		List<AttributeGroupVO> agVOs = (List<AttributeGroupVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return agVOs;
	}

	@Override
	public List<AttributeGroupVO> getProductAttrglist(List<String> pro_attrglist) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {}
}
