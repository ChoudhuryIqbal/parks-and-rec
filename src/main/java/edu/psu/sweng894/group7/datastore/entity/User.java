package edu.psu.sweng894.group7.datastore.entity;
import javax.persistence.*;
import java.util.List;

/**
 *  Enity to manage user accounts
 */
@Entity
@NamedQuery(query = "select u from User u", name = "query_find_all_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;


    @OneToMany(cascade = {CascadeType.ALL})
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        String roleNames="";
        for(Role role: roles)
            roleNames=roleNames+","+role.getRolename();

        roleNames=roleNames.substring(0,roleNames.length());
        return String.format("User [id=%s, name=%s, role=%s]", id, username, roleNames);
    }

}
