package edu.psu.sweng894.group7.service.util;

import edu.psu.sweng894.group7.service.controller.model.UserModel;

public class Validator {

    static public void validateUserModel(UserModel UserModel) throws Exception{
        String msg="";
        if(UserModel.getUserId() == null){
           throw new Exception("userId is required");
        }
        if(UserModel.getUsername() == null){
            throw  new Exception("user name is required");
        }
        if(UserModel.getPassword() == null){
            throw new Exception("passeword is required");
        }
    }
}