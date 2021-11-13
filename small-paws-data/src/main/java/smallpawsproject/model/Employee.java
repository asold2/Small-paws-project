package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Employee extends EndUser
{
  private int id;
  private role role;
  public enum role{Veterinarian, AnimalAttendant}


  public Employee(){};

  @JsonCreator
  public Employee(@JsonProperty("userName")String userName, @JsonProperty("password") String password, role userRole)
  {
    super(userName, password);
    this.role = userRole;
  }

  public role getRole()
  {
    return role;
  }

  public void setRole(role role)
  {
    this.role = role;
  }


}
