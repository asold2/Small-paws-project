package smallpawsproject.services.impl;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.RegistrationService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService
{

  private JSONObject jsonObject = new JSONObject();
  private PetOwnerRepository petOwnerRepository;

  @Autowired
  public void setPetOwnerRepository(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    jsonObject.put("id", petOwner.getId());
    jsonObject.put("fisrtName", petOwner.getFirstName());
    jsonObject.put("lastName", petOwner.getLastName());
    jsonObject.put("age", petOwner.getAge());
    jsonObject.put("sex", petOwner.getSex());
    jsonObject.put("familyStatus", petOwner.getFamilyStatus());
    jsonObject.put("avgIncome", petOwner.getAvgIncome());
    jsonObject.put("address", petOwner.getAdress());
    jsonObject.put("jobTitle", petOwner.getJobTitle());
    jsonObject.put("username", petOwner.getUserName());
    jsonObject.put("password", petOwner.getPassword());

    try {
      FileWriter file = new FileWriter("accounts.json");
      file.write(jsonObject.toJSONString());
      file.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }



    //    petOwnerRepository.save(petOwner);

  }
}
