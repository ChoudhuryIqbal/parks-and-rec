package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="Sport_SEQ", sequenceName="Sport_Seq")
@NamedQuery(query = "select s from Sport s", name = "query_find_sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sport_SEQ")
    private Long sportId;
    private String sportName;
    private String sportDescription;
    private Long departmentId;

//    public Sport(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }

    public Long getSportId() {
        return sportId;
    }
    public void setSportId(Long id) {
        this.sportId = id;
    }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getSportName() {
        return sportName;
    }
    public void setSportName(String name) {
        this.sportName = name;
    }

    public String getSportDescription() {
        return sportDescription;
    }
    public void setSportDescription(String description) {
        this.sportDescription = description;
    }
}

