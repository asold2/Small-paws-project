package smallpawsproject.services;

import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.impl.UsersServiceImpl;
import smallpawsproject.services.impl.PetOwnerServiceImpl;

public class ServiceFactory
{
  private PetOwnerService petOwnerService;
  private UsersService usersService;



  private final UsersRepository usersRepository;
  private final PetOwnerRepository petOwnerRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository, UsersRepository usersRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
    this.usersRepository = usersRepository;

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
}
