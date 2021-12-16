package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.EndUser;
import smallpawsproject.repositories.UsersRepository;
import smallpawsproject.services.UsersService;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService
{

  @Autowired
  private final UsersRepository usersRepository;

  /**
   * This is the constructor that initializes the
   * repository for interacting with the database further in the program.
   * @param usersRepository
   */
  public UsersServiceImpl(UsersRepository usersRepository)
  {
    this.usersRepository = usersRepository;
  }

  @Override public List<EndUser> getUsers()
  {
    return  usersRepository.findAll();
  }
}
