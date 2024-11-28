package com.avfinal.controller;

import com.avfinal.business.UserRoleBusiness;
import com.avfinal.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleBusiness userRoleBusiness;

    // ADMIN : 67426d97b62ae171b58495e5

    @GetMapping("/{id}")
    public UserRole getUserRoleById(@PathVariable String id) throws ClassNotFoundException {
        return userRoleBusiness.findById(id);
    }

    @PostMapping("")
    public UserRole addUserRole(@RequestBody UserRole userRole) throws ClassNotFoundException {
        return userRoleBusiness.save(userRole);
    }

    @GetMapping("")
    public List<UserRole> getAllUserRole() throws ClassNotFoundException {
        return userRoleBusiness.findAll();
    }
}
