package smallpawsproject.rmi;

import smallpawsproject.dataaccess.DataAccess;
import smallpawsproject.model.Account;
import smallpawsproject.model.PetOwner;

import java.rmi.RemoteException;
import java.util.List;

public class ServerImpl implements Server
{
  private DataAccess dataAccess;

  public ServerImpl(DataAccess dataAccess){
    this.dataAccess = dataAccess;
    System.out.println("Server started");
  }

  @Override public void registerPetOwner(PetOwner petOwner)
      throws RemoteException
  {
    dataAccess.registerPetOwner(petOwner);
    System.out.println("Pet owner sent to dataAccess");
  }

  @Override public List<PetOwner> getPetOwners() throws RemoteException
  {
    return dataAccess.getPetOwners();
  }

  @Override public List<Account> getAccounts() throws RemoteException
  {
    return dataAccess.getAccounts();
  }
}
