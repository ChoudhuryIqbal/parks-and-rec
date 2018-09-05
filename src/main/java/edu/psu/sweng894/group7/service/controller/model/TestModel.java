package edu.psu.sweng894.group7.datastore.service.controller.model;

import org.springframework.stereotype.Component;


@Component
public class TestModel {

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    String course=null;
    String cohort=null;










}
