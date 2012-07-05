package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface UserRepository {

  void add(Entity entity);

  Entity getUserByUsername(String username);

  void addExpenses(String username, Expenses expenses);

  List<Expenses> getAllExpenses(String username);
}
