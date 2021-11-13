package smallpawsproject.rmi;

import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientRMI extends Remote
{
  void registerPetOwner(PetOwner petOwner) throws RemoteException;

  void connect() throws RemoteException;
  public static final String CLIENT_SERVICE_NAME = "rmi://localhost/client";
  List<PetOwner> getPetOwners() throws RemoteException;
  List<EndUser> getUsers() throws RemoteException;
}
