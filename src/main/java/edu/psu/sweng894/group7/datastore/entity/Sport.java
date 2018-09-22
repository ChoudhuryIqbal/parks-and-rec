package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="Sport_SEQ", sequenceName="Sport_Seq")
@NamedQuery(query = "select s from Sport s", name = "query_find_sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sport_SEQ")
    private Long id;
    private String name;
    private String description;

//    public Sport(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

