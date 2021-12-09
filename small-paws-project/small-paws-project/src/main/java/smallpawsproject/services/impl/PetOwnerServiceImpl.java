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
import smallpawsproject.model.EndUser;
import smallpawsproject.model.PetOwner;

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
  @Autowired
  private  ClientFactory clientFactory;

  private  ClientRMI client;
  private List<PetOwner> petOwners;


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
    System.out.println("At the beggining of method");
    for(int i=0; i<petOwners.size(); i++){
      if(petOwners.get(i).getUserName().equals(petOwner.getUserName())
          || petOwners.get(i).getId() == (petOwner.getId())
          || (petOwners.get(i).getFirstName().equals(petOwner.getFirstName()) && petOwners.get(i).getLastName().equals(petOwner.getLastName()))
          || petOwners.get(i).getEmail().equals(petOwner.getEmail())){
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
    PetOwner temp = null;
    for(PetOwner petOwner:petOwners){
      if(petOwner.getUserId()==id){
        temp = petOwner;
        break;
      }
    }


    return temp;
  }

}
