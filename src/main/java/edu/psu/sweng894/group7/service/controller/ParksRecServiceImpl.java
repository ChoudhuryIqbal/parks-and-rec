package edu.psu.sweng894.group7.service.controller;


import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.service.ParksRecService;
import edu.psu.sweng894.group7.service.exception.*;
import edu.psu.sweng894.group7.service.util.SecureAPI;
import edu.psu.sweng894.group7.service.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/services/v1")
public class ParksRecServiceImpl implements ParksRecService {
    @Autowired
    UserService userService;
    @Autowired
    LeagueService leagueService;

    @Autowired
    SecurityServices securityService;

    @Autowired
    SportService sportService;

    //start of example services
    @Override
    public String get() throws Exception {
        return "Example of get call";
    }

    @Override
    public TestModel put(@PathVariable("id") String id, @RequestParam(value = "name", required = false) String name, @RequestBody TestModel model) throws Exception {
        return model;
    }

    @Override
    public String delete(@RequestParam(name = "name", required = false, defaultValue = "Have a nice Day") String name) {
        return "deleted";
    }

    @Override
    public TestModel post(@PathVariable("id") String id, @RequestBody TestModel model) {
        return model;

    }
    //End of example services

    //start of use cases
    @Override
    @SecureAPI
    public UserModel getUserById(long id, @RequestHeader("token") String token) {
        UserModel userModel = new UserModel();
        try {
            AppUser appUser = userService.find(id);
            userModel.setUserId(appUser.getId());
            userModel.setRoles(appUser.getRoles());
            userModel.setUsername(appUser.getUsername());
        } catch (Exception ex) {
            throw new AppUserException("User not found." + ex.getMessage());
        }
        return userModel;
    }

    @Override
    @SecureAPI
    public List<UserModel> getUserByName(String userName, @RequestHeader("token") String token) {
        List<UserModel> users = new ArrayList<>();
        try {
            List<AppUser> appUsers = userService.findAll();
            for (AppUser tempuser : appUsers) {
                if (tempuser.getName().equalsIgnoreCase(userName)) {
                    UserModel user = new UserModel();
                    user.setUserId(tempuser.getId());
                    user.setUsername(tempuser.getName());
                    String roleNames = "";
                    user.setRoles(tempuser.getRoles());
                    users.add(user);
                }
            }
        } catch (Exception ex) {
            throw new AppUserException("User not found. " + ex.getMessage());
        }
        return users;
    }

    @Override
    @SecureAPI
    public UserModel addUser(@RequestBody UserModel userModel, @RequestHeader("token") String token) {
        AppUser user = new AppUser();
        long id = 0l;
        UserModel newUser = new UserModel();
        try {
            Validator.validateUserModel(userModel);
            user.setPassword(userModel.getPassword());
            user.setRoles(userModel.getRoles());
            user.setUsername(userModel.getUsername());
            //user.setUserId(userModel.getUserId());
            id = userService.insert(user);
            newUser = getUserById(id, token);
        } catch (org.springframework.dao.DataIntegrityViolationException iex) {
            iex.printStackTrace();
            throw new AppUserException("Duplicate Data");
        } catch (Exception ex) {
            throw new AppUserException(ex.getMessage());
        }
        return newUser;
    }

    @Override
    @SecureAPI
    public UserModel updateUser(@RequestBody UserModel userModel, @RequestHeader("token") String token) {
        AppUser appUser = new AppUser();
        UserModel updatedUser = new UserModel();
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles = new ArrayList<>();
        try {
            Validator.validateUserModel(userModel);
            //appUser.setUserId(userModel.getUserId());
            appUser.setPassword(userModel.getPassword());
            appUser.setRoles(userModel.getRoles());
            appUser.setUsername(userModel.getUsername());
            userService.update(appUser);
            updatedUser = getUserById(userModel.getUserId(), token);
        }
        catch (Exception ex) {
            throw new AppUserException("User update Failed:"+ ex.getMessage());
        }
        return updatedUser;
    }

    @Override
    @SecureAPI
    public List<Roles> getRoles(@RequestHeader("token") String token) throws Exception {
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles = new ArrayList<>();
        try {
            List<edu.psu.sweng894.group7.datastore.entity.Roles> roles = userService.findAllRoles();
            for (edu.psu.sweng894.group7.datastore.entity.Roles role : roles) {
                Roles temprole = new Roles();
                temprole.setDescription(role.getDescription());
                temprole.setRoleId(role.getRoleId());
                temprole.setRolename(role.getRolename());
                userRoles.add(temprole);
            }
        } catch (Exception ex) {
            throw new RoleException(ex.getMessage());
        }
        return userRoles;
    }

    @Override
    public String login(@RequestBody UserModel signedUser) throws Exception {
        String token = "";
        try {
            List<AppUser> appUsers = userService.findAll();
            for (AppUser appUser : appUsers) {
                if (appUser.getName().equalsIgnoreCase(signedUser.getUsername()) && appUser.getPassword().equals(signedUser.getPassword())) {
                    token = securityService.generateToken(signedUser.getUsername());
                    ;
                }
            }
        } catch (Exception ex) {
            throw new LoginException(ex.getMessage());
        }
        return token;
    }


    @Override
    @SecureAPI
    public LeagueModel getLeagueById(long id, @RequestHeader("token") String token) {
        LeagueModel leagueModel = new LeagueModel();
        try {
            Leagues league = leagueService.find(id);
            leagueModel.setLeagueId(league.getLeagueId());
            leagueModel.setLeagueName(league.getLeagueName());
            leagueModel.setDescription(league.getDescription());
            leagueModel.setSportId(league.getSportId());
            leagueModel.setAgeMin(league.getAgeMin());
            leagueModel.setAgeMax(league.getAgeMax());
            leagueModel.setCoed(league.getCoed());
            leagueModel.setTeamMin(league.getTeamMin());
            leagueModel.setTeamMax(league.getTeamMax());
            leagueModel.setLeagueSchedule(league.getLeagueSchedule());
            leagueModel.setLeagueRules(league.getLeagueRules());
        } catch (Exception ex) {
            throw new LeagueException("League not found." + ex.getMessage());
        }
        return leagueModel;
    }


    @Override
    @SecureAPI
    public LeagueModel addLeague(@RequestBody LeagueModel leagueModel, @RequestHeader("token") String token) {
        Leagues league = new Leagues();
        long id = 0l;
        try {
            Validator.validateLeagueModel(leagueModel);
            league.setLeagueName(leagueModel.getLeagueName());
            league.setDescription(leagueModel.getDescription());
            league.setSportId(leagueModel.getSportId());
            league.setAgeMin(leagueModel.getAgeMin());
            league.setAgeMax(leagueModel.getAgeMax());
            league.setCoed(leagueModel.getCoed());
            league.setTeamMin(leagueModel.getTeamMin());
            league.setTeamMax(leagueModel.getTeamMax());
            league.setLeagueSchedule(leagueModel.getLeagueSchedule());
            league.setLeagueRules(leagueModel.getLeagueRules());
            id = leagueService.insert(league);
        } catch (Exception ex) {
            throw new LeagueException(ex.getMessage());
        }
        return getLeagueById(id, token);
    }

    @Override
    @SecureAPI
    public  SportModel getSportById(long id){
        SportModel sportModel = new SportModel();
        try {
            Sport sport = sportService.find(id);
            sportModel.setId(sport.getId());
            sportModel.setName(sport.getName());
            sportModel.setDescription(sport.getDescription());
        }catch(Exception ex){
            throw new SportException("sport not found." + ex.getMessage());
        }
        return sportModel;
    }

    @Override
    @SecureAPI
    public SportModel addSport(@RequestBody SportModel sportModel){
        Sport sport = new Sport();
        long id = 0l;
        try {
            Validator.validateSportModel(sportModel);
            sport.setName(sportModel.getName());
            sport.setDescription(sportModel.getDescription());
            id = sportService.insert(sport);
        }
        catch(Exception e){
            throw new SportException(e.getMessage());
        }
        return getSportById(id);
    }

   //end  of use cases


    //health check
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        return "I am alive";
    }


}
