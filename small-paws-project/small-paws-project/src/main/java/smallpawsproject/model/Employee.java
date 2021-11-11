package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
public class Employee
{
  @Id
  @GeneratedValue
  private int id;
  private role role;
  public enum role{Veterinarian, AnimalAttendant}

  private Account account;

  public Employee(){};

  @JsonCreator
  public Employee(@JsonProperty("id") int id ,@JsonProperty("role") role userRole)
  {
    this.id = id;

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

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }
}
