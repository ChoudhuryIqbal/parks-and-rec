package edu.psu.sweng894.group7.service.util;

import edu.psu.sweng894.group7.service.controller.model.LeagueModel;
import edu.psu.sweng894.group7.service.controller.model.SportModel;
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
        if (sportModel.getSportId() == null) {
            throw new ValidationException("sportID is required");
        }
        if (sportModel.getSportName() == null) {
            throw new ValidationException("Name is required");
        }
        if (sportModel.getSportDescription() == null) {
            throw new ValidationException("Description is required");
        }
        if (sportModel.getDepartmentId() == null){
            throw new ValidationException("departmentId is required");
        }
    }
}