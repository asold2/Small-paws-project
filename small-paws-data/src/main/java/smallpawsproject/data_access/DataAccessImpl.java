package smallpawsproject.data_access;

import smallpawsproject.model.*;
import smallpawsproject.services.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * This is the class that uses all the services that connect to the database.
 */
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
    return serviceFactory.getUserService().getUsers();

  }

  @Override
  public void addAnimal(Animal animal) {
    serviceFactory.getAnimalService().addAnimal(animal);
  }

  @Override
  public void updateAnimal(Animal animal) {
    serviceFactory.getAnimalService().updateAnimal(animal);
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
  public void updateAdoptionRequest(AdoptionRequest adoptionRequest) {
    serviceFactory.getAdoptionRequestService().updateAdoptionRequest(adoptionRequest);
  }

  @Override
  public void addCertificate(Certificate certificate) {
    serviceFactory.getCertificateService().addCertificate(certificate);
  }

  @Override
  public List<Certificate> getCertificates() {
    return serviceFactory.getCertificateService().getCertificates();
  }


}
