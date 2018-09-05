package edu.psu.sweng894.group7.datastore.service.service;


import edu.psu.sweng894.group7.datastore.service.controller.model.TestModel;
import edu.psu.sweng894.group7.datastore.service.controller.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

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
     * This api gets the registered user
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(path="/getUser", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUser(@RequestParam(name="userName", required=true) String userName) throws Exception;

    @RequestMapping(path="addUser", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserModel addUser(@RequestBody UserModel user) throws Exception;

}


