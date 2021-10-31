package smallpawsproject.services.impl;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.AnimalRepository;
import smallpawsproject.repositories.PetOwnerRepository;
import smallpawsproject.services.AnimalServices;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class AnimalServicesImpl implements AnimalServices
{
  private JSONArray jsonArray = new JSONArray();
  private JSONObject jsonObject;
  private AnimalRepository animalRepository;
  JSONParser parser = new JSONParser();


  @Autowired
  public void setAnimalRepository(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @Override public void AddAnimal(Animal animal)
  {
      jsonObject = new JSONObject();
      jsonObject.put("TypeOfAnimal", animal.getTypeOfAnimal());
      jsonObject.put("Age", animal.getAge());
      jsonObject.put("Description", animal.getDescription());

      jsonArray.add(jsonObject);

      try {
        FileWriter fileWriter = new FileWriter("small-paws-project/small-paws-project/animals.json");

        fileWriter.write(jsonArray.toJSONString());
        fileWriter.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

  @Override public ArrayList<Animal> GetAnimals()
  {
    ArrayList<Animal> animals = new ArrayList<>();

    try
          {
            FileReader reader = new FileReader("small-paws-project/small-paws-project/animals.json");
            jsonArray = (JSONArray) parser.parse(reader);

            if (jsonArray!=null)
            {
              for (int i = 0; i < jsonArray.size(); i++)
              {
                animals.add((Animal) jsonArray.get(i));
              }
            }
          }
          catch (FileNotFoundException | ParseException e)
          {
            e.printStackTrace();
          }
    return animals;
  }
}
