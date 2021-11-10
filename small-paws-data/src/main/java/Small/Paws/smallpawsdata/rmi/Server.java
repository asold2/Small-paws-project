package Small.Paws.smallpawsdata.rmi;

import Small.Paws.smallpawsdata.model.PetOwner;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote
{
  public static final String ServerTier3 = "rmi://localhost/ServerTier3";

  //  public static final String T3_SERVICE_NAME = "rmi://localhost/T3";
  void registerPetOwner(PetOwner petOwner) throws RemoteException;
  String test() throws RemoteException;

}
