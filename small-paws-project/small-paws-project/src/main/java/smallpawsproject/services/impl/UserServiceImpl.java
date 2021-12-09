package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.UsersService;

import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService
{
  @Autowired
  private ClientFactory clientFactory;
  private ClientRMI client;
  private List<EndUser> users = new ArrayList<>();

  public UserServiceImpl(){
    this.clientFactory = new ClientFactory();
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
      System.out.println(client.getUsers() + "!!!!!!!!!!!!!!!");

      users = client.getUsers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<EndUser> getUsers()
  {
    try
    {
      return client.getUsers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public EndUser check(String userName, String password)
  {
    users = getUsers();
    EndUser answer = new EndUser();
    for(EndUser user : users){
      if(user.getUserName().equals(userName)&&user.getPassword().equals(password)){
        answer = user;
        break;
      }
      else{
        answer = null;
      }
    }
    return answer;
  }

  @Override
  public Veterinarian getUserById(int id) {
    EndUser temp = new Veterinarian();
    for(EndUser e : users){
      if(e.getUserId()==id){
        temp = e;
        break;
      }
    }

    return (Veterinarian) temp;
  }
}
