package edu.psu.sweng894.group7.datastore.service.controller.model;

import edu.psu.sweng894.group7.datastore.service.datastore.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserModel {

    private Long id;
    private String username;
    private String password;
    private List<UserRole> roles;

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    //private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String roleNames="";
        for(UserRole role: roles)
            roleNames=roleNames+","+role.getRolename();
        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("AppUser [name=%s, role=%s]", username, roleNames);
    }
}
