package smallpawsproject.data_access;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.ServiceFactory;

import java.util.List;

public interface DataAccess
{
  void registerPetOwner(PetOwner petOwner);
  List<PetOwner> getPetOwners();
  List<Animal> getAnimals();
  List<EndUser> getUsers();

  void addAnimal(Animal animal);
  Animal updateAnimal(Animal animal);

  List<AdoptionRequest> getAdoptionRequests();
  void makeNewRequest(AdoptionRequest adoptionRequest);
  AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest);
  void setServicefactory(ServiceFactory serviceFactory);
}
