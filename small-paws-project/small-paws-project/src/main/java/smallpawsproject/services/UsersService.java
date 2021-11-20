package smallpawsproject.services;

import smallpawsproject.model.EndUser;

import java.util.List;

public interface UsersService
{
  List<EndUser> getUsers();
  int check(String userName, String password);
}
