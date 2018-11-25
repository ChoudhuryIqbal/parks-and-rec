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


    private String orgid;
    private String orgname;
    private String email;
    private String address;
    private String phone;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    private String rolename;

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    @lombok.Generated
    @Override
    public String toString() {
        String roleNames="";
        for(UserRoleMap role: roles)
            roleNames=roleNames+","+role.getRole_id();
        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("AppUser [name=%s, role=%s, orgname=%s]", username, roleNames, orgname);
    }
}
