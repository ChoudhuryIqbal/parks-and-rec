package edu.psu.sweng894.group7.service.util;

import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
import edu.psu.sweng894.group7.service.controller.model.TeamModel;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import edu.psu.sweng894.group7.service.exception.ValidationException;

public class Validator {

    static public void validateUserModel(UserModel UserModel) throws ValidationException{
        String msg="";
        /*
        if(UserModel.getUserId() == null){
           throw new Exception("userId is required");
        } */
        if(UserModel.getUsername() == null){
            throw  new ValidationException("user name is required");
        }
        if(UserModel.getPassword() == null){
            throw new ValidationException("passeword is required");
        }
        if(UserModel.getOrgid() == null || UserModel.getOrgid().equalsIgnoreCase("1")){
            throw new ValidationException("org id is required and should be greater than 1");
        }

        if(UserModel.getEmail() == null){
            throw new ValidationException("email is required");
        }
        if(UserModel.getOrgname() == null){
            throw new ValidationException("org name is required");
        }

        if(UserModel.getPhone() == null){
            throw new ValidationException("phone number is required");
        }
    }

    static public void validateLeagueModel(LeagueModel leagueModel) throws ValidationException {
        String msg = "";
        if (leagueModel.getLeagueId() == null) {
            throw new ValidationException("leagueId is required");
        }
        if (leagueModel.getLeagueName() == null) {
            throw new ValidationException("leagueName is required");
        }
        if (leagueModel.getDescription() == null) {
            throw new ValidationException("Description is required");
        }
        if (leagueModel.getSportId() == null) {
            throw new ValidationException("SportId is required");
        }
    }

    static public void validateSportModel(SportModel sportModel) throws ValidationException {
        String msg = "";
        if (sportModel.getName() == null) {
            throw new ValidationException("Name is required");
        }
        if (sportModel.getDescription() == null) {
            throw new ValidationException("Description is required");
        }

    }

    static public void validateTeamModel(TeamModel teamModel) throws ValidationException {
        String msg = "";
        if (teamModel.getTeamId() == null) {
            throw new ValidationException("teamID is required");
        }
        if (teamModel.getTeamName() == null) {
            throw new ValidationException("Name is required");
        }
        if (teamModel.getLeagueId() == null) {
            throw new ValidationException("LeagueID is required");
        }

    }
}