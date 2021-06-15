package org.springMVChibernateCRUD.service;

import org.springMVChibernateCRUD.dao.UserDAO;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(value = "JpaTransactionManager")
//@Transactional(value = "HibernateTransactionManager")
@Transactional
public class UserServiceImp implements UserService {

    // SET WHICH Persistence context implementation we will use
    @Autowired
    @Qualifier(value = "UserDaoJpaImp")
//    @Qualifier(value = "UserDaoHibernateImp")
    private UserDAO userDAO;

//    @Autowired
//    public UserServiceImp(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }

    //    Enables Spring's transactional behaviour.
//    Can be applied to a class, an interface or a method.
//    This annotation is enabled by setting in the context configuration file.
//    The attribute readOnly = true which sets the transaction
//    to read only mode so that it cannot modify data in any case.
    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDAO.getListUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) {
        userDAO.updateUser(id, user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

}
