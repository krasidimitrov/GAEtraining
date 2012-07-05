package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
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
    
    assertThat(datastoreService.get(key), is(equalTo(entity)));
  }

  @Test
  public void returnUserEntity(){
    Entity entity = new Entity("User", "kpackapgo");
    entity.setProperty("password", "abc123");
    entity.setProperty("email", "555@mail.bg");
    userRepository.add(entity);

    //Do i need to compare each property for this test?
    assertThat(userRepository.getUserByUsername("kpackapgo"), is(equalTo(entity)));
  }
  
  @Test
  public void addExpensesToUser(){

    Entity entity = new Entity("User", "kpackapgo");
    entity.setProperty("password", "abc123");
    entity.setProperty("email", "555@mail.bg");
    String username = "kpackapgo";


    Expenses expenses = new Expenses("gas money", 13.25);

    userRepository.add(entity);
    userRepository.addExpenses(username, expenses);

    Query query = new Query("Expenses");
    query.setAncestor(entity.getKey());
    query.setFilter(new Query.FilterPredicate("expensesName", Query.FilterOperator.EQUAL, "gas money"));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    Entity actualExpensesEntity = preparedQuery.asSingleEntity();

    assertThat((String) actualExpensesEntity.getProperty("expensesName"), is(equalTo(expenses.getExpensesName())));
    assertThat((Double) actualExpensesEntity.getProperty("expensesValue"), is(equalTo(expenses.getExpensesValue())));
  }

  @Test
  public void returnListOfExpenses(){
    Entity entity = new Entity("User", "kpackapgo");
    entity.setProperty("password", "abc123");
    entity.setProperty("email", "555@mail.bg");
    String username = "kpackapgo";
    userRepository.add(entity);

    for(int i=0; i<10; i++){
      Expenses expenses = new Expenses("expensesType"+i, 2*i+1);
      userRepository.addExpenses(username, expenses);
    }

    Query query = new Query("Expenses");
    query.setAncestor(entity.getKey());
    query.setFilter(new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.GREATER_THAN, entity.getKey()));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
//    int actualEntitiesCount = preparedQuery.countEntities(FetchOptions.Builder.withDefaults());

    int actualListSize = userRepository.getAllExpenses(username).size();
    assertThat(actualListSize, is(equalTo(10)));
  }

}
