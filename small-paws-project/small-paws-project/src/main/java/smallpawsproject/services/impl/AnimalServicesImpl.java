package smallpawsproject.services.impl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.AnimalServices;

import java.io.*;
import java.util.ArrayList;

@Service
public class AnimalServicesImpl implements AnimalServices
{
  private JSONArray jsonArray = new JSONArray();
  private JSONObject jsonObject;
  JSONParser parser = new JSONParser();


  @Override public void AddAnimal(Animal animal)
  {
    try
    {
      FileReader reader = new FileReader(new File("../small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/animals.json"));
      jsonArray = (JSONArray) parser.parse(reader);
    }
    catch (FileNotFoundException | ParseException e)
    {
      e.printStackTrace();
    }

      jsonObject = new JSONObject();
      jsonObject.put("TypeOfAnimal", animal.getTypeOfAnimal());
      jsonObject.put("Age", animal.getAge());
      jsonObject.put("Description", animal.getDescription());

      jsonArray.add(jsonObject);

      try {
        FileWriter fileWriter = new FileWriter("../small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/animals.json");

        fileWriter.write(jsonArray.toJSONString());
        fileWriter.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

  @Override public JSONArray GetAnimals()
  {
    try
          {
            FileReader reader = new FileReader(new File("../small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/animals.json"));
            jsonArray = (JSONArray) parser.parse(reader);

          }
          catch (FileNotFoundException | ParseException e)
          {
            e.printStackTrace();
          }
    return jsonArray;
  }
}
