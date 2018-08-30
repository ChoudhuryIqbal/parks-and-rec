package edu.psu.sweng894.group7.service;


import edu.psu.sweng894.group7.controller.model.TestModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

public interface ParksRecService {


    @RequestMapping(value="/hello", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String sayHello() throws Exception;


    @RequestMapping(value="/hello/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String sayHello(
            @PathVariable("id") String id,
            @RequestParam(value="Hello Group7", required=false) String group,
            @RequestBody TestModel model
    ) throws Exception;


    @RequestMapping(value="/greeting", method=RequestMethod.GET,   produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name);


}


