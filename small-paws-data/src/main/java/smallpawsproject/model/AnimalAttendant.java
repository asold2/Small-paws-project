package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
public class AnimalAttendant extends EndUser
{


  @JsonCreator
  public AnimalAttendant(String userName, String password, String email, String role)
  {
    super(userName, password, email, role);
  }
  public AnimalAttendant(){}


}
