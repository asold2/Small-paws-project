package smallpawsproject.services;

import smallpawsproject.model.PetOwner;

import java.util.List;

public interface RegistrationService
{
  List<PetOwner> getAllPetOwners();
  PetOwner registerPetOwner(PetOwner petOwner);

}
