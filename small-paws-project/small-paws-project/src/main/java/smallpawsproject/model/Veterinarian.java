package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Verianarian")
@Entity
public class Veterinarian extends EndUser
{

  @JsonCreator
  public Veterinarian(String userName, String password, String role)
  {
    super(userName, password, role);
  }
  public Veterinarian(){}


}
