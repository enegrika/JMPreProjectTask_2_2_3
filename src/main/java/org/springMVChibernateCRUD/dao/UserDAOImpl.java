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

//    private List<User> userList = new ArrayList<>();
//    private Long id;
//    {
//        User u1 = new User("cxxcv", "sdfds@dfgdfg.com");
//        User u2 = new User("cxxcv", "sdfds@dfgdfg.com");
//        User u3 = new User("cxxcv", "sdfds@dfgdfg.com");
//        User u4 = new User("cxxcv", "sdfds@dfgdfg.com");
//        u1.setId(1L);
//        u2.setId(2L);
//        u3.setId(3L);
//        u4.setId(4L);
//        userList.add(u1);
//        userList.add(u2);
//        userList.add(u3);
//        userList.add(u4);
//    }

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User");
        return query.getResultList();
    }
}
