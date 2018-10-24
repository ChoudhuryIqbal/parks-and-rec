package edu.psu.sweng894.group7.datastore.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

// TODO:  Implementation not complete, need the entity for Sport to be able to map the Sports and Leagues to each other.

@Entity
@SequenceGenerator(name="League_SEQ", sequenceName="League_Seq")
@NamedQuery(query = "select l from Leagues l", name = "query_find_leagues")
public class Leagues {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "League_SEQ")
    private Long leagueId;
    private String leagueName;
    private String description;
    private Long sportId;
    private Integer ageMin;
    private Integer ageMax;
    private Boolean coed;
    private Integer teamMin;
    private Integer teamMax;
    private String leagueSchedule;
    private String leagueRules;
    private String  orgid;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Long getLeagueId() {
        return leagueId;
    }
    public void setLeagueId(Long roleId) {
        this.leagueId = roleId;
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
    public void setSportId(Long sportId) { this.sportId = sportId; }

    public Integer getAgeMin() { return ageMin; }
    public void setAgeMin(Integer ageMin) { this.ageMin = ageMin; }

    public Integer getAgeMax() { return ageMax; }
    public void setAgeMax(Integer ageMax) { this.ageMax = ageMax; }

    public Boolean getCoed() { return coed; }
    public void setCoed(Boolean coed) { this.coed = coed; }

    public Integer getTeamMin() { return teamMin; }
    public void setTeamMin(Integer teamMin) { this.teamMin = teamMin; }

    public Integer getTeamMax() { return teamMax; }
    public void setTeamMax(Integer teamMax) { this.teamMax = teamMax; }

    public String getLeagueSchedule() { return leagueSchedule; }
    public void setLeagueSchedule(String leagueSchedule) { this.leagueSchedule = leagueSchedule; }

    public String getLeagueRules() { return leagueRules; }
    public void setLeagueRules(String leagueRules) { this.leagueRules = leagueRules; }

}
