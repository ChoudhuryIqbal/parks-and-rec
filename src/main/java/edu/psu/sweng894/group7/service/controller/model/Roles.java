package edu.psu.sweng894.group7.service.controller.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Roles {
    @NotNull
    private Long roleId;
    @NotNull
    private String rolename;
    @NotNull
    private String description;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
