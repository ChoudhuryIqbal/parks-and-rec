package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.service.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.service.datastore.entity.UserRole;
import edu.psu.sweng894.group7.datastore.service.datastore.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/*
 *	Steps for TDD.
 *
 * 1. Do basic  implementation of the functionlaity.
 * 2. start with simple test case and make it pass.
 * 3. Add details to the implementation.
 * 4. Expand test case and repeat the steps.
 */


/*
Test cases to test UserService. This service deals with persistance layer. we do not need the
persistance implemenation to test the functionality, so we use mock objects.
*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Configuration.class})
public class UserServiceTests {

	@Autowired
	private AppUser appUser;
	@Autowired
	UserService userService;

	@Before
	public void setUp() {
	Mockito.when(userService.find(1l)).thenReturn(appUser);
		Mockito.when(userService.insert(appUser)).thenReturn(appUser.getId());

	}

	@Test
	public void findTest() {
		AppUser appUser=userService.find(1l);
		assertTrue(appUser.getId()==appUser.getId());

	}

	@Test
	public void insertTest() {
		long id =userService.insert(appUser);
		assertTrue(id==1l);

	}

}
