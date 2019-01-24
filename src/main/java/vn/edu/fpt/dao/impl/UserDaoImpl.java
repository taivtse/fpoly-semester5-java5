package vn.edu.fpt.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.UserDao;
import vn.edu.fpt.entity.UserEntity;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM UserEntity", UserEntity.class).getResultList();
    }
}
