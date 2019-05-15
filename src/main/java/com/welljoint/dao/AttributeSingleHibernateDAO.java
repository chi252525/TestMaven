package com.welljoint.dao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.AttributeSingleVO;
@Repository
public class AttributeSingleHibernateDAO extends HibernateDaoSupport implements AttributeSingleDAO_interface{
	private static final String GET_ALL_STMT = "from AttributeSingleVO order by sequence";
	private static final String FINDBYATTRNAME = "from AttributeSingleVO where AttributesName=?";
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public AttributeSingleVO findByPrimaryKey(String id) {
		AttributeSingleVO attrsVO=getHibernateTemplate().get(AttributeSingleVO.class, id);
		return attrsVO;
	}

	@Override
	public AttributeSingleVO findByAttributeName(String attributeName) {
		AttributeSingleVO attrsVO = (AttributeSingleVO) getHibernateTemplate().find(FINDBYATTRNAME,attributeName).get(0);
        return attrsVO;
	}

	@Override
	public List<AttributeSingleVO> getAll() {
		List<AttributeSingleVO> attrsVOs = (List<AttributeSingleVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return attrsVOs;
	}

	public static void main(String[] args) {}
}
