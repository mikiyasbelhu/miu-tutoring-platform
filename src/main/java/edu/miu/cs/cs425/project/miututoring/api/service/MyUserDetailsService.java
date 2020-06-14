package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.User;
import edu.miu.cs.cs425.project.miututoring.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails. UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MyUserDetailsService(UserRepository users) {
        this.userRepository = users;
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
    }

    public User saveUser(User user){
        if(!userRepository.findByUsername(user.getUsername()).isPresent()){
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    public void saveUsers(List<User> users){
        for(User user:users){
            if(!userRepository.findByUsername(user.getUsername()).isPresent()){
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
