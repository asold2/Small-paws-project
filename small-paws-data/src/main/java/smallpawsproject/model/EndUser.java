package smallpawsproject.model;



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
  private String role;


public EndUser(){}

  public EndUser(String userName, String password,String role)
  {
    this.role = role;
    this.userName = userName;
    this.password = password;
  }


  public EndUser(int Id, String userName, String password,String role)
  {
    userId = Id;
    this.userName = userName;
    this.password = password;
    this.role = role;
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

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }
}
