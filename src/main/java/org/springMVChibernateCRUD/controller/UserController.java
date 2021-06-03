package org.springMVChibernateCRUD.controller;

import org.springMVChibernateCRUD.model.User;
import org.springMVChibernateCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USERS LIST
    @GetMapping(value = "/users")
    public String getListUsers(Model model) {
        model.addAttribute("userList", userService.getListUsers());
        return "/users/userList";
    }

    //GET USER by ID
    @GetMapping(value = "/users/{id}")
    public String getUser(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("userById", userService.getUser(id));
        return "/users/showUser";
    }

    // ADD USER 2 methods - GET to OPEN FORM for adding new user
    @GetMapping("/users/newUser")
    public String addUser(@ModelAttribute("newUse") User user) {
        return "/users/newUser";
    }

    @PostMapping(value = "/users")
    public String createNewUser(@ModelAttribute("newUser") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/newUser";
        }
        userService.addUser(user);
        return "redirect:/users";
    }


    //TODO deleteUser




    // WELCOME PAGE
    @Value("${msg.title}")
    private String titleString;

    @GetMapping(value = {"/index", "/"})
    public String index(Model model) {
        model.addAttribute("title", titleString);

        return "users/index";
    }


}
