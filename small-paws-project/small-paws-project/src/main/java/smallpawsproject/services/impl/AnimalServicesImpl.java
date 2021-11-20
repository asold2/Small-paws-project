package smallpawsproject.services.impl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;
import smallpawsproject.rmi.ClientFactory;
import smallpawsproject.rmi.ClientRMI;
import smallpawsproject.services.AnimalServices;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class AnimalServicesImpl implements AnimalServices
{
  private JSONArray jsonArray = new JSONArray();
  private JSONObject jsonObject;
  JSONParser parser = new JSONParser();

  @Autowired
  private final ClientFactory clientFactory;

  private final ClientRMI client;

  public AnimalServicesImpl(ClientFactory clientFactory) {

    this.clientFactory = clientFactory;
    client = clientFactory.getClient();

    try {
      client.connect();
    }catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void AddAnimal(Animal animal)
  {
    try
    {
      client.addAnimal(animal);
      System.out.println("Sending to server");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public JSONArray GetAnimals()
  {
return null;
  }
}
