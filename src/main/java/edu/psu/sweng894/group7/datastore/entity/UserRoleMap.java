package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;

@Entity
@NamedQuery(query = "select r from UserRoleMap r", name = "query_find_all_roles")
@SequenceGenerator(name="user_role_seq", sequenceName="user_role_seq")
public class UserRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="user_role_seq")
    Long id;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    private Long role_id;
    private Long user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }






}
