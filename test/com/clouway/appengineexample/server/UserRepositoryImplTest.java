package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRepositoryImplTest {


  DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
  UserRepository userRepository = new UserRepositoryImpl(datastoreService);

  private final LocalServiceTestHelper helper =
          new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

  @Before
  public void setUp() {
    helper.setUp();
  }

  @After
  public void tearDown() {
    helper.tearDown();
  }


  @Test
  public void addEntityToDataStore() throws EntityNotFoundException {

    Entity entity = new Entity("User", "kpackapgo");
    entity.setProperty("password", "abc123");
    entity.setProperty("email", "555@mail.bg");
    
    userRepository.add(entity);
    Key key = entity.getKey();
  }

  @Test
  public void returnUserEntity(){
    Entity entity = new Entity("User", "kpackapgo");
    entity.setProperty("password", "abc123");
    entity.setProperty("email", "555@mail.bg");

    userRepository.add(entity);

    assertThat(entity, is(equalTo(userRepository.getUserByUsername("kpackapgo"))));
  }
}
