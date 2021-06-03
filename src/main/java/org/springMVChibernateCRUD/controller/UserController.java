package org.springMVChibernateCRUD.controller;

import org.springMVChibernateCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;

    public UserController(){}

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getListUsers(Model model) {
        model.addAttribute("userList", userService.getListUsers());
        return "users";
    }

//TODO getUser by ID

    @GetMapping(value = "/{id}}")
    public String getUser(@PathVariable(name = "id") int id, Model model){

        model.addAttribute("userById", userService.getUser(id));

        return "showUser";
    }

}
