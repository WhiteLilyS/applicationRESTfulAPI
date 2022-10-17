package com.example.applicationrestfulapi.security;

import com.example.applicationrestfulapi.repository.UsersTableRepository;
import com.example.applicationrestfulapi.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import static com.example.applicationrestfulapi.security.SecurityConfig.passwordEncoder;

//import static com.example.applicationrestfulapi.security.SecurityConfig.passwordEncoder;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersTableRepository usersTableRepository;
    @Autowired
    private CustomAuthencationProvider customAuthencationProvider;
    @Autowired
    UserTableService userTableService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userTableService);
        auth.authenticationProvider(customAuthencationProvider);
    }


    @Bean
    public static BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and().formLogin()
//                .loginPage("/login")
                .defaultSuccessUrl("/user/pageRequest")
                .permitAll();
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/registration");
    }
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//            .passwordEncoder(BCryptPasswordEncoder())
//            .withUser("nina")
//            .password(passwordEncoder().encode("nina"))
//            .roles("ADMIN", "USER");

//}
//    @Autowired

//    UserDetailsService userDetailsService;

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userTableService);
//        auth.authenticationProvider(securityConfig);
//    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/registration").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/home", true)
//                .permitAll();

//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .httpBasic();
//    }

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers(PathRequest.toStaticResources().
//                        atCommonLocations()).permitAll()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(
//                        new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
//                .and()
//                .httpBasic();
//    }
//    @Override
//    protected void configure(
//            AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("nina")
//
//                .password(passwordEncoder().encode("nina"))
//                .roles("ADMIN", "USER");
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {f
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
//    }

    //
//    @Bean
//    public UserDetailsService userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//        UserDetails user = userBuilder.username("user").password("password").roles("USER").build();
//        UserDetails admin = userBuilder.username("user").password("password").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
//    }

}
