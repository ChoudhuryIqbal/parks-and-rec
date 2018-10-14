package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Sport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Config.class})
public class SportServiceTest {

    @Autowired
    private Sport sport;
    @Autowired
    SportService sportService;
    @Before
    public void setUp() {
        Mockito.when(sportService.find(1l)).thenReturn(sport);
        Mockito.when(sportService.insert(sport)).thenReturn(sport.getSportId());
    }

    @Test
    public void findTest(){
        Sport sportTest = sportService.find(1l);
        assertTrue(sportTest.getSportId()==sport.getSportId());
    }

    @Test
    public void insertTest(){
        long id =sportService.insert(sport);
        assertTrue(id==1l);
    }

    @Test
    public void updateTest(){

    }

    @Test
    public void deleteTest(){

    }

}
