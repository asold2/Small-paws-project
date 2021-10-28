package smallpawsproject.smallpawsproject.model;

public class Veterinarian extends UserLogIn
{
  private String userName;
  private String password;

  public Veterinarian(String userName, String password)
  {
    super(userName,password);
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
}
