package org.springMVChibernateCRUD.dao;

import org.springMVChibernateCRUD.model.User;
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
    private EntityManager entityManager;

    //____________________ Using Java Persistence Query Language (JPQL)__________________________________
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
        User u = entityManager.find(User.class, id);
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        entityManager.persist(u);
    }

    @Override
    public void deleteUser(Long id) {
        User u = entityManager.find(User.class, id);
        entityManager.remove(u);
    }
}
