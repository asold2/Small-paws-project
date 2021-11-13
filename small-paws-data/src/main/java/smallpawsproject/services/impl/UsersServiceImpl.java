package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.model.EndUser;
import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.services.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService
{

  @Autowired
  private final UsersRepository usersRepository;

  public UsersServiceImpl(UsersRepository usersRepository)
  {
    this.usersRepository = usersRepository;
  }

  @Override public List<EndUser> getUsers()
  {
    return usersRepository.findAll();
  }
}
