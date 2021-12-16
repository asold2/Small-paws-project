package smallpawsproject.rmi;

import org.springframework.stereotype.Component;
import smallpawsproject.data_access.DataAccess;
import smallpawsproject.model.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * This is the server that accepts the clients and communicates
 * with them to pass the information to the DataAccess class
 * that will be stored or taken from the database.
 */
public class ServerImpl implements Server
{
  private final DataAccess dataAccess;

  public ServerImpl(DataAccess dataAccess){
    this.dataAccess = dataAccess;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
      throws RemoteException
  {
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
  public void updateAnimal(Animal animal) throws RemoteException {
    dataAccess.updateAnimal(animal);
  }

  @Override
  public void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException {
    dataAccess.makeNewRequest(adoptionRequest);
  }

  @Override
  public List<AdoptionRequest> getAdoptionRequests() throws RemoteException {
    return dataAccess.getAdoptionRequests();
  }

  @Override
  public void updateAdoptionRequest(AdoptionRequest adoptionRequest) {
    dataAccess.updateAdoptionRequest(adoptionRequest);
  }

  @Override
  public void addCertificate(Certificate certificate) throws RemoteException {
    dataAccess.addCertificate(certificate);
  }

  @Override
  public List<Certificate> getCertificates() throws RemoteException {
    return dataAccess.getCertificates();
  }
}
