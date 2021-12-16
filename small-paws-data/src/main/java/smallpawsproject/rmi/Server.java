package smallpawsproject.rmi;

import org.springframework.stereotype.Component;
import smallpawsproject.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
@SuppressWarnings("unused")
public interface Server extends Remote
{
  void registerPetOwner(PetOwner petOwner) throws RemoteException;
  List<PetOwner> getPetOwners() throws RemoteException;
  List<EndUser> getAccounts() throws RemoteException;
  void addAnimal(Animal animal) throws RemoteException;
  List<Animal> getAnimals() throws RemoteException;
  void updateAnimal(Animal animal) throws RemoteException;

  void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException;
  List<AdoptionRequest> getAdoptionRequests() throws RemoteException;
  void updateAdoptionRequest(AdoptionRequest adoptionRequest) throws RemoteException;
  void addCertificate(Certificate certificate) throws RemoteException;
  List<Certificate> getCertificates() throws RemoteException;



}
