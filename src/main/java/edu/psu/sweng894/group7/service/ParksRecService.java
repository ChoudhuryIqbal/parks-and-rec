package edu.psu.sweng894.group7.service;



import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.awt.*;
import java.util.List;

public interface ParksRecService {

    //Example services

    /*
     *
     * These services are examples to follow.
     *
     */
    @RequestMapping(path="/get", method=RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String get() throws Exception;

    @RequestMapping(path="/put/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TestModel put(@PathVariable("id") String id, @RequestParam(value="name", required=false) String name, @RequestBody TestModel model ) throws Exception;

    @RequestMapping(path="/delete", method=RequestMethod.DELETE,   produces=MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestParam(name="name", required=false, defaultValue="have a nice Day") String name);

    @RequestMapping(path="post/{id}", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public TestModel post(@PathVariable("id") String name ,@RequestBody TestModel model);
    //End of Example services


    //Project services
    /**
     * This api fetches the  registered user by id from data store.
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/getUserById", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUserById(@RequestParam(name="userId", required=true) long id, @RequestHeader("token") String token) throws Exception;


    /**
     * This api fetches the  registered user by id from data store. If more than one user found, then return a list of users
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/getUserByName", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> getUserByName(@RequestParam(name="userName", required=true) String userName,  @RequestHeader("token") String token) throws Exception;



    /**
     * This create a new user in the database.
     * @param userModel
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/createUser", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserModel addUser(@RequestBody UserModel userModel,  @RequestHeader("token") String token) throws Exception;


    @RequestMapping(path="/deleteUser", method=RequestMethod.DELETE,  produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestParam(name="id", required=false) long id,@RequestHeader("token") String token);


    /**
     * This validates user and authenticates.
     * @param signedUser
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/login", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_HTML_VALUE)
    public String login(@RequestBody UserModel signedUser) throws Exception;


    @RequestMapping(path="/updateUser", method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserModel updateUser(@RequestBody UserModel userModel,  @RequestHeader("token") String token) throws Exception;


    @RequestMapping(path="/getRoles", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public  List<Roles> getRoles( @RequestHeader("token") String token) throws Exception;


    @RequestMapping(path="/getAllLeagues", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<LeagueModel>  getAllLeagues(@RequestHeader("token") String token) throws Exception;


    @RequestMapping(path="/getLeagueById", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LeagueModel getLeagueById(@RequestParam(name="leagueId", required=true) long id,@RequestParam(name="orgid", required=true) String orgid, @RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/createLeague", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public LeagueModel addLeague(@RequestBody LeagueModel leagueModel, @RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/updateLeague", method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public LeagueModel updateLeague(@RequestBody LeagueModel leagueModel, @RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/createSport", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public SportModel addSport(@RequestBody SportModel sportModel, @RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/getSportById", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SportModel getSportById(@RequestParam(name="sportId", required=true) long id, @RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/deleteSport", method=RequestMethod.DELETE,   produces=MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteSport(@RequestParam(name="sportId", required=true, defaultValue="") long id, @RequestHeader("token") String token);

    @RequestMapping(path="/updateSport", method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public SportModel updateSport(@RequestBody SportModel sportModel,  @RequestHeader("token") String token) throws Exception;


    @RequestMapping(path="/getAllSports", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<SportModel>  getAllSports(@RequestHeader("token") String token) throws Exception;

    @RequestMapping(path="/getSportByName", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<SportModel> getSportByName(@RequestParam(name="sportName", required=true) String userName, String orgId,  @RequestHeader("token") String token) throws Exception;


}


