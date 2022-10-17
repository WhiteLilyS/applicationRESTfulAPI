package com.example.applicationrestfulapi.Controller;


import com.example.applicationrestfulapi.entity.UsersTable;
import com.example.applicationrestfulapi.repository.UsersTableRepository;
import com.example.applicationrestfulapi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping()
    public String getRegistration() {
        return "public/registration";

    }
    @GetMapping("/authorization")
    public String getAuthorization() {
        return "public/login";

    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           Model model){
        if(usersTableRepository.existsByUsername(username)){
            model.addAttribute("ThisUsernameAlreadyExists","ThisUsernameAlreadyExists");
            return "public/registration";
        }
        UsersTable usersTable = new UsersTable();
        usersTable.setUsername(username);
        usersTable.setPassword(passwordEncoder.encode(password));
        usersTable.setRoleId(1L);
        usersTable.setIs_active(registrationService.booleanRand());
        usersTableRepository.save(usersTable);

        return "public/login";
    }
}
