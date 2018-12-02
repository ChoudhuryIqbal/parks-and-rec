package edu.psu.sweng894.group7.datastore.service;
import edu.psu.sweng894.group7.datastore.entity.Leagues;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

/*
 *	Steps for TDD.
 *
 * 1. Do basic  implementation of the functionality.
 * 2. start with simple test case and make it pass.
 * 3. Add details to the implementation.
 * 4. Expand test case and repeat the steps.
 */
/*
Test cases to test LeagueService. This service deals with persistence layer. we do not need the
persistence implementation to test the functionality, so we use mock objects.
*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Config.class})
public class LeagueServiceTests {
    @Autowired
    private Leagues league;
    @Autowired
    LeagueService leagueService;
    @Before
    public void setUp() {
        Mockito.when(leagueService.find(1l)).thenReturn(league);
        Mockito.when(leagueService.insert(league)).thenReturn(league.getLeagueId());
    }
    @Test
    public void findTest() {
        Leagues league = leagueService.find(1l);
        assertTrue(league.getLeagueId()==league.getLeagueId());
    }
    @Test
    public void insertTest() {
        long id =leagueService.insert(league);
        assertTrue(id==1l);
    }

    @Test
    public void updateTestPass() {
        leagueService.update(league);
        assertTrue(leagueService.find(league.getLeagueId())==league);
    }
}