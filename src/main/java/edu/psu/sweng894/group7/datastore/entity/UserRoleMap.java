package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;

@Entity
@NamedQuery(query = "select r from UserRoleMap r", name = "query_find_all_roles")
@SequenceGenerator(name="UserRole_SEQ", sequenceName="UserRole_Seq")
public class UserRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="UserRole_SEQ")
    Long id;
    private Long roleId;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }




}
