package smallpawsproject.services;

import org.springframework.stereotype.Component;
import smallpawsproject.repositories.AdoptionRequestRepository;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.impl.AdoptionRequestServiceImpl;
import smallpawsproject.services.impl.AnimalServiceImpl;
import smallpawsproject.services.impl.UsersServiceImpl;
import smallpawsproject.services.impl.PetOwnerServiceImpl;

@Component
public class ServiceFactory
{
  private PetOwnerService petOwnerService;
  private UsersService usersService;
  private AnimalService animalService;
  private AdoptionRequestService adoptionRequestService;



  private final UsersRepository usersRepository;
  private final PetOwnerRepository petOwnerRepository;
  private final AnimalRepository animalRepository;
  private final AdoptionRequestRepository adoptionRequestRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository, UsersRepository usersRepository, AnimalRepository animalRepository, AdoptionRequestRepository adoptionRequestRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
    this.usersRepository = usersRepository;
    this.animalRepository = animalRepository;
    this.adoptionRequestRepository = adoptionRequestRepository;
  }
  public UsersService getUserService(){
    if(usersService ==null){
      usersService = new UsersServiceImpl(usersRepository);
      return usersService;
    }
    return usersService;
  }

  public PetOwnerService getPetOwnerService(){
    if(petOwnerService==null){
      petOwnerService = new PetOwnerServiceImpl(petOwnerRepository);
      return petOwnerService;
    }
    return petOwnerService;
  }

  public AnimalService getAnimalService() {
    if (animalService==null)
    {
      animalService = new AnimalServiceImpl(animalRepository);
    }
    return animalService;
  }
  public AdoptionRequestService getAdoptionRequestService(){
    if(adoptionRequestService==null){
      adoptionRequestService = new AdoptionRequestServiceImpl(adoptionRequestRepository);
    }
    return  adoptionRequestService;
  }

}
