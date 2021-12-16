package smallpawsproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.PetOwnerService;
import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class PetOwnerServiceImpl implements PetOwnerService
{
  @Autowired
  private final ClientFactory clientFactory;

  private ClientRMI client;
  private List<PetOwner> petOwners;

  @Autowired
  public PetOwnerServiceImpl (){
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
     petOwners = client.getPetOwners();

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * This method checks if petOwner with same ID, Username or email already exists
   * and if not it will create a new object of PetOwner
   * @param petOwner is object of PetOwner
   * @return Method return message according to if new petOwner was created or not
   */

  @Override public int registerPetOwner(PetOwner petOwner)
  {
    for (PetOwner owner : petOwners) {
      if (owner.getUserName().equals(petOwner.getUserName())
              || owner.getId().equals(petOwner.getId())
              || owner.getEmail().equals(petOwner.getEmail())) {
        return HttpServletResponse.SC_CONFLICT;
      }
    }

    try
    {
      client.registerPetOwner(petOwner);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    petOwners.add(petOwner);
    return HttpServletResponse.SC_CREATED;
  }

  /**
   * This method is used to check if there is already User with same username
   * @param userName is passed from 1st tier
   * @return This method returns different message according to if username is already existing or not
   */

  @Override public int checkUsername(String userName)
  {
    System.out.println(userName);
    int answer = 0;
    for(PetOwner user : petOwners){
      if(user.getUserName().equals(userName)){
        answer = HttpServletResponse.SC_FORBIDDEN;
        break;
      }
      else{
        answer = HttpServletResponse.SC_ACCEPTED;
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
  public PetOwner getUserById(int id) {
    try {
      petOwners = client.getPetOwners();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    PetOwner temp = new PetOwner();
    for(PetOwner petOwner:petOwners){
      if(petOwner.getUserId()==id){
        temp = petOwner;
        break;
      }
    }


    return temp;
  }

  @Override
  public void setClient(ClientRMI clientRMI) {
    this.client = clientRMI;
  }

  public List<PetOwner> getPetOwners() {
    return petOwners;
  }
}
