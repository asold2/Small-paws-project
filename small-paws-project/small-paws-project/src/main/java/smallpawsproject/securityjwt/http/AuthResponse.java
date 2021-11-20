package smallpawsproject.securityjwt.http;

public class AuthResponse implements AuthResponseInterface
{
  private String jwt;

  public AuthResponse(String jwt)
  {
    this.jwt = jwt;
  }

  public AuthResponse()
  {
  }

  public String getJwt()
  {
    return jwt;
  }

  public void setJwt(String jwt)
  {
    this.jwt = jwt;
  }
}
