package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "end_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class EndUser implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String userName;
  private String password;


public EndUser(){}

  public EndUser(String userName, String password)
  {
    this.userName = userName;
    this.password = password;
  }

  @JsonCreator
  public EndUser(int Id, String userName, String password)
  {
    userId = Id;
    this.userName = userName;
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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
