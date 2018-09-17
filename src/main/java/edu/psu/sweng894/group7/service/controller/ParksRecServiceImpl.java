package edu.psu.sweng894.group7.service.controller;



import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.service.ParksRecService;
import edu.psu.sweng894.group7.service.exception.AppUserException;
import edu.psu.sweng894.group7.service.exception.LeagueException;
import edu.psu.sweng894.group7.service.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services/v1")
public class ParksRecServiceImpl implements ParksRecService {
    @Autowired
    UserService userService;
    @Autowired
    LeagueService leagueService;

    @Autowired
    SecurityServices securityService;

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
    public UserModel getUserById(long id, @RequestHeader HttpHeaders headers) {
        UserModel userModel = new UserModel();
        List<UserModel> users = new ArrayList<>();
        java.util.List<java.lang.String> headersList = headers.get("token");
        String authToken="";
        if(headersList != null)
            authToken = headersList.get(0);
        if (!authToken.equals("") && securityService.validate(authToken)) {
            try {
                AppUser appUser = userService.find(id);
                userModel.setUserId(appUser.getUserId());
                userModel.setRoles(appUser.getRoles());
                userModel.setUsername(appUser.getUsername());
            } catch (Exception ex) {
                throw new AppUserException("user not found." + ex.getMessage());
            }
        }
        return userModel;
    }

    @Override
    public List<UserModel> getUserByName(String userName, @RequestHeader HttpHeaders headers) {
        List<UserModel> users = new ArrayList<>();
        java.util.List<java.lang.String> headersList = headers.get("token");
        String authToken="";
        if(headersList != null)
            authToken = headersList.get(0);
        if (!authToken.equals("") && securityService.validate(authToken)) {
            try {
                List<AppUser> appUsers = userService.findAll();
                for (AppUser tempuser : appUsers) {
                    if (tempuser.getName().equalsIgnoreCase(userName)) {
                        UserModel user = new UserModel();
                        user.setUserId(tempuser.getUserId());
                        user.setUsername(tempuser.getName());
                        String roleNames = "";
                        user.setRoles(tempuser.getRoles());
                        users.add(user);
                    }
                }
            } catch (Exception ex) {
                throw new AppUserException("User not found. " + ex.getMessage());
            }
        }
        return users;
    }

    @Override
    public UserModel addUser(@RequestBody UserModel userModel, @RequestHeader HttpHeaders headers) {
        AppUser user = new AppUser();
        long id = 0l;
        java.util.List<java.lang.String> headersList = headers.get("token");
        String authToken="";
        if(headersList != null)
             authToken = headersList.get(0);
        if (!authToken.equals("") && securityService.validate(authToken)) {
            try {
                Validator.validateUserModel(userModel);
                user.setPassword(userModel.getPassword());
                user.setRoles(userModel.getRoles());
                user.setUsername(userModel.getUsername());
                user.setUserId(userModel.getUserId());
                id = userService.insert(user);
                userModel=getUserById(id, headers);
            }catch(org.springframework.dao.DataIntegrityViolationException iex){
                throw new AppUserException("Duplicate date");
            }
            catch (Exception ex) {
                throw new AppUserException(ex.getMessage());
            }
        }else{
            userModel = new UserModel();
        }
        return userModel;
    }


    @Override
    public UserModel updateUser(@RequestBody UserModel userModel, @RequestHeader HttpHeaders headers) {
        AppUser appUser = new AppUser();
        java.util.List<java.lang.String> headersList = headers.get("token");
        String authToken="";
        if(headersList != null)
            authToken = headersList.get(0);
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles = new ArrayList<>();
        if (!authToken.equals("")  && securityService.validate(authToken)) {
            try {
                Validator.validateUserModel(userModel);
                appUser.setUserId(userModel.getUserId());
                appUser.setPassword(userModel.getPassword());
                appUser.setRoles(userModel.getRoles());
                appUser.setUsername(userModel.getUsername());
                userService.update(appUser);
                userModel=getUserById(userModel.getUserId(), headers);
            } catch (Exception ex) {
                throw new AppUserException(ex.getMessage());
            }
        }else{
            userModel = new UserModel();
        }
        return userModel;
    }

    @Override
    public List<Roles> getRoles(@RequestHeader HttpHeaders headers) throws Exception {
        java.util.List<java.lang.String> headersList = headers.get("token");
        String authToken="";
        if(headersList != null)
            authToken = headersList.get(0);
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles = new ArrayList<>();
        try {
            if (!authToken.equals("") && securityService.validate(authToken)) {
                List<edu.psu.sweng894.group7.datastore.entity.Roles> roles = userService.findAllRoles();
                for (edu.psu.sweng894.group7.datastore.entity.Roles role : roles) {
                    Roles temprole = new Roles();
                    temprole.setDescription(role.getDescription());
                    temprole.setRoleId(role.getRoleId());
                    temprole.setRolename(role.getRolename());
                    userRoles.add(temprole);
                }
            }
        } catch (Exception ex) {
            throw ex;
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
                    token = securityService.generateToken();
                    ;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return token;
    }


    @Override
    public LeagueModel getLeagueById(long id)  {
        LeagueModel leagueModel = new LeagueModel();
        try {
            Leagues league = leagueService.find(id);
            leagueModel.setLeagueId(league.getLeagueId());
            leagueModel.setLeagueName(league.getLeagueName());
            leagueModel.setDescription(league.getDescription());
            leagueModel.setSportId(league.getSportId());
        }catch(Exception ex){
            throw new LeagueException("league not found." + ex.getMessage());
        }
        return leagueModel;
    }


    @Override
    public LeagueModel addLeague(@RequestBody LeagueModel leagueModel){
        Leagues league = new Leagues();
        long id=0l;
        try {
            Validator.validateLeagueModel(leagueModel);
            //league.setLeagueId(leagueModel.getLeagueId());
            league.setLeagueName(leagueModel.getLeagueName());
            league.setDescription(leagueModel.getDescription());
            id = leagueService.insert(league);
        }catch(Exception ex){
            throw new LeagueException(ex.getMessage());
        }
        return getLeagueById(id);
    }

   //end  of use cases


    //health check
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        return "I am alive";
    }


}
