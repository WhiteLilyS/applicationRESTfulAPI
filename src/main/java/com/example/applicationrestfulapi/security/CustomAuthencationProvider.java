package com.example.applicationrestfulapi.security;


import com.example.applicationrestfulapi.entity.RoleTable;
import com.example.applicationrestfulapi.entity.UsersTable;
import com.example.applicationrestfulapi.repository.RoleTableRepository;
import com.example.applicationrestfulapi.repository.UsersTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//@PropertySource()
public class CustomAuthencationProvider implements AuthenticationProvider {
    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private RoleTableRepository roleTableRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        UsersTable usersTable = usersTableRepository.findByUsername(userName);
        if(usersTable == null){
            throw new BadCredentialsException("Unknown user "+userName);
        }
        if(!bCryptPasswordEncoder.matches(password, usersTable.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        RoleTable roleTable = roleTableRepository.findById(usersTable.getRoleId()).get();
        UserDetails userDetails = User.builder()
                .username(usersTable.getUsername())
                .password(usersTable.getPassword())
                .roles(roleTable.getRoleName())
                .build();
        return new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
