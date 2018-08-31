package edu.psu.sweng894.group7.controller;

import edu.psu.sweng894.group7.controller.model.TestModel;
import edu.psu.sweng894.group7.service.ParksRecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/services/v1")
public class ParksRecServiceImpl  implements ParksRecService{

    @Override
    public String get() throws Exception {
        return "Example of get call";
    }

    @Override
    public TestModel put (@PathVariable("id") String id, @RequestParam(value="name", required=false) String name,  @RequestBody TestModel model ) throws Exception{
        return  model;
    }

    @Override
    public String delete(@RequestParam(name="name", required=false, defaultValue="Have a nice Day") String name){
        return "deleted";
    }

    @Override
    public TestModel post(@PathVariable("id") String id,@RequestBody TestModel model ) {
       return model;

    }
}
