package smallpawsproject.services;

import smallpawsproject.model.EndUser;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.rmi.ClientRMI;

import java.util.List;

public interface UsersService
{
  List<EndUser> getUsers();
  EndUser check(String userName, String password);
  Veterinarian getUserById(int id);
  void setClient(ClientRMI clientRMI); // method used to inject the client into the service for testing
}
