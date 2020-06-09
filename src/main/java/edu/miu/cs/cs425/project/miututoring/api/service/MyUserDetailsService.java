package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.User;
import edu.miu.cs.cs425.project.miututoring.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository users) {
        this.userRepository = users;
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
    }

    public User saveUser(User user){
        if(!userRepository.findByUsername(user.getUsername()).isPresent()){
            return userRepository.save(user);
        }
        return null;
    }

    public void saveUsers(List<User> users){
        for(User user:users){
            if(!userRepository.findByUsername(user.getUsername()).isPresent()){
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
