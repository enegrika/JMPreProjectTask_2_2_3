package org.springMVChibernateCRUD.service;

import org.springMVChibernateCRUD.dao.UserDAO;
import org.springMVChibernateCRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDAO.getListUsers();
    }
}
