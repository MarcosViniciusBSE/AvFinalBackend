package com.avfinal.model.DTO;

import com.avfinal.model.UserRole;
import java.util.Set;

public class UserDTO {
    private String id;
    private String username;
    private Set<UserRole> userRoles;

    public UserDTO(String id, String username, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.userRoles = userRoles;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public static UserDTO fromUser(com.avfinal.model.User user) {
        return new UserDTO(
                user.getId().toString(), // Convert ObjectId to String
                user.getUsername(),
                user.getUserRoles()
        );
    }
}
