package edu.psu.sweng894.group7.service.util;

import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
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

    static public void validateLeagueModel(LeagueModel leagueModel) throws Exception {
        String msg = "";
        if (leagueModel.getLeagueId() == null) {
            throw new Exception("leagueId is required");
        }
        if (leagueModel.getLeagueName() == null) {
            throw new Exception("leagueName is required");
        }
        if (leagueModel.getDescription() == null) {
            throw new Exception("Description is required");
        }
        if (leagueModel.getSportId() == null) {
            throw new Exception("SportId is required");
        }
    }

    static public void validateSportModel(SportModel sportModel) throws Exception {
        String msg = "";
        if (sportModel.getId() == null) {
            throw new Exception("ID is required");
        }
        if (sportModel.getName() == null) {
            throw new Exception("Name is required");
        }
        if (sportModel.getDescription() == null) {
            throw new Exception("Description is required");
        }
    }
}