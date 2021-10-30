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
  String userName;
  String password;

  public UserLogIn(){};

  @JsonCreator
  public UserLogIn(Long id ,String userName, String password)
  {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }
}
