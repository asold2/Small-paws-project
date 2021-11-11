package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;

@Entity
public class AnimalAttendant extends Employee
{


  @JsonCreator
  public AnimalAttendant(String userName, String password, role userRole)
  {
    super( userName, password, userRole);
  }
  public AnimalAttendant(){}


}
