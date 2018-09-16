package edu.psu.sweng894.group7.datastore.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@SequenceGenerator(name="Token_Seq", sequenceName="Token_Seq")
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="Token_Seq")
    Long id;

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
