package edu.psu.sweng894.group7.service;


import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

public interface ParksRecService {

    //Example services

    /*
     *
     * These services are examples to follow.
     *
     */
    @RequestMapping(path="/get", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
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
    public UserModel getUserById(@RequestParam(name="userId", required=true) long id, @RequestHeader HttpHeaders headers) throws Exception;


    /**
     * This api fetches the  registered user by id from data store. If more than one user found, then return a list of users
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/getUserByName", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> getUserByName(@RequestParam(name="userName", required=true) String userName,  @RequestHeader HttpHeaders headers) throws Exception;



    /**
     * This create a new user in the database.
     * @param userModel
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/createUser", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserModel addUser(@RequestBody UserModel userModel,  @RequestHeader HttpHeaders headers) throws Exception;


    /**
     * This validates user and authenticates.
     * @param signedUser
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/login", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_HTML_VALUE)
    public String login(@RequestBody UserModel signedUser) throws Exception;


    @RequestMapping(path="/updateUser", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserModel updateUser(@RequestBody UserModel userModel,  @RequestHeader HttpHeaders headers) throws Exception;


    @RequestMapping(path="/getRoles", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public  List<Roles> getRoles( @RequestHeader HttpHeaders headers) throws Exception;


}


