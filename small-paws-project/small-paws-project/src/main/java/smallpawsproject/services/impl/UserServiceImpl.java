package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.EndUser;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.UsersService;

import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService
{
  @Autowired
  private ClientFactory clientFactory;
  private ClientRMI client;
  private List<EndUser> users;

  public UserServiceImpl(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
    client = clientFactory.getClient();
    try
    {
      client.connect();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

    try
    {
      users = client.getUsers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<EndUser> getUsers()
  {
    return null;
  }

  @Override public int check(String userName, String password)
  {
    int answer = 0;
    for(EndUser user : users){
      if(user.getUserName().equals(userName)&&user.getPassword().equals(password)){
        answer = HttpServletResponse.SC_ACCEPTED;
        break;
      }
      else{
        answer = HttpServletResponse.SC_FORBIDDEN;
      }
    }
    return answer;
  }
}
