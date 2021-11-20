package smallpawsproject.securityjwt.http;

public class AuthErrorResponse implements AuthResponseInterface
{
  private String error;

  public AuthErrorResponse(String error)
  {
    this.error = error;
  }

  public AuthErrorResponse()
  {
  }

  public String getError()
  {
    return error;
  }

  public void setError(String error)
  {
    this.error = error;
  }
}
