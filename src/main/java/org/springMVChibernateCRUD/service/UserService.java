package org.springMVChibernateCRUD.service;

import org.springMVChibernateCRUD.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();

    User getUser(Long id);

    void addUser(User user);

    void update(Long id, User user);

    void delete(Long id);


}
