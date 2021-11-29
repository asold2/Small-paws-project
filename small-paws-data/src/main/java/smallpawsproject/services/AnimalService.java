package smallpawsproject.services;

import smallpawsproject.model.Animal;

import java.util.List;

public interface AnimalService {
    void addAnimal(Animal animal);
    List<Animal> getAnimals();

    Animal updateAnimal(Animal animal);
}
