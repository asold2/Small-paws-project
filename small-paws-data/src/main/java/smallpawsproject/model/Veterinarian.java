package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Verianarian")
@Entity
public class Veterinarian extends EndUser
{
//  @Id
//  private Long id;
//  private String userName;
//  private String password;
//  private role role;

  @JsonCreator
  public Veterinarian(String userName, String password)
  {
    super(userName, password);
  }
  public Veterinarian(){}

//  @Override public Long getId()
//  {
//    return id;
//  }
//
//  @Override public void setId(Long id)
//  {
//    this.id = id;
//  }
//
//  @Override public role getRole()
//  {
//    return role;
//  }
//
//  @Override public void setRole(role role)
//  {
//    this.role = role;
//  }
//
//  public String getUserName()
//  {
//    return userName;
//  }
//
//  public void setUserName(String userName)
//  {
//    this.userName = userName;
//  }
//
//  public String getPassword()
//  {
//    return password;
//  }
//
//  public void setPassword(String password)
//  {
//    this.password = password;
//  }
}
