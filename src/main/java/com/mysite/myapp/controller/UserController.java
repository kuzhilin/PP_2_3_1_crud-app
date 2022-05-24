package com.mysite.myapp.controller;

import com.mysite.myapp.model.User;
import com.mysite.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/index";
    }

    @GetMapping(params = "id")
    public String read(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user";
    }
//
    @PostMapping(params = "action=update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/user";
    }

    @PostMapping(params = "action=delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/user";
    }

}
