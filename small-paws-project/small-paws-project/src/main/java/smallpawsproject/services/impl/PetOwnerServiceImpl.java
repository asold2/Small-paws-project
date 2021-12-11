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


  @Override public int registerPetOwner(PetOwner petOwner)
  {
    System.out.println("At the beginning of method");
    for (PetOwner owner : petOwners) {
      if (owner.getUserName().equals(petOwner.getUserName())
              || owner.getId().equals(petOwner.getId())
              || (owner.getFirstName().equals(petOwner.getFirstName()) && owner.getLastName().equals(petOwner.getLastName()))
              || owner.getEmail().equals(petOwner.getEmail())) {
        return HttpServletResponse.SC_CONFLICT;
      }
    }

    try
    {
      client.registerPetOwner(petOwner);
      System.out.println("Sending to server");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    System.out.println("Pet owner sent to server");
    petOwners.add(petOwner);
    return HttpServletResponse.SC_CREATED;
  }

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

  @Override
  public PetOwner getUserById(int id) {
    try {
      petOwners = client.getPetOwners();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    System.out.println(id + "the correct id");
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
