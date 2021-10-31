package smallpawsproject.services;

import org.springframework.stereotype.Service;
import smallpawsproject.model.Animal;

@Service
public interface AnimalServices
{
  void AddAnimal(Animal animal);
}
