package edu.psu.sweng894.group7.controller.model;

import edu.psu.sweng894.group7.datastore.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserModel {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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
        for(Role role: roles)
            roleNames=roleNames+","+role.getRolename();
        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("User [name=%s, role=%s]", username, roleNames);
    }
}
