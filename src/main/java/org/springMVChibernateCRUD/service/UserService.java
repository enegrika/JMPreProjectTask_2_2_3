package org.springMVChibernateCRUD.service;

import org.springMVChibernateCRUD.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();

    User getUser(int id);

    void addUser(User user);

    void update(int id, User user);

    void delete(int id);


}
