package org.springMVChibernateCRUD.dao;

import org.hibernate.SessionFactory;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository (value = "UserDaoHibernateImp")
public class UserDaoHibernateImp implements UserDAO {

    // DATABASE CONNECTION

    @Autowired
    @Qualifier(value = "getSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u ORDER BY u.id");
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
//        sessionFactory.getCurrentSession().persist(user);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(Long id, User userToUpdate) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE User SET name=?1, email=?2 WHERE id=?3");
        query.setParameter(1, userToUpdate.getName());
        query.setParameter(2, userToUpdate.getEmail());
        query.setParameter(3, id);
        query.executeUpdate();
    }

    @Override
    public void deleteUser(Long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("DELETE FROM User WHERE id=?1");
        query.setParameter(1, id);
        query.executeUpdate();
    }
}
