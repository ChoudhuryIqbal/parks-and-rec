package edu.psu.sweng894.group7.service.controller;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.service.UserService;
import edu.psu.sweng894.group7.service.controller.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ControllerConfig.class})
public class ParksRecServiceImplTests {

    @Autowired
    private AppUser appUser;

    @Autowired
    UserModel userModel;

    @Autowired
    ParksRecServiceImpl parksRecServiceImpl;

    @Autowired
    UserService userService;

    //mock all database calls for unit testing.
    @Before
    public void setUp() {
        Mockito.when(userService.find(0l)).thenReturn(appUser);
        Mockito.when(userService.insert(appUser)).thenReturn(appUser.getUserId());
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        Mockito.when(userService.findAll()).thenReturn(appUserList);
    }

    @Test
    public void createUser() throws Exception{
        UserModel responce= parksRecServiceImpl.addUser(userModel);
        assertTrue(responce.getUserId()==userModel.getUserId());

    }

    @Test
    public void getUserById() throws Exception{
        UserModel responce= parksRecServiceImpl.getUserById(appUser.getUserId());
        assertTrue(responce.getUserId()==userModel.getUserId());

    }


}
