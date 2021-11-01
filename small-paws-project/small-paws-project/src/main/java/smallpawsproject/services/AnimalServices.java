package smallpawsproject.services;

import net.minidev.json.JSONArray;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;

import java.util.ArrayList;

@Service
public interface AnimalServices
{
  void AddAnimal(Animal animal);
  JSONArray GetAnimals();
}
