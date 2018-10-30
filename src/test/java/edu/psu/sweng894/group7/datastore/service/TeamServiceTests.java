package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Teams;
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
public class TeamServiceTests {

    @Autowired
    private Teams team;
    @Autowired
    TeamService teamService;
    @Before
    public void setUp() {
        Mockito.when(teamService.find(1l)).thenReturn(team);
        Mockito.when(teamService.insert(team)).thenReturn(team.getTeamId());
    }

    @Test
    public void findTest(){
        Teams sportTest = teamService.find(1l);
        assertTrue(sportTest.getTeamId()==team.getTeamId());
    }

    @Test
    public void insertTest(){
        long id =teamService.insert(team);
        assertTrue(id==1l);
    }

    @Test
    public void updateTest(){

    }

    @Test
    public void deleteTest(){

    }
}
