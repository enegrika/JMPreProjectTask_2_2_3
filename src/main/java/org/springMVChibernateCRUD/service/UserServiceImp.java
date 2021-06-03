package org.springMVChibernateCRUD.service;

import org.springMVChibernateCRUD.dao.UserDAO;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

}
