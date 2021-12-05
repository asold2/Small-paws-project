package smallpawsproject.rmi;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Server extends Remote
{

  void registerPetOwner(PetOwner petOwner) throws RemoteException;
  List<PetOwner> getPetOwners() throws RemoteException;
  List<EndUser> getAccounts() throws RemoteException;
  void addAnimal(Animal animal) throws RemoteException;
  List<Animal> getAnimals() throws RemoteException;


  Animal updateAnimal(Animal animal) throws RemoteException;

  void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException;
  List<AdoptionRequest> getAdoptionRequests() throws RemoteException;

  AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) throws RemoteException;


}
