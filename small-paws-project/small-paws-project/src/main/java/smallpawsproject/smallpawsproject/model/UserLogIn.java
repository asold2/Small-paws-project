package smallpawsproject.smallpawsproject.model;

public abstract class UserLogIn
{
  String username;
  String password;

  public UserLogIn(String username, String password)
  {
    this.username = username;
    this.password = password;
  }
}
