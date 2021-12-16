package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.EndUser;
import smallpawsproject.model.Veterinarian;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.UsersService;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService
{
  @Autowired
  private final ClientFactory clientFactory;
  private  ClientRMI client;
  private List<EndUser> users = new ArrayList<>();

  @Autowired
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

  /**
   * This method checks if any users username and password matched the sent ones.
   * @param userName is passed from 1st tier
   * @param password is passed from 1st tier
   * @return Method returns specific user if he matched the sent username and password
   * if no user matches these parameters it will return null
   */

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

  /**
   * This method gets user according to its specific id
   * @param id indicates the user that the functionality must be used upon
   * @return Method returns specific petOwner according to the id
   */

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


  @Override
  public void setClient(ClientRMI clientRMI) {
    this.client = clientRMI;
  }
}
