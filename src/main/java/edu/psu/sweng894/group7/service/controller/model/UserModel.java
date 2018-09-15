package edu.psu.sweng894.group7.service.controller.model;

import edu.psu.sweng894.group7.datastore.entity.UserRoleMap;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;



public class UserModel {

    @NotNull
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private List<UserRoleMap> roles;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<UserRoleMap> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleMap> roles) {
        this.roles = roles;
    }

    //private String role;
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
        for(UserRoleMap role: roles)
            roleNames=roleNames+","+role.getRoleId();
        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("AppUser [name=%s, role=%s]", username, roleNames);
    }
}
