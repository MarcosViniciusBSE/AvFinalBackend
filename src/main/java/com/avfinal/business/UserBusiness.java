package com.avfinal.business;

import com.avfinal.config.SecurityConfig;
import com.avfinal.model.User;
import com.avfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoAuthUserDetailBusiness mongoAuthUserDetailBusiness;

    @Autowired
    SecurityConfig securityConfig;

    public User getUserByUsername(String username) {
        username = username.trim();
        User user = userRepository.findUserByUsername(username);
        user.setPassword(null);
        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean autenticade(String username, String password){
        UserDetails usuario = mongoAuthUserDetailBusiness.loadUserByUsername(username);
        if(!securityConfig.bCryptPasswordEncoder().matches(password, usuario.getPassword())) {
            throw new SecurityException("Usuário ou senha inválidos");
        }
        return true;
    }

}
