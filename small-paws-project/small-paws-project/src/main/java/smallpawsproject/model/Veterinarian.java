package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Veterinarian")
@Entity
public class Veterinarian extends EndUser
{

  @JsonCreator
  public Veterinarian(String userName, String password, String email, String role)
  {
    super(userName, password, email, role);
  }
  public Veterinarian(){}


}
