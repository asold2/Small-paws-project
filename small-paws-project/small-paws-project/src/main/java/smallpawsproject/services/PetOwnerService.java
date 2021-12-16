package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.rmi.ClientRMI;

@Service
public interface PetOwnerService
{
  int registerPetOwner(PetOwner petOwner);

  int checkUsername(String userName);

  PetOwner getUserById(int id);
  void setClient(ClientRMI clientRMI); // method used to inject the client into the service for testing

}
