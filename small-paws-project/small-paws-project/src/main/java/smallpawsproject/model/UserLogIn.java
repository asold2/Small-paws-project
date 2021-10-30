package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

@Table(name="LogInUsers")
@Entity
public class UserLogIn
{
  @Id
  @Column(name="User_id")
  @GeneratedValue
  private Long id;
  private String userName;
  private String password;

  public UserLogIn(){};

  @JsonCreator
  public UserLogIn(Long id ,String userName, String password)
  {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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
