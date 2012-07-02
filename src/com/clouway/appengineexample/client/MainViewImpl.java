package com.clouway.appengineexample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MainViewImpl extends Composite{
  interface MainViewImplUiBinder extends UiBinder<HTMLPanel, MainViewImpl> {
  }

  private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

  @UiField
  Label helloLabel;
  @UiField
  Button logoutButton;

  public MainViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    helloLabel.setText("Hello");
  }

  @UiHandler("logoutButton")
  public void onLogoutButtonClicked(ClickEvent event){
    Window.alert("HAHA");
  }

}