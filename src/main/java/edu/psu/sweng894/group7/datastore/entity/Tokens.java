package edu.psu.sweng894.group7.datastore.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@SequenceGenerator(name="token_seq", sequenceName="token_seq")
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="token_seq")
    Long id;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Column(name="username", unique=true)
    private String username;

    @NotNull
    String token;

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @NotNull
    Timestamp createdTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
