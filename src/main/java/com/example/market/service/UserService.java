package com.example.market.service;

import com.example.market.exception.DuplicateUserException;
import com.example.market.model.entity.Role;
import com.example.market.model.entity.User;
import com.example.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if(user==null){
           throw new UsernameNotFoundException("User not found");
       }
       return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),listAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> listAuthority(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private User saveUser(User user){
       return userRepository.save(user);
    }

    public boolean deleteById(org.springframework.security.core.userdetails.User user){
        User user1 = userRepository.findByUsername(user.getUsername());
        userRepository.deleteById(user1.getId());
        return true;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User createNewUser(User user){
        user.setRoles(Collections.singleton(Role.USER));
        if(getUserByUsername(user.getUsername())!=null){
            throw new DuplicateUserException();
        }
        else return saveUser(user);
    }


}
