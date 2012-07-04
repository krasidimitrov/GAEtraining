package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserRepositoryImpl implements UserRepository {


  private final DatastoreService datastoreService;

  public UserRepositoryImpl(DatastoreService datastoreService){

    this.datastoreService = datastoreService;
  }


  @Override
  public void add(Entity entity) {
    datastoreService.put(entity);
  }

  @Override
  public Entity getUserByUsername(String username) {
    Key key = KeyFactory.createKey("User", username);
    try {
      return datastoreService.get(key);
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void addExpenses(String username, Expenses expenses) {
    Entity expensesEntity = new Entity("Expenses", KeyFactory.createKey("User", username));
    expensesEntity.setProperty("expensesName", expenses.getExpensesName());
    expensesEntity.setProperty("expensesValue", expenses.getExpensesValue());

    datastoreService.put(expensesEntity);
  }
}
