package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;

@Service
public interface PetOwnerService
{
  int registerPetOwner(PetOwner petOwner);

  int checkUsername(String userName);

  PetOwner getUserById(int id);
}
