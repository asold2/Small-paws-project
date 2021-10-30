package smallpawsproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AnimalAttendant extends UserLogIn
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String userName;
  private String password;

  public AnimalAttendant(String userName, String password)
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
