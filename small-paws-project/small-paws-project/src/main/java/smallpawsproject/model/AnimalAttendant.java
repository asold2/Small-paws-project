package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AnimalAttendant extends EndUser
{


  @JsonCreator
  public AnimalAttendant(String userName, String password, String email)
  {
    super(userName, password, email);
  }
  public AnimalAttendant(){}

}
