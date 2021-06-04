package org.springMVChibernateCRUD.dao;

import org.springMVChibernateCRUD.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getListUsers();

    User getUser(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

}
