package com.niantic.controllers;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.services.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UsersController {
    private UserDao userDao = new UserDao();

    @GetMapping("/users")
    public String getAllUsers (Model model)
    {
        ArrayList<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "All Users");

        return "users/index";
    }
    @GetMapping("/users/add")
    public String addUser (Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("action", "add");
        model.addAttribute("pageTitle", "Add User");

        return "users/add_edit";
    }

    @PostMapping("/users/add")
    public String addUser (Model model, @ModelAttribute("user") User user)
    {
        userDao.addUser(user);
        model.addAttribute("user", user);

        return "redirect:/users";
    }

    @GetMapping("users/{id}/edit")

    public String editUser (Model model, @PathVariable int id)
    {
        User user = userDao.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        model.addAttribute("pageTitle", "Edit User");

        return "users/add_edit";
    }

    @PostMapping("users/{id}/edit")

    public String editUser (@ModelAttribute("user") User user, @PathVariable int id)
    {
        user.setUserId(id);
        userDao.updateUser(user);

        return "redirect:/users";
    }

    @GetMapping("users/{id}/delete")
    public String deleteUser(Model model, @PathVariable int id)
    {
        User user = userDao.getUserById(id);

        if(user == null)
        {
            model.addAttribute("message", String.format("There is no user with id %d.", id));
            return "404";
        }

        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Delete User");
        return "users/delete";
    }

    @PostMapping("users/{id}/delete")
    public String deleteUser(@PathVariable int id)
    {
        userDao.deleteUser(id);
        return "redirect:/users";
    }
}
