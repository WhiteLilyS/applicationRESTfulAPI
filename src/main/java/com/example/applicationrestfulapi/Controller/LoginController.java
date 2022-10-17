package com.example.applicationrestfulapi.Controller;

import com.example.applicationrestfulapi.entity.RoleTable;
import com.example.applicationrestfulapi.entity.UsersTable;

import com.example.applicationrestfulapi.repository.RoleTableRepository;
import com.example.applicationrestfulapi.repository.UsersTableRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RoleTableRepository roleTableRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        RoleTable roleTable = roleTableRepository.findById(usersTable.getRoleId()).get();
//        if (!usersTable.getPassword().equals(password)) {
//            model.addAttribute("fail", "fail");
//            return "public/login";
//        }
        if (!bCryptPasswordEncoder.matches(password, usersTable.getPassword())) {
            model.addAttribute("fail", "fail");
            return "public/login";
        }
        if (!usersTable.getIs_active()) {
            model.addAttribute("failIsActive", "failIsActive");
            return "public/login";
        }
//        SecurityConfig securityConfig = new SecurityConfig();
//        securityConfig.authenticate(username);
//        return "redirect:/pageRequest/"+roleTable.getRoleName().toLowerCase().substring(5);
//        return "redirect:/"+roleTable.getRoleName().toLowerCase().substring(5)+"/pageRequest";

        return "redirect:/user/pageRequest";
//        return "public/pageRequest";
    }
//    @RequestMapping(value="/checkUser",method = { RequestMethod.POST})
//    public ModelAndView checkUser(HttpServletResponse response,
//                                  @RequestParam(name = "username") String username,
//                                  @RequestParam(name = "password") String password, Model model) throws IOException {
//
//        if (!usersTableRepository.existsByUsername(username)) {
//            model.addAttribute("fail", "fail");
//            response.sendRedirect("/login");
//            return null;
//        }
//        UsersTable usersTable = usersTableRepository.findByUsername(username);
//        RoleTable roleTable = roleTableRepository.findById(usersTable.getRoleId()).get();
////        if (!usersTable.getPassword().equals(password)) {
////            model.addAttribute("fail", "fail");
////            return "public/login";
////        }
//        if (!bCryptPasswordEncoder.matches(password, usersTable.getPassword())) {
//            model.addAttribute("fail", "fail");
//            response.sendRedirect("/login");
//            return null;
//        }
//        if (!usersTable.getIs_active()) {
//            model.addAttribute("failIsActive", "failIsActive");
//            response.sendRedirect("/login");
//            return null;
//        }
//        response.sendRedirect("/user/pageRequest");
//        return null;
//    }
}
