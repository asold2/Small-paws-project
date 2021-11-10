package smallpawsproject.rmi;

import smallpawsproject.model.PetOwner;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote
{
  public static final String ServerTier3 = "rmi://localhost/ServerTier3";
  void registerPetOwner(PetOwner petOwner) throws RemoteException;
  String test()  throws RemoteException;


}
