package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.RegistrationService;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService
{
  private PetOwnerRepository petOwnerRepository;

  @Autowired
  public void setPetOwnerRepository(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }




  @Override public List<PetOwner> getAllPetOwners()
  {
    return (List<PetOwner>) petOwnerRepository.findAll();
  }

  @Override public PetOwner registerPetOwner(PetOwner petOwner)
  {
    return null;
  }
}
