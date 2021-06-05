package org.springMVChibernateCRUD.dao;

import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository(value = "UserDaoJpaImp")
public class UserDaoJpaImp implements UserDAO {

    @PersistenceContext(unitName = "getEMF")
//    @Autowired
    private EntityManager entityManager;

    // Using Java Persistence Query Language (JPQL)
//    ______________________________________________________
    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery(
                "select u from User u order by u.id", User.class)
                .getResultList();
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id=?1", User.class);
        query.setParameter(1, id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        TypedQuery<User> query = entityManager.createQuery(
                "UPDATE User SET name=?1, email=?2 WHERE id=?3", User.class
        );
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getEmail());
        query.setParameter(3, id);
        query.executeUpdate();

//        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "DELETE FROM User WHERE id=?1", User.class
        );
        query.setParameter(1, id);
        query.executeUpdate();

//        entityManager.remove();

    }
}