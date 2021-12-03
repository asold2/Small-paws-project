package smallpawsproject.model;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "end_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class EndUser implements Serializable
{

  private static final long serialVersionUID = 663126647076776891L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;
  private String userName;
  private String password;
  private String role;
  private String email;


public EndUser(){}

  public EndUser(String userName, String password, String email, String role)
  {
    this.role = role;
    this.userName = userName;
    this.password = password;
    this.email = email;
  }


  public EndUser(Integer Id, String userName, String password,String role)
  {
    userId = Id;
    this.userName = userName;
    this.password = password;
    this.role = role;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
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

  public String getEmail() {
    return email;
  }
  public String getRole()
  {
    return role;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public void setRole(String role)
  {
    this.role = role;
  }
}
