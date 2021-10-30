package smallpawsproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veterinarian extends UserLogIn
{
  @Id
  private Long id;
  private String userName;
  private String password;

  public Veterinarian(Long id,String userName, String password)
  {
    super(id,userName,password);
  }
  public Veterinarian(){}

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
