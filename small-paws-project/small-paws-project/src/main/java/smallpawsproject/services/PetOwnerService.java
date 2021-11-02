package smallpawsproject.services;

//import org.springframework.security.core.userdetails.UserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;

import java.util.List;
@Service
public interface PetOwnerService
{
  int registerPetOwner(PetOwner petOwner);

  int authenticatePetOwner(String username, String password)
      throws JsonProcessingException;
//  void save(List<PetOwner> list);
}
