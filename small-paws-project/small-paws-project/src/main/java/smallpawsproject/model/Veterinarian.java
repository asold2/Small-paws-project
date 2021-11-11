package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Veterinarian extends Employee
{

  @JsonCreator
  public Veterinarian(int id, role userRole)
  {
    super(id,userRole);
  }
  public Veterinarian(){}


}
