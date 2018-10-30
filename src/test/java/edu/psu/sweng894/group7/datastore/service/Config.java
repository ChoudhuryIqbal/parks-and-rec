package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.UserRoleMap;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import edu.psu.sweng894.group7.datastore.entity.Teams;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Config {
    @MockBean
    private EntityManager entityManager;
    @MockBean
    UserService userService;
    @Bean
    public EntityManager entityManager(){
        return entityManager;
    }
    @Bean
    public UserService userService(){
        return userService;
    }
    @Bean
    public AppUser appUsers() {
        AppUser appUser = new AppUser();
        appUser.setId(1l);
        appUser.setUsername("TestUser");
        appUser.setPassword("TestPassword");
        List<UserRoleMap> roles = new ArrayList<>();
        UserRoleMap role = new UserRoleMap();
        //role.setDescription("Test Role Description");
        role.setRole_id(1l);
        //role.setRolename("TestRole");
        roles.add(role);
       // appUser.setRoles(roles);
        appUser.setRolename("Admin");
        return appUser;
    }

    @MockBean
    LeagueService leagueService;
    @Bean
    public LeagueService leagueService() { return leagueService; }
    @Bean
    public Leagues leagues() {
        Leagues league = new Leagues();
        league.setLeagueId(1l);
        league.setLeagueName("TestLeague");
        league.setSportId(1l);
        league.setDescription("TestLeagueDescription");
        return league;
    }

    @MockBean
    SportService sportService;
    @Bean
    public SportService sportService() { return sportService; }
    @Bean
    public Sport sport() {
        Sport sport = new Sport();
        sport.setId(1l);
        sport.setName("TestSport");
        sport.setDescription("TestSportDescription");
        return sport;
    }

    @MockBean
    TeamService teamService;
    @Bean
    public TeamService teamService() { return teamService; }
    @Bean
    public Teams team() {
        Teams team = new Teams();
        team.setTeamId(1l);
        team.setTeamName("TestTeam");
        team.setDescription("TestTeamDescription");
        team.setTeamManager("Manager");
        team.setLeagueId(1l);
        return team;
    }
}