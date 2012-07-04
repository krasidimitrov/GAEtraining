package com.clouway.appengineexample.server;

import com.google.appengine.api.datastore.Entity;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface UserRepository {

  void add(Entity entity);
}
