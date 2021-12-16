package smallpawsproject.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.model.*;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientRMIImpl extends UnicastRemoteObject implements ClientRMI
{
  @Autowired
  private Server server;
  Registry registry = LocateRegistry.getRegistry("localhost", 1090);


  public ClientRMIImpl() throws RemoteException
  {
    super();
  }

  @Override public void connect()
  {
    try
    {
      server = (Server) registry.lookup("ServerTier3");
    }
    catch (NotBoundException | RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    try
    {
      server.registerPetOwner(petOwner);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<PetOwner> getPetOwners() throws RemoteException
  {
    return server.getPetOwners();
  }

  @Override public List<EndUser> getUsers() throws RemoteException
  {
    return server.getAccounts();
  }

  @Override
  public List<Animal> getAnimals() throws RemoteException {
    return server.getAnimals();
  }

  @Override
  public void addAnimal(Animal animal) throws RemoteException {
    server.addAnimal(animal);
  }

  @Override
  public void updateAnimal(Animal animal) throws RemoteException {
    server.updateAnimal(animal);
  }

  @Override
  public void setServer(Server server) throws RemoteException {
    this.server = server;
  }

  @Override
  public List<AdoptionRequest> getAdoptionRequests() throws RemoteException {
    return server.getAdoptionRequests();
  }

  @Override
  public void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException {

      server.makeNewRequest(adoptionRequest);
  }

  @Override
  public void updateAdoptionRequest(AdoptionRequest adoptionRequest) throws RemoteException {
    server.updateAdoptionRequest(adoptionRequest);
  }

  @Override
  public void addCertificate(Certificate certificate) throws RemoteException {
    server.addCertificate(certificate);
  }

  @Override
  public List<Certificate> getCertificates() throws RemoteException {
    return server.getCertificates();
  }

}
