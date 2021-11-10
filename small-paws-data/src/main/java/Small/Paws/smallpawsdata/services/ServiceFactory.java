package Small.Paws.smallpawsdata.services;

import Small.Paws.smallpawsdata.repositories.PetOwnerRepository;
import Small.Paws.smallpawsdata.services.impl.PetOwnerServiceImpl;

public class ServiceFactory
{
  private PetOwnerService petOwnerService;




  private final PetOwnerRepository petOwnerRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  public PetOwnerService getPetOwnerService(){
    if(petOwnerService==null){
      petOwnerService = new PetOwnerServiceImpl(petOwnerRepository);
      return petOwnerService;
    }
    return petOwnerService;
  }
}
