package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.modelUsersTable.IUsersTableRepository;
import com.example.applicationrestfulapi.modelUsersTable.UsersTable;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.PasswordAuthentication;

//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
@RequestMapping("/login")

public class LoginController {
    //
//    @Autowired
//    private PasswordAuthentication passwordAuthenticatio;
    @Autowired
    private IUsersTableRepository iUsersTableRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @GetMapping("")
    public String getRequest() {
        return "public/login";
    }

    @PostMapping("/checkUser")
    public String checkUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password, Model model) {
        //TODO изменить проверку пароля добавив шифровку
        if (!iUsersTableRepository.existsByUsername(username)) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        UsersTable usersTable = iUsersTableRepository.findByUsername(username);
        if (!usersTable.getPassword().equals(password)) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }

        return "redirect:/pageRequest";
    }
}
