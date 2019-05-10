package com.welljoint.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.OrdersPhoneVO;
@Repository
public class OrdersPhoneHibernateDAO extends HibernateDaoSupport implements OrdersPhoneDAO_interface{
	private static final String DELETE = "delete from OrdersPhoneVO where ordersNum =?";	
	private static final String GET_ALL_STMT="from OrdersPhoneVO order by id";
	private static final String UPDATE_TAKETIME_STMT="UPDATE OrdersPhoneVO set TakeTime=? Where ordersNum=?";
	private static final String UPDATE_PROCESSSTATUS_STMT="UPDATE OrdersPhoneVO set ProcessStatus=? Where OrdersNum=?";
	private static final String GET_ONE_STMT="from OrdersPhoneVO where OrdersNum=?";      
	private static final String FINBYUUID="from OrdersPhoneVO where uuid=?";
    private static final String FINBYINNERNUM_QRCODE="from OrdersPhoneVO where innerNumqrcode=?";
    private static final String GET_TODAY_BY_PROCESS_STATUS = "from OrdersPhoneVO where ProcessStatus in (?)order by TakeTime asc";
    private static final String GET_TODAY_UNHANDLED = "from OrdersPhoneVO where ProcessStatus=1 order by takeTime asc";
  //用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    @Override
	public void insert(OrdersPhoneVO opVO) {
    	getHibernateTemplate().saveOrUpdate(opVO);
	}

	@Override
	public void update(OrdersPhoneVO opVO) {
		getHibernateTemplate().update(opVO);
	}

	@Override
	public void updateTakeTime(String ordersNum, Long takeTime) {
		OrdersPhoneVO opVO = (OrdersPhoneVO) getHibernateTemplate().find(UPDATE_TAKETIME_STMT,ordersNum,takeTime);
		getHibernateTemplate().update(opVO);
	}

	@Override
	public void updateProcessStatus(String ordersNum, Integer processStatus) {
		OrdersPhoneVO opVO = (OrdersPhoneVO) getHibernateTemplate().find(UPDATE_PROCESSSTATUS_STMT,ordersNum,processStatus);
		getHibernateTemplate().update(opVO);
	}

	@Override
	public void delete(String ordersNum) {
		OrdersPhoneVO opVO=getHibernateTemplate().get(OrdersPhoneVO.class, ordersNum);
		getHibernateTemplate().delete(opVO);
		
	}

	@Override
	public OrdersPhoneVO findByPrimaryKey(String ordersNum) {
		OrdersPhoneVO opVO=getHibernateTemplate().get(OrdersPhoneVO.class, ordersNum);
		return opVO;
		
	}

	@Override
	public List<OrdersPhoneVO> getAll() {
		List<OrdersPhoneVO> opVOs = (List<OrdersPhoneVO>) getHibernateTemplate().find(GET_ALL_STMT);
        return opVOs;
	}

	@Override
	public List<OrdersPhoneVO> getTodayByProcessStatus(Integer processStatus) {
		List<OrdersPhoneVO> opVOs = (List<OrdersPhoneVO>) getHibernateTemplate().find(GET_TODAY_BY_PROCESS_STATUS,processStatus);
        return opVOs;
	}

	@Override
	public List<OrdersPhoneVO> getTodayUnhandled() {
		List<OrdersPhoneVO> opVOs = (List<OrdersPhoneVO>) getHibernateTemplate().find(GET_TODAY_UNHANDLED);
        return opVOs;
	}

	@Override
	public OrdersPhoneVO findByUuid(String uuid) {
		OrdersPhoneVO opVO=(OrdersPhoneVO) getHibernateTemplate().find(FINBYUUID, uuid);
		return opVO;
	}

	@Override
	public OrdersPhoneVO findbyinnerNumqrcode(String innerNumqrcode) {
		OrdersPhoneVO opVO=(OrdersPhoneVO) getHibernateTemplate().find(FINBYINNERNUM_QRCODE, innerNumqrcode);
		return opVO;
	}

	
}
