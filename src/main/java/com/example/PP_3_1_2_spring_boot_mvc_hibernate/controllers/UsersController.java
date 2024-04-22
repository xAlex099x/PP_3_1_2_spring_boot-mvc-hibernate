package com.example.PP_3_1_2_spring_boot_mvc_hibernate.controllers;

import com.example.PP_3_1_2_spring_boot_mvc_hibernate.models.User;
import com.example.PP_3_1_2_spring_boot_mvc_hibernate.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service){
        this.service = service;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", service.allUsers());
        return "users/all_users";
    }

    @GetMapping("/{id}/")
    public String userById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userById", service.userById(id));
        return "users/user_by_id";
    }

    @GetMapping("/new_user/")
    public String newUser(@ModelAttribute("new_user") User user) {
        return "users/new_user";
    }

    @PostMapping
    public String create(@ModelAttribute("new_user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "users/new_user/";
        }
        service.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit/")
    public String updateForm(@PathVariable("id") int id, Model model){
        System.out.println("Hi");
        model.addAttribute("user", service.userById(id));
        return "users/edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult ,
                             @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "users/edit_user";
        }
        service.updateUser(id,user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}/")
    public String deleteUser(@PathVariable("id") int id){
        service.deleteUser(id);
        return "redirect:/users";
    }
}
