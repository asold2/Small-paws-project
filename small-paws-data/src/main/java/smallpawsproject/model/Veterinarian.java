package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.*;

@Table(name = "Verianarian")
@Entity
public class Veterinarian extends EndUser
{

  @OneToMany(mappedBy = "veterinarianId")
  private List<AdoptionRequest> myDecisions;



  @JsonCreator
  public Veterinarian(String userName, String password, String email, String role)
  {
    super(userName, password, email, role);
  }
  public Veterinarian(){}




}
