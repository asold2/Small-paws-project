package smallpawsproject.data_access;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataAccessImpl implements DataAccess
{
  @Autowired
  private ServiceFactory serviceFactory;
  private static DataAccess dataAccessInstance = null;

  public void setServicefactory(ServiceFactory serviceFactory){
    this.serviceFactory = serviceFactory;
  }


  private DataAccessImpl()
  {
  }

  public static synchronized DataAccess dataAccess(){
    if(dataAccessInstance==null){
      dataAccessInstance = new DataAccessImpl();
    }
    return dataAccessInstance;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    serviceFactory.getPetOwnerService().registerPetOwner(petOwner);
  }


  @Override public List<PetOwner> getPetOwners()
  {
    return serviceFactory.getPetOwnerService().getPetOwners();
  }

  @Override public List<Animal> getAnimals()
  {
    return serviceFactory.getAnimalService().getAnimals();
  }

  @Override public List<EndUser> getUsers()
  {
    System.out.println("In method to get users");
    return serviceFactory.getUserService().getUsers();

  }

  @Override
  public void addAnimal(Animal animal) {
    serviceFactory.getAnimalService().addAnimal(animal);
  }

  @Override
  public Animal updateAnimal(Animal animal) {
    return serviceFactory.getAnimalService().updateAnimal(animal);
  }

  @Override
  public List<AdoptionRequest> getAdoptionRequests() {
    return serviceFactory.getAdoptionRequestService().getAdoptionRequests();
  }

  @Override
  public void makeNewRequest(AdoptionRequest adoptionRequest) {
      serviceFactory.getAdoptionRequestService().makeNewRequest(adoptionRequest);
  }

  @Override
  public AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) {
    return serviceFactory.getAdoptionRequestService().updateAdoptionRequest(adoptionRequest);
  }
}
