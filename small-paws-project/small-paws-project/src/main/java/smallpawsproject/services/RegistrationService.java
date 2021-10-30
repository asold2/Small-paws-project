package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;

import java.util.List;
@Service
public interface RegistrationService
{
  void registerPetOwner(PetOwner petOwner);

}
