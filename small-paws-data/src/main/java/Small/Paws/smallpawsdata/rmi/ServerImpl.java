package Small.Paws.smallpawsdata.rmi;

import Small.Paws.smallpawsdata.dataaccess.DataAccess;
import Small.Paws.smallpawsdata.model.PetOwner;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerImpl implements Server
{
  private DataAccess dataAccess;

  public ServerImpl(DataAccess dataAccess){
    this.dataAccess = dataAccess;
    try
    {
      Registry registry = LocateRegistry.createRegistry(1090);
      registry.bind("ServerTier3", this);
      System.out.println("Registry in server impl constr.");
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void registerPetOwner(PetOwner petOwner)
      throws RemoteException
  {
    dataAccess.registerPetOwner(petOwner);
  }

  @Override public String test()
  {
    return "test";
  }
}
