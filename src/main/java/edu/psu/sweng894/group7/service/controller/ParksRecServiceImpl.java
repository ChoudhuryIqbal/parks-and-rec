package edu.psu.sweng894.group7.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.psu.sweng894.group7.datastore.entity.*;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.datastore.service.LeagueService;
import edu.psu.sweng894.group7.datastore.service.SportService;
import edu.psu.sweng894.group7.service.ParksRecService;
import edu.psu.sweng894.group7.service.exception.*;
import edu.psu.sweng894.group7.service.util.SecureAPI;
import edu.psu.sweng894.group7.service.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/services/v1")
public class ParksRecServiceImpl implements ParksRecService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
            //getUser
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            AppUser appUser = userService.find(id);
            userModel.setUserId(appUser.getId());
            //userModel.setRoles(appUser.getRoles());
            userModel.setRolename(appUser.getRolename());
            userModel.setUsername(appUser.getUsername());
            userModel.setAddress(appUser.getAddress());
            userModel.setEmail(appUser.getEmail());
            userModel.setOrgid(appUser.getOrgid());
            userModel.setOrgname(appUser.getOrgname());
            userModel.setPhone(appUser.getPhone());

            if(!admin && appuserByToken.getId() !=appUser.getId()){
                throw new AppUserException("Un-authorized");
            }
        } catch (Exception ex) {
            throw new AppUserException("User not found." + ex.getMessage());
        }

        return userModel;
    }

    /**
     * Returns all matching user names
     * @param userName
     * @param token
     * @return
     */
    @Override
    @SecureAPI
    public List<UserModel> getUserByName(String userName, @RequestHeader("token") String token) {
        List<UserModel> users = new ArrayList<>();
        try {
            List<AppUser> appUsers = userService.findAll();
            for (AppUser tempuser : appUsers) {
                if (tempuser.getName().equalsIgnoreCase(userName)) {
                    UserModel userModel = new UserModel();
                    userModel.setUserId(tempuser.getId());
                    userModel.setUsername(tempuser.getName());
                    userModel.setAddress(tempuser.getAddress());
                    userModel.setEmail(tempuser.getEmail());
                    userModel.setOrgid(tempuser.getOrgid());
                    userModel.setOrgname(tempuser.getOrgname());
                    userModel.setPhone(tempuser.getPhone());
                    String roleNames = "";
                    //userModel.setRoles(tempuser.getRoles());
                    userModel.setRolename(tempuser.getRolename());
                    users.add(userModel);
                }
            }
        } catch (Exception ex) {
            throw new AppUserException("User not found. " + ex.getMessage());
        }
        printResponce(users);
        return users;
    }

    /**
     * Adds a new user
     * @param userModel
     * @param token
     * @return
     */
    @Override
    @SecureAPI
    public UserModel addUser(@RequestBody UserModel userModel, @RequestHeader("token") String token) {
        AppUser user = new AppUser();
        long id = 0l;
        UserModel newUser = new UserModel();
        try {
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            if(admin) {
                Validator.validateUserModel(userModel);
                user.setPassword(userModel.getPassword());
                //user.setRoles(userModel.getRoles());
                user.setRolename(userModel.getRolename());
                user.setUsername(userModel.getUsername());
                user.setAddress(userModel.getUsername());
                user.setEmail(userModel.getEmail());
                user.setPhone(userModel.getPhone());
                user.setOrgname(userModel.getOrgname());
                id = userService.insert(user);
                newUser = getUserById(id, token);
            }else{
                throw new AppUserException("Un-authorized");
            }
        } catch (org.springframework.dao.DataIntegrityViolationException iex) {
            iex.printStackTrace();
            throw new AppUserException("Duplicate-Data");
        } catch (Exception ex) {
            throw new AppUserException(ex.getMessage());
        }
        printResponce(newUser);
        return newUser;
    }

    /**
     * Deletes the requested user
     * @param id
     * @param token
     * @return
     */
    @Override
    @SecureAPI
    public String deleteUser(@RequestParam(name="id", required=false) long id, @RequestHeader("token") String token){
        try{
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            AppUser appUser = userService.find(id);
            if(appUser != null){
                if(admin)
                    userService.delete(appUser);
                else
                    throw new AppUserException("Un-authorized");
            }

        }catch(Exception ex){
            throw new AppUserException(ex.getMessage());
        }
        return "{ \"status\":\"success\" }";
    }

    @Override
    @SecureAPI
    public UserModel updateUser(@RequestBody UserModel userModel, @RequestHeader("token") String token) {
        AppUser appUser = null;
        UserModel updatedUser = new UserModel();
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles = new ArrayList<>();
        try {
            Validator.validateUserModel(userModel);
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            appUser=userService.find(userModel.getUserId());
            if(appUser != null) {
                appUser.setPassword(userModel.getPassword());
                //appUser.setRoles(userModel.getRoles());
                appUser.setRolename(userModel.getRolename());
                appUser.setUsername(userModel.getUsername());
                appUser.setAddress(userModel.getUsername());
                appUser.setEmail(userModel.getEmail());
                appUser.setPhone(userModel.getPhone());
                appUser.setOrgname(userModel.getOrgname());
                if (admin)
                    userService.update(appUser);
                else {
                    if (appUser.getId() == appuserByToken.getId()) {
                        userService.update(appUser);
                    } else {
                        throw new AppUserException("Un-authorized");
                    }
                }
                updatedUser = getUserById(userModel.getUserId(), token);
            }
        }
        catch (Exception ex) {
            throw new AppUserException("User update Failed:"+ ex.getMessage());
        }
        printResponce(updatedUser);
        return updatedUser;
    }

    /**
     * Get all available roles
     * @param token
     * @return
     * @throws Exception
     */
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
        printResponce(userRoles);
        return userRoles;
    }

    @Override
    public String login(@RequestBody UserModel signedUser) throws Exception {
        String token = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(signedUser);
            logger.info("Request:"+ jsonInString);
            List<AppUser> appUsers = userService.findAll();
            for (AppUser appUser : appUsers) {
                if (appUser.getName().equalsIgnoreCase(signedUser.getUsername()) && appUser.getPassword().equals(signedUser.getPassword())) {
                    token = securityService.generateToken(signedUser.getUsername(), appUser.getUsername(), appUser.getId());
                }
            }
        } catch (Exception ex) {
            throw new LoginException(ex.getMessage());
        }
        printResponce(token);
        return token;
    }


    /**
     * Returns the league by id
     * @param id
     * @param orgid
     * @param token
     * @return
     */
    @Override
    @SecureAPI
    public LeagueModel getLeagueById(long id, String orgid, @RequestHeader("token") String token) {
        LeagueModel leagueModel = new LeagueModel();
        try {
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            Leagues league = leagueService.find(id);
            if(league != null) {
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
                leagueModel.setUserId(league.getUserId());
                leagueModel.setOrgid(league.getOrgid());

            }
        } catch (Exception ex) {
            throw new LeagueException("League not found." + ex.getMessage());
        }
        printResponce(leagueModel);
        return leagueModel;
    }


    @Override
    @SecureAPI
    public LeagueModel addLeague(@RequestBody LeagueModel leagueModel, @RequestHeader("token") String token) {
        Leagues league = new Leagues();
        long id = 0l;
        try {
            Validator.validateLeagueModel(leagueModel);
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            if(admin) {
                league.setUserId(appuserByToken.getId());
                league.setOrgid(appuserByToken.getOrgid());
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
            }else{
                throw new LeagueException("Un-authorized");
            }
        } catch (Exception ex) {
            throw new LeagueException("addLeague failed." + ex.getMessage());
        }
        LeagueModel model=getLeagueById(id, leagueModel.getOrgid(),token);
        printResponce(model);
        return model;
    }

    @Override
    @SecureAPI
    public LeagueModel updateLeague(@RequestBody LeagueModel leagueModel, @RequestHeader("token") String token) throws Exception {
        Leagues league =null;
        LeagueModel updatedLeague = null;
        try {
            Validator.validateLeagueModel(leagueModel);
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            league=leagueService.find(leagueModel.getLeagueId());
            if(league != null) {
                league.setLeagueId(leagueModel.getLeagueId());
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
                if (admin) {
                    leagueService.update(league);
                    updatedLeague = getLeagueById(leagueModel.getLeagueId(), leagueModel.getOrgid(), token);
                }
                else {
                    throw new LeagueException("Un-authorized");
                }
            }
        } catch (Exception ex) {
            throw new LeagueException("League update Failed. "+ex.getMessage());
        }
        printResponce(updatedLeague);
        return updatedLeague;
    }

    @Override
    @SecureAPI
    public List<LeagueModel> getAllLeagues(@RequestHeader("token") String token) throws Exception{
        List<LeagueModel> leagueModels = new ArrayList<>();
        try {
            AppUser appuserByToken = getUser(token);
            boolean admin = isAdmin(appuserByToken);

            List<Leagues> leagues=leagueService.findAll();
            for (Leagues league : leagues) {
                    LeagueModel leagueModel = new LeagueModel();
                    leagueModel.setOrgid(league.getOrgid());
                    leagueModel.setUserId(league.getUserId());
                    leagueModel.setDescription(league.getDescription());
                    leagueModel.setLeagueId(league.getLeagueId());
                    leagueModel.setSportId(league.getSportId());
                    leagueModel.setAgeMin(league.getAgeMin());
                    leagueModel.setAgeMax(league.getAgeMax());
                    leagueModel.setCoed(league.getCoed());
                    leagueModel.setTeamMin(league.getTeamMin());
                    leagueModel.setTeamMax(league.getTeamMax());
                    leagueModel.setLeagueSchedule(league.getLeagueSchedule());
                    leagueModel.setLeagueRules(league.getLeagueRules());
                    leagueModels.add(leagueModel);
            }
        }catch (Exception ex){
            throw new LeagueException("League finding failed" + ex.getMessage());
        }
        return leagueModels;
    }

    @Override
    @SecureAPI
    public List<SportModel>  getAllSports(@RequestHeader("token") String token) throws Exception{
        List<SportModel> sportModels = new ArrayList<>();
        try{
            AppUser appuserByToken = getUser(token);
            boolean admin = isAdmin(appuserByToken);
            List<Sport> sports= sportService.findAll();
            for(Sport sport:sports){
                    SportModel model = new SportModel();
                    model.setDescription(sport.getDescription());
                    model.setName(sport.getName());
                    model.setOrgid(sport.getOrgid());
                    model.setUserId(sport.getUserId());
                    sportModels.add(model);

            }

        }catch (Exception ex){
            throw new SportException("Sport finding failed.");
        }
        return sportModels;
    }

    @Override
    @SecureAPI
    public List<SportModel> getSportByName(String sportName, String orgId, @RequestHeader("token") String token) {
        List<SportModel> sports = new ArrayList<>();
        try {
            List<Sport> sportList = sportService.findAll();
            for (Sport tempSport : sportList) {
                if (tempSport.getName().equalsIgnoreCase(sportName) && tempSport.getOrgid().equalsIgnoreCase(orgId)) {
                    SportModel sportModel = new SportModel();
                    sportModel.setId(tempSport.getId());
                    sportModel.setName(tempSport.getName());
                    sportModel.setDescription(tempSport.getDescription());
                    sportModel.setOrgid(tempSport.getOrgid());
                    sports.add(sportModel);
                }
            }
        } catch (Exception ex) {
            throw new SportException("Sport not found. " + ex.getMessage());
        }
        printResponce(sports);
        return sports;
    }

    @Override
    @SecureAPI
    public  SportModel getSportById(long id,  @RequestHeader("token") String token){
        SportModel sportModel = new SportModel();
        try {
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            Sport sport = sportService.find(id);
            sportModel.setId(sport.getId());
            sportModel.setName(sport.getName());
            sportModel.setDescription(sport.getDescription());
            sportModel.setOrgid(sport.getOrgid());
            sportModel.setUserId(sport.getUserId());
        }catch(Exception ex){
            throw new SportException("Sport not found." + ex.getMessage());
        }
        return sportModel;
    }

    @Override
    @SecureAPI
    public String deleteSport(long id, @RequestHeader("token") String token) {
        try{
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            AppUser appUser = userService.find(id);
            Sport sport = sportService.find(id);
            if (admin) {
                sportService.delete(sport);

            } else {
                throw new SportException("Un-authorized");
            }

        }catch(Exception ex){
            throw new SportException(ex.getMessage());
        }
        return "{ \"status\":\"success\" }";

    }

    @Override
    @SecureAPI
    public SportModel updateSport(@RequestBody SportModel sportModel,  @RequestHeader("token") String token) throws Exception {
        try{
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            Sport sport = sportService.find(sportModel.getId());
                sport.setDescription(sportModel.getDescription());
                sport.setName(sportModel.getName());
                sportService.update(sport);
                sport = sportService.find(sportModel.getId());
                sportModel.setUserId(sport.getUserId());
                sportModel.setOrgid(sport.getOrgid());
                sportModel.setName(sport.getName());
                sportModel.setDescription(sport.getDescription());
            if (admin) {
                sportService.update(sport);
                sportModel=getSportById(sportModel.getId(), token);
            } else {
                throw new SportException("Un-authorized");
            }

        }catch(Exception ex){
            throw new SportException("Updating sport failed." + ex.getMessage());
        }
        return sportModel;
    }

    @Override
    @SecureAPI
    public SportModel addSport(@RequestBody SportModel sportModel,  @RequestHeader("token") String token){
        Sport sport = new Sport();
        SportModel model;
        long id = 0l;
        try {
            Validator.validateSportModel(sportModel);
            AppUser appuserByToken=getUser(token);
            boolean admin=isAdmin(appuserByToken);
            if(admin) {
                sport.setName(sportModel.getName());
                sport.setUserId(appuserByToken.getId());
                sport.setDescription(sportModel.getDescription());
                sport.setOrgid(appuserByToken.getOrgid());
                id = sportService.insert(sport);
            }else
                throw new SportException("Un-authorized");

        }
        catch(Exception e){
            throw new SportException("add sport failed. " + e.getMessage());
        }
        model=getSportById(id, token);
        printResponce(model);
        return model;
    }

    //end  of use cases



    //health check
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        return "I am alive";
    }

    void printResponce(Object object){
        try {
            String jsonInString= new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(object);
            logger.info("Response from method: "+ jsonInString);
        }catch(Exception ex){
            //ignore
        }

    }

    AppUser getUser(String authtoken){
        Tokens token=securityService.findToken(authtoken);
        AppUser appuser=userService.find(token.getUserid());
        return appuser;
    }
    /*
    boolean isAdmin(AppUser appUser){
        List<UserRoleMap> roles=appUser.getRoles();
        boolean admin=false;
        if(appUser.getUsername().equalsIgnoreCase("Admin"))
            return true;
        for(UserRoleMap role:roles){
            if(role.getRole_id()==1)
                admin=true;
        }
        return admin;
    }*/

    boolean isAdmin(AppUser appUser){
        String roleName=appUser.getRolename();
        boolean admin=false;
        if(roleName.equalsIgnoreCase("Admin"))
            return true;

        return admin;
    }


}
