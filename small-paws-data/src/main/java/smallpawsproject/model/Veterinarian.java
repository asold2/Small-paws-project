package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Verianarian")
@Entity
public class Veterinarian extends EndUser
{


  @JsonCreator
  public Veterinarian(String userName, String password)
  {
    super(userName, password);
  }
  public Veterinarian(){}


}
