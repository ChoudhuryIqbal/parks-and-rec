package edu.psu.sweng894.group7.service.controller.model;

import edu.psu.sweng894.group7.datastore.entity.Leagues;

import javax.validation.constraints.NotNull;
import java.util.List;


public class LeagueModel {

    @NotNull
    private Long leagueId;
    @NotNull
    private String leagueName;
    @NotNull
    private String description;
    @NotNull
    private Long sportId;
    private Integer ageMin;
    private Integer ageMax;
    private Boolean coed;
    private Integer teamMin;
    private Integer teamMax;
    private String leagueSchedule;
    private String leagueRules;


    public Long getLeagueId() {
        return leagueId;
    }
    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSportId() { return sportId; }
    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Integer getAgeMin() { return ageMin; }
    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() { return ageMax; }
    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Boolean getCoed() { return coed; }
    public void setCoed(Boolean coed) { this.coed = coed; }

    public Integer getTeamMin() { return teamMin; }
    public void setTeamMin(Integer teamMin) {
        this.teamMin = teamMin;
    }

    public Integer getTeamMax() { return teamMax; }
    public void setTeamMax(Integer teamMax) {
        this.teamMax = teamMax;
    }

    public String getLeagueSchedule() { return leagueSchedule; }
    public void setLeagueSchedule(String leagueSchedule) { this.leagueSchedule = leagueSchedule; }

    public String getLeagueRules() { return leagueRules; }
    public void setLeagueRules(String leagueRules) { this.leagueRules = leagueRules; }
}
