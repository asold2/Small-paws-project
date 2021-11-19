package smallpawsproject.services;

import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.impl.AnimalServiceImpl;
import smallpawsproject.services.impl.UsersServiceImpl;
import smallpawsproject.services.impl.PetOwnerServiceImpl;

public class ServiceFactory
{
  private PetOwnerService petOwnerService;
  private UsersService usersService;
  private AnimalService animalService;



  private final UsersRepository usersRepository;
  private final PetOwnerRepository petOwnerRepository;
  private final AnimalRepository animalRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository, UsersRepository usersRepository, AnimalRepository animalRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
    this.usersRepository = usersRepository;
    this.animalRepository = animalRepository;

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
}
