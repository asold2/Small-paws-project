package smallpawsproject.rmi;

import smallpawsproject.data_access.DataAccess;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;

import java.rmi.RemoteException;
import java.util.List;

public class ServerImpl implements Server
{
  private DataAccess dataAccess;

  public ServerImpl(DataAccess dataAccess){
    this.dataAccess = dataAccess;
    System.out.println("Server started");
  }

  @Override public void registerPetOwner(PetOwner petOwner)
      throws RemoteException
  {
    System.out.println("Pet owner sent to dataAccess");
    dataAccess.registerPetOwner(petOwner);
  }

  @Override public List<PetOwner> getPetOwners() throws RemoteException
  {
    return dataAccess.getPetOwners();
  }

  @Override public List<EndUser> getAccounts() throws RemoteException
  {
    return dataAccess.getUsers();
  }

  @Override
  public void addAnimal(Animal animal) throws RemoteException {
    dataAccess.addAnimal(animal);
  }

  @Override
  public List<Animal> getAnimals() throws RemoteException {
    return dataAccess.getAnimals();
  }

  @Override
  public Animal updateAnimal(Animal animal) throws RemoteException {
    return dataAccess.updateAnimal(animal);
  }

  @Override
  public void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException {
    dataAccess.makeNewRequest(adoptionRequest);
    System.out.println("Sending to dataAccess");
  }

  @Override
  public List<AdoptionRequest> getAdoptionRequests() throws RemoteException {
    return dataAccess.getAdoptionRequests();
  }

  @Override
  public AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) {
    return dataAccess.updateAdoptionRequest(adoptionRequest);
  }
}
