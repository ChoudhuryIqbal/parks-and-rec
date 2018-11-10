package edu.psu.sweng894.group7.datastore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="Team_SEQ", sequenceName="Team_Seq")
@NamedQuery(query = "select t from Teams t", name = "query_find_teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sport_SEQ")
    private Long teamId;
    private String teamName;
    private String description;
    private String teamManager; //Maybe change from String to AppUser?
    private Long leagueId;
    private Boolean isChampion;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(String teamManager) {
        this.teamManager = teamManager;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Boolean getIsChampion() { return isChampion; }

    public void setIsChampion(Boolean champion) { isChampion = champion; }
}
