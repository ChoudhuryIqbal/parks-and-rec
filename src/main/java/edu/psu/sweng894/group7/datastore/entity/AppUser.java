package edu.psu.sweng894.group7.datastore.entity;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  Entity to manage user accounts
 */
@Entity
@SequenceGenerator(name="AppUser_SEQ", sequenceName="AppUser_Seq")
@NamedQuery(query = "select u from AppUser u", name = "query_find_all_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="AppUser_SEQ")
    Long id;

    @Column(name="userId", unique=true)
    @NotNull
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private String password;


    @OneToMany (cascade=CascadeType.ALL)
    @JoinColumn(name="userId")
    private List<UserRoleMap> roles;

    public List<UserRoleMap> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleMap> roles) {
        this.roles = roles;
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

    //only set method to set the password allowed.
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        String roleNames="";
        for(UserRoleMap role: roles)
            roleNames=roleNames+","+role.getRoleId();

        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("AppUser [id=%s, name=%s, role=%s]", userId, username, roleNames);
    }

}
