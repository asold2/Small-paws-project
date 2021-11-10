package smallpawsproject.rmi;

import smallpawsproject.model.PetOwner;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMI extends Remote
{
  int registerPetOwner(PetOwner petOwner) throws RemoteException;
  String test()  throws RemoteException;
  public static final String CLIENT_SERVICE_NAME = "rmi://localhost/client";
}
