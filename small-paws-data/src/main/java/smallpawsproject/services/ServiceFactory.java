package smallpawsproject.services;

import smallpawsproject.repositories.AccountsRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.impl.AccountsServiceImpl;
import smallpawsproject.services.impl.PetOwnerServiceImpl;

public class ServiceFactory
{
  private PetOwnerService petOwnerService;
  private AccountsService accountsService;



  private final AccountsRepository accountsRepository;
  private final PetOwnerRepository petOwnerRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository, AccountsRepository accountsRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
    this.accountsRepository = accountsRepository;

  }
  public AccountsService getAccountService(){
    if(accountsService==null){
      accountsService = new AccountsServiceImpl(accountsRepository);
      return accountsService;
    }
    return accountsService;
  }

  public PetOwnerService getPetOwnerService(){
    if(petOwnerService==null){
      petOwnerService = new PetOwnerServiceImpl(petOwnerRepository);
      return petOwnerService;
    }
    return petOwnerService;
  }
}
