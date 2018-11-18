package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.datastore.entity.*;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.datastore.service.TeamService;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.controller.model.TeamModel;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class ControllerConfig {


    @MockBean
    UserService userService;

    @MockBean
    LeagueService leagueService;
    
    @MockBean
    SecurityServices securityService;

    @MockBean
    HttpHeaders headers;

    @MockBean
    SportService sportService;

    @MockBean
    TeamService teamService;


    @Bean
    public ParksRecServiceImpl parksRecServiceImpl()  {
        ParksRecServiceImpl parksRecServiceImpl = new ParksRecServiceImpl();
        parksRecServiceImpl.userService=userService;
        parksRecServiceImpl.securityService=securityService;
        parksRecServiceImpl.leagueService=leagueService;
        parksRecServiceImpl.sportService=sportService;
        parksRecServiceImpl.teamService=teamService;
        return parksRecServiceImpl;
    }

    @Bean
    public UserModel userModel() {
        UserModel userModel = new UserModel();
        userModel.setUserId(0l);
        userModel.setUsername("TestUser");
        userModel.setPassword("TestPassword");
        userModel.setOrgid("Test Org id");
        userModel.setOrgname("Org name");
        userModel.setEmail("email@email.com");
        userModel.setPhone("3033033030");
        List<UserRoleMap> roles = new ArrayList<>();
        UserRoleMap role = new UserRoleMap();
        //role.setDescription("Test Role Description");
        role.setRole_id(0l);
        //role.setRolename("TestRole");
        roles.add(role);
        userModel.setRoles(roles);
        return userModel;
    }

    @Bean
    public UserModel userModelWrong () {
        UserModel userModel = new UserModel();
        userModel.setUserId(-9l);
        userModel.setUsername("NotSame");
        return userModel;
    }

    @Bean
    public AppUser appUsers() {
        AppUser appUser = new AppUser();
        appUser.setId(0l);
        appUser.setUsername("TestUser");
        appUser.setPassword("TestPassword");
        List<UserRoleMap> roles = new ArrayList<>();
        UserRoleMap role = new UserRoleMap();
        appUser.setRolename("Admin");
        appUser.setOrgid("Test Org id");
        return appUser;
    }

    @Bean
    public Roles roles() {
        Roles role = new Roles();
        role.setRoleId(0l);
        role.setDescription("Test role");
        role.setRolename("Role name");
        return role;
    }

    @Bean
    public LeagueModel leagueModel() {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setLeagueId(0l);
        leagueModel.setLeagueName("TestLeagueName");
        leagueModel.setDescription("TestLeagueDescription");
        leagueModel.setSportId(0l);
        leagueModel.setOrgid("Test Org id");
        return leagueModel;
    }

    @Bean
    public LeagueModel leagueModelWrong() {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setLeagueId(-5l);
        leagueModel.setLeagueName("Wrong League Name");
        return leagueModel;
    }

    @Bean
    public Leagues league() {
        Leagues league = new Leagues();
        league.setLeagueId(0l);
        league.setLeagueName("TestLeagueName");
        league.setDescription("TestLeagueDescription");
        league.setSportId(0l);
        league.setOrgid("Test Org id");
        return league;
    }

    @Bean
    public Sport sport(){
        Sport sport = new Sport();
        sport.setId(0l);
        sport.setName("Test Sport Name");
        sport.setDescription("test description");
        sport.setOrgid("Test Org id");
        return sport;
    }

    @Bean
    public SportModel sportModel(){
        SportModel sportModel = new SportModel();
        sportModel.setId(0l);
        sportModel.setName("Test Sport Name");
        sportModel.setDescription("test description");
        sportModel.setOrgid("Test Org id");
        return sportModel;
    }

    @Bean
    public SportModel sportModelWrong() {
        SportModel sportModel = new SportModel();
        sportModel.setId(-5l);
        sportModel.setName("Wrong Sport Name");
        return sportModel;
    }

    @Bean
    public Teams team(){
        Teams team = new Teams();
        team.setTeamId(0l);
        team.setTeamName("Test Sport Name");
        team.setDescription("test description");
        team.setTeamManager("Joe");
        team.setLeagueId(0l);
        return team;
    }

    @Bean
    public TeamModel teamModel(){
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamId(0l);
        teamModel.setTeamName("Test Sport Name");
        teamModel.setDescription("test description");
        teamModel.setTeamManager("Joe");
        teamModel.setLeagueId(0l);
        teamModel.setIsChampion(true);
        return teamModel;
    }

    @Bean
    public TeamModel teamModelWrong() {
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamId(-5l);
        teamModel.setTeamName("Wrong Team Name");
        return teamModel;
    }

    @Bean
    public Tokens token(){
        Tokens token = new Tokens();
        token.setUserid(0l);
        token.setUsername("Admin");
        token.setToken("ADMIN-TOKEN");
        return token;
    }

    @Bean
    public Tokens tokenWrong() {
        Tokens token = new Tokens();
        token.setUserid(-1l);
        token.setUsername("");
        token.setToken("");
        return token;
    }

}