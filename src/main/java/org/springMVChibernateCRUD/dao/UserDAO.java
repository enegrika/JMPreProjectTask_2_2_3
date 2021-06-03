package org.springMVChibernateCRUD.dao;

import org.springMVChibernateCRUD.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getListUsers();

    User getUser(int id);

    void addUser(User user);

    void update(int id, User user);

    void delete(int id);

}
