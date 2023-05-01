package ru.croc.Users;

public class Users {

  private int id;
  private String userName;

  public Users(int id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public Users(String userName) {
    this.userName = userName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
