package edu.psu.sweng894.group7.datastore.entity;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 *  Entity to manage user accounts
 */
@Entity
@SequenceGenerator(name="app_user_seq", sequenceName="app_user_seq")
@NamedQuery(query = "select u from AppUser u", name = "query_find_all_users")
public class AppUser {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="app_user_seq")
    Long id;


    @NotNull
    private String username;
    @NotNull
    private String password;

    String orgid= UUID.randomUUID().toString();

    private String orgname;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }



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


    /*
    @OneToMany (cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<UserRoleMap> roles;

    public List<UserRoleMap> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleMap> roles) {
        this.roles = roles;
    }*/


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    //only set method to set the password allowed.
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/


    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        /*
        String roleNames="";
        for(UserRoleMap role: roles)
            roleNames=roleNames+","+role.getRole_id();

        roleNames=roleNames.substring(0,roleNames.length());
        */
        return String.format("AppUser [id=%s, name=%s, role=%s]", username, rolename);
    }

}
