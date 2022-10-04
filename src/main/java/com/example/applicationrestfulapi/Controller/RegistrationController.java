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

import javax.management.relation.Role;

//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private IUsersTableRepository iUsersTableRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String getRegistration() {
        return "public/registration";

    }
    @GetMapping("/authorization")
    public String getAuthorization() {
        return "public/login";

    }
    @PostMapping("/user/register")
    public String userRegistration(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password){
        UsersTable user = new UsersTable();
        user.setUsername(username);
        user.setPassword(password);
//        user.setPassword(passwordEncoder.encode(password));
        user.setIs_active(false);
        iUsersTableRepository.save(user);

        return "public/login";
    }
}
