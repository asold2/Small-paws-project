package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.PetOwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetOwnerServiceImpl implements PetOwnerService
{
  @Autowired
  private final PetOwnerRepository petOwnerRepository;

  public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    petOwnerRepository.save(petOwner);
  }

  @Override public List<PetOwner> getPetOwners()
  {
    return petOwnerRepository.findAll();
  }
}
