package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.modelUsersTable.UsersTableRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTable;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
@RequestMapping("/login")

public class LoginController {
    //
//    @Autowired
//    private PasswordAuthentication passwordAuthentication;
    @Autowired
    private UsersTableRepository usersTableRepository;


    @GetMapping("")
    public String getRequest() {
        return "public/login";
    }

    @PostMapping("/checkUser")
    public String checkUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password, Model model) {
        //TODO изменить проверку пароля добавив шифровку
        if (!usersTableRepository.existsByUsername(username) || username == null) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        UsersTable usersTable = usersTableRepository.findByUsername(username);
        if (!usersTable.getPassword().equals(password)) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        if (!usersTable.getIs_active()) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }

        return "redirect:/pageRequest";
    }
}
