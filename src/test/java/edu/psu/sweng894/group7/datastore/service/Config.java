package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Config {
    @MockBean
    private EntityManager entityManager;
    @MockBean
    UserService userService;
    @Bean
    public EntityManager entityManager(){
        return entityManager;
    }
    @Bean
    public UserService userService(){
        return userService;
    }
    @Bean
    public AppUser appUsers() {
        AppUser appUser = new AppUser();
        appUser.setId(1l);
        appUser.setUsername("TestUser");
        appUser.setPassword("TestPassword");
        List<UserRole> roles = new ArrayList<>();
        UserRole role = new UserRole();
        role.setDescription("Test Role Description");
        role.setId(1l);
        role.setRolename("TestRole");
        roles.add(role);
        appUser.setRoles(roles);
        return appUser;
    }
}