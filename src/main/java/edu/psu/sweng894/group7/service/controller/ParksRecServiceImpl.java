package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.service.controller.model.Roles;
import edu.psu.sweng894.group7.service.controller.model.TestModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.service.ParksRecService;
import edu.psu.sweng894.group7.service.exception.AppUserException;
import edu.psu.sweng894.group7.service.util.Validator;
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
    public UserModel getUserById(long id)  {
        UserModel userModel = new UserModel();
        try {
            AppUser appUser = userService.find(id);
            userModel.setUserId(appUser.getUserId());
            userModel.setRoles(appUser.getRoles());
            userModel.setUsername(appUser.getUsername());
        }catch(Exception ex){
            throw new AppUserException("user not found." + ex.getMessage());
        }
        return userModel;
    }

    @Override
    public List<UserModel>  getUserByName(String userName) {
        List<UserModel> users= new ArrayList<>();
        try {
            List<AppUser> appUsers=userService.findAll();
            for(AppUser tempuser: appUsers){
                if(tempuser.getName().equalsIgnoreCase(userName)){
                    UserModel user= new UserModel();
                    user.setUserId(tempuser.getUserId());
                    user.setUsername(tempuser.getName());
                    String roleNames="";
                    user.setRoles(tempuser.getRoles());
                    users.add(user);
                }
            }
        }catch(Exception ex){
            throw new AppUserException("User not found. " + ex.getMessage());
        }
        return users;
    }

    @Override
    public UserModel addUser(@RequestBody UserModel userModel){
        AppUser user = new AppUser();
        long id=0l;
        try {
            Validator.validateUserModel(userModel);
            user.setPassword(userModel.getPassword());
            user.setRoles(userModel.getRoles());
            user.setUsername(userModel.getUsername());
            user.setUserId(userModel.getUserId());
            id = userService.insert(user);
        }catch(Exception ex){
            throw new AppUserException(ex.getMessage());
        }
        return getUserById(id);
    }


    @Override
    public UserModel updateUser(@RequestBody UserModel userModel)  {
        AppUser appUser = new AppUser();
        try {
            Validator.validateUserModel(userModel);
            appUser.setUserId(userModel.getUserId());
            appUser.setPassword(userModel.getPassword());
            appUser.setRoles(userModel.getRoles());
            appUser.setUsername(userModel.getUsername());
            userService.update(appUser);
        }catch(Exception ex){
            throw new AppUserException(ex.getMessage());
        }
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
        UserModel userModel = new UserModel();
        try {
            List<AppUser> appUsers=userService.findAll();
            for(AppUser appUser: appUsers){
                if(appUser.getName().equalsIgnoreCase(signedUser.getUsername()) && appUser.getPassword().equals(signedUser.getPassword())){

                    userModel.setUserId(appUser.getUserId());
                    userModel.setUsername(appUser.getUsername());
                    userModel.setRoles(appUser.getRoles());
                    return userModel;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userModel;
    }
   //end  of use cases

    //start of project services
    @RequestMapping(path="", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        return "I am alive";
    }


}
