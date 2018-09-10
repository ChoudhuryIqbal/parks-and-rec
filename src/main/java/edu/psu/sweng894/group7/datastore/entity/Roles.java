package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;

@Entity
@NamedQuery(query = "select r from Roles r", name = "query_find_roles")
public class Roles {

    @Id
    private Long roleId;
    private String rolename;
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
