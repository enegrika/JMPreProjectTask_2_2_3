package org.springMVChibernateCRUD.dao;

import org.hibernate.SessionFactory;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    // DATABASE CONNECTION

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where id=?1");
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(Long id, User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
