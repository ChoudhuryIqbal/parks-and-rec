package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.service.ParksRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/services/v1")
public class ParksRecServiceImpl  implements ParksRecService{


    @Autowired
    UserService userService;


    //start of example services
    @Override
    public String get() throws Exception {
        return "Example of get call";
    }

    @Override
    public TestModel put (@PathVariable("id") String id, @RequestParam(value="name", required=false) String name, @RequestBody TestModel model ) throws Exception{
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
    //End of example services

    //start of use cases
    @Override
    public UserModel getUserById(long id) throws Exception {
        AppUser appUser=userService.find(id);
        UserModel userModel = new UserModel();
        userModel.setId(appUser.getUserId());
        userModel.setRoles(appUser.getRoles());
        userModel.setUsername(appUser.getUsername());
        return userModel;
    }

    @Override
    public List<UserModel>  getUserByName(String userName) throws Exception {
        List<UserModel> users= new ArrayList<>();
        try {
            List<AppUser> appUsers=userService.findAll();
            for(AppUser tempuser: appUsers){
                if(tempuser.getName().equalsIgnoreCase(userName)){
                    UserModel user= new UserModel();
                    user.setId(tempuser.getUserId());
                    user.setUsername(tempuser.getName());
                    String roleNames="";
                    user.setRoles(tempuser.getRoles());
                    users.add(user);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public UserModel addUser(@RequestBody UserModel userModel) throws Exception{
        AppUser user = new AppUser();
        user.setPassword(userModel.getPassword());
        user.setRoles(userModel.getRoles());
        user.setUsername(userModel.getUsername());
        long id=userService.insert(user);
        return getUserById(id);
    }


    @Override
    public UserModel updateUser(@RequestBody UserModel userModel) throws Exception {
        AppUser appUser = new AppUser();
        appUser.setPassword(userModel.getPassword());
        appUser.setRoles(userModel.getRoles());
        appUser.setUsername(userModel.getUsername());
        userService.update(appUser);
        return userModel;
    }

    @Override
    public List<Roles> getRoles() throws Exception {
        List<edu.psu.sweng894.group7.datastore.entity.Roles> roles= userService.findAllRoles();
        List<edu.psu.sweng894.group7.service.controller.model.Roles> userRoles= new ArrayList<>();
        for(edu.psu.sweng894.group7.datastore.entity.Roles role : roles){
            Roles temprole = new Roles();
            temprole.setDescription(role.getDescription());
            temprole.setRoleId(role.getRoleId());
            temprole.setRolename(role.getRolename());
            userRoles.add(temprole);
        }
        return userRoles;
    }

    @Override
    public UserModel login(@RequestBody UserModel signedUser) throws Exception{
        try {
            List<AppUser> appUsers=userService.findAll();
            for(AppUser appUser: appUsers){
                if(appUser.getName().equalsIgnoreCase(signedUser.getUsername()) && appUser.getPassword().equals(signedUser.getPassword())){
                   return signedUser;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
   //end  of use cases

    //start of project services
    @RequestMapping(path="", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        return "I am alive";
    }


}
