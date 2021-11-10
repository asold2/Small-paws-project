package smallpawsproject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.PetOwnerService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.*;
import java.util.Collection;
import java.util.List;
import java.io.File;

@Service
public class PetOwnerServiceImpl implements PetOwnerService
{
//  private JSONArray jsonArray = new JSONArray();
//  private JSONObject jsonObject;
  private PetOwnerRepository petOwnerRepository;
  @Autowired
  private ClientFactory clientFactory;

  private ClientRMI client;
//  JSONParser parser = new JSONParser();
//  private final FileReader reader = new FileReader(new File("../small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/accounts.json"));
//  private List<PetOwner> petOwners;
//  private final ObjectMapper objectMapper = new ObjectMapper();


  public PetOwnerServiceImpl (ClientFactory clientFactory){
    this.clientFactory = clientFactory;
    client = clientFactory.getClient();
    System.out.println("Client started");
  }

  @Autowired
  public void setPetOwnerRepository(PetOwnerRepository petOwnerRepository)
  {

    this.petOwnerRepository = petOwnerRepository;

//    try
//    {
//      jsonArray = (JSONArray) parser.parse(reader);
//    }
//    catch (ParseException e)
//    {
//      e.printStackTrace();
//    }

//    try
//    {
//      petOwners = new ArrayList<>();
//      petOwners = objectMapper.readValue(jsonArray.toJSONString(), new TypeReference<List<PetOwner>>(){});
//    }
//    catch (JsonProcessingException e)
//    {
//      e.printStackTrace();
//    }


  }

  @Override public int registerPetOwner(PetOwner petOwner)
  {

    System.out.println(client);

    //    for(int i=0; i<petOwners.size(); i++){
//      if(petOwners.get(i).getUserName().equals(petOwner.getUserName())
//      || petOwners.get(i).getId().equals(petOwner.getId())
//      || (petOwners.get(i).getFirstName().equals(petOwner.getFirstName()) && petOwners.get(i).getLastName().equals(petOwner.getLastName()))){
//        return HttpServletResponse.SC_CONFLICT;
//      }
//    }

//    jsonObject = new JSONObject();
//    jsonObject.put("id", petOwner.getId());
//    jsonObject.put("firstName", petOwner.getFirstName());
//    jsonObject.put("lastName", petOwner.getLastName());
//    jsonObject.put("age", petOwner.getAge());
//    jsonObject.put("sex", petOwner.getSex());
//    jsonObject.put("familyStatus", petOwner.getFamilyStatus());
//    jsonObject.put("avgIncome", petOwner.getAvgIncome());
//    jsonObject.put("address", petOwner.getAdress());
//    jsonObject.put("jobTitle", petOwner.getJobTitle());
//    jsonObject.put("username", petOwner.getUserName());
//    jsonObject.put("password", petOwner.getPassword());
//
//    jsonArray.add(jsonObject);
//    petOwners.add(petOwner);

//    try {
//      FileWriter fileWriter = new FileWriter(new File("small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/accounts.json"));
//
//      fileWriter.write(jsonArray.toJSONString());
//      fileWriter.close();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//      petOwnerRepository.save(petOwner);
    return HttpServletResponse.SC_CREATED;
  }

  @Override public int authenticatePetOwner(String username, String password)
  {
    int answer = 0;
//    for (PetOwner petOwner : petOwners)
//    {
//      if ((petOwner.getUserName().equals(username)) && (petOwner.getPassword()
//          .equals(password)))
//      {
//            answer = HttpServletResponse.SC_ACCEPTED;
//            break;
//      }
//      else
//      {
//       answer = HttpServletResponse.SC_FORBIDDEN;
//      }
//    }
    return answer;
  }
}
