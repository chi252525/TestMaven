package com.welljoint.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.welljoint.entity.User;

@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
    //HibernateDaoSupport來操作資料庫更加方便

    //用來注入sessionFactory（不注入會報錯）
    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public User find(String userName,String password){
        //注意：以下是HQL語句
        List<?> users = getHibernateTemplate().find("from User where userName=? and password=?",userName,password);
        return users.size()>0?(User)users.get(0):null;
    }
}