package org.springMVChibernateCRUD.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springMVChibernateCRUD.model.User;
import org.springMVChibernateCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//CREATE ("GET" - get info from server and "POST" - send info to server)

    // GET Request page to create NEW USER
    // OPEN FORM for adding new user
    @GetMapping("/newUser")
    public String addUser(@ModelAttribute("newUse") User user) {
        logger.info("addUser() \"GET\" mapping - started");
        return "/users/newUser";
    }

    // POST NEW USER to LIST and redirect back to the whole list page
    @PostMapping
    public String createNewUser(@ModelAttribute("newUser") @Valid User user,
                                BindingResult bindingResult) {
        logger.info("createNewUser - started");
        if (bindingResult.hasErrors()) {
            logger.warn("createNewUser() - has fields error");
            return "/users/newUser";
        }
        userService.addUser(user);
        logger.info("created newUser() - ended");

        return "redirect:/users";
    }

//READ (GET)

    // GET ALL USERS LIST
    @GetMapping
    public String getListUsers(Model model) {
        logger.info("get all users list - started");
        model.addAttribute("userList", userService.getListUsers());
        logger.info("get all users list - ended");
        return "/users/userList";
    }

    //GET one USER by ID
    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable(name = "id") Long id, Model model) {
        logger.info("get userById  - started");
        model.addAttribute("userById", userService.getUser(id));
        logger.info("get userById - ended");

        return "/users/showUser";
    }

//UPDATE    (GET and POST)

    @GetMapping(value = "/{id}/editUser")
    public String editUserRequest(@PathVariable("id") long id, Model model) {
        logger.info("GET EDIT form userById - started");
        model.addAttribute("userById", userService.getUser(id));
        logger.info("GET EDIT userById - ended");
        return "/users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("userById") @Valid User user,
                             BindingResult bindingResult,
                             @PathVariable("id") long id,
                             Model model) {
        logger.info("PATCH(UPDATE) userById - started");


        if (bindingResult.hasErrors()) {
            logger.warn("PATCH (UPDATE) userById - error with entered parameters");

            return "/users/editUser";
        }
        userService.updateUser(id, user);
        logger.info("PATCH (UPDATE) userById - ended");
        return "redirect:/users";
    }

    //DELETE  (POST)
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@ModelAttribute("userById") User user,
                             @PathVariable("id") Long id,
                             Model model) {
        logger.info("DELETE userById - started");

        userService.deleteUser(id);

        logger.info("DELETE userById - ended");

        return "redirect:/users";
    }

}
