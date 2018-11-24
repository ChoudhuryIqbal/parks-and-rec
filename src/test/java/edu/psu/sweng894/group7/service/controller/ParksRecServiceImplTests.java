package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import edu.psu.sweng894.group7.datastore.entity.Tokens;
import edu.psu.sweng894.group7.datastore.entity.Teams;
import edu.psu.sweng894.group7.datastore.entity.Roles;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.datastore.service.TeamService;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.controller.model.TeamModel;
import edu.psu.sweng894.group7.service.exception.*;
import junit.framework.AssertionFailedError;
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

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ControllerConfig.class})
public class ParksRecServiceImplTests {

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
    @Autowired
    TeamService teamService;

    //Models
    @Autowired
    private AppUser appUser;
    @Autowired
    UserModel userModel;
    @Autowired
    UserModel userModelWrong;
    @Autowired
    private Roles roles;
    @Autowired
    private Leagues league;
    @Autowired
    LeagueModel leagueModel;
    @Autowired
    LeagueModel leagueModelWrong;
    @Autowired
    private Sport sport;
    @Autowired
    SportModel sportModel;
    @Autowired
    SportModel sportModelWrong;
    @Autowired
    private Teams team;
    @Autowired
    TeamModel teamModel;
    @Autowired
    TeamModel teamModelWrong;
    @Autowired
    Tokens token;
    @Autowired
    Tokens tokenWrong;

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
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(roles);
        Mockito.when(userService.findAllRoles()).thenReturn(rolesList);



        Mockito.when(leagueService.find(0l)).thenReturn(league);
        Mockito.when(leagueService.find(-5l)).thenThrow(LeagueException.class);
        Mockito.when(leagueService.insert(league)).thenReturn(league.getLeagueId());
        List<Leagues> leaguesList = new ArrayList<>();
        leaguesList.add(league);
        Mockito.when(leagueService.findAll()).thenReturn(leaguesList);

        Mockito.when(sportService.find(0l)).thenReturn(sport);
        Mockito.when(sportService.find(-5l)).thenThrow(SportException.class);
        Mockito.when(sportService.insert(sport)).thenReturn(sport.getId());
        List <Sport> sportList = new ArrayList<>();
        sportList.add(sport);
        Mockito.when(sportService.findAll()).thenReturn(sportList);

        Mockito.when(teamService.find(0l)).thenReturn(team);
        Mockito.when(teamService.find(-5l)).thenThrow(TeamException.class);
        Mockito.when(teamService.insert(team)).thenReturn(team.getTeamId());
        List <Teams> teamList = new ArrayList<>();
        teamList.add(team);
        Mockito.when(teamService.findAll()).thenReturn(teamList);
    }

    @Test
    public void createUserPass() throws Exception{
        UserModel responce= parksRecServiceImpl.addUser(userModel,token.getToken());
        assertTrue(responce.getUserId()==userModel.getUserId());
    }

    @Test
    public void createUserFail() {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.addUser(null, token.getToken());
    }

    @Test
    public void createUserUnAuthorizedFail() {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.addUser(userModel, null);
    }

    @Test
    public void createUserDuplicateFail() {
        exception.expect(AssertionError.class);
        UserModel response = parksRecServiceImpl.addUser(userModel, token.getToken());
        UserModel response2 = parksRecServiceImpl.addUser(userModel, token.getToken());
        assertFalse(response.getUserId()==response2.getUserId());
    }

    @Test
    public void deleteUserPass() throws Exception {
        parksRecServiceImpl.deleteUser(appUser.getId(), token.getToken());
    }

    @Test
    public void deleteUserFail() {
        exception.expect(AppUserException.class);
        parksRecServiceImpl.deleteUser(-5l, token.getToken());
    }

    @Test
    public void updateUserPass() throws Exception {
        UserModel response = parksRecServiceImpl.updateUser(userModel, token.getToken());
        assertTrue(response.getUsername()==userModel.getUsername());
    }

    @Test
    public void updateUserFail() throws Exception {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.updateUser(userModelWrong, token.getToken());
    }

    @Test
    public void updateUserUnAuthorizedFail() {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.updateUser(userModelWrong, tokenWrong.getToken());
    }

    @Test
    public void loginPass() throws Exception {
        Mockito.when(securityService.generateToken(userModel.getUsername(), appUser.getUsername(),appUser.getId())).thenReturn("token");
        String response = parksRecServiceImpl.login(userModel);
        assertTrue(response == "token");
    }

    @Test
    public void loginFail() throws Exception {
        exception.expect(AssertionFailedError.class);
        Mockito.when(securityService.generateToken(userModelWrong.getUsername(), appUser.getUsername(),appUser.getId())).thenReturn("token");
        String response = parksRecServiceImpl.login(userModelWrong);
        assertFalse(response == "");
    }

    @Test
    public void getRolesPass() throws Exception {
        List <edu.psu.sweng894.group7.service.controller.model.Roles> response = parksRecServiceImpl.getRoles(token.getToken());
        assertTrue(!response.isEmpty());
    }

/*    @Test
    public void getRolesFail() throws Exception {
        exception.expect(Exception.class);
        List <edu.psu.sweng894.group7.service.controller.model.Roles> response = parksRecServiceImpl.getRoles(tokenWrong.getToken());
    } */

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
    public void getUserByIdUnAuthorized() {
        exception.expect(AppUserException.class);
        UserModel response = parksRecServiceImpl.getUserById(-9l, "incorrect");
    }

    @Test
    public void createLeaguePass() throws Exception {
        LeagueModel response = parksRecServiceImpl.addLeague(leagueModel,token.getToken());
        assertTrue(response.getLeagueId()==leagueModel.getLeagueId());
    }

    @Test
    public void createLeagueFail() {
        exception.expect(LeagueException.class);
        LeagueModel response = parksRecServiceImpl.addLeague(null, token.getToken());
    }

    @Test
    public void deleteLeaguePass() throws Exception {
        parksRecServiceImpl.deleteLeague(league.getLeagueId(), token.getToken());
    }

    @Test
    public void deleteLeagueFail() {
        exception.expect(LeagueException.class);
        parksRecServiceImpl.deleteLeague(-5l, token.getToken());
    }

    @Test
    public void getAllLeaguesPass() throws Exception {
        List<LeagueModel> response = parksRecServiceImpl.getAllLeagues(token.getToken());
        assertTrue(!response.isEmpty());
    }

    @Test
    public void getAllLeaguesFail() throws Exception {
        exception.expect(LeagueException.class);
        List<LeagueModel> response = parksRecServiceImpl.getAllLeagues(null);
    }

    @Test
    public void getLeagueByIdPass() throws Exception {
        LeagueModel response = parksRecServiceImpl.getLeagueById(league.getLeagueId(),token.getToken());
        assertTrue(response.getLeagueId()==league.getLeagueId());
    }

    @Test
    public void getLeagueByIdFail() {
        exception.expect(LeagueException.class);
        LeagueModel response = parksRecServiceImpl.getLeagueById(-5l, token.getToken());
    }

    @Test
    public void updateLeaguePass() throws Exception {
        LeagueModel response = parksRecServiceImpl.updateLeague(leagueModel, token.getToken());
        assertTrue(response.getLeagueName()==leagueModel.getLeagueName());
    }

    @Test
    public void updateLeagueFail() throws Exception {
        exception.expect(LeagueException.class);
        LeagueModel response = parksRecServiceImpl.updateLeague(leagueModelWrong, token.getToken());
    }

    @Test
    public void createSportPass() throws Exception{
        SportModel response = parksRecServiceImpl.addSport(sportModel, token.getToken());
        assertTrue(response.getId()==sport.getId());
    }

    @Test
    public void createSportFail() {
        exception.expect(SportException.class);
        SportModel response = parksRecServiceImpl.addSport(null, token.getToken());
    }

    @Test
    public void deleteSportPass() throws Exception {
        parksRecServiceImpl.deleteSport(sport.getId(), token.getToken());
    }

    @Test
    public void deleteSportFail() {
        exception.expect(SportException.class);
        parksRecServiceImpl.deleteSport(-5l, token.getToken());
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

    @Test
    public void getSportByNamePass() throws Exception {
        List <SportModel> response = parksRecServiceImpl.getSportByName(sport.getName(), token.getToken());
        assertTrue(!response.isEmpty());
    }

    @Test
    public void getSportByNameFail() {
        exception.expect(Exception.class);
        List <SportModel> response = parksRecServiceImpl.getSportByName(null, null);
    }

    @Test
    public void getAllSportsPass() throws Exception {
        List <SportModel> response = parksRecServiceImpl.getAllSports(token.getToken());
        assertTrue(!response.isEmpty());
    }

    @Test
    public void getAllSportsFail() throws Exception {
        exception.expect(SportException.class);
        List <SportModel> response = parksRecServiceImpl.getAllSports(null);
        String name = response.get(0).getName();
    }

    @Test
    public void updateSportPass() throws Exception {
        SportModel response = parksRecServiceImpl.updateSport(sportModel, token.getToken());
        assertTrue(response.getName()==sportModel.getName());
    }

    @Test
    public void updateSportFail() throws Exception {
        exception.expect(SportException.class);
        SportModel response = parksRecServiceImpl.updateSport(sportModelWrong, token.getToken());
    }

    @Test
    public void getUserByNamePass() throws Exception {
        List <UserModel> response = parksRecServiceImpl.getUserByName(appUser.getName(), token.getToken());
        assertTrue((response.get(0).getUsername()==appUser.getUsername()));
    }

    // Needs refinement to throw a proper AppUser Exception
    @Test
    public void getUserByNameFail() {
        exception.expect(Exception.class);
        List <UserModel> response = parksRecServiceImpl.getUserByName(null, token.getToken());
        String name = response.get(0).getUsername();
    }

    @Test
    public void getTeamByIdPass() throws Exception {
        TeamModel response = parksRecServiceImpl.getTeamById(team.getTeamId(), token.getToken());
        assertTrue((response.getTeamId()==team.getTeamId()));
    }

    @Test
    public void getTeamByIdFail() {
        exception.expect(TeamException.class);
        TeamModel response = parksRecServiceImpl.getTeamById(-5l, token.getToken());
    }

    @Test
    public void getTeamByNamePass() throws Exception {
        List <TeamModel> response = parksRecServiceImpl.getTeamByName(team.getTeamName(), team.getLeagueId(), token.getToken());
        assertTrue(!response.isEmpty());
    }

    @Test
    public void getTeamByNameFail() {
        exception.expect(Exception.class);
        List <TeamModel> response = parksRecServiceImpl.getTeamByName(null, -5l, token.getToken());
        String name = response.get(0).getTeamName();
    }

    @Test
    public void createTeamPass() throws Exception {
        TeamModel response = parksRecServiceImpl.addTeam(teamModel, token.getToken());
        assertTrue((response.getTeamId()==teamModel.getTeamId()));
    }

    @Test
    public void createTeamFail() {
        exception.expect(TeamException.class);
        TeamModel response = parksRecServiceImpl.addTeam(null, token.getToken());
    }

    @Test
    public void deleteTeamPass() throws Exception {
        parksRecServiceImpl.deleteTeam(team.getTeamId(), token.getToken());
    }

    @Test
    public void deleteTeamFail() {
        exception.expect(TeamException.class);
        parksRecServiceImpl.deleteTeam(-5l, token.getToken());
    }

    @Test
    public void deleteTeamUnAuthorized() {
        exception.expect(TeamException.class);
        Mockito.when(securityService.findToken(Mockito.any(String.class))).thenReturn(tokenWrong);
        parksRecServiceImpl.deleteTeam(team.getTeamId(), "incorrect");
    }

    @Test
    public void getAllTeamsPass() throws Exception {
        List <TeamModel> response = parksRecServiceImpl.getAllTeams(team.getLeagueId(), token.getToken());
        assertTrue(response.get(0).getTeamName()==team.getTeamName());
    }

    @Test
    public void getAllTeamsFail() throws Exception {
        exception.expect(Exception.class);
        List <TeamModel> response = parksRecServiceImpl.getAllTeams(-5l, token.getToken());
        String name = response.get(0).getTeamName();
    }

    @Test
    public void updateTeamPass() throws Exception {
        TeamModel response = parksRecServiceImpl.updateTeam(teamModel, token.getToken());
        assertTrue(response.getTeamName()==teamModel.getTeamName());
    }

    @Test
    public void updateTeamFail() throws Exception {
        exception.expect(TeamException.class);
        TeamModel response = parksRecServiceImpl.updateTeam(teamModelWrong, token.getToken());
    }

}
