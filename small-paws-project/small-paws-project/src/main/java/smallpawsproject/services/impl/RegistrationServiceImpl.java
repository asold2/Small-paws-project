package smallpawsproject.services.impl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.RegistrationService;

import java.io.*;

@Service
public class RegistrationServiceImpl implements RegistrationService
{
    private JSONArray jsonArray = new JSONArray();
  private JSONObject jsonObject;
  private PetOwnerRepository petOwnerRepository;
  JSONParser parser = new JSONParser();


  @Autowired
  public void setPetOwnerRepository(PetOwnerRepository petOwnerRepository)
  {
    this.petOwnerRepository = petOwnerRepository;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    try
    {
      FileReader reader = new FileReader("small-paws-project/small-paws-project/accounts.json");
        jsonArray = (JSONArray) parser.parse(reader);
    }
    catch (FileNotFoundException | ParseException e)
    {
      e.printStackTrace();
    }


    jsonObject = new JSONObject();
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

    jsonArray.add(jsonObject);

    try {
      FileWriter fileWriter = new FileWriter("small-paws-project/small-paws-project/accounts.json");

      fileWriter.write(jsonArray.toJSONString());
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
        petOwnerRepository.save(petOwner);
  }


}
