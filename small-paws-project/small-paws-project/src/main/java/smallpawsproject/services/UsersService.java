package smallpawsproject.services;

import smallpawsproject.model.EndUser;
import smallpawsproject.model.Veterinarian;

import java.util.List;

public interface UsersService
{
  List<EndUser> getUsers();
  EndUser check(String userName, String password);

  Veterinarian getUserById(int id);
}
