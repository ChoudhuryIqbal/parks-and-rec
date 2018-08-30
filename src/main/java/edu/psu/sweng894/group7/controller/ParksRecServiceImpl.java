package edu.psu.sweng894.group7.controller;

import edu.psu.sweng894.group7.controller.model.TestModel;
import edu.psu.sweng894.group7.service.ParksRecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/services/v1")
public class ParksRecServiceImpl  implements ParksRecService{

    @Override
    public String sayHello() throws Exception {
        return "Hello Group 7!";
    }

    @Override
    public String sayHello(String id, String group, TestModel model) throws Exception {
        System.out.println(id);
        return "Hello Group 7";
    }

    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name)
    {
        return("greeting");
    }
}
