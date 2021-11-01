package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AnimalAttendant extends Employee
{
  @Id
  private Long id;
  private String userName;
  private String password;
  private role role;

  @JsonCreator
  public AnimalAttendant(Long id,String userName, String password, role userRole)
  {
    super(id,userName,password, userRole);
  }
  public AnimalAttendant(){}

  @Override public role getRole()
  {
    return role;
  }

  @Override public void setRole(role role)
  {
    this.role = role;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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
