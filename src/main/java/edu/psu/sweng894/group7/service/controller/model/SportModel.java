package edu.psu.sweng894.group7.service.controller.model;

import edu.psu.sweng894.group7.datastore.entity.Sport;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SportModel {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
