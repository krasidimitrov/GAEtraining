package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

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
}
