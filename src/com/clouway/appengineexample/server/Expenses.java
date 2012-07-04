package com.clouway.appengineexample.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Expenses {
  private String expensesName;
  private double expensesValue;
  
  public Expenses(String expensesName, double expensesValue){
    this.expensesName = expensesName;
    this.expensesValue = expensesValue;
  }

  public String getExpensesName() {
    return expensesName;
  }

  public void setExpensesName(String expensesName) {
    this.expensesName = expensesName;
  }

  public double getExpensesValue() {
    return expensesValue;
  }

  public void setExpensesValue(double expensesValue) {
    this.expensesValue = expensesValue;
  }

}
