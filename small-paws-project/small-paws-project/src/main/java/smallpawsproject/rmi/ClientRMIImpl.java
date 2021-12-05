package smallpawsproject.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.model.AdoptionRequest;
import smallpawsproject.model.Animal;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;

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
  public Animal updateAnimal(Animal animal) throws RemoteException {
    return server.updateAnimal(animal);
  }

  @Override
  public List<AdoptionRequest> getAdoptionRequests() throws RemoteException {
    return server.getAdoptionRequests();
  }

  @Override
  public void makeNewRequest(AdoptionRequest adoptionRequest) throws RemoteException {

      server.makeNewRequest(adoptionRequest);
    System.out.println("getting request over the tiers");
  }

  @Override
  public AdoptionRequest updateAdoptionRequest(AdoptionRequest adoptionRequest) throws RemoteException {
    return server.updateAdoptionRequest(adoptionRequest);
  }

}
