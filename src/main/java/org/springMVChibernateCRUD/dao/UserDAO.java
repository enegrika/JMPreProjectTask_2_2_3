package org.springMVChibernateCRUD.dao;

import org.springMVChibernateCRUD.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getListUsers();

}
