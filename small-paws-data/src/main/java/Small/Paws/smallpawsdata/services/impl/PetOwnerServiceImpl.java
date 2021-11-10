package Small.Paws.smallpawsdata.services.impl;

import Small.Paws.smallpawsdata.model.PetOwner;
import Small.Paws.smallpawsdata.repositories.PetOwnerRepository;
import Small.Paws.smallpawsdata.services.PetOwnerService;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerServiceImpl implements PetOwnerService
{
  private final PetOwnerRepository petOwnerRepository;

  public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    petOwnerRepository.save(petOwner);
  }
}
