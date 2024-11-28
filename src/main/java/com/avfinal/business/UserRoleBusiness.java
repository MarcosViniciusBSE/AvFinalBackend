package com.avfinal.business;

import com.avfinal.model.UserRole;
import com.avfinal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleBusiness {

    @Autowired
    RoleRepository roleRepository;

    public UserRole findById(String id) throws ClassNotFoundException {
        Optional<UserRole> userRole = roleRepository.findById(id);
        if(userRole.isEmpty()){
            throw new ClassNotFoundException("A role requisitada não existe");
        }
        return userRole.get();
    }

    public UserRole save(UserRole role){
        return roleRepository.save(role);
    }

    public void deleteById(String id) throws ClassNotFoundException {
        Optional<UserRole> userRole = roleRepository.findById(id);
        if(userRole.isEmpty()){
            throw new ClassNotFoundException("A role requisitada não existe");
        }
        roleRepository.deleteById(id);
    }

    public List<UserRole> findAll(){
        return roleRepository.findAll();
    }

    public UserRole update(UserRole role) throws ClassNotFoundException {
        Optional<UserRole> userRoleAtual = roleRepository.findById(role.getId());
        if(userRoleAtual.isEmpty()){
            throw new ClassNotFoundException("A role requisitada não existe");
        }
        userRoleAtual.get().setName(role.getName());
        return roleRepository.save(userRoleAtual.get());
    }

}
