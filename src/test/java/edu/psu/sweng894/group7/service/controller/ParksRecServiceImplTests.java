package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import org.junit.Before;
import org.junit.Test;
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

    @Autowired
    SecurityServices securityService;

    @Autowired
    UserService userService;

    @Autowired

    private Leagues league;
    @Autowired
    LeagueModel leagueModel;
    @Autowired
    LeagueService leagueService;
    @Autowired
    HttpHeaders headers;

    private Sport sport;
    @Autowired
    SportModel sportModel;
    @Autowired
    SportService sportService;



    //mock all database calls for unit testing.
    @Before
    public void setUp() {
        Mockito.when(userService.find(0l)).thenReturn(appUser);
        Mockito.when(userService.insert(appUser)).thenReturn(appUser.getUserId());
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        Mockito.when(userService.findAll()).thenReturn(appUserList);

        Mockito.when(leagueService.find(0l)).thenReturn(league);
        Mockito.when(leagueService.insert(league)).thenReturn(league.getLeagueId());

        Mockito.when(sportService.find(0l)).thenReturn(sport);
        Mockito.when(sportService.insert(sport)).thenReturn(sport.getId());

        java.util.List<java.lang.String> headersList = new ArrayList<>();
        headersList.add(0, "ADMIN-TOKEN");
        Mockito.when( headers.get("token")).thenReturn(headersList);
        Mockito.when( securityService.validate("ADMIN-TOKEN")).thenReturn(Boolean.TRUE);

    }

    @Test
    public void createUser() throws Exception{
        UserModel responce= parksRecServiceImpl.addUser(userModel,headers);
        assertTrue(responce.getUserId()==userModel.getUserId());

    }

    @Test
    public void getUserById() throws Exception{
        UserModel responce= parksRecServiceImpl.getUserById(appUser.getUserId(),headers);
        assertTrue(responce.getUserId()==userModel.getUserId());

    }

    @Test
    public void createLeague() throws Exception {
        LeagueModel response = parksRecServiceImpl.addLeague(leagueModel);
        assertTrue(response.getLeagueId()==leagueModel.getLeagueId());
    }

    @Test
    public void getLeagueById() throws Exception {
        LeagueModel response = parksRecServiceImpl.getLeagueById(league.getLeagueId());
        assertTrue(response.getLeagueId()==league.getLeagueId());
    }

    @Test
    public void createSport() throws Exception{
        SportModel response = parksRecServiceImpl.addSport(sportModel);
        assertTrue(response.getId()==sport.getId());
    }

    @Test
    public void getSportById() throws  Exception{
        SportModel response = parksRecServiceImpl.getSportById(sport.getId());
        assertTrue((response.getId()==sport.getId()));
    }
}
