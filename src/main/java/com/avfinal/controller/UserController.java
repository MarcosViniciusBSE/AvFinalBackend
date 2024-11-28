package com.avfinal.controller;

import com.avfinal.business.UserBusiness;
import com.avfinal.config.SecurityConfig;
import com.avfinal.exceptions.UnauthorizedException;
import com.avfinal.model.DTO.UserDTO;
import com.avfinal.model.SessionObject;
import com.avfinal.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @Autowired
    SecurityConfig securityConfig;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userBusiness.getById(id);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userBusiness.getUserByUsername(username);
    }

    @GetMapping("")
    public List<UserDTO> getUsers() {
        List<UserDTO> users = new ArrayList<>();
        userBusiness.getAllUser().forEach(user -> {
            users.add(user.toDTO());
        });
        return users;
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        user.setPassword(securityConfig.bCryptPasswordEncoder().encode(user.getPassword()));
        return userBusiness.saveUser(user);
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user) {
        user.setPassword(securityConfig.bCryptPasswordEncoder().encode(user.getPassword()));
        return userBusiness.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userBusiness.deleteUser(id);
    }

    @PostMapping("/autenticate")
    public boolean autenticate(@RequestBody User user, HttpSession session) {
        boolean response = userBusiness.autenticade(user.getUsername(), user.getPassword());
        User userDb = userBusiness.getUserByUsername(user.getUsername());
        if(response){
            userDb.setPassword(null);
            session.setAttribute("user", userDb);
            session.setAttribute("userId", userDb.getId().toString());
        }
        return response;
    }

    @GetMapping("/me")
    public SessionObject getAuthenticatedUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        SessionObject sessionObj = new SessionObject(session.getAttribute("userId").toString(), user);
        if (user != null) {
            return sessionObj;
        }
        throw new UnauthorizedException("Usuário não autenticado");
    }

}
