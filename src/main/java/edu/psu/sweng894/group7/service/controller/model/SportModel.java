package edu.psu.sweng894.group7.service.controller.model;

import edu.psu.sweng894.group7.datastore.entity.Sport;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SportModel {

    @NotNull
    private Long sportId;
    @NotNull
    private String sportName;
    @NotNull
    private String sportDescription;
    @NotNull
    private Long departmentId;

    public Long getSportId() { return sportId; }
    public void setSportId(Long id) { this.sportId = id; }

    public String getSportName() { return sportName; }
    public void setSportName(String name) { this.sportName = name; }

    public String getSportDescription() { return sportDescription; }
    public void setSportDescription(String description) { this.sportDescription = description; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
