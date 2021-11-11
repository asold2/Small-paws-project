package smallpawsproject.services;

import smallpawsproject.model.PetOwner;

import java.util.List;

public interface PetOwnerService
{
  void registerPetOwner(PetOwner petOwner);
  List<PetOwner> getPetOwners();
}
