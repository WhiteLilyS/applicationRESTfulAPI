package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.modelUsersTable.UsersTable;

import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsersTableRepository usersTableRepository;


    @GetMapping("")
    public String getRequest() {
        return "public/login";
    }

    @PostMapping("/checkUser")
    public String checkUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password, Model model) {

        if (!usersTableRepository.existsByUsername(username)) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        UsersTable usersTable = usersTableRepository.findByUsername(username);
        if (!usersTable.getPassword().equals(password)) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        if (!usersTable.getIs_active()) {
            model.addAttribute("failIsActive", "failIsActive");
            return "public/login";
        }

        return "redirect:/pageRequest";
    }
}
