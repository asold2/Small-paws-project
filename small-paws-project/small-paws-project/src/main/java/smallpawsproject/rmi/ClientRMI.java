package smallpawsproject.rmi;

import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientRMI extends Remote
{
  void registerPetOwner(PetOwner petOwner) throws RemoteException;
  void connect() throws RemoteException;
  @SuppressWarnings({"unused", "UnnecessaryInterfaceModifier"})
  public static final String CLIENT_SERVICE_NAME = "rmi://localhost/client";
  List<PetOwner> getPetOwners() throws RemoteException;
  List<EndUser> getUsers() throws RemoteException;
  List<Animal> getAnimals() throws RemoteException;
  void addAnimal(Animal animal) throws RemoteException;
  void updateAnimal(Animal animal) throws RemoteException;
  void setServer(Server server) throws RemoteException;


  List<AdoptionRequest> getAdoptionRequests() throws RemoteException;

  void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException;
  void updateAdoptionRequest(AdoptionRequest adoptionRequest) throws RemoteException;


}
