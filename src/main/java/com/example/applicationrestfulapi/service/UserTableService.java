package com.example.applicationrestfulapi.service;

import com.example.applicationrestfulapi.entity.RoleTable;
import com.example.applicationrestfulapi.entity.UsersTable;
import com.example.applicationrestfulapi.repository.RoleTableRepository;
import com.example.applicationrestfulapi.repository.UsersTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserTableService implements UserDetailsService {
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RoleTableRepository roleTableRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersTable usersTable= usersTableRepository.findByUsername(username);
        RoleTable roleTable = roleTableRepository.findById(usersTable.getRoleId()).get();
        if (usersTable == null) {
            throw new UsernameNotFoundException("Unknown user: "+username);
        }
        UserDetails user = User.builder()
                .username(usersTable.getUsername())
                .password(usersTable.getPassword())
                .roles(roleTable.getRoleName())
                .build();
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UsersTable usersTable = usersTableRepository.findByUsername(username);
//        if(usersTable == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        return usersTable;
//    }
}
