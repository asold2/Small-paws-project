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
  private int userId;
  private String userName;
  private String password;
  private String email;


public EndUser(){}

  public EndUser(String userName, String password, String email)
  {
    this.userName = userName;
    this.password = password;
    this.email = email;
  }


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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
