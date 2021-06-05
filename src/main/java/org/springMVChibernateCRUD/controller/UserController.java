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
@RequestMapping(value = "/users")
public class UserController {

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
        return "/users/newUser";
    }

    // POST NEW USER to LIST and redirect back to the whole list page
    @PostMapping
    public String createNewUser(@ModelAttribute("newUser") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/newUser";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

//READ (GET)

    // GET ALL USERS LIST
    @GetMapping
    public String getListUsers(Model model) {
        model.addAttribute("userList", userService.getListUsers());
        return "/users/userList";
    }

    //GET one USER by ID
    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("userById", userService.getUser(id));
        return "/users/showUser";
    }

//UPDATE    (GET and POST)

    @GetMapping(value = "/{id}/editUser")
    public String editUserRequest(@PathVariable("id") long id, Model model) {
        model.addAttribute("userById", userService.getUser(id));
        return "/users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("userById") @Valid User user,
                             BindingResult bindingResult,
                             @PathVariable("id") long id,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "/users/editUser";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

//DELETE  (POST)
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@ModelAttribute("userById") User user,
                             @PathVariable("id") Long id,
                             Model model) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
