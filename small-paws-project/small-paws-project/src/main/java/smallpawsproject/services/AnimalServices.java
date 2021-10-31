package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;

import java.util.ArrayList;

@Service
public interface AnimalServices
{
  void AddAnimal(Animal animal);
  ArrayList<Animal> GetAnimals();
}
