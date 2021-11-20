package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class AnimalAttendant extends EndUser
{


  @JsonCreator
  public AnimalAttendant(String userName, String password)
  {
    super(userName, password);
  }
  public AnimalAttendant(){}


}
