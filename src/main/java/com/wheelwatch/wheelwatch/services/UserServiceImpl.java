package com.wheelwatch.wheelwatch.services;

import com.wheelwatch.wheelwatch.dtos.UserDto;
import com.wheelwatch.wheelwatch.entities.User;
import com.wheelwatch.wheelwatch.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //get all users
    @Override
    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }
    @Override
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);

        response.add("http://localhost:8080/login.html");
        return response;
    }
    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
                response.add(String.valueOf(userOptional.get().getUsername()));
            } else {
                response.add("Username or password incorrect.");
            }
        } else {
            response.add("Username or password incorrect.");
        }
        return response;
    }
}
