package smallpawsproject.securityjwt.http;

//JSON auth requst -> AuthRequest(obj)
public class AuthRequest
{
  private String userName;
  private String password;
  private String role;
  public AuthRequest()
  {
  }

  public AuthRequest(String userName, String password, String role)
  {
    this.userName = userName;
    this.password = password;
    this.role = role;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
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
