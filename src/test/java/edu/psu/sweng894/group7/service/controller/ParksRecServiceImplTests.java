package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import edu.psu.sweng894.group7.datastore.entity.Tokens;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.exception.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ControllerConfig.class})
public class ParksRecServiceImplTests {

    @Autowired
    private AppUser appUser;

    @Autowired
    UserModel userModel;

    @Autowired
    ParksRecServiceImpl parksRecServiceImpl;

    //Mocked services
    @Autowired
    SecurityServices securityService;
    @Autowired
    UserService userService;
    @Autowired
    LeagueService leagueService;
    @Autowired
    SportService sportService;

    //Models
    @Autowired
    private Leagues league;
    @Autowired
    LeagueModel leagueModel;
    @Autowired
    private Sport sport;
    @Autowired
    SportModel sportModel;
    @Autowired
    Tokens token;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    //mock all database calls for unit testing.
    @Before
    public void setUp() {

        //security service
        Mockito.when(securityService.findToken(Mockito.any(String.class))).thenReturn(token);

        Mockito.when(userService.find(0l)).thenReturn(appUser);
        Mockito.when(userService.find(-5l)).thenThrow(AppUserException.class);
        Mockito.when(userService.insert(appUser)).thenReturn(appUser.getId());
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        Mockito.when(userService.findAll()).thenReturn(appUserList);

        Mockito.when(leagueService.find(0l)).thenReturn(league);
        Mockito.when(leagueService.find(-5l)).thenThrow(LeagueException.class);
        Mockito.when(leagueService.insert(league)).thenReturn(league.getLeagueId());


        Mockito.when(sportService.find(0l)).thenReturn(sport);
        Mockito.when(sportService.find(-5l)).thenThrow(SportException.class);
        Mockito.when(sportService.insert(sport)).thenReturn(sport.getId());

    }

    @Test
    public void createUser() throws Exception{
        UserModel responce= parksRecServiceImpl.addUser(userModel,token.getToken());
        assertTrue(responce.getUserId()==userModel.getUserId());

    }


    @Test
    public void getUserByIdPass() throws Exception{
        UserModel responce= parksRecServiceImpl.getUserById(appUser.getId(),token.getToken());
        assertTrue(responce.getUserId()==userModel.getUserId());
    }

    @Test
    public void getUserByIdFail() {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.getUserById(-5l, token.getToken());
    }

    @Test
    public void createLeague() throws Exception {
        LeagueModel response = parksRecServiceImpl.addLeague(leagueModel,token.getToken());
        assertTrue(response.getLeagueId()==leagueModel.getLeagueId());
    }


    @Test
    public void getLeagueByIdPass() throws Exception {
        LeagueModel response = parksRecServiceImpl.getLeagueById(league.getLeagueId(),leagueModel.getOrgid(),token.getToken());
        assertTrue(response.getLeagueId()==league.getLeagueId());
    }

    @Test
    public void getLeagueByIdFail() {
        exception.expect(LeagueException.class);
        LeagueModel response = parksRecServiceImpl.getLeagueById(-5l,"string", token.getToken());
    }

    @Test
    public void createSport() throws Exception{
        SportModel response = parksRecServiceImpl.addSport(sportModel, token.getToken());
        assertTrue(response.getId()==sport.getId());
    }


    @Test
    public void getSportByIdPass() throws  Exception{
        SportModel response = parksRecServiceImpl.getSportById(sport.getId(), token.getToken());
        assertTrue((response.getId()==sport.getId()));
    }

    @Test
    public void getSportByIdFail() {
        exception.expect(SportException.class);
        SportModel response = parksRecServiceImpl.getSportById(-5l, token.getToken());
    }



}
