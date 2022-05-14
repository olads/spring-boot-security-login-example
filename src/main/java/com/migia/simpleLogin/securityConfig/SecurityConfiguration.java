package com.migia.simpleLogin.securityConfig;

import com.migia.simpleLogin.User.Role;
import com.migia.simpleLogin.User.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    UserService userService;
    BCryptPasswordEncoder encoder;

    public SecurityConfiguration(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.cors().and()
		.csrf().disable()
                //.addFilter(new JwtUserAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/student").hasRole(Role.STUDENT.name())
                .antMatchers("/admin").hasRole(Role.ADMIN.name())
                ;
                /*.formLogin()
		.loginProcessingUrl("/perfom_login")
		.defaultSuccessUrl("/admin");
*/

    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
