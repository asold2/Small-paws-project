package smallpawsproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLogIn
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  String userName;
  String password;

  public UserLogIn(){};

  public UserLogIn(String userName, String password)
  {
    this.userName = userName;
    this.password = password;
  }
}
