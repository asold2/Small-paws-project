package smallpawsproject.services;

import net.minidev.json.JSONArray;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;
import smallpawsproject.rmi.ClientRMI;

import java.util.List;

@Service
public interface AnimalServices
{
  JSONArray GetAnimals();
  void AddAnimal(Animal animal);
  void updateAnimal(Animal animal);
  void setClient(ClientRMI clientRMI); // method used to inject the client into the service for testing

}
