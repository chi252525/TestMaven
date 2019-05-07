package com.welljoint.dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.welljoint.entity.ProductVO;


public class ProductHibernateDAO extends HibernateDaoSupport implements ProductDAO_interface{
	private static final String GET_ALL_STMT="from ProductVO where orderdisplay=1 order by sequence ASC";
	private static final String DELETE = "delete from ProductVO where Id =?";	
//	private static final String SELECT_PRODUCTCLASSKEY= "select distinct ProductClassKey,ProductClass from ProductVO";
	private static final String SELECT_PRODUCTCLASSKEY="select distinct productClasskey,productClass from ProductVO";
	private static final String FINBYPRODUCTCLASSKEY="from ProductVO WHERE productClasskey=? and orderdisplay=1 ORDER BY sequence ASC";
	private static final String FINBYPRODUCTNAME="from ProductVO where productionName=?";
	
	//用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> pVOs = (List<ProductVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return pVOs;
	}

	
	@Override
	public List<ProductVO> getProductKeys() {
		List<ProductVO> pVOs = (List<ProductVO>) getHibernateTemplate().find(SELECT_PRODUCTCLASSKEY);
        return pVOs;
	}

	@Override
	public List<ProductVO> findbyProductClassKey(String productClassKey) {
		List<ProductVO> pVOs = (List<ProductVO>) getHibernateTemplate().find(FINBYPRODUCTCLASSKEY,productClassKey);
        return pVOs;
	}

	@Override
	public void insert(ProductVO productVO) {
		getHibernateTemplate().saveOrUpdate(productVO);
		
	}

	@Override
	public void update(ProductVO productVO) {
		getHibernateTemplate().update(productVO);
		
	}

	@Override
	public void delete(String id) {
		ProductVO pVO=getHibernateTemplate().get(ProductVO.class, id);
		getHibernateTemplate().delete(pVO);
	}

	@Override
	public ProductVO findbyProductName(String productName) {
		ProductVO pVO = (ProductVO) getHibernateTemplate().find(FINBYPRODUCTNAME,productName);
        return pVO;
	}

	@Override
	public ProductVO findbyId(Integer id) {
		ProductVO pVO=getHibernateTemplate().get(ProductVO.class, id);
		return pVO;
	}
	public static void main(String[] args) {}
}
