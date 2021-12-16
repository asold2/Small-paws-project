package smallpawsproject.services;

import org.springframework.stereotype.Component;
import smallpawsproject.repositories.*;
import smallpawsproject.services.impl.*;

@Component
public class ServiceFactory
{
  private PetOwnerService petOwnerService;
  private UsersService usersService;
  private AnimalService animalService;
  private AdoptionRequestService adoptionRequestService;
  private CertificateService certificateService;


  private final UsersRepository usersRepository;
  private final PetOwnerRepository petOwnerRepository;
  private final AnimalRepository animalRepository;
  private final CertificateRepository certificateRepository;
  private final AdoptionRequestRepository adoptionRequestRepository;
  public ServiceFactory(PetOwnerRepository petOwnerRepository, UsersRepository usersRepository, AnimalRepository animalRepository, CertificateRepository certificateRepository, AdoptionRequestRepository adoptionRequestRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
    this.usersRepository = usersRepository;
    this.animalRepository = animalRepository;
    this.certificateRepository = certificateRepository;
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

  public CertificateService getCertificateService(){
    if(certificateService==null){
      certificateService = new CertificateServiceImpl(certificateRepository);
    }
    return certificateService;
  }

}
