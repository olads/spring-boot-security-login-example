package com.migia.simpleLogin.User;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This service is implemented talk the database and
 * manage the retrieval of User from the database
 */
@Service
public class UserService implements UserDetailsService {

    UserRepository repository;
    BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        var user = repository.findByName(name);
        if(user.isPresent()) System.out.println(" User with name " + name + " Found with role " + user.get().getRole().name());
        return user.orElseThrow(() -> new UsernameNotFoundException("The user with this email is not found"));
    }

    public String save(String name, String password){
        Optional<User> duplicate = repository.findByName(name);
        if(duplicate.isPresent()){
            return "User with name: " + name + " exists";
        }
                User user = new User(name,(name + "@gmail.com"), encoder.encode(password), (name.charAt(0) == 's') ? Role.STUDENT : Role.ADMIN);
        repository.save(user);
        return "User has been saved successfully";

    }

}
